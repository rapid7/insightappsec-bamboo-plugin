package com.rapid7.ias.bamboo.impl;

import com.atlassian.bamboo.collections.ActionParametersMap;
import com.atlassian.bamboo.credentials.CredentialsData;
import com.atlassian.bamboo.credentials.CredentialsAccessor;
import com.atlassian.bamboo.task.AbstractTaskConfigurator;
import com.atlassian.bamboo.task.TaskDefinition;
import com.atlassian.bamboo.utils.error.ErrorCollection;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.bamboo.utils.i18n.I18nBean;
import com.atlassian.bamboo.utils.i18n.I18nBeanFactory;
import static com.atlassian.bamboo.credentials.UsernamePasswordCredentialType.CFG_PASSWORD;

import com.rapid7.ias.client.ApiClient;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.rapid7.ias.bamboo.util.UtilityLogger;
import com.rapid7.ias.client.model.ResourceApp;
import com.rapid7.ias.client.model.ResourceScanConfig;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;

import java.util.Hashtable;
import java.util.Map;

@Scanned
public class InsightAppSecScanTaskConfigurator extends AbstractTaskConfigurator implements IasConstants {

    @ComponentImport CredentialsAccessor credentialsAccessor;
    private I18nBean i18nBean;

    /**
     *
     * @param i18nBeanFactory
     * @param credentialsAccessor
     */
    public InsightAppSecScanTaskConfigurator(
            @ComponentImport I18nBeanFactory i18nBeanFactory,
            @ComponentImport CredentialsAccessor credentialsAccessor) {
        this.credentialsAccessor = credentialsAccessor;
        this.i18nBean = i18nBeanFactory.getI18nBean();
    }

    /**
     *
     * @return
     */
    private Map<Long, String> getCredentials() {
        Map<Long, String> credentials = new Hashtable<>();
        for (CredentialsData data : credentialsAccessor.getAllCredentials()) {
            if (data.getName().startsWith("Rapid7")) {
                credentials.put(data.getId(), data.getName());
            }
        }
        return credentials;
    }

    /**
     *
     * generateTaskConfig method is needed to save the configuration
     */
    public Map<String,String> generateTaskConfigMap(@NotNull ActionParametersMap params,
                                                    @Nullable TaskDefinition previousTaskDefinition){

        Map<String,String> configMap = super.generateTaskConfigMap(params,previousTaskDefinition);
        configMap.put(SELECTED_REGION,params.getString(SELECTED_REGION));
        configMap.put(OTHER_REGION,params.getString(OTHER_REGION));
        configMap.put(SELECTED_CREDENTIAL, params.getString(SELECTED_CREDENTIAL));
        configMap.put(APP_NAME,params.getString(APP_NAME));
        configMap.put(SCAN_CONFIG_NAME,params.getString(SCAN_CONFIG_NAME));
        configMap.put(PROXY_HOST,params.getString(PROXY_HOST));
        configMap.put(PROXY_PORT,params.getString(PROXY_PORT));
        configMap.put(SELECTED_ADVANCE_ON,params.getString(SELECTED_ADVANCE_ON));  // Plugin action after scan triggered
        if (params.getString(SELECTED_ADVANCE_ON).equals("STARTED")) {
            configMap.put(CHECK_INTERVAL,params.getString(CHECK_INTERVAL_STARTED));
            configMap.put(MAX_PENDING,params.getString(MAX_PENDING_STARTED));
        } else {  // Set interval and pending for all other statuses
            configMap.put(CHECK_INTERVAL,params.getString(CHECK_INTERVAL));
            configMap.put(MAX_PENDING,params.getString(MAX_PENDING));
        }
        configMap.put(MAX_EXECUTION,params.getString(MAX_EXECUTION));
        configMap.put(FINDINGS_REPORT_GENERATION,params.getString(FINDINGS_REPORT_GENERATION));
        configMap.put(VULN_QUERY_ENFORCEMENT,params.getString(VULN_QUERY_ENFORCEMENT));
        configMap.put(DEBUGGING,params.getString(DEBUGGING));
        // Reporting related configs
        configMap.put(VULN_QUERY,params.getString(VULN_QUERY));
        return configMap;
    }

