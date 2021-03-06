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


package com.rapid7.ias.client.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * WebServiceAuthConfig
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-11-27T23:32:49.618-05:00")
public class WebServiceAuthConfig {
  @SerializedName("auth_web_method")
  private String authWebMethod = null;

  @SerializedName("auth_wsdl")
  private String authWsdl = null;

  @SerializedName("enabled")
  private Boolean enabled = null;

  @SerializedName("extract_auth_token")
  private Boolean extractAuthToken = null;

  @SerializedName("get_auth_token_xpath")
  private String getAuthTokenXpath = null;

  @SerializedName("put_auth_token_xpath")
  private String putAuthTokenXpath = null;

  @SerializedName("web_service_parameter_list")
  private java.util.List<WebServiceParameter> webServiceParameterList = null;

  public WebServiceAuthConfig authWebMethod(String authWebMethod) {
    this.authWebMethod = authWebMethod;
    return this;
  }

   /**
   * Get authWebMethod
   * @return authWebMethod
  **/
  @ApiModelProperty(value = "")
  public String getAuthWebMethod() {
    return authWebMethod;
  }

  public void setAuthWebMethod(String authWebMethod) {
    this.authWebMethod = authWebMethod;
  }

  public WebServiceAuthConfig authWsdl(String authWsdl) {
    this.authWsdl = authWsdl;
    return this;
  }

   /**
   * Get authWsdl
   * @return authWsdl
  **/
  @ApiModelProperty(value = "")
  public String getAuthWsdl() {
    return authWsdl;
  }

  public void setAuthWsdl(String authWsdl) {
    this.authWsdl = authWsdl;
  }

  public WebServiceAuthConfig enabled(Boolean enabled) {
    this.enabled = enabled;
    return this;
  }

   /**
   * Get enabled
   * @return enabled
  **/
  @ApiModelProperty(value = "")
  public Boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public WebServiceAuthConfig extractAuthToken(Boolean extractAuthToken) {
    this.extractAuthToken = extractAuthToken;
    return this;
  }

   /**
   * Get extractAuthToken
   * @return extractAuthToken
  **/
  @ApiModelProperty(value = "")
  public Boolean isExtractAuthToken() {
    return extractAuthToken;
  }

  public void setExtractAuthToken(Boolean extractAuthToken) {
    this.extractAuthToken = extractAuthToken;
  }

  public WebServiceAuthConfig getAuthTokenXpath(String getAuthTokenXpath) {
    this.getAuthTokenXpath = getAuthTokenXpath;
    return this;
  }

   /**
   * Get getAuthTokenXpath
   * @return getAuthTokenXpath
  **/
  @ApiModelProperty(value = "")
  public String getGetAuthTokenXpath() {
    return getAuthTokenXpath;
  }

  public void setGetAuthTokenXpath(String getAuthTokenXpath) {
    this.getAuthTokenXpath = getAuthTokenXpath;
  }

  public WebServiceAuthConfig putAuthTokenXpath(String putAuthTokenXpath) {
    this.putAuthTokenXpath = putAuthTokenXpath;
    return this;
  }

   /**
   * Get putAuthTokenXpath
   * @return putAuthTokenXpath
  **/
  @ApiModelProperty(value = "")
  public String getPutAuthTokenXpath() {
    return putAuthTokenXpath;
  }

  public void setPutAuthTokenXpath(String putAuthTokenXpath) {
    this.putAuthTokenXpath = putAuthTokenXpath;
  }

  public WebServiceAuthConfig webServiceParameterList(java.util.List<WebServiceParameter> webServiceParameterList) {
    this.webServiceParameterList = webServiceParameterList;
    return this;
  }

  public WebServiceAuthConfig addWebServiceParameterListItem(WebServiceParameter webServiceParameterListItem) {
    if (this.webServiceParameterList == null) {
      this.webServiceParameterList = new java.util.ArrayList<WebServiceParameter>();
    }
    this.webServiceParameterList.add(webServiceParameterListItem);
    return this;
  }

   /**
   * Get webServiceParameterList
   * @return webServiceParameterList
  **/
  @ApiModelProperty(value = "")
  public java.util.List<WebServiceParameter> getWebServiceParameterList() {
    return webServiceParameterList;
  }

  public void setWebServiceParameterList(java.util.List<WebServiceParameter> webServiceParameterList) {
    this.webServiceParameterList = webServiceParameterList;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WebServiceAuthConfig webServiceAuthConfig = (WebServiceAuthConfig) o;
    return Objects.equals(this.authWebMethod, webServiceAuthConfig.authWebMethod) &&
        Objects.equals(this.authWsdl, webServiceAuthConfig.authWsdl) &&
        Objects.equals(this.enabled, webServiceAuthConfig.enabled) &&
        Objects.equals(this.extractAuthToken, webServiceAuthConfig.extractAuthToken) &&
        Objects.equals(this.getAuthTokenXpath, webServiceAuthConfig.getAuthTokenXpath) &&
        Objects.equals(this.putAuthTokenXpath, webServiceAuthConfig.putAuthTokenXpath) &&
        Objects.equals(this.webServiceParameterList, webServiceAuthConfig.webServiceParameterList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authWebMethod, authWsdl, enabled, extractAuthToken, getAuthTokenXpath, putAuthTokenXpath, webServiceParameterList);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WebServiceAuthConfig {\n");

    sb.append("    authWebMethod: ").append(toIndentedString(authWebMethod)).append("\n");
    sb.append("    authWsdl: ").append(toIndentedString(authWsdl)).append("\n");
    sb.append("    enabled: ").append(toIndentedString(enabled)).append("\n");
    sb.append("    extractAuthToken: ").append(toIndentedString(extractAuthToken)).append("\n");
    sb.append("    getAuthTokenXpath: ").append(toIndentedString(getAuthTokenXpath)).append("\n");
    sb.append("    putAuthTokenXpath: ").append(toIndentedString(putAuthTokenXpath)).append("\n");
    sb.append("    webServiceParameterList: ").append(toIndentedString(webServiceParameterList)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

