package com.rapid7.ias.bamboo.impl;

import com.atlassian.bamboo.collections.ActionParametersMap;
import com.atlassian.bamboo.credentials.CredentialsData;
import com.atlassian.bamboo.credentials.CredentialsManager;
import com.atlassian.bamboo.task.AbstractTaskConfigurator;
import com.atlassian.bamboo.task.TaskDefinition;
import com.atlassian.bamboo.utils.error.ErrorCollection;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.bamboo.utils.i18n.I18nBean;
import com.atlassian.bamboo.utils.i18n.I18nBeanFactory;
import com.atlassian.util.concurrent.NotNull;
import com.atlassian.util.concurrent.Nullable;

import com.rapid7.ias.bamboo.util.UtilityLogger;
import com.rapid7.ias.client.model.ResourceApp;
import com.rapid7.ias.client.model.ResourceScanConfig;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.Hashtable;
import java.util.Map;

@Scanned
public class InsightAppSecScanTaskConfigurator extends AbstractTaskConfigurator implements IasConstants {

    private CredentialsManager credentialsManager;
    private I18nBean i18nBean;

    /**
     *
     * @param i18nBeanFactory
     * @param credentialsManager
     */
    public InsightAppSecScanTaskConfigurator(
            @ComponentImport I18nBeanFactory i18nBeanFactory,
            @ComponentImport CredentialsManager credentialsManager) {
        this.credentialsManager = credentialsManager;
        this.i18nBean = i18nBeanFactory.getI18nBean();
    }

    /**
     *
     * @return
     */
    private Map<Long, String> getCredentials() {
        Map<Long, String> credentials = new Hashtable<>();
        for (CredentialsData data : credentialsManager.getAllCredentials()) {
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
        // Plugin action after scan triggered
        configMap.put(SELECTED_ADVANCE_ON,params.getString(SELECTED_ADVANCE_ON));
        configMap.put(CHECK_INTERVAL,params.getString(CHECK_INTERVAL));
        configMap.put(MAX_EXECUTION,params.getString(MAX_EXECUTION));
        configMap.put(MAX_PENDING,params.getString(MAX_PENDING));
        configMap.put(FINDINGS_REPORT_GENERATION,params.getString(FINDINGS_REPORT_GENERATION));
        configMap.put(VULN_QUERY_ENFORCEMENT,params.getString(VULN_QUERY_ENFORCEMENT));
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
                         @NotNull ErrorCollection errorCollection){
        Logger log = Logger.getLogger(InsightAppSecScanTaskConfigurator.class);
        UtilityLogger logger = new UtilityLogger(log);

        super.validate(params, errorCollection);
        String regionValue = params.getString(SELECTED_REGION);
        if (regionValue.equals("OTHER")){
            regionValue = params.getString(OTHER_REGION);
        }

        String credentialId = params.getString(SELECTED_CREDENTIAL);
        CredentialsData cred = credentialsManager.getCredentials(Long.parseLong(credentialId));

        String appNameValue = params.getString(APP_NAME);
        String scanConfigNameValue = params.getString(SCAN_CONFIG_NAME);

        if (StringUtils.isEmpty(regionValue)){
            errorCollection.addError(SELECTED_REGION, i18nBean.getText("error.region"));
        }else if(StringUtils.isEmpty(appNameValue)){
            errorCollection.addError(APP_NAME, i18nBean.getText("error.appName"));
        }else if(StringUtils.isEmpty(scanConfigNameValue)){
            errorCollection.addError(SCAN_CONFIG_NAME, i18nBean.getText("error.scanConfigName"));
        }else{
            InsightAppSecHelper iasHelper = new InsightAppSecHelper(regionValue, cred.getConfiguration().get("password"), logger);
            ResourceApp application = iasHelper.getApplication(appNameValue);
            if (application == null) {
                errorCollection.addError(APP_NAME, i18nBean.getText("error.invalidAppConfig"));
            } else {
                ResourceScanConfig scanConfig = iasHelper.getScanConfiguration(scanConfigNameValue, application.getId());
                if (scanConfig == null) {
                    errorCollection.addError(SCAN_CONFIG_NAME, i18nBean.getText("error.invalidScanConfig"));
                }
            }
        }
    }

    /**
     *
     * @param context
     */
    @Override
    public void populateContextForCreate(@NotNull final Map<String,Object> context){
        super.populateContextForCreate(context);
        context.put(CREDENTIAL_LIST, getCredentials());
        context.put(REGION_LIST, REGION_OPTIONS_LIST);
        context.put(CHECK_INTERVAL, i18nBean.getText("default.checkInterval"));
        context.put(MAX_EXECUTION, i18nBean.getText("default.maxExecution"));
        context.put(MAX_PENDING, i18nBean.getText("default.maxPending"));
        context.put(ADVANCE_ON, ADVANCE_ON_OPTIONS_LIST);
        context.put(FINDINGS_REPORT_GENERATION, i18nBean.getText("default.findingsReportGeneration"));
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
        context.put(CHECK_INTERVAL, config.get(CHECK_INTERVAL));
        context.put(MAX_EXECUTION, config.get(MAX_EXECUTION));
        context.put(MAX_PENDING, config.get(MAX_PENDING));
        context.put(FINDINGS_REPORT_GENERATION, config.get(FINDINGS_REPORT_GENERATION));
        context.put(VULN_QUERY_ENFORCEMENT, config.get(VULN_QUERY_ENFORCEMENT));
        context.put(VULN_QUERY, config.get(VULN_QUERY));
    }
}