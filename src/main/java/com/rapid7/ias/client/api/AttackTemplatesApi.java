/*
 * InsightAppSec API
 * <p>Welcome to the reference documentation for the public APIs available for InsightAppSec.</p><p>Here are a few resources to help you learn how to start using our APIs:</p><ul><li><a href='https://insight.help.rapid7.com/docs/api-overview'>Learn about basic concepts and capabilities</a></li><li><a href='https://insight.help.rapid7.com/docs/managing-platform-api-keys'>Get an Insight platform API key to set up authentication</a></li><li><a href='https://insight.help.rapid7.com/docs/product-apis'>See available Insight product APIs</a></li><li><a href='https://insightappsec.help.rapid7.com/docs'>Learn more about InsightAppSec</a></li></ul><p>After you've got the basics down, you can use this API guide to find examples of requests and responses.</p></p><h2>Permissions</h2><p>This API uses the platform role of a user's API key to inherit a set of permissions.</p><p><b>Notes</b>:<li>Platform Admins do not require explicit access to an App in order to see it, or anything associated with it</li><li>Organization keys will inherit Product Admin permissions.</li></p><table><thead><tr><th>Role</th><th>Permissions</th></tr></thead><tbody><tr><td>Product Admin</td><td><li><code>Manage Apps</code></li><li><code>Read Attack Templates</code></li><li><code>Manage Targets</code><li><code>Manage Scan Configs</code><li><code>Manage Scans</code></li><li><code>Manage Vulnerabilities</code></li></td></tr><tr><td>Read Write</td><td><li><code>Manage Apps</code></li><li><code>Read Attack Templates</code></li><li><code>Read Targets</code><li><code>Manage Scan Configs</code><li><code>Manage Scans</code></li><li><code>Manage Vulnerabilities</code></li></td></tr><tr><td>Read Only</td><td><li><code>Read Apps</code></li><li><code>Read Attack Templates</code></li><li><code>Read Targets</code><li><code>Read Scan Configs</code><li><code>Read Scans</code></li><li><code>Read Vulnerabilities</code></li></td></tr></tbody><table><a href=\"#pagination\"><h2>Pagination Parameters</h2></a><p>The following query parameters can be used to control the order, offset and size of the returned data</p><table><thead><tr><th>Parameter</th><th>Description</th><th>Example</th></tr></thead><tbody><tr><td><code>index</code></td><td><p>The 0-based index of the page of data desired (default: 0)<p></td><td><code>?index=2</code></td></tr><tr><td><code>size</code></td><td><p>The size of the page of data desired (min: 1, max: 1000, default: 50)<p></td><td><code>?size=10</code></td></tr><tr><td><code>sort</code></td><td><p>The sort terms and (optional) directions for the desired ordering of data (default: specific to each resource)<p></td><td><code>?sort=scan.submit_time,DESC,scan.status</code></td></tr></tbody></table><h2>Date, Time and Timezone Support</h2><p>The value of date and date-time type properties are fixed to the common ISO-8601 format. Currently, all date or date-time data in response bodies are expressed in UTC, and it is also expected that data provided in request bodies are also expressed in UTC.</p><table><thead><tr><th>Value</th><th>Format</th><th>Notes</th></tr></thead><tbody><tr><td>Date</td><td>YYYY-MM-DD</td><td>Defaults to 12 am UTC (if used for a date &amp; time)</td></tr><tr><td>Date &amp; time only</td><td>YYYY-MM-DD'T'hh:mm:ss[.nnn]</td><td>Defaults to UTC</td></tr><tr><td>Date &amp; time in UTC</td><td>YYYY-MM-DD'T'hh:mm:ss[.nnn]Z</td><td></td></tr></tbody></table><h2>Searching and Filtering</h2><p>One characteristic of a powerful API is support for complex search and filtering of resources using a flexible query language. It is entirely possible to provide basic search and filtering functionality on standard GET resource collection operations using HTTP query parameters, but this becomes restrictive when attempting to encode boolean logic. Other solutions are also possible but come with equally restrictive compromises. The most effective way to facilitate this complex search and filtering is to provide it as a top-level API with a robust Domain-Specific Language (DSL).</p><p>For simple data retrieval that supports paging and sorting functionality, use the traditional resource-specific GET collection operations; for more complex data retrieval that supports a user-crafted query DSL, use the global Search operation.</p><h2>Errors</h2><p>Any response from the API with an HTTP Status Code in the 4xx or 5xx range indicates an error. If in the 4xx range it indicates a client-side error, where in the 5xx range it indicates a server-side error. All responses indicating an error should be JSON formatted, and contain both a \"status\" and a \"message\" property indicating the cause of the error, a \"r7-correlation-id\" header should also always be returned in the format of a UUID, which uniquely identifies the HTTP exchange that took place and caused the error (this header is present on <i>every</i> request). On some occasions, an \"error_code\" property will be included in the response body, which can provide the support team further context into the cause of the error in cases where the message is not explicitly clear.</p><p>When contacting support about error responses received from the API, please attempt to include, at minimum:<ul><li>A description of the requests intent</li><li>The request URL</li><li>The request body</li><li>The error response body</li><li>The error response \"r7-correlation-id\" header</li></ul></p><h3>Validation Errors</h3><p>Specifically, when a response with an HTTP Status Code of 422 is returned, this indicates a validation error, almost always received in a response from a non-idempotent request (normally PUT, POST or DELETE). In this case, there should be an additional \"errors\" property included in the JSON response body that should describe the cause of the validation exception; this will almost always require a modification to the request body to resolve. </p>
 *
 * OpenAPI spec version: v1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.rapid7.ias.client.api;

import com.google.gson.reflect.TypeToken;
import com.rapid7.ias.client.*;
import com.rapid7.ias.client.model.PageAttackTemplate;
import com.rapid7.ias.client.model.ResourceAttackTemplate;

import java.io.IOException;
import java.lang.reflect.Type;

public class AttackTemplatesApi {
    private ApiClient apiClient;

    public AttackTemplatesApi() {
        this(Configuration.getDefaultApiClient());
    }

    public AttackTemplatesApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Build call for getAttackTemplate
     * @param attackTemplateId attack-template-id (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getAttackTemplateCall(java.util.UUID attackTemplateId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/attack-templates/{attack-template-id}"
            .replaceAll("\\{" + "attack-template-id" + "\\}", apiClient.escapeString(attackTemplateId.toString()));

        java.util.List<Pair> localVarQueryParams = new java.util.ArrayList<Pair>();
        java.util.List<Pair> localVarCollectionQueryParams = new java.util.ArrayList<Pair>();

        java.util.Map<String, String> localVarHeaderParams = new java.util.HashMap<String, String>();

        java.util.Map<String, Object> localVarFormParams = new java.util.HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json;charset=UTF-8"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            "application/json;charset=UTF-8"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call getAttackTemplateValidateBeforeCall(java.util.UUID attackTemplateId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'attackTemplateId' is set
        if (attackTemplateId == null) {
            throw new ApiException("Missing the required parameter 'attackTemplateId' when calling getAttackTemplate(Async)");
        }
        

        com.squareup.okhttp.Call call = getAttackTemplateCall(attackTemplateId, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Get Attack Template
     * Get an attack template
     * @param attackTemplateId attack-template-id (required)
     * @return ResourceAttackTemplate
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ResourceAttackTemplate getAttackTemplate(java.util.UUID attackTemplateId) throws ApiException {
        ApiResponse<ResourceAttackTemplate> resp = getAttackTemplateWithHttpInfo(attackTemplateId);
        return resp.getData();
    }

    /**
     * Get Attack Template
     * Get an attack template
     * @param attackTemplateId attack-template-id (required)
     * @return ApiResponse&lt;ResourceAttackTemplate&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<ResourceAttackTemplate> getAttackTemplateWithHttpInfo(java.util.UUID attackTemplateId) throws ApiException {
        com.squareup.okhttp.Call call = getAttackTemplateValidateBeforeCall(attackTemplateId, null, null);
        Type localVarReturnType = new TypeToken<ResourceAttackTemplate>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Get Attack Template (asynchronously)
     * Get an attack template
     * @param attackTemplateId attack-template-id (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getAttackTemplateAsync(java.util.UUID attackTemplateId, final ApiCallback<ResourceAttackTemplate> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = getAttackTemplateValidateBeforeCall(attackTemplateId, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<ResourceAttackTemplate>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for getAttackTemplates
     * @param index  (optional)
     * @param size  (optional)
     * @param sort  (optional)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getAttackTemplatesCall(Integer index, Integer size, String sort, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/attack-templates";

        java.util.List<Pair> localVarQueryParams = new java.util.ArrayList<Pair>();
        java.util.List<Pair> localVarCollectionQueryParams = new java.util.ArrayList<Pair>();
        if (index != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("index", index));
        if (size != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("size", size));
        if (sort != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("sort", sort));

        java.util.Map<String, String> localVarHeaderParams = new java.util.HashMap<String, String>();

        java.util.Map<String, Object> localVarFormParams = new java.util.HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json;charset=UTF-8"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            "application/json;charset=UTF-8"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call getAttackTemplatesValidateBeforeCall(Integer index, Integer size, String sort, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        

        com.squareup.okhttp.Call call = getAttackTemplatesCall(index, size, sort, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Get Attack Templates
     * Get a page of attack templates, based on supplied &lt;a href&#x3D;\&quot;#pagination\&quot;&gt;pagination parameters&lt;/a&gt;. &lt;p&gt;The default sort for Attack Templates is \&quot;name\&quot; (ascending); for a full list of sortable properties, refer to the Search Catalog detailed in the Search API.&lt;/p&gt;
     * @param index  (optional)
     * @param size  (optional)
     * @param sort  (optional)
     * @return PageAttackTemplate
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public PageAttackTemplate getAttackTemplates(Integer index, Integer size, String sort) throws ApiException {
        ApiResponse<PageAttackTemplate> resp = getAttackTemplatesWithHttpInfo(index, size, sort);
        return resp.getData();
    }

    /**
     * Get Attack Templates
     * Get a page of attack templates, based on supplied &lt;a href&#x3D;\&quot;#pagination\&quot;&gt;pagination parameters&lt;/a&gt;. &lt;p&gt;The default sort for Attack Templates is \&quot;name\&quot; (ascending); for a full list of sortable properties, refer to the Search Catalog detailed in the Search API.&lt;/p&gt;
     * @param index  (optional)
     * @param size  (optional)
     * @param sort  (optional)
     * @return ApiResponse&lt;PageAttackTemplate&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<PageAttackTemplate> getAttackTemplatesWithHttpInfo(Integer index, Integer size, String sort) throws ApiException {
        com.squareup.okhttp.Call call = getAttackTemplatesValidateBeforeCall(index, size, sort, null, null);
        Type localVarReturnType = new TypeToken<PageAttackTemplate>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Get Attack Templates (asynchronously)
     * Get a page of attack templates, based on supplied &lt;a href&#x3D;\&quot;#pagination\&quot;&gt;pagination parameters&lt;/a&gt;. &lt;p&gt;The default sort for Attack Templates is \&quot;name\&quot; (ascending); for a full list of sortable properties, refer to the Search Catalog detailed in the Search API.&lt;/p&gt;
     * @param index  (optional)
     * @param size  (optional)
     * @param sort  (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getAttackTemplatesAsync(Integer index, Integer size, String sort, final ApiCallback<PageAttackTemplate> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = getAttackTemplatesValidateBeforeCall(index, size, sort, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<PageAttackTemplate>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
