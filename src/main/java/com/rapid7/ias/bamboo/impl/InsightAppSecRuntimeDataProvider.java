package com.rapid7.ias.bamboo.impl;

import com.atlassian.bamboo.ResultKey;
import com.atlassian.bamboo.credentials.CredentialsAccessor;
import com.atlassian.bamboo.credentials.CredentialsData;
import com.atlassian.bamboo.security.SecureToken;
import com.atlassian.bamboo.security.SecureTokenService;
import com.atlassian.bamboo.serialization.WhitelistedSerializable;
import com.atlassian.bamboo.task.RuntimeTaskDataProvider;
import com.atlassian.bamboo.task.TaskDefinition;
import com.atlassian.bamboo.task.runtime.RuntimeTaskDefinition;
import com.atlassian.bamboo.v2.build.CommonContext;
import com.atlassian.bamboo.v2.build.agent.messages.AuthenticableMessage;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static com.atlassian.bamboo.credentials.UsernamePasswordCredentialType.CFG_PASSWORD;

public class InsightAppSecRuntimeDataProvider implements RuntimeTaskDataProvider, IasConstants {
    @Inject
    private CredentialsAccessor credentialsAccessor;

    @NotNull
    @Override
    public Map<String, String> populateRuntimeTaskData(@NotNull TaskDefinition taskDefinition,
                                                       @NotNull CommonContext commonContext) {
        final Map<String, String> configuration = taskDefinition.getConfiguration();
        final Map<String, String> result = new HashMap<>();

        // Get Shared Credential for API auth
        propagateSharedCredentialsValues(InsightAppSecScanTaskConfigurator.SELECTED_CREDENTIAL, taskDefinition, configuration, result);

        return result;
    }

    private void propagateSharedCredentialsValues(String sharedCredentialsKey, @NotNull TaskDefinition taskDefinition,
                                                  Map<String, String> configuration, Map<String, String> result) {
        final String credentialsId = configuration.get(sharedCredentialsKey);
        if (StringUtils.isNotBlank(credentialsId)) {
            final CredentialsData credentials = credentialsAccessor.getCredentials(Long.parseLong(credentialsId));
            if (credentials == null) {
                throw new IllegalStateException("Can't find API Key as shared credentials with id " + credentialsId
                        + " for task "
                        + (StringUtils.isEmpty(taskDefinition.getUserDescription()) ? taskDefinition.getPluginKey() : taskDefinition.getUserDescription()));
            }

            // We only need password field for api_key; we don't use the name
            final String password = credentials.getConfiguration().get(CFG_PASSWORD);
            result.put(CFG_PASSWORD, password);
        }
    }

    @Override
    public void processRuntimeTaskData(@NotNull RuntimeTaskDefinition runtimeTaskDefinition, @NotNull CommonContext commonContext)
    {
    }
}