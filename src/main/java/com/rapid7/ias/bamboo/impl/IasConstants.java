package com.rapid7.ias.bamboo.impl;

import java.util.Arrays;
import java.util.List;

public interface IasConstants {
    String APP_NAME                   = "appName";
    String SCAN_CONFIG_NAME           = "scanConfigName";
    String SELECTED_CREDENTIAL        = "selectedCred";
    String CREDENTIAL_LIST            = "credentialList";
    String REGION_LIST                = "regionList";
    String SELECTED_REGION            = "region";
    String OTHER_REGION               = "otherRegion";
    String CHECK_INTERVAL_STARTED     = "checkIntervalStarted";
    String MAX_PENDING_STARTED        = "maxPendingStarted";
    String CHECK_INTERVAL             = "checkInterval";
    String MAX_PENDING                = "maxPending";
    String MAX_EXECUTION              = "maxExecution";
    String SELECTED_ADVANCE_ON        = "advanceOn";
    String ADVANCE_ON                 = "advanceOnOptions";
    String FINDINGS_REPORT_GENERATION = "findingsReportGeneration";
    String VULN_QUERY_ENFORCEMENT     = "vulnQueryEnforcement";
    String VULN_QUERY                 = "vulnQuery";
    String PROXY_HOST                 = "proxyHost";
    String PROXY_PORT                 = "proxyPort";
    String DEBUGGING                  = "debugging";


    List<String> REGION_OPTIONS_LIST = Arrays.asList("US","US2","US3","EU","AU","CA","AP","OTHER");

    List<String> ADVANCE_ON_OPTIONS_LIST = Arrays.asList(
            "SUBMITTED",
            "STARTED",
            "COMPLETED");
}
