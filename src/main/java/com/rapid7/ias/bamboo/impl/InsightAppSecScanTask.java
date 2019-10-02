package com.rapid7.ias.bamboo.impl;

import com.atlassian.bamboo.configuration.ConfigurationMap;
import com.atlassian.bamboo.credentials.CredentialsData;
import com.atlassian.bamboo.credentials.CredentialsManager;
import com.atlassian.bamboo.plan.artifact.ArtifactDefinitionContext;
import com.atlassian.bamboo.plan.artifact.ArtifactDefinitionContextImpl;
import com.atlassian.bamboo.plan.artifact.ArtifactPublishingResult;
import com.atlassian.bamboo.task.*;
import com.atlassian.bamboo.util.Narrow;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.bamboo.build.artifact.ArtifactManager;

import com.rapid7.ias.bamboo.util.UtilityLogger;
import com.rapid7.ias.client.model.ResourceApp;
import com.rapid7.ias.client.model.ResourceScanConfig;
import com.rapid7.ias.client.model.ResourceVulnerability;
import org.jetbrains.annotations.NotNull;

import org.apache.log4j.Logger;

import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Scanned
public class InsightAppSecScanTask implements CommonTaskType, IasConstants {
    private CredentialsManager credentialsManager;
    private UtilityLogger logger;
    private static final Logger log = Logger.getLogger(InsightAppSecScanTask.class);

    private String region;
    private String appName;
    private String scanConfigName;

    @ComponentImport ArtifactManager artifactManager;

    public InsightAppSecScanTask(@ComponentImport ArtifactManager artifactManager,
                                 @ComponentImport CredentialsManager credentialsManager) {
        this.credentialsManager = credentialsManager;
        this.artifactManager = artifactManager;
    }

