package com.rapid7.ias.bamboo.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rapid7.ias.client.ApiClient;
import com.rapid7.ias.client.ApiException;
import com.rapid7.ias.client.api.*;
import com.rapid7.ias.client.ApiResponse;
import com.rapid7.ias.bamboo.util.UtilityLogger;
import com.rapid7.ias.client.model.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InsightAppSecHelper {
    private UtilityLogger logger;

    public ScansApi scansApi;
    public ScanConfigsApi scanConfigsApi;
    public AppsApi appsApi;
    public SearchApi searchApi;
    public VulnerabilitiesApi vulnerabilitiesApi;

    public InsightAppSecHelper(String region, String apiKey, UtilityLogger logger) {
        this.logger = logger;

        // Build generic client with API key set
        ApiClient client = new ApiClient();
        client.setBasePath("https://" + region + ".api.insight.rapid7.com/ias/v1");
        client.addDefaultHeader("X-Api-Key", apiKey);

        scansApi = new ScansApi(client);
        scanConfigsApi = new ScanConfigsApi(client);
        appsApi = new AppsApi(client);
        searchApi = new SearchApi(client);
        vulnerabilitiesApi = new VulnerabilitiesApi(client);
    }

    public String runScan(UUID scanConfigId){
        Scan scan = new Scan(); // Scan | scan
        RequiredIdResource scanConfig = new RequiredIdResource();

        // Trigger scan for scan config by uuid
        scanConfig.setId(scanConfigId);
        scan.setScanConfig(scanConfig);
        try {
            ApiResponse response = scansApi.submitScanWithHttpInfo(scan);
            String scanLocation = null;
            String scanId = null;

            Map<String, List<String>> headers = response.getHeaders();
            for (Entry<String, List<String>> pair : headers.entrySet()){
                // Pull Location where Scan ID can be found
                if (pair.getKey().equals("Location")) {
                    scanLocation = pair.getValue().get(0);
                }
            }

            if (scanLocation == null) {
                return null;
            }

            // Pull Scan ID from end of location URL
            Pattern pattern = Pattern.compile(".+/(.+)$");
            Matcher matcher = pattern.matcher(scanLocation);
            if (matcher.find()) {
                scanId = matcher.group(1);
            }

            return scanId;
        } catch (com.rapid7.ias.client.ApiException iase) {
            logger.error("InsightAppSec Scan Exception: " + iase.getResponseBody() + " (" + iase.getCode() + ")");
            return null;
        } catch (Exception e) {
            logger.error("Exception when calling ScansApi#submitScan: " + e.toString());
            return null;
        }
    }

    public Map<String,String> scanStatus(String scanId) {
        Map<String, String> status = new HashMap<String,String>() {{
            put("Status", "Unknown");
            put("Reason", "Unknown");
        }};

        try {
            ResourceScan scan = scansApi.getScan(UUID.fromString(scanId));

            status = new HashMap<String,String>() {{
                put("Status", scan.getStatus().getValue());
                if (scan.getFailureReason() != null) {
                    put("Reason", Objects.toString(scan.getFailureReason().getValue(), ""));
                } else {
                    put("Reason", "");
                }
            }};
        } catch (ApiException iase) {
            logger.error("Failed to retrieve scan by ID " + scanId + ": " + iase.getCode());
        }
        return status;
    }

    public ResourceScanConfig getScanConfiguration(String scanConfigName, UUID appId) {
        Integer index = 0;
        Integer size = 1000;
        Boolean cont = true;

        while(cont) {
            try {
                PageScanConfig scanConfigs = scanConfigsApi.getScanConfigs(index, size, null);

                List<ResourceScanConfig> listScanConfigs = scanConfigs.getData();
                for (ResourceScanConfig scanConfig : listScanConfigs) {
                    if (scanConfig.getName().equals(scanConfigName) && scanConfig.getApp().getId().equals(appId)) {
                        return scanConfig;
                    }
                }

                // Check if more pages exist
                if((index + 1) >= scanConfigs.getMetadata().getTotalPages()) {
                    cont = false;
                }
            } catch (com.rapid7.ias.client.ApiException iase) {
                logger.error("IAS Scan Config Exception: " + iase.getResponseBody() + " (" + iase.getCode() + ")");
                return null;
            } catch (Exception e) {
                logger.error("Exception when calling ScanConfigsApi#getScanConfigs: " + e.toString());
                return null;
            }

            // Increment for next page
            index++;
        }

        return null;
    }

    public ResourceApp getApplication(String appName) {
        Integer index = 0;
        Integer size = 1000;
        Boolean cont = true;

        while(cont) {
            try {
                PageApp apps = appsApi.getApps(index, size, null);

                List<ResourceApp> listApps = apps.getData();
                for (ResourceApp app : listApps) {
                    if (app.getName().equals(appName)) {
                        return app;
                    }
                }

                // Check if more pages exist
                if ((index + 1) >= apps.getMetadata().getTotalPages()) {
                    cont = false;
                }
            } catch (com.rapid7.ias.client.ApiException iase) {
                logger.error("InsightAppSec Apps Client Exception: " + iase.getResponseBody() +
                        " (" + iase.getCode() + ")");
                return null;
            } catch (Exception e) {
                logger.error("Exception when calling AppsApi#getApps");
                return null;
            }

            // Increment for next page
            index++;
        }

        return null;
    }

    public List<ResourceVulnerability> getFindings(String scanId, String queryCondition) {
        Integer totalPages;
        Integer page = 0;
        Integer size = 1000;

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setType(SearchRequest.TypeEnum.VULNERABILITY);

        String query = "vulnerability.scans.id='" + scanId + "'";
        if (!queryCondition.equals("")) {
            query = query + " && " + queryCondition;
        }
        searchRequest.setQuery(query);

        List<ResourceVulnerability> findings = new ArrayList<>();

        do {
            try {
                // Manually call performSearch due to generalized response returned
                com.squareup.okhttp.Call call = searchApi.performSearchCall(searchRequest, page, size, null, null, null);
                ApiResponse<PageVulnerability> result = searchApi.getApiClient().execute(call, PageVulnerability.class);

                PageVulnerability pageVulnerability = result.getData();
                List<ResourceVulnerability> vulns = pageVulnerability.getData();

                for (ResourceVulnerability vuln : vulns) {
                    findings.add(vuln);
                }

                // Assign total pages
                totalPages = pageVulnerability.getMetadata().getTotalPages();
            } catch (com.rapid7.ias.client.ApiException iase) {
                logger.error("InsightAppSec Apps Client Exception: " + iase.getResponseBody() +
                        " (" + iase.getCode() + ")");
                return null;
            } catch (Exception e) {
                logger.error("Exception when calling SearchApi#performSearch");
                return null;
            }

            // Increment for next page
            page++;
        } while (page < totalPages);

        return findings;
    }

    public void generateScanMetrics(List<ResourceVulnerability> vulns, String filename) {
        /**
         * Example Metric Report Output
         * {
         *     "metrics": {
         *         "severity":{
         *             "HIGH":0,
         *             "MEDIUM":10
         *         },
         *         "module":{
         *             "XSS":22,
         *             "SQL Injection":8
         *         }
         *     }
         * }
         */
        Map<String,Integer> severityMetrics = new HashMap<>();
        Map<String,Integer> moduleMetrics = new HashMap<>();

        for (ResourceVulnerability vuln : vulns) {
            String module = "Unknown";
            try {
                module = vulnerabilitiesApi.getModule(vuln.getVariances().get(0).getModule().getId()).getName();
            } catch (ApiException e) { }
            if (moduleMetrics.containsKey(module)) {
                moduleMetrics.put(module, moduleMetrics.get(module) + 1);
            } else {
                moduleMetrics.put(module, 1);
            }

            String severity = vuln.getSeverity().getValue();
            if (severityMetrics.containsKey(severity)) {
                severityMetrics.put(severity, severityMetrics.get(severity) + 1);
            } else {
                severityMetrics.put(severity, 1);
            }
        }

        Map<String,Map<String,Integer>> metrics = new HashMap<>();
        metrics.put("severity", severityMetrics);
        metrics.put("module", moduleMetrics);

        try (Writer writer = new FileWriter(filename)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            gson.toJson(metrics, writer);
        } catch (IOException e) {
            logger.error("Failed to generate findings metric report: " + e.getMessage());
        }
    }

    public void generateFindingsReport(List<ResourceVulnerability> vulns, String filename) {
        ArrayList findingsList = new ArrayList();
        for (ResourceVulnerability vuln : vulns) {
            // Define Root Cause
            Map<String,String> rootCause = new HashMap<>();
            rootCause.put("Url", vuln.getRootCause().getUrl());
            rootCause.put("Parameter", vuln.getRootCause().getParameter());
            rootCause.put("Method", vuln.getRootCause().getMethod());

            // Define Variances with fetch of attack and module from IDs
            Map<String,String> variance = new HashMap<>();
            try {
                variance.put("Attack", vulnerabilitiesApi.getAttack(vuln.getVariances().get(0).getAttack().getId(), vuln.getVariances().get(0).getModule().getId()).getType());
            } catch (ApiException e) {
                variance.put("Attack", vuln.getVariances().get(0).getAttack().getId());
            }
            try {
                variance.put("Module", vulnerabilitiesApi.getModule(vuln.getVariances().get(0).getModule().getId()).getName());
            } catch (ApiException e) {
                variance.put("Module", vuln.getVariances().get(0).getModule().getId().toString());
            }

            Map<String,Object> finding = new HashMap<>();
            finding.put("AppId", vuln.getApp().getId().toString());
            finding.put("VulnId", vuln.getId().toString());
            finding.put("Status", vuln.getStatus().getValue());
            finding.put("Severity", vuln.getSeverity().getValue());
            finding.put("RootCause", rootCause);
            finding.put("Variance", variance);

            findingsList.add(finding);
        }

        try (Writer writer = new FileWriter(filename)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(findingsList, writer);
        } catch (IOException e) {
            logger.error("Failed to generate findings report: " + e.getMessage());
        }
    }
}
