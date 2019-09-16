package it.com.rapid7.ias.bamboo;

import com.rapid7.ias.bamboo.impl.InsightAppSecHelper;
import com.rapid7.ias.client.model.ResourceVulnerability;

import java.util.List;

public class InsightAppSecHelperTest {
    private static final String REGION = "US";
    private static final String API_KEY = "";
    private static final String SCAN_ID = "";

    public static void main(String[] args) {
        InsightAppSecHelper ias = new InsightAppSecHelper(REGION, API_KEY, null);

        List<ResourceVulnerability> findings = ias.getFindings(SCAN_ID, "");

        System.out.println("Number of findings returned: " + findings.size());

        ias.generateFindingsReport(findings, "insightappsec-scan-findings.json");
        ias.generateScanMetrics(findings, "insightappsec-scan-metrics.json");
    }
}