    @NotNull
    @Override
    public TaskResult execute(CommonTaskContext taskContext) throws TaskException {
        TaskResultBuilder result = TaskResultBuilder.newBuilder(taskContext);
        logger = new UtilityLogger(log, taskContext.getBuildLogger());

        ConfigurationMap configMap = taskContext.getConfigurationMap();
        region = configMap.get(SELECTED_REGION);
        appName = configMap.get(APP_NAME);
        scanConfigName = configMap.get(SCAN_CONFIG_NAME);

        String credentialId = configMap.get(SELECTED_CREDENTIAL);
        CredentialsData cred = credentialsManager.getCredentials(Long.parseLong(credentialId));

        logger.info("Rapid7 InsightAppSec Platform Region: " + region);
        logger.info("App: " + appName);
        logger.info("Scan Config: "+ scanConfigName);

        // Initialize helper with necessary clients
        InsightAppSecHelper iasHelper = new InsightAppSecHelper(region, cred.getConfiguration().get("password"), logger);

        try {
            ResourceApp app = iasHelper.getApplication(appName);

            if (app == null) {
                return result.failed().build();
            } else {
                logger.info("Retrieved app ID: " + app.getId().toString());
            }

            ResourceScanConfig scanConfig = iasHelper.getScanConfiguration(scanConfigName, app.getId());
            if (scanConfig == null) {
                return result.failed().build();
            } else {
                logger.info("Retrieved scan config ID: " + scanConfig.getId().toString());
            }

            // Submit Scan
            String scanId;
            try {
                scanId = iasHelper.runScan(scanConfig.getId());
                if (scanId == null) {
                    logger.error("Scan submitted for " + appName + " app; failing due to no scan ID being returned");
                    return result.failed().build();
                } else {
                    logger.info("Scan submitted for " + appName + " app; scan ID: " + scanId);
                }

                // Return success if scan submitted and Advance On set to Submitted
                if (configMap.get(SELECTED_ADVANCE_ON).equals("SUBMITTED")) {
                    logger.info("Scan submitted, marking task as success.  Advance On: " + configMap.get(SELECTED_ADVANCE_ON));
                    return result.success().build();
                }
            } catch (Exception e) {
                logger.error("Failed to initiate scan: " + e.toString());
                Thread.currentThread().interrupt();
                return result.failedWithError().build();
            }

            // Track Scan Status
            Integer elapsedTime = 0;
            Map<String, String> scanStatus = new HashMap<String, String>() {{
                put("Status", "Unknown");
                put("Reason", "Unknown");
            }};
            while (!Arrays.asList("FAILED", "COMPLETE").contains(scanStatus.get("Status"))) {
                scanStatus = iasHelper.scanStatus(scanId);
                logger.info("Scan status for " + scanId + ": " + scanStatus.get("Status"));

                // Fail task early if scan status is Failed
                if (scanStatus.get("Status").startsWith("FAILED")) {
                    logger.info("Scan failed (" + scanStatus.get("Reason") + "), failing task for scan ID: " + scanId);
                    Thread.currentThread().interrupt();
                    return result.failed().build();
                }

                // Return success if scan status is Running and Advance On set to Started
                if (configMap.get(SELECTED_ADVANCE_ON).equals("STARTED") && scanStatus.get("Status").equals("RUNNING")) {
                    logger.info("Scan running, marking task as success.  Advance On: " + configMap.get(SELECTED_ADVANCE_ON));
                    return result.success().build();
                }

                // Stop early if max pending reached
                if (scanStatus.get("Status").equals("PENDING") &&
                        elapsedTime > Integer.parseInt(configMap.get(MAX_PENDING))) {
                    logger.info("Scan reached max pending timeout, failing task for scan ID: " + scanId);
                    Thread.currentThread().interrupt();
                    return result.failed().build();
                }

                // Stop early if max execution reached
                if (!scanStatus.get("Status").equals("COMPLETE") &&
                        elapsedTime > Integer.parseInt(configMap.get(MAX_EXECUTION))) {
                    logger.info("Scan reached max execution timeout, failing task for scan ID: " + scanId);
                    Thread.currentThread().interrupt();
                    return result.failed().build();
                }

                // Sleep before attempting again
                logger.debug("Sleeping for " + configMap.get(CHECK_INTERVAL) + " minutes while scanning");
                try {
                    TimeUnit.MINUTES.sleep(Integer.parseInt(configMap.get(CHECK_INTERVAL)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Increment elapsed time
                elapsedTime += Integer.parseInt(configMap.get(CHECK_INTERVAL));
            }

            if (scanStatus.get("Status").equals("COMPLETE")) {
                logger.info("Successful InsightAppSec scan for " + appName + " application; Scan Config " +
                        scanConfigName + ". Scan ID: " + scanId);

                // Check if report generation is expected
                if (Boolean.parseBoolean(configMap.get(FINDINGS_REPORT_GENERATION))) {
                    // Get all findings from scan for report creation
                    List<ResourceVulnerability> allFindings = iasHelper.getFindings(scanId, "");

                    // Generate metric and vulnerability reports
                    iasHelper.generateScanMetrics(allFindings,
                            taskContext.getWorkingDirectory() + "/insightappsec-scan-metrics.json");
                    iasHelper.generateFindingsReport(allFindings,
                            taskContext.getWorkingDirectory() + "/insightappsec-scan-findings.json");

                    // Publish artifacts
                    final TaskContext buildTaskContext = Narrow.downTo(taskContext, TaskContext.class);
                    if (buildTaskContext != null) {
                        publishArtifacts(buildTaskContext, "reports", taskContext.getWorkingDirectory());
                    }
                }

                // Check if vulnerabilities for vuln query
                if (Boolean.parseBoolean(configMap.get(VULN_QUERY_ENFORCEMENT))) {
                    List<ResourceVulnerability> queryFindings = iasHelper.getFindings(scanId, configMap.get(VULN_QUERY));

                    if (queryFindings.size() > 0) {
                        logger.info("Search for scan ID " + scanId + " and query " +
                                configMap.get(VULN_QUERY) + " returned " + queryFindings.size() +
                                " findings; failing build");
                        return result.failed().build();
                    }
                }

                return result.success().build();
            } else {
                logger.info("InsightAppSec scan failed for Scan ID " + scanId + ": " + scanStatus.get("Status"));
                return result.failed().build();
            }
        } catch (InsightAppSecException iase) {
            logger.error("Invalid API key during scan initiation: " + iase.toString());
            Thread.currentThread().interrupt();
            return result.failedWithError().build();
        }
    }

    public String getName() {
        return "Rapid7 InsightAppSec";
    }

    private void publishArtifacts(TaskContext taskContext, String name, File directory) {

        logger.info("Publishing artifact(s) for scan");

        ArtifactDefinitionContext artifact = new ArtifactDefinitionContextImpl(name, true, null);
        artifact.setCopyPattern("insightappsec-scan-*.json");

        ArtifactPublishingResult result = artifactManager.publish(
                taskContext.getBuildLogger(),
                taskContext.getBuildContext().getPlanResultKey(),
                directory,
                artifact,
                new Hashtable<>(),
                1);

        taskContext.getBuildContext().getArtifactContext().addPublishingResult(result);
    }
}