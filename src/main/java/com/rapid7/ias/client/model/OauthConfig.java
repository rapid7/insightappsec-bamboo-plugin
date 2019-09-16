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

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModelProperty;

import java.io.IOException;
import java.util.Objects;

/**
 * OauthConfig
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-11-27T23:32:49.618-05:00")
public class OauthConfig {
  /**
   * Gets or Sets authorizationGrantType
   */
  @JsonAdapter(AuthorizationGrantTypeEnum.Adapter.class)
  public enum AuthorizationGrantTypeEnum {
    NULL("NULL"),
    
    AUTHORIZATION_CODE("AUTHORIZATION_CODE"),
    
    IMPLICIT("IMPLICIT"),
    
    RESOURCE_OWNER_PASSWORD_CREDENTIALS("RESOURCE_OWNER_PASSWORD_CREDENTIALS"),
    
    CLIENT_CREDENTIALS("CLIENT_CREDENTIALS");

    private String value;

    AuthorizationGrantTypeEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static AuthorizationGrantTypeEnum fromValue(String text) {
      for (AuthorizationGrantTypeEnum b : AuthorizationGrantTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<AuthorizationGrantTypeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final AuthorizationGrantTypeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public AuthorizationGrantTypeEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return AuthorizationGrantTypeEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("authorization_grant_type")
  private AuthorizationGrantTypeEnum authorizationGrantType = null;

  @SerializedName("authorization_server_url")
  private String authorizationServerUrl = null;

  @SerializedName("client_id")
  private String clientId = null;

  @SerializedName("client_scope")
  private String clientScope = null;

  @SerializedName("client_secret")
  private String clientSecret = null;

  @SerializedName("client_state")
  private String clientState = null;

  @SerializedName("extension_grant")
  private String extensionGrant = null;

  @SerializedName("never_do_basic_auth")
  private Boolean neverDoBasicAuth = null;

  @SerializedName("password")
  private String password = null;

  @SerializedName("password_form")
  private String passwordForm = null;

  @SerializedName("redirect_uri")
  private String redirectUri = null;

  @SerializedName("resource_owner_url")
  private String resourceOwnerUrl = null;

  @SerializedName("resource_server_url")
  private String resourceServerUrl = null;

  @SerializedName("username")
  private String username = null;

  @SerializedName("username_form")
  private String usernameForm = null;

  public OauthConfig authorizationGrantType(AuthorizationGrantTypeEnum authorizationGrantType) {
    this.authorizationGrantType = authorizationGrantType;
    return this;
  }

   /**
   * Get authorizationGrantType
   * @return authorizationGrantType
  **/
  @ApiModelProperty(value = "")
  public AuthorizationGrantTypeEnum getAuthorizationGrantType() {
    return authorizationGrantType;
  }

  public void setAuthorizationGrantType(AuthorizationGrantTypeEnum authorizationGrantType) {
    this.authorizationGrantType = authorizationGrantType;
  }

  public OauthConfig authorizationServerUrl(String authorizationServerUrl) {
    this.authorizationServerUrl = authorizationServerUrl;
    return this;
  }

   /**
   * Get authorizationServerUrl
   * @return authorizationServerUrl
  **/
  @ApiModelProperty(value = "")
  public String getAuthorizationServerUrl() {
    return authorizationServerUrl;
  }

  public void setAuthorizationServerUrl(String authorizationServerUrl) {
    this.authorizationServerUrl = authorizationServerUrl;
  }

  public OauthConfig clientId(String clientId) {
    this.clientId = clientId;
    return this;
  }

   /**
   * Get clientId
   * @return clientId
  **/
  @ApiModelProperty(value = "")
  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public OauthConfig clientScope(String clientScope) {
    this.clientScope = clientScope;
    return this;
  }

   /**
   * Get clientScope
   * @return clientScope
  **/
  @ApiModelProperty(value = "")
  public String getClientScope() {
    return clientScope;
  }

  public void setClientScope(String clientScope) {
    this.clientScope = clientScope;
  }

  public OauthConfig clientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
    return this;
  }

   /**
   * Get clientSecret
   * @return clientSecret
  **/
  @ApiModelProperty(value = "")
  public String getClientSecret() {
    return clientSecret;
  }

  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }

  public OauthConfig clientState(String clientState) {
    this.clientState = clientState;
    return this;
  }

   /**
   * Get clientState
   * @return clientState
  **/
  @ApiModelProperty(value = "")
  public String getClientState() {
    return clientState;
  }

  public void setClientState(String clientState) {
    this.clientState = clientState;
  }

  public OauthConfig extensionGrant(String extensionGrant) {
    this.extensionGrant = extensionGrant;
    return this;
  }

   /**
   * Get extensionGrant
   * @return extensionGrant
  **/
  @ApiModelProperty(value = "")
  public String getExtensionGrant() {
    return extensionGrant;
  }

  public void setExtensionGrant(String extensionGrant) {
    this.extensionGrant = extensionGrant;
  }

  public OauthConfig neverDoBasicAuth(Boolean neverDoBasicAuth) {
    this.neverDoBasicAuth = neverDoBasicAuth;
    return this;
  }

   /**
   * Get neverDoBasicAuth
   * @return neverDoBasicAuth
  **/
  @ApiModelProperty(value = "")
  public Boolean isNeverDoBasicAuth() {
    return neverDoBasicAuth;
  }

  public void setNeverDoBasicAuth(Boolean neverDoBasicAuth) {
    this.neverDoBasicAuth = neverDoBasicAuth;
  }

  public OauthConfig password(String password) {
    this.password = password;
    return this;
  }

   /**
   * Get password
   * @return password
  **/
  @ApiModelProperty(value = "")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public OauthConfig passwordForm(String passwordForm) {
    this.passwordForm = passwordForm;
    return this;
  }

   /**
   * Get passwordForm
   * @return passwordForm
  **/
  @ApiModelProperty(value = "")
  public String getPasswordForm() {
    return passwordForm;
  }

  public void setPasswordForm(String passwordForm) {
    this.passwordForm = passwordForm;
  }

  public OauthConfig redirectUri(String redirectUri) {
    this.redirectUri = redirectUri;
    return this;
  }

   /**
   * Get redirectUri
   * @return redirectUri
  **/
  @ApiModelProperty(value = "")
  public String getRedirectUri() {
    return redirectUri;
  }

  public void setRedirectUri(String redirectUri) {
    this.redirectUri = redirectUri;
  }

  public OauthConfig resourceOwnerUrl(String resourceOwnerUrl) {
    this.resourceOwnerUrl = resourceOwnerUrl;
    return this;
  }

   /**
   * Get resourceOwnerUrl
   * @return resourceOwnerUrl
  **/
  @ApiModelProperty(value = "")
  public String getResourceOwnerUrl() {
    return resourceOwnerUrl;
  }

  public void setResourceOwnerUrl(String resourceOwnerUrl) {
    this.resourceOwnerUrl = resourceOwnerUrl;
  }

  public OauthConfig resourceServerUrl(String resourceServerUrl) {
    this.resourceServerUrl = resourceServerUrl;
    return this;
  }

   /**
   * Get resourceServerUrl
   * @return resourceServerUrl
  **/
  @ApiModelProperty(value = "")
  public String getResourceServerUrl() {
    return resourceServerUrl;
  }

  public void setResourceServerUrl(String resourceServerUrl) {
    this.resourceServerUrl = resourceServerUrl;
  }

  public OauthConfig username(String username) {
    this.username = username;
    return this;
  }

   /**
   * Get username
   * @return username
  **/
  @ApiModelProperty(value = "")
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public OauthConfig usernameForm(String usernameForm) {
    this.usernameForm = usernameForm;
    return this;
  }

   /**
   * Get usernameForm
   * @return usernameForm
  **/
  @ApiModelProperty(value = "")
  public String getUsernameForm() {
    return usernameForm;
  }

  public void setUsernameForm(String usernameForm) {
    this.usernameForm = usernameForm;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OauthConfig oauthConfig = (OauthConfig) o;
    return Objects.equals(this.authorizationGrantType, oauthConfig.authorizationGrantType) &&
        Objects.equals(this.authorizationServerUrl, oauthConfig.authorizationServerUrl) &&
        Objects.equals(this.clientId, oauthConfig.clientId) &&
        Objects.equals(this.clientScope, oauthConfig.clientScope) &&
        Objects.equals(this.clientSecret, oauthConfig.clientSecret) &&
        Objects.equals(this.clientState, oauthConfig.clientState) &&
        Objects.equals(this.extensionGrant, oauthConfig.extensionGrant) &&
        Objects.equals(this.neverDoBasicAuth, oauthConfig.neverDoBasicAuth) &&
        Objects.equals(this.password, oauthConfig.password) &&
        Objects.equals(this.passwordForm, oauthConfig.passwordForm) &&
        Objects.equals(this.redirectUri, oauthConfig.redirectUri) &&
        Objects.equals(this.resourceOwnerUrl, oauthConfig.resourceOwnerUrl) &&
        Objects.equals(this.resourceServerUrl, oauthConfig.resourceServerUrl) &&
        Objects.equals(this.username, oauthConfig.username) &&
        Objects.equals(this.usernameForm, oauthConfig.usernameForm);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authorizationGrantType, authorizationServerUrl, clientId, clientScope, clientSecret, clientState, extensionGrant, neverDoBasicAuth, password, passwordForm, redirectUri, resourceOwnerUrl, resourceServerUrl, username, usernameForm);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OauthConfig {\n");

    sb.append("    authorizationGrantType: ").append(toIndentedString(authorizationGrantType)).append("\n");
    sb.append("    authorizationServerUrl: ").append(toIndentedString(authorizationServerUrl)).append("\n");
    sb.append("    clientId: ").append(toIndentedString(clientId)).append("\n");
    sb.append("    clientScope: ").append(toIndentedString(clientScope)).append("\n");
    sb.append("    clientSecret: ").append(toIndentedString(clientSecret)).append("\n");
    sb.append("    clientState: ").append(toIndentedString(clientState)).append("\n");
    sb.append("    extensionGrant: ").append(toIndentedString(extensionGrant)).append("\n");
    sb.append("    neverDoBasicAuth: ").append(toIndentedString(neverDoBasicAuth)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    passwordForm: ").append(toIndentedString(passwordForm)).append("\n");
    sb.append("    redirectUri: ").append(toIndentedString(redirectUri)).append("\n");
    sb.append("    resourceOwnerUrl: ").append(toIndentedString(resourceOwnerUrl)).append("\n");
    sb.append("    resourceServerUrl: ").append(toIndentedString(resourceServerUrl)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    usernameForm: ").append(toIndentedString(usernameForm)).append("\n");
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

