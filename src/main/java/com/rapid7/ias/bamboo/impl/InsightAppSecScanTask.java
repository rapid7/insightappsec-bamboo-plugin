package com.rapid7.ias.bamboo.impl;

import com.atlassian.bamboo.configuration.ConfigurationMap;
import com.atlassian.bamboo.plan.artifact.ArtifactDefinitionContext;
import com.atlassian.bamboo.plan.artifact.ArtifactDefinitionContextImpl;
import com.atlassian.bamboo.plan.artifact.ArtifactPublishingResult;
import com.atlassian.bamboo.security.SecureToken;
import com.atlassian.bamboo.task.CommonTaskContext;
import com.atlassian.bamboo.task.CommonTaskType;
import com.atlassian.bamboo.task.TaskContext;
import com.atlassian.bamboo.task.TaskException;
import com.atlassian.bamboo.task.TaskResult;
import com.atlassian.bamboo.task.TaskResultBuilder;
import com.atlassian.bamboo.util.Narrow;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.bamboo.build.artifact.ArtifactManager;
import static com.atlassian.bamboo.credentials.UsernamePasswordCredentialType.CFG_PASSWORD;

import com.rapid7.ias.bamboo.util.UtilityLogger;
import com.rapid7.ias.client.model.ResourceApp;
import com.rapid7.ias.client.model.ResourceScanConfig;
import com.rapid7.ias.client.model.ResourceVulnerability;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;

import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Scanned
public class InsightAppSecScanTask implements CommonTaskType, IasConstants {
    private UtilityLogger logger;
    private static final Logger log = LogManager.getLogger(InsightAppSecScanTask.class);

    private String region;
    private String appName;
    private String scanConfigName;
    private String proxyHost;
    private String proxyPort;

    private String debugging;

    @ComponentImport ArtifactManager artifactManager;

    public InsightAppSecScanTask(@ComponentImport ArtifactManager artifactManager) {
        this.artifactManager = artifactManager;
    }

    @NotNull
    @Override
    public TaskResult execute(CommonTaskContext taskContext) throws TaskException {
        TaskResultBuilder result = TaskResultBuilder.newBuilder(taskContext);
        logger = new UtilityLogger(log, taskContext.getBuildLogger());

        // Get RuntimeContext for pre-task pull of API Key
        Map<String, String> runtimeContext = taskContext.getRuntimeTaskContext();
        final String apiKey = runtimeContext.get(CFG_PASSWORD);

        ConfigurationMap configMap = taskContext.getConfigurationMap();
        region = configMap.get(SELECTED_REGION);
        appName = configMap.get(APP_NAME);
        scanConfigName = configMap.get(SCAN_CONFIG_NAME);

        proxyHost = configMap.get(PROXY_HOST);
        proxyPort = configMap.get(PROXY_PORT);

        debugging = configMap.get(DEBUGGING);

        if (apiKey == null) {
            logger.error("Previously configured credential is no longer defined within Bamboo; please review task and" +
                    " credential configuration");
            Thread.currentThread().interrupt();
            return result.failedWithError().build();
        }

        logger.info("Rapid7 InsightAppSec Platform Region: " + region);
        logger.info("App: " + appName);
        logger.info("Scan Config: "+ scanConfigName);

        // Initialize helper with necessary clients
        InsightAppSecHelper iasHelper = new InsightAppSecHelper(region, apiKey, logger, proxyHost
        , proxyPort, debugging);

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
                        elapsedTime > Integer.parseInt(configMap.getOrDefault(MAX_PENDING, "30"))) {
                    logger.info("Scan reached max pending timeout, failing task for scan ID: " + scanId);
                    Thread.currentThread().interrupt();
                    return result.failed().build();
                }

                // Stop early if max execution reached
                if (!scanStatus.get("Status").equals("COMPLETE") &&
                        elapsedTime > Integer.parseInt(configMap.getOrDefault(MAX_EXECUTION, "120"))) {
                    logger.info("Scan reached max execution timeout, failing task for scan ID: " + scanId);
                    Thread.currentThread().interrupt();
                    return result.failed().build();
                }

                // Sleep before attempting again
                String checkInterval = configMap.getOrDefault(CHECK_INTERVAL, "5");
                logger.debug("Sleeping for " + checkInterval + " minutes while scanning");
                try {
                    TimeUnit.MINUTES.sleep(Integer.parseInt(checkInterval));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Increment elapsed time
                elapsedTime += Integer.parseInt(checkInterval);
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

        ArtifactDefinitionContext artifact = new ArtifactDefinitionContextImpl(name, true, SecureToken.create());
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