    /**
     *
     * @param params
     * @param errorCollection
     */
    @Override
    public void validate(@NotNull ActionParametersMap params,
                         @NotNull ErrorCollection errorCollection) {
        Logger log = LogManager.getLogger(InsightAppSecScanTaskConfigurator.class);
        UtilityLogger logger = new UtilityLogger(log);

        super.validate(params, errorCollection);
        String regionValue = params.getString(SELECTED_REGION);
        if (regionValue.equals("OTHER")) {
            regionValue = params.getString(OTHER_REGION);
        }

        String credentialId = params.getString(SELECTED_CREDENTIAL);
        String appNameValue = params.getString(APP_NAME);
        String scanConfigNameValue = params.getString(SCAN_CONFIG_NAME);
        String proxyHostValue = params.getString(PROXY_HOST);
        String proxyPortValue = params.getString(PROXY_PORT);
        String debugging = params.getString(DEBUGGING);
        Boolean proxyEnabled = null;

        if (credentialId == null) {
            errorCollection.addError(SELECTED_CREDENTIAL, i18nBean.getText("error.apiKey"));
        } else if (credentialsAccessor.getCredentials(Long.parseLong(credentialId)) == null) {
            errorCollection.addError(SELECTED_CREDENTIAL, i18nBean.getText("error.apiKeyNotExist"));
        } else if (StringUtils.isEmpty(regionValue)) {
            errorCollection.addError(SELECTED_REGION, i18nBean.getText("error.region"));
        } else if (StringUtils.isEmpty(appNameValue)) {
            errorCollection.addError(APP_NAME, i18nBean.getText("error.appName"));
        } else if (StringUtils.isEmpty(scanConfigNameValue)) {
            errorCollection.addError(SCAN_CONFIG_NAME, i18nBean.getText("error.scanConfigName"));
        } else if (StringUtils.isNotEmpty(proxyPortValue) && !StringUtils.isNumeric(proxyPortValue)) {
            errorCollection.addError(PROXY_PORT, i18nBean.getText("error.proxyPort"));
        }else{
            CredentialsData cred = credentialsAccessor.getCredentials(Long.parseLong(credentialId));

            if (proxyHostValue.isEmpty()) {
                proxyHostValue = null;
                proxyPortValue = null;
            }
            else {
                proxyEnabled = checkProxyConnection(proxyHostValue, proxyPortValue);
            }
            InsightAppSecHelper iasHelper = new InsightAppSecHelper(regionValue, cred.getConfiguration().get(CFG_PASSWORD), logger, proxyHostValue, proxyPortValue, debugging);

            try {
                ResourceApp application = iasHelper.getApplication(appNameValue);
                if (application == null && proxyEnabled == null || application == null && proxyEnabled) {
                    errorCollection.addError(APP_NAME, i18nBean.getText("error.invalidAppConfig"));
                } else if (application == null && !proxyEnabled) {
                    errorCollection.addError(PROXY_HOST, i18nBean.getText("error.proxyConnection"));
                    errorCollection.addError(PROXY_PORT, i18nBean.getText("error.proxyConnection"));
                } else {
                    ResourceScanConfig scanConfig = iasHelper.getScanConfiguration(scanConfigNameValue, application.getId());
                    if (scanConfig == null) {
                        errorCollection.addError(SCAN_CONFIG_NAME, i18nBean.getText("error.invalidScanConfig"));
                    }
                }
            } catch (InsightAppSecException iase) {
                errorCollection.addError(SELECTED_CREDENTIAL, i18nBean.getText("error.authFailure"));
            }
        }
    }

    private boolean checkProxyConnection(String proxyHostValue, String proxyPortValue) {
        ApiClient apiClient = new ApiClient(proxyHostValue, proxyPortValue);
        apiClient.setBasePath("https://www.rapid7.com");

        return apiClient.checkProxyConnection();
    }

    /**
     *
     * @param context
     */
    @Override
    public void populateContextForCreate(@NotNull final Map<String,Object> context){
        super.populateContextForCreate(context);
        Map<Long, String> credentials = getCredentials();
        if (credentials.size() > 0) {
            context.put(CREDENTIAL_LIST, getCredentials());
        }
        context.put(REGION_LIST, REGION_OPTIONS_LIST);
        context.put(CHECK_INTERVAL_STARTED, i18nBean.getText("default.checkInterval"));
        context.put(MAX_PENDING_STARTED, i18nBean.getText("default.maxPending"));
        context.put(CHECK_INTERVAL, i18nBean.getText("default.checkInterval"));
        context.put(MAX_PENDING, i18nBean.getText("default.maxPending"));
        context.put(MAX_EXECUTION, i18nBean.getText("default.maxExecution"));
        context.put(ADVANCE_ON, ADVANCE_ON_OPTIONS_LIST);
        context.put(FINDINGS_REPORT_GENERATION, i18nBean.getText("default.findingsReportGeneration"));
        context.put(DEBUGGING, i18nBean.getText("default.debugging"));
    }

    /**
     *
     * @param context
     * @param taskDefinition
     */
    @Override
    public void populateContextForEdit(@NotNull final Map<String,Object> context,TaskDefinition taskDefinition){
        super.populateContextForEdit(context, taskDefinition);
        context.put(CREDENTIAL_LIST, getCredentials());
        context.put(REGION_LIST, REGION_OPTIONS_LIST);
        context.put(ADVANCE_ON, ADVANCE_ON_OPTIONS_LIST);

        Map<String, String> config = taskDefinition.getConfiguration();
        context.put(SELECTED_CREDENTIAL, config.get(SELECTED_CREDENTIAL));
        context.put(SELECTED_REGION, config.get(SELECTED_REGION));
        context.put(OTHER_REGION, config.get(OTHER_REGION));
        context.put(SELECTED_ADVANCE_ON, config.get(SELECTED_ADVANCE_ON));
        context.put(APP_NAME, config.get(APP_NAME));
        context.put(SCAN_CONFIG_NAME, config.get(SCAN_CONFIG_NAME));
        context.put(PROXY_HOST, config.get(PROXY_HOST));
        context.put(PROXY_PORT, config.get(PROXY_PORT));
        context.put(CHECK_INTERVAL_STARTED, config.get(CHECK_INTERVAL));
        context.put(MAX_PENDING_STARTED, config.get(MAX_PENDING));
        context.put(CHECK_INTERVAL, config.get(CHECK_INTERVAL));
        context.put(MAX_PENDING, config.get(MAX_PENDING));
        context.put(MAX_EXECUTION, config.get(MAX_EXECUTION));
        context.put(FINDINGS_REPORT_GENERATION, config.get(FINDINGS_REPORT_GENERATION));
        context.put(VULN_QUERY_ENFORCEMENT, config.get(VULN_QUERY_ENFORCEMENT));
        context.put(VULN_QUERY, config.get(VULN_QUERY));
        context.put(DEBUGGING, config.get(DEBUGGING));
    }
}
