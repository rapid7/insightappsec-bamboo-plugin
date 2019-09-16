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
import com.rapid7.ias.client.model.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ScansApi {
    private ApiClient apiClient;

    public ScansApi() {
        this(Configuration.getDefaultApiClient());
    }

    public ScansApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Build call for deleteUsingDELETE
     * @param scanId scan-id (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call deleteUsingDELETECall(java.util.UUID scanId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/scans/{scan-id}"
            .replaceAll("\\{" + "scan-id" + "\\}", apiClient.escapeString(scanId.toString()));

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
        return apiClient.buildCall(localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call deleteUsingDELETEValidateBeforeCall(java.util.UUID scanId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'scanId' is set
        if (scanId == null) {
            throw new ApiException("Missing the required parameter 'scanId' when calling deleteUsingDELETE(Async)");
        }
        

        com.squareup.okhttp.Call call = deleteUsingDELETECall(scanId, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Delete Scan
     * Delete a Scan
     * @param scanId scan-id (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void deleteUsingDELETE(java.util.UUID scanId) throws ApiException {
        deleteUsingDELETEWithHttpInfo(scanId);
    }

    /**
     * Delete Scan
     * Delete a Scan
     * @param scanId scan-id (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Void> deleteUsingDELETEWithHttpInfo(java.util.UUID scanId) throws ApiException {
        com.squareup.okhttp.Call call = deleteUsingDELETEValidateBeforeCall(scanId, null, null);
        return apiClient.execute(call);
    }

    /**
     * Delete Scan (asynchronously)
     * Delete a Scan
     * @param scanId scan-id (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call deleteUsingDELETEAsync(java.util.UUID scanId, final ApiCallback<Void> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = deleteUsingDELETEValidateBeforeCall(scanId, progressListener, progressRequestListener);
        apiClient.executeAsync(call, callback);
        return call;
    }
    /**
     * Build call for getScan
     * @param scanId scan-id (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getScanCall(java.util.UUID scanId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/scans/{scan-id}"
            .replaceAll("\\{" + "scan-id" + "\\}", apiClient.escapeString(scanId.toString()));

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
    private com.squareup.okhttp.Call getScanValidateBeforeCall(java.util.UUID scanId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'scanId' is set
        if (scanId == null) {
            throw new ApiException("Missing the required parameter 'scanId' when calling getScan(Async)");
        }
        

        com.squareup.okhttp.Call call = getScanCall(scanId, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Get Scan
     * Get a Scan
     * @param scanId scan-id (required)
     * @return ResourceScan
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ResourceScan getScan(java.util.UUID scanId) throws ApiException {
        ApiResponse<ResourceScan> resp = getScanWithHttpInfo(scanId);
        return resp.getData();
    }

    /**
     * Get Scan
     * Get a Scan
     * @param scanId scan-id (required)
     * @return ApiResponse&lt;ResourceScan&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<ResourceScan> getScanWithHttpInfo(java.util.UUID scanId) throws ApiException {
        com.squareup.okhttp.Call call = getScanValidateBeforeCall(scanId, null, null);
        Type localVarReturnType = new TypeToken<ResourceScan>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Get Scan (asynchronously)
     * Get a Scan
     * @param scanId scan-id (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getScanAsync(java.util.UUID scanId, final ApiCallback<ResourceScan> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = getScanValidateBeforeCall(scanId, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<ResourceScan>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for getScanAction
     * @param scanId scan-id (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getScanActionCall(java.util.UUID scanId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/scans/{scan-id}/action"
            .replaceAll("\\{" + "scan-id" + "\\}", apiClient.escapeString(scanId.toString()));

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
    private com.squareup.okhttp.Call getScanActionValidateBeforeCall(java.util.UUID scanId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'scanId' is set
        if (scanId == null) {
            throw new ApiException("Missing the required parameter 'scanId' when calling getScanAction(Async)");
        }
        

        com.squareup.okhttp.Call call = getScanActionCall(scanId, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Get Scan Action
     * Get any current Scan action
     * @param scanId scan-id (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void getScanAction(java.util.UUID scanId) throws ApiException {
        getScanActionWithHttpInfo(scanId);
    }

    /**
     * Get Scan Action
     * Get any current Scan action
     * @param scanId scan-id (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Void> getScanActionWithHttpInfo(java.util.UUID scanId) throws ApiException {
        com.squareup.okhttp.Call call = getScanActionValidateBeforeCall(scanId, null, null);
        return apiClient.execute(call);
    }

    /**
     * Get Scan Action (asynchronously)
     * Get any current Scan action
     * @param scanId scan-id (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getScanActionAsync(java.util.UUID scanId, final ApiCallback<Void> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = getScanActionValidateBeforeCall(scanId, progressListener, progressRequestListener);
        apiClient.executeAsync(call, callback);
        return call;
    }
    /**
     * Build call for getScanEngineEvents
     * @param scanId scan-id (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getScanEngineEventsCall(java.util.UUID scanId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/scans/{scan-id}/engine-events"
            .replaceAll("\\{" + "scan-id" + "\\}", apiClient.escapeString(scanId.toString()));

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
    private com.squareup.okhttp.Call getScanEngineEventsValidateBeforeCall(java.util.UUID scanId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'scanId' is set
        if (scanId == null) {
            throw new ApiException("Missing the required parameter 'scanId' when calling getScanEngineEvents(Async)");
        }
        

        com.squareup.okhttp.Call call = getScanEngineEventsCall(scanId, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Get Scan Engine Events
     * Get the engine events from a Scan
     * @param scanId scan-id (required)
     * @return java.util.List&lt;LogEvent&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public java.util.List<LogEvent> getScanEngineEvents(java.util.UUID scanId) throws ApiException {
        ApiResponse<java.util.List<LogEvent>> resp = getScanEngineEventsWithHttpInfo(scanId);
        return resp.getData();
    }

    /**
     * Get Scan Engine Events
     * Get the engine events from a Scan
     * @param scanId scan-id (required)
     * @return ApiResponse&lt;java.util.List&lt;LogEvent&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<java.util.List<LogEvent>> getScanEngineEventsWithHttpInfo(java.util.UUID scanId) throws ApiException {
        com.squareup.okhttp.Call call = getScanEngineEventsValidateBeforeCall(scanId, null, null);
        Type localVarReturnType = new TypeToken<List<LogEvent>>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Get Scan Engine Events (asynchronously)
     * Get the engine events from a Scan
     * @param scanId scan-id (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getScanEngineEventsAsync(java.util.UUID scanId, final ApiCallback<List<LogEvent>> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = getScanEngineEventsValidateBeforeCall(scanId, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<List<LogEvent>>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for getScanExecutionDetails
     * @param scanId scan-id (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getScanExecutionDetailsCall(java.util.UUID scanId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/scans/{scan-id}/execution-details"
            .replaceAll("\\{" + "scan-id" + "\\}", apiClient.escapeString(scanId.toString()));

        List<Pair> localVarQueryParams = new java.util.ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new java.util.ArrayList<Pair>();

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
    private com.squareup.okhttp.Call getScanExecutionDetailsValidateBeforeCall(java.util.UUID scanId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'scanId' is set
        if (scanId == null) {
            throw new ApiException("Missing the required parameter 'scanId' when calling getScanExecutionDetails(Async)");
        }
        

        com.squareup.okhttp.Call call = getScanExecutionDetailsCall(scanId, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Get Scan Execution Details
     * Get real-time details of the execution of a Scan
     * @param scanId scan-id (required)
     * @return ScanExecutionDetails
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ScanExecutionDetails getScanExecutionDetails(java.util.UUID scanId) throws ApiException {
        ApiResponse<ScanExecutionDetails> resp = getScanExecutionDetailsWithHttpInfo(scanId);
        return resp.getData();
    }

    /**
     * Get Scan Execution Details
     * Get real-time details of the execution of a Scan
     * @param scanId scan-id (required)
     * @return ApiResponse&lt;ScanExecutionDetails&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<ScanExecutionDetails> getScanExecutionDetailsWithHttpInfo(java.util.UUID scanId) throws ApiException {
        com.squareup.okhttp.Call call = getScanExecutionDetailsValidateBeforeCall(scanId, null, null);
        Type localVarReturnType = new TypeToken<ScanExecutionDetails>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Get Scan Execution Details (asynchronously)
     * Get real-time details of the execution of a Scan
     * @param scanId scan-id (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getScanExecutionDetailsAsync(java.util.UUID scanId, final ApiCallback<ScanExecutionDetails> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = getScanExecutionDetailsValidateBeforeCall(scanId, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<ScanExecutionDetails>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for getScanPlatformEvents
     * @param scanId scan-id (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getScanPlatformEventsCall(java.util.UUID scanId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/scans/{scan-id}/platform-events"
            .replaceAll("\\{" + "scan-id" + "\\}", apiClient.escapeString(scanId.toString()));

        List<Pair> localVarQueryParams = new java.util.ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new java.util.ArrayList<Pair>();

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
    private com.squareup.okhttp.Call getScanPlatformEventsValidateBeforeCall(java.util.UUID scanId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'scanId' is set
        if (scanId == null) {
            throw new ApiException("Missing the required parameter 'scanId' when calling getScanPlatformEvents(Async)");
        }
        

        com.squareup.okhttp.Call call = getScanPlatformEventsCall(scanId, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Get Scan Platform Events
     * Get the platform events from a Scan
     * @param scanId scan-id (required)
     * @return java.util.List&lt;LogEvent&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public List<LogEvent> getScanPlatformEvents(java.util.UUID scanId) throws ApiException {
        ApiResponse<List<LogEvent>> resp = getScanPlatformEventsWithHttpInfo(scanId);
        return resp.getData();
    }

    /**
     * Get Scan Platform Events
     * Get the platform events from a Scan
     * @param scanId scan-id (required)
     * @return ApiResponse&lt;java.util.List&lt;LogEvent&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<List<LogEvent>> getScanPlatformEventsWithHttpInfo(java.util.UUID scanId) throws ApiException {
        com.squareup.okhttp.Call call = getScanPlatformEventsValidateBeforeCall(scanId, null, null);
        Type localVarReturnType = new TypeToken<List<LogEvent>>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Get Scan Platform Events (asynchronously)
     * Get the platform events from a Scan
     * @param scanId scan-id (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getScanPlatformEventsAsync(java.util.UUID scanId, final ApiCallback<List<LogEvent>> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = getScanPlatformEventsValidateBeforeCall(scanId, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<List<LogEvent>>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for getScans
     * @param index  (optional)
     * @param size  (optional)
     * @param sort  (optional)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getScansCall(Integer index, Integer size, String sort, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/scans";

        List<Pair> localVarQueryParams = new java.util.ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new java.util.ArrayList<Pair>();
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
    private com.squareup.okhttp.Call getScansValidateBeforeCall(Integer index, Integer size, String sort, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        

        com.squareup.okhttp.Call call = getScansCall(index, size, sort, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Get Scans
     * Get a page of Scans, based on supplied &lt;a href&#x3D;\&quot;#pagination\&quot;&gt;pagination parameters&lt;/a&gt;. &lt;p&gt;The default sort for Scans is \&quot;submit_time\&quot; (descending); for a full list of sortable properties, refer to the Search Catalog detailed in the Search API.&lt;/p&gt;
     * @param index  (optional)
     * @param size  (optional)
     * @param sort  (optional)
     * @return PageScan
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public PageScan getScans(Integer index, Integer size, String sort) throws ApiException {
        ApiResponse<PageScan> resp = getScansWithHttpInfo(index, size, sort);
        return resp.getData();
    }

    /**
     * Get Scans
     * Get a page of Scans, based on supplied &lt;a href&#x3D;\&quot;#pagination\&quot;&gt;pagination parameters&lt;/a&gt;. &lt;p&gt;The default sort for Scans is \&quot;submit_time\&quot; (descending); for a full list of sortable properties, refer to the Search Catalog detailed in the Search API.&lt;/p&gt;
     * @param index  (optional)
     * @param size  (optional)
     * @param sort  (optional)
     * @return ApiResponse&lt;PageScan&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<PageScan> getScansWithHttpInfo(Integer index, Integer size, String sort) throws ApiException {
        com.squareup.okhttp.Call call = getScansValidateBeforeCall(index, size, sort, null, null);
        Type localVarReturnType = new TypeToken<PageScan>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Get Scans (asynchronously)
     * Get a page of Scans, based on supplied &lt;a href&#x3D;\&quot;#pagination\&quot;&gt;pagination parameters&lt;/a&gt;. &lt;p&gt;The default sort for Scans is \&quot;submit_time\&quot; (descending); for a full list of sortable properties, refer to the Search Catalog detailed in the Search API.&lt;/p&gt;
     * @param index  (optional)
     * @param size  (optional)
     * @param sort  (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getScansAsync(Integer index, Integer size, String sort, final ApiCallback<PageScan> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = getScansValidateBeforeCall(index, size, sort, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<PageScan>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for submitScan
     * @param scan scan (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call submitScanCall(Scan scan, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = scan;

        // create path and map variables
        String localVarPath = "/scans";

        List<Pair> localVarQueryParams = new java.util.ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new java.util.ArrayList<Pair>();

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
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call submitScanValidateBeforeCall(Scan scan, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'scan' is set
        if (scan == null) {
            throw new ApiException("Missing the required parameter 'scan' when calling submitScan(Async)");
        }
        

        com.squareup.okhttp.Call call = submitScanCall(scan, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Submit Scan
     * Submit a new Scan
     * @param scan scan (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void submitScan(Scan scan) throws ApiException {
        submitScanWithHttpInfo(scan);
    }

    /**
     * Submit Scan
     * Submit a new Scan
     * @param scan scan (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Void> submitScanWithHttpInfo(Scan scan) throws ApiException {
        com.squareup.okhttp.Call call = submitScanValidateBeforeCall(scan, null, null);
        return apiClient.execute(call);
    }

    /**
     * Submit Scan (asynchronously)
     * Submit a new Scan
     * @param scan scan (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call submitScanAsync(Scan scan, final ApiCallback<Void> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = submitScanValidateBeforeCall(scan, progressListener, progressRequestListener);
        apiClient.executeAsync(call, callback);
        return call;
    }
    /**
     * Build call for submitScanAction
     * @param action action (required)
     * @param scanId scan-id (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call submitScanActionCall(ScanStateActionResource action, java.util.UUID scanId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = action;

        // create path and map variables
        String localVarPath = "/scans/{scan-id}/action"
            .replaceAll("\\{" + "scan-id" + "\\}", apiClient.escapeString(scanId.toString()));

        List<Pair> localVarQueryParams = new java.util.ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new java.util.ArrayList<Pair>();

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
        return apiClient.buildCall(localVarPath, "PUT", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call submitScanActionValidateBeforeCall(ScanStateActionResource action, java.util.UUID scanId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'action' is set
        if (action == null) {
            throw new ApiException("Missing the required parameter 'action' when calling submitScanAction(Async)");
        }
        
        // verify the required parameter 'scanId' is set
        if (scanId == null) {
            throw new ApiException("Missing the required parameter 'scanId' when calling submitScanAction(Async)");
        }
        

        com.squareup.okhttp.Call call = submitScanActionCall(action, scanId, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Submit Scan Action
     * Submit a new Scan action
     * @param action action (required)
     * @param scanId scan-id (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void submitScanAction(ScanStateActionResource action, java.util.UUID scanId) throws ApiException {
        submitScanActionWithHttpInfo(action, scanId);
    }

    /**
     * Submit Scan Action
     * Submit a new Scan action
     * @param action action (required)
     * @param scanId scan-id (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Void> submitScanActionWithHttpInfo(ScanStateActionResource action, java.util.UUID scanId) throws ApiException {
        com.squareup.okhttp.Call call = submitScanActionValidateBeforeCall(action, scanId, null, null);
        return apiClient.execute(call);
    }

    /**
     * Submit Scan Action (asynchronously)
     * Submit a new Scan action
     * @param action action (required)
     * @param scanId scan-id (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call submitScanActionAsync(ScanStateActionResource action, java.util.UUID scanId, final ApiCallback<Void> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = submitScanActionValidateBeforeCall(action, scanId, progressListener, progressRequestListener);
        apiClient.executeAsync(call, callback);
        return call;
    }
}
