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
 * AuthConfig
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-11-27T23:32:49.618-05:00")
public class AuthConfig {
  @SerializedName("assume_successful_login")
  private Boolean assumeSuccessfulLogin = null;

  /**
   * Gets or Sets autoLogonSecurity
   */
  @JsonAdapter(AutoLogonSecurityEnum.Adapter.class)
  public enum AutoLogonSecurityEnum {
    LOW("LOW"),
    
    MEDIUM("MEDIUM"),
    
    HIGH("HIGH");

    private String value;

    AutoLogonSecurityEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static AutoLogonSecurityEnum fromValue(String text) {
      for (AutoLogonSecurityEnum b : AutoLogonSecurityEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<AutoLogonSecurityEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final AutoLogonSecurityEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public AutoLogonSecurityEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return AutoLogonSecurityEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("auto_logon_security")
  private AutoLogonSecurityEnum autoLogonSecurity = null;

  @SerializedName("blacklist_multi_password_forms")
  private Boolean blacklistMultiPasswordForms = null;

  @SerializedName("blacklist_single_password_forms")
  private Boolean blacklistSinglePasswordForms = null;

  @SerializedName("browser_form_login_config")
  private BrowserFormLoginConfig browserFormLoginConfig = null;

  @SerializedName("create_non_authenticated_session")
  private Boolean createNonAuthenticatedSession = null;

  @SerializedName("discover_login_form")
  private Boolean discoverLoginForm = null;

  @SerializedName("discovery_depth")
  private Long discoveryDepth = null;

  @SerializedName("discovery_max_links")
  private Long discoveryMaxLinks = null;

  /**
   * Gets or Sets discoveryPrioritization
   */
  @JsonAdapter(DiscoveryPrioritizationEnum.Adapter.class)
  public enum DiscoveryPrioritizationEnum {
    FIRST_IN_FIRST_OUT("FIRST_IN_FIRST_OUT"),
    
    SMART("SMART"),
    
    DIRECTORY_BREADTH_FIRST("DIRECTORY_BREADTH_FIRST"),
    
    FOUND_BREADTH_FIRST("FOUND_BREADTH_FIRST"),
    
    FOUND_DEPTH_FIRST("FOUND_DEPTH_FIRST"),
    
    JUICY("JUICY"),
    
    LOGIN_FORM_DISCOVERY("LOGIN_FORM_DISCOVERY"),
    
    LOGIN("LOGIN");

    private String value;

    DiscoveryPrioritizationEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static DiscoveryPrioritizationEnum fromValue(String text) {
      for (DiscoveryPrioritizationEnum b : DiscoveryPrioritizationEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<DiscoveryPrioritizationEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final DiscoveryPrioritizationEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public DiscoveryPrioritizationEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return DiscoveryPrioritizationEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("discovery_prioritization")
  private DiscoveryPrioritizationEnum discoveryPrioritization = null;

  @SerializedName("http_auth")
  private Boolean httpAuth = null;

  @SerializedName("http_header_with_token_replacement")
  private String httpHeaderWithTokenReplacement = null;

  @SerializedName("logged_in_header_regex")
  private String loggedInHeaderRegex = null;

  @SerializedName("logged_in_regex")
  private String loggedInRegex = null;

  @SerializedName("login_depth")
  private Long loginDepth = null;

  @SerializedName("login_link_regex")
  private String loginLinkRegex = null;

  @SerializedName("login_max_links")
  private Long loginMaxLinks = null;

  /**
   * Gets or Sets loginPrioritization
   */
  @JsonAdapter(LoginPrioritizationEnum.Adapter.class)
  public enum LoginPrioritizationEnum {
    FIRST_IN_FIRST_OUT("FIRST_IN_FIRST_OUT"),
    
    SMART("SMART"),
    
    DIRECTORY_BREADTH_FIRST("DIRECTORY_BREADTH_FIRST"),
    
    FOUND_BREADTH_FIRST("FOUND_BREADTH_FIRST"),
    
    FOUND_DEPTH_FIRST("FOUND_DEPTH_FIRST"),
    
    JUICY("JUICY"),
    
    LOGIN_FORM_DISCOVERY("LOGIN_FORM_DISCOVERY"),
    
    LOGIN("LOGIN");

    private String value;

    LoginPrioritizationEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static LoginPrioritizationEnum fromValue(String text) {
      for (LoginPrioritizationEnum b : LoginPrioritizationEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<LoginPrioritizationEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final LoginPrioritizationEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public LoginPrioritizationEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return LoginPrioritizationEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("login_prioritization")
  private LoginPrioritizationEnum loginPrioritization = null;

  @SerializedName("logout_detection")
  private Boolean logoutDetection = null;

  @SerializedName("logout_detection_frequency")
  private Long logoutDetectionFrequency = null;

  @SerializedName("logout_link_regex")
  private String logoutLinkRegex = null;

  @SerializedName("logout_post_body_regex")
  private String logoutPostBodyRegex = null;

  @SerializedName("macro_file")
  private MacroFile macroFile = null;

  @SerializedName("max_macro_relogin_attempts")
  private Long maxMacroReloginAttempts = null;

  @SerializedName("oauth")
  private Boolean oauth = null;

  @SerializedName("oauth_config")
  private OauthConfig oauthConfig = null;

  @SerializedName("password_form")
  private String passwordForm = null;

  @SerializedName("password_http")
  private String passwordHttp = null;

  @SerializedName("post_session_token_regex")
  private String postSessionTokenRegex = null;

  @SerializedName("postpone_login_action")
  private Boolean postponeLoginAction = null;

  @SerializedName("relogin_after_session_loss")
  private Boolean reloginAfterSessionLoss = null;

  @SerializedName("reset_cookies")
  private Boolean resetCookies = null;

  @SerializedName("response_body_token_regex")
  private String responseBodyTokenRegex = null;

  @SerializedName("restart_proxy_before_relogin")
  private Boolean restartProxyBeforeRelogin = null;

  @SerializedName("scope_constraint_list")
  private java.util.List<ScopeConstraint> scopeConstraintList = null;

  @SerializedName("seed_link")
  private String seedLink = null;

  @SerializedName("session_cookie_lifespan")
  private Double sessionCookieLifespan = null;

  @SerializedName("session_cookie_regex")
  private String sessionCookieRegex = null;

  @SerializedName("session_loss_header_regex")
  private String sessionLossHeaderRegex = null;

  @SerializedName("session_loss_regex")
  private String sessionLossRegex = null;

  @SerializedName("traffic_file")
  private TrafficFile trafficFile = null;

  @SerializedName("treat_failed_relogin_as_error")
  private Boolean treatFailedReloginAsError = null;

  /**
   * Gets or Sets type
   */
  @JsonAdapter(TypeEnum.Adapter.class)
  public enum TypeEnum {
    NONE("NONE"),
    
    FORM("FORM"),
    
    MACRO("MACRO"),
    
    SESSION_TAKEOVER("SESSION_TAKEOVER"),
    
    SSO_REDIRECT("SSO_REDIRECT"),
    
    TRAFFIC("TRAFFIC");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static TypeEnum fromValue(String text) {
      for (TypeEnum b : TypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<TypeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final TypeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public TypeEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return TypeEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("type")
  private TypeEnum type = null;

  @SerializedName("url_session_token_regex")
  private String urlSessionTokenRegex = null;

  @SerializedName("use_browser_form_login")
  private Boolean useBrowserFormLogin = null;

  @SerializedName("username_form")
  private String usernameForm = null;

  @SerializedName("username_http")
  private String usernameHttp = null;

  @SerializedName("verify_not_loggedin")
  private Boolean verifyNotLoggedin = null;

  @SerializedName("web_service_auth_config")
  private WebServiceAuthConfig webServiceAuthConfig = null;

  public AuthConfig assumeSuccessfulLogin(Boolean assumeSuccessfulLogin) {
    this.assumeSuccessfulLogin = assumeSuccessfulLogin;
    return this;
  }

   /**
   * Get assumeSuccessfulLogin
   * @return assumeSuccessfulLogin
  **/
  @ApiModelProperty(value = "")
  public Boolean isAssumeSuccessfulLogin() {
    return assumeSuccessfulLogin;
  }

  public void setAssumeSuccessfulLogin(Boolean assumeSuccessfulLogin) {
    this.assumeSuccessfulLogin = assumeSuccessfulLogin;
  }

  public AuthConfig autoLogonSecurity(AutoLogonSecurityEnum autoLogonSecurity) {
    this.autoLogonSecurity = autoLogonSecurity;
    return this;
  }

   /**
   * Get autoLogonSecurity
   * @return autoLogonSecurity
  **/
  @ApiModelProperty(value = "")
  public AutoLogonSecurityEnum getAutoLogonSecurity() {
    return autoLogonSecurity;
  }

  public void setAutoLogonSecurity(AutoLogonSecurityEnum autoLogonSecurity) {
    this.autoLogonSecurity = autoLogonSecurity;
  }

  public AuthConfig blacklistMultiPasswordForms(Boolean blacklistMultiPasswordForms) {
    this.blacklistMultiPasswordForms = blacklistMultiPasswordForms;
    return this;
  }

   /**
   * Get blacklistMultiPasswordForms
   * @return blacklistMultiPasswordForms
  **/
  @ApiModelProperty(value = "")
  public Boolean isBlacklistMultiPasswordForms() {
    return blacklistMultiPasswordForms;
  }

  public void setBlacklistMultiPasswordForms(Boolean blacklistMultiPasswordForms) {
    this.blacklistMultiPasswordForms = blacklistMultiPasswordForms;
  }

  public AuthConfig blacklistSinglePasswordForms(Boolean blacklistSinglePasswordForms) {
    this.blacklistSinglePasswordForms = blacklistSinglePasswordForms;
    return this;
  }

   /**
   * Get blacklistSinglePasswordForms
   * @return blacklistSinglePasswordForms
  **/
  @ApiModelProperty(value = "")
  public Boolean isBlacklistSinglePasswordForms() {
    return blacklistSinglePasswordForms;
  }

  public void setBlacklistSinglePasswordForms(Boolean blacklistSinglePasswordForms) {
    this.blacklistSinglePasswordForms = blacklistSinglePasswordForms;
  }

  public AuthConfig browserFormLoginConfig(BrowserFormLoginConfig browserFormLoginConfig) {
    this.browserFormLoginConfig = browserFormLoginConfig;
    return this;
  }

   /**
   * Get browserFormLoginConfig
   * @return browserFormLoginConfig
  **/
  @ApiModelProperty(value = "")
  public BrowserFormLoginConfig getBrowserFormLoginConfig() {
    return browserFormLoginConfig;
  }

  public void setBrowserFormLoginConfig(BrowserFormLoginConfig browserFormLoginConfig) {
    this.browserFormLoginConfig = browserFormLoginConfig;
  }

  public AuthConfig createNonAuthenticatedSession(Boolean createNonAuthenticatedSession) {
    this.createNonAuthenticatedSession = createNonAuthenticatedSession;
    return this;
  }

   /**
   * Get createNonAuthenticatedSession
   * @return createNonAuthenticatedSession
  **/
  @ApiModelProperty(value = "")
  public Boolean isCreateNonAuthenticatedSession() {
    return createNonAuthenticatedSession;
  }

  public void setCreateNonAuthenticatedSession(Boolean createNonAuthenticatedSession) {
    this.createNonAuthenticatedSession = createNonAuthenticatedSession;
  }

  public AuthConfig discoverLoginForm(Boolean discoverLoginForm) {
    this.discoverLoginForm = discoverLoginForm;
    return this;
  }

   /**
   * Get discoverLoginForm
   * @return discoverLoginForm
  **/
  @ApiModelProperty(value = "")
  public Boolean isDiscoverLoginForm() {
    return discoverLoginForm;
  }

  public void setDiscoverLoginForm(Boolean discoverLoginForm) {
    this.discoverLoginForm = discoverLoginForm;
  }

  public AuthConfig discoveryDepth(Long discoveryDepth) {
    this.discoveryDepth = discoveryDepth;
    return this;
  }

   /**
   * Get discoveryDepth
   * @return discoveryDepth
  **/
  @ApiModelProperty(value = "")
  public Long getDiscoveryDepth() {
    return discoveryDepth;
  }

  public void setDiscoveryDepth(Long discoveryDepth) {
    this.discoveryDepth = discoveryDepth;
  }

  public AuthConfig discoveryMaxLinks(Long discoveryMaxLinks) {
    this.discoveryMaxLinks = discoveryMaxLinks;
    return this;
  }

   /**
   * Get discoveryMaxLinks
   * @return discoveryMaxLinks
  **/
  @ApiModelProperty(value = "")
  public Long getDiscoveryMaxLinks() {
    return discoveryMaxLinks;
  }

  public void setDiscoveryMaxLinks(Long discoveryMaxLinks) {
    this.discoveryMaxLinks = discoveryMaxLinks;
  }

  public AuthConfig discoveryPrioritization(DiscoveryPrioritizationEnum discoveryPrioritization) {
    this.discoveryPrioritization = discoveryPrioritization;
    return this;
  }

   /**
   * Get discoveryPrioritization
   * @return discoveryPrioritization
  **/
  @ApiModelProperty(value = "")
  public DiscoveryPrioritizationEnum getDiscoveryPrioritization() {
    return discoveryPrioritization;
  }

  public void setDiscoveryPrioritization(DiscoveryPrioritizationEnum discoveryPrioritization) {
    this.discoveryPrioritization = discoveryPrioritization;
  }

  public AuthConfig httpAuth(Boolean httpAuth) {
    this.httpAuth = httpAuth;
    return this;
  }

   /**
   * Get httpAuth
   * @return httpAuth
  **/
  @ApiModelProperty(value = "")
  public Boolean isHttpAuth() {
    return httpAuth;
  }

  public void setHttpAuth(Boolean httpAuth) {
    this.httpAuth = httpAuth;
  }

  public AuthConfig httpHeaderWithTokenReplacement(String httpHeaderWithTokenReplacement) {
    this.httpHeaderWithTokenReplacement = httpHeaderWithTokenReplacement;
    return this;
  }

   /**
   * Get httpHeaderWithTokenReplacement
   * @return httpHeaderWithTokenReplacement
  **/
  @ApiModelProperty(value = "")
  public String getHttpHeaderWithTokenReplacement() {
    return httpHeaderWithTokenReplacement;
  }

  public void setHttpHeaderWithTokenReplacement(String httpHeaderWithTokenReplacement) {
    this.httpHeaderWithTokenReplacement = httpHeaderWithTokenReplacement;
  }

  public AuthConfig loggedInHeaderRegex(String loggedInHeaderRegex) {
    this.loggedInHeaderRegex = loggedInHeaderRegex;
    return this;
  }

   /**
   * Get loggedInHeaderRegex
   * @return loggedInHeaderRegex
  **/
  @ApiModelProperty(value = "")
  public String getLoggedInHeaderRegex() {
    return loggedInHeaderRegex;
  }

  public void setLoggedInHeaderRegex(String loggedInHeaderRegex) {
    this.loggedInHeaderRegex = loggedInHeaderRegex;
  }

  public AuthConfig loggedInRegex(String loggedInRegex) {
    this.loggedInRegex = loggedInRegex;
    return this;
  }

   /**
   * Get loggedInRegex
   * @return loggedInRegex
  **/
  @ApiModelProperty(value = "")
  public String getLoggedInRegex() {
    return loggedInRegex;
  }

  public void setLoggedInRegex(String loggedInRegex) {
    this.loggedInRegex = loggedInRegex;
  }

  public AuthConfig loginDepth(Long loginDepth) {
    this.loginDepth = loginDepth;
    return this;
  }

   /**
   * Get loginDepth
   * @return loginDepth
  **/
  @ApiModelProperty(value = "")
  public Long getLoginDepth() {
    return loginDepth;
  }

  public void setLoginDepth(Long loginDepth) {
    this.loginDepth = loginDepth;
  }

  public AuthConfig loginLinkRegex(String loginLinkRegex) {
    this.loginLinkRegex = loginLinkRegex;
    return this;
  }

   /**
   * Get loginLinkRegex
   * @return loginLinkRegex
  **/
  @ApiModelProperty(value = "")
  public String getLoginLinkRegex() {
    return loginLinkRegex;
  }

  public void setLoginLinkRegex(String loginLinkRegex) {
    this.loginLinkRegex = loginLinkRegex;
  }

  public AuthConfig loginMaxLinks(Long loginMaxLinks) {
    this.loginMaxLinks = loginMaxLinks;
    return this;
  }

   /**
   * Get loginMaxLinks
   * @return loginMaxLinks
  **/
  @ApiModelProperty(value = "")
  public Long getLoginMaxLinks() {
    return loginMaxLinks;
  }

  public void setLoginMaxLinks(Long loginMaxLinks) {
    this.loginMaxLinks = loginMaxLinks;
  }

  public AuthConfig loginPrioritization(LoginPrioritizationEnum loginPrioritization) {
    this.loginPrioritization = loginPrioritization;
    return this;
  }

   /**
   * Get loginPrioritization
   * @return loginPrioritization
  **/
  @ApiModelProperty(value = "")
  public LoginPrioritizationEnum getLoginPrioritization() {
    return loginPrioritization;
  }

  public void setLoginPrioritization(LoginPrioritizationEnum loginPrioritization) {
    this.loginPrioritization = loginPrioritization;
  }

  public AuthConfig logoutDetection(Boolean logoutDetection) {
    this.logoutDetection = logoutDetection;
    return this;
  }

   /**
   * Get logoutDetection
   * @return logoutDetection
  **/
  @ApiModelProperty(value = "")
  public Boolean isLogoutDetection() {
    return logoutDetection;
  }

  public void setLogoutDetection(Boolean logoutDetection) {
    this.logoutDetection = logoutDetection;
  }

  public AuthConfig logoutDetectionFrequency(Long logoutDetectionFrequency) {
    this.logoutDetectionFrequency = logoutDetectionFrequency;
    return this;
  }

   /**
   * Get logoutDetectionFrequency
   * @return logoutDetectionFrequency
  **/
  @ApiModelProperty(value = "")
  public Long getLogoutDetectionFrequency() {
    return logoutDetectionFrequency;
  }

  public void setLogoutDetectionFrequency(Long logoutDetectionFrequency) {
    this.logoutDetectionFrequency = logoutDetectionFrequency;
  }

  public AuthConfig logoutLinkRegex(String logoutLinkRegex) {
    this.logoutLinkRegex = logoutLinkRegex;
    return this;
  }

   /**
   * Get logoutLinkRegex
   * @return logoutLinkRegex
  **/
  @ApiModelProperty(value = "")
  public String getLogoutLinkRegex() {
    return logoutLinkRegex;
  }

  public void setLogoutLinkRegex(String logoutLinkRegex) {
    this.logoutLinkRegex = logoutLinkRegex;
  }

  public AuthConfig logoutPostBodyRegex(String logoutPostBodyRegex) {
    this.logoutPostBodyRegex = logoutPostBodyRegex;
    return this;
  }

   /**
   * Get logoutPostBodyRegex
   * @return logoutPostBodyRegex
  **/
  @ApiModelProperty(value = "")
  public String getLogoutPostBodyRegex() {
    return logoutPostBodyRegex;
  }

  public void setLogoutPostBodyRegex(String logoutPostBodyRegex) {
    this.logoutPostBodyRegex = logoutPostBodyRegex;
  }

  public AuthConfig macroFile(MacroFile macroFile) {
    this.macroFile = macroFile;
    return this;
  }

   /**
   * Get macroFile
   * @return macroFile
  **/
  @ApiModelProperty(value = "")
  public MacroFile getMacroFile() {
    return macroFile;
  }

  public void setMacroFile(MacroFile macroFile) {
    this.macroFile = macroFile;
  }

  public AuthConfig maxMacroReloginAttempts(Long maxMacroReloginAttempts) {
    this.maxMacroReloginAttempts = maxMacroReloginAttempts;
    return this;
  }

   /**
   * Get maxMacroReloginAttempts
   * @return maxMacroReloginAttempts
  **/
  @ApiModelProperty(value = "")
  public Long getMaxMacroReloginAttempts() {
    return maxMacroReloginAttempts;
  }

  public void setMaxMacroReloginAttempts(Long maxMacroReloginAttempts) {
    this.maxMacroReloginAttempts = maxMacroReloginAttempts;
  }

  public AuthConfig oauth(Boolean oauth) {
    this.oauth = oauth;
    return this;
  }

   /**
   * Get oauth
   * @return oauth
  **/
  @ApiModelProperty(value = "")
  public Boolean isOauth() {
    return oauth;
  }

  public void setOauth(Boolean oauth) {
    this.oauth = oauth;
  }

  public AuthConfig oauthConfig(OauthConfig oauthConfig) {
    this.oauthConfig = oauthConfig;
    return this;
  }

   /**
   * Get oauthConfig
   * @return oauthConfig
  **/
  @ApiModelProperty(value = "")
  public OauthConfig getOauthConfig() {
    return oauthConfig;
  }

  public void setOauthConfig(OauthConfig oauthConfig) {
    this.oauthConfig = oauthConfig;
  }

  public AuthConfig passwordForm(String passwordForm) {
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

  public AuthConfig passwordHttp(String passwordHttp) {
    this.passwordHttp = passwordHttp;
    return this;
  }

   /**
   * Get passwordHttp
   * @return passwordHttp
  **/
  @ApiModelProperty(value = "")
  public String getPasswordHttp() {
    return passwordHttp;
  }

  public void setPasswordHttp(String passwordHttp) {
    this.passwordHttp = passwordHttp;
  }

  public AuthConfig postSessionTokenRegex(String postSessionTokenRegex) {
    this.postSessionTokenRegex = postSessionTokenRegex;
    return this;
  }

   /**
   * Get postSessionTokenRegex
   * @return postSessionTokenRegex
  **/
  @ApiModelProperty(value = "")
  public String getPostSessionTokenRegex() {
    return postSessionTokenRegex;
  }

  public void setPostSessionTokenRegex(String postSessionTokenRegex) {
    this.postSessionTokenRegex = postSessionTokenRegex;
  }

  public AuthConfig postponeLoginAction(Boolean postponeLoginAction) {
    this.postponeLoginAction = postponeLoginAction;
    return this;
  }

   /**
   * Get postponeLoginAction
   * @return postponeLoginAction
  **/
  @ApiModelProperty(value = "")
  public Boolean isPostponeLoginAction() {
    return postponeLoginAction;
  }

  public void setPostponeLoginAction(Boolean postponeLoginAction) {
    this.postponeLoginAction = postponeLoginAction;
  }

  public AuthConfig reloginAfterSessionLoss(Boolean reloginAfterSessionLoss) {
    this.reloginAfterSessionLoss = reloginAfterSessionLoss;
    return this;
  }

   /**
   * Get reloginAfterSessionLoss
   * @return reloginAfterSessionLoss
  **/
  @ApiModelProperty(value = "")
  public Boolean isReloginAfterSessionLoss() {
    return reloginAfterSessionLoss;
  }

  public void setReloginAfterSessionLoss(Boolean reloginAfterSessionLoss) {
    this.reloginAfterSessionLoss = reloginAfterSessionLoss;
  }

  public AuthConfig resetCookies(Boolean resetCookies) {
    this.resetCookies = resetCookies;
    return this;
  }

   /**
   * Get resetCookies
   * @return resetCookies
  **/
  @ApiModelProperty(value = "")
  public Boolean isResetCookies() {
    return resetCookies;
  }

  public void setResetCookies(Boolean resetCookies) {
    this.resetCookies = resetCookies;
  }

  public AuthConfig responseBodyTokenRegex(String responseBodyTokenRegex) {
    this.responseBodyTokenRegex = responseBodyTokenRegex;
    return this;
  }

   /**
   * Get responseBodyTokenRegex
   * @return responseBodyTokenRegex
  **/
  @ApiModelProperty(value = "")
  public String getResponseBodyTokenRegex() {
    return responseBodyTokenRegex;
  }

  public void setResponseBodyTokenRegex(String responseBodyTokenRegex) {
    this.responseBodyTokenRegex = responseBodyTokenRegex;
  }

  public AuthConfig restartProxyBeforeRelogin(Boolean restartProxyBeforeRelogin) {
    this.restartProxyBeforeRelogin = restartProxyBeforeRelogin;
    return this;
  }

   /**
   * Get restartProxyBeforeRelogin
   * @return restartProxyBeforeRelogin
  **/
  @ApiModelProperty(value = "")
  public Boolean isRestartProxyBeforeRelogin() {
    return restartProxyBeforeRelogin;
  }

  public void setRestartProxyBeforeRelogin(Boolean restartProxyBeforeRelogin) {
    this.restartProxyBeforeRelogin = restartProxyBeforeRelogin;
  }

  public AuthConfig scopeConstraintList(java.util.List<ScopeConstraint> scopeConstraintList) {
    this.scopeConstraintList = scopeConstraintList;
    return this;
  }

  public AuthConfig addScopeConstraintListItem(ScopeConstraint scopeConstraintListItem) {
    if (this.scopeConstraintList == null) {
      this.scopeConstraintList = new java.util.ArrayList<ScopeConstraint>();
    }
    this.scopeConstraintList.add(scopeConstraintListItem);
    return this;
  }

   /**
   * Get scopeConstraintList
   * @return scopeConstraintList
  **/
  @ApiModelProperty(value = "")
  public java.util.List<ScopeConstraint> getScopeConstraintList() {
    return scopeConstraintList;
  }

  public void setScopeConstraintList(java.util.List<ScopeConstraint> scopeConstraintList) {
    this.scopeConstraintList = scopeConstraintList;
  }

  public AuthConfig seedLink(String seedLink) {
    this.seedLink = seedLink;
    return this;
  }

   /**
   * Get seedLink
   * @return seedLink
  **/
  @ApiModelProperty(value = "")
  public String getSeedLink() {
    return seedLink;
  }

  public void setSeedLink(String seedLink) {
    this.seedLink = seedLink;
  }

  public AuthConfig sessionCookieLifespan(Double sessionCookieLifespan) {
    this.sessionCookieLifespan = sessionCookieLifespan;
    return this;
  }

   /**
   * Get sessionCookieLifespan
   * @return sessionCookieLifespan
  **/
  @ApiModelProperty(value = "")
  public Double getSessionCookieLifespan() {
    return sessionCookieLifespan;
  }

  public void setSessionCookieLifespan(Double sessionCookieLifespan) {
    this.sessionCookieLifespan = sessionCookieLifespan;
  }

  public AuthConfig sessionCookieRegex(String sessionCookieRegex) {
    this.sessionCookieRegex = sessionCookieRegex;
    return this;
  }

   /**
   * Get sessionCookieRegex
   * @return sessionCookieRegex
  **/
  @ApiModelProperty(value = "")
  public String getSessionCookieRegex() {
    return sessionCookieRegex;
  }

  public void setSessionCookieRegex(String sessionCookieRegex) {
    this.sessionCookieRegex = sessionCookieRegex;
  }

  public AuthConfig sessionLossHeaderRegex(String sessionLossHeaderRegex) {
    this.sessionLossHeaderRegex = sessionLossHeaderRegex;
    return this;
  }

   /**
   * Get sessionLossHeaderRegex
   * @return sessionLossHeaderRegex
  **/
  @ApiModelProperty(value = "")
  public String getSessionLossHeaderRegex() {
    return sessionLossHeaderRegex;
  }

  public void setSessionLossHeaderRegex(String sessionLossHeaderRegex) {
    this.sessionLossHeaderRegex = sessionLossHeaderRegex;
  }

  public AuthConfig sessionLossRegex(String sessionLossRegex) {
    this.sessionLossRegex = sessionLossRegex;
    return this;
  }

   /**
   * Get sessionLossRegex
   * @return sessionLossRegex
  **/
  @ApiModelProperty(value = "")
  public String getSessionLossRegex() {
    return sessionLossRegex;
  }

  public void setSessionLossRegex(String sessionLossRegex) {
    this.sessionLossRegex = sessionLossRegex;
  }

  public AuthConfig trafficFile(TrafficFile trafficFile) {
    this.trafficFile = trafficFile;
    return this;
  }

   /**
   * Get trafficFile
   * @return trafficFile
  **/
  @ApiModelProperty(value = "")
  public TrafficFile getTrafficFile() {
    return trafficFile;
  }

  public void setTrafficFile(TrafficFile trafficFile) {
    this.trafficFile = trafficFile;
  }

  public AuthConfig treatFailedReloginAsError(Boolean treatFailedReloginAsError) {
    this.treatFailedReloginAsError = treatFailedReloginAsError;
    return this;
  }

   /**
   * Get treatFailedReloginAsError
   * @return treatFailedReloginAsError
  **/
  @ApiModelProperty(value = "")
  public Boolean isTreatFailedReloginAsError() {
    return treatFailedReloginAsError;
  }

  public void setTreatFailedReloginAsError(Boolean treatFailedReloginAsError) {
    this.treatFailedReloginAsError = treatFailedReloginAsError;
  }

  public AuthConfig type(TypeEnum type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(value = "")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public AuthConfig urlSessionTokenRegex(String urlSessionTokenRegex) {
    this.urlSessionTokenRegex = urlSessionTokenRegex;
    return this;
  }

   /**
   * Get urlSessionTokenRegex
   * @return urlSessionTokenRegex
  **/
  @ApiModelProperty(value = "")
  public String getUrlSessionTokenRegex() {
    return urlSessionTokenRegex;
  }

  public void setUrlSessionTokenRegex(String urlSessionTokenRegex) {
    this.urlSessionTokenRegex = urlSessionTokenRegex;
  }

  public AuthConfig useBrowserFormLogin(Boolean useBrowserFormLogin) {
    this.useBrowserFormLogin = useBrowserFormLogin;
    return this;
  }

   /**
   * Get useBrowserFormLogin
   * @return useBrowserFormLogin
  **/
  @ApiModelProperty(value = "")
  public Boolean isUseBrowserFormLogin() {
    return useBrowserFormLogin;
  }

  public void setUseBrowserFormLogin(Boolean useBrowserFormLogin) {
    this.useBrowserFormLogin = useBrowserFormLogin;
  }

  public AuthConfig usernameForm(String usernameForm) {
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

  public AuthConfig usernameHttp(String usernameHttp) {
    this.usernameHttp = usernameHttp;
    return this;
  }

   /**
   * Get usernameHttp
   * @return usernameHttp
  **/
  @ApiModelProperty(value = "")
  public String getUsernameHttp() {
    return usernameHttp;
  }

  public void setUsernameHttp(String usernameHttp) {
    this.usernameHttp = usernameHttp;
  }

  public AuthConfig verifyNotLoggedin(Boolean verifyNotLoggedin) {
    this.verifyNotLoggedin = verifyNotLoggedin;
    return this;
  }

   /**
   * Get verifyNotLoggedin
   * @return verifyNotLoggedin
  **/
  @ApiModelProperty(value = "")
  public Boolean isVerifyNotLoggedin() {
    return verifyNotLoggedin;
  }

  public void setVerifyNotLoggedin(Boolean verifyNotLoggedin) {
    this.verifyNotLoggedin = verifyNotLoggedin;
  }

  public AuthConfig webServiceAuthConfig(WebServiceAuthConfig webServiceAuthConfig) {
    this.webServiceAuthConfig = webServiceAuthConfig;
    return this;
  }

   /**
   * Get webServiceAuthConfig
   * @return webServiceAuthConfig
  **/
  @ApiModelProperty(value = "")
  public WebServiceAuthConfig getWebServiceAuthConfig() {
    return webServiceAuthConfig;
  }

  public void setWebServiceAuthConfig(WebServiceAuthConfig webServiceAuthConfig) {
    this.webServiceAuthConfig = webServiceAuthConfig;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthConfig authConfig = (AuthConfig) o;
    return Objects.equals(this.assumeSuccessfulLogin, authConfig.assumeSuccessfulLogin) &&
        Objects.equals(this.autoLogonSecurity, authConfig.autoLogonSecurity) &&
        Objects.equals(this.blacklistMultiPasswordForms, authConfig.blacklistMultiPasswordForms) &&
        Objects.equals(this.blacklistSinglePasswordForms, authConfig.blacklistSinglePasswordForms) &&
        Objects.equals(this.browserFormLoginConfig, authConfig.browserFormLoginConfig) &&
        Objects.equals(this.createNonAuthenticatedSession, authConfig.createNonAuthenticatedSession) &&
        Objects.equals(this.discoverLoginForm, authConfig.discoverLoginForm) &&
        Objects.equals(this.discoveryDepth, authConfig.discoveryDepth) &&
        Objects.equals(this.discoveryMaxLinks, authConfig.discoveryMaxLinks) &&
        Objects.equals(this.discoveryPrioritization, authConfig.discoveryPrioritization) &&
        Objects.equals(this.httpAuth, authConfig.httpAuth) &&
        Objects.equals(this.httpHeaderWithTokenReplacement, authConfig.httpHeaderWithTokenReplacement) &&
        Objects.equals(this.loggedInHeaderRegex, authConfig.loggedInHeaderRegex) &&
        Objects.equals(this.loggedInRegex, authConfig.loggedInRegex) &&
        Objects.equals(this.loginDepth, authConfig.loginDepth) &&
        Objects.equals(this.loginLinkRegex, authConfig.loginLinkRegex) &&
        Objects.equals(this.loginMaxLinks, authConfig.loginMaxLinks) &&
        Objects.equals(this.loginPrioritization, authConfig.loginPrioritization) &&
        Objects.equals(this.logoutDetection, authConfig.logoutDetection) &&
        Objects.equals(this.logoutDetectionFrequency, authConfig.logoutDetectionFrequency) &&
        Objects.equals(this.logoutLinkRegex, authConfig.logoutLinkRegex) &&
        Objects.equals(this.logoutPostBodyRegex, authConfig.logoutPostBodyRegex) &&
        Objects.equals(this.macroFile, authConfig.macroFile) &&
        Objects.equals(this.maxMacroReloginAttempts, authConfig.maxMacroReloginAttempts) &&
        Objects.equals(this.oauth, authConfig.oauth) &&
        Objects.equals(this.oauthConfig, authConfig.oauthConfig) &&
        Objects.equals(this.passwordForm, authConfig.passwordForm) &&
        Objects.equals(this.passwordHttp, authConfig.passwordHttp) &&
        Objects.equals(this.postSessionTokenRegex, authConfig.postSessionTokenRegex) &&
        Objects.equals(this.postponeLoginAction, authConfig.postponeLoginAction) &&
        Objects.equals(this.reloginAfterSessionLoss, authConfig.reloginAfterSessionLoss) &&
        Objects.equals(this.resetCookies, authConfig.resetCookies) &&
        Objects.equals(this.responseBodyTokenRegex, authConfig.responseBodyTokenRegex) &&
        Objects.equals(this.restartProxyBeforeRelogin, authConfig.restartProxyBeforeRelogin) &&
        Objects.equals(this.scopeConstraintList, authConfig.scopeConstraintList) &&
        Objects.equals(this.seedLink, authConfig.seedLink) &&
        Objects.equals(this.sessionCookieLifespan, authConfig.sessionCookieLifespan) &&
        Objects.equals(this.sessionCookieRegex, authConfig.sessionCookieRegex) &&
        Objects.equals(this.sessionLossHeaderRegex, authConfig.sessionLossHeaderRegex) &&
        Objects.equals(this.sessionLossRegex, authConfig.sessionLossRegex) &&
        Objects.equals(this.trafficFile, authConfig.trafficFile) &&
        Objects.equals(this.treatFailedReloginAsError, authConfig.treatFailedReloginAsError) &&
        Objects.equals(this.type, authConfig.type) &&
        Objects.equals(this.urlSessionTokenRegex, authConfig.urlSessionTokenRegex) &&
        Objects.equals(this.useBrowserFormLogin, authConfig.useBrowserFormLogin) &&
        Objects.equals(this.usernameForm, authConfig.usernameForm) &&
        Objects.equals(this.usernameHttp, authConfig.usernameHttp) &&
        Objects.equals(this.verifyNotLoggedin, authConfig.verifyNotLoggedin) &&
        Objects.equals(this.webServiceAuthConfig, authConfig.webServiceAuthConfig);
  }

  @Override
  public int hashCode() {
    return Objects.hash(assumeSuccessfulLogin, autoLogonSecurity, blacklistMultiPasswordForms, blacklistSinglePasswordForms, browserFormLoginConfig, createNonAuthenticatedSession, discoverLoginForm, discoveryDepth, discoveryMaxLinks, discoveryPrioritization, httpAuth, httpHeaderWithTokenReplacement, loggedInHeaderRegex, loggedInRegex, loginDepth, loginLinkRegex, loginMaxLinks, loginPrioritization, logoutDetection, logoutDetectionFrequency, logoutLinkRegex, logoutPostBodyRegex, macroFile, maxMacroReloginAttempts, oauth, oauthConfig, passwordForm, passwordHttp, postSessionTokenRegex, postponeLoginAction, reloginAfterSessionLoss, resetCookies, responseBodyTokenRegex, restartProxyBeforeRelogin, scopeConstraintList, seedLink, sessionCookieLifespan, sessionCookieRegex, sessionLossHeaderRegex, sessionLossRegex, trafficFile, treatFailedReloginAsError, type, urlSessionTokenRegex, useBrowserFormLogin, usernameForm, usernameHttp, verifyNotLoggedin, webServiceAuthConfig);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuthConfig {\n");

    sb.append("    assumeSuccessfulLogin: ").append(toIndentedString(assumeSuccessfulLogin)).append("\n");
    sb.append("    autoLogonSecurity: ").append(toIndentedString(autoLogonSecurity)).append("\n");
    sb.append("    blacklistMultiPasswordForms: ").append(toIndentedString(blacklistMultiPasswordForms)).append("\n");
    sb.append("    blacklistSinglePasswordForms: ").append(toIndentedString(blacklistSinglePasswordForms)).append("\n");
    sb.append("    browserFormLoginConfig: ").append(toIndentedString(browserFormLoginConfig)).append("\n");
    sb.append("    createNonAuthenticatedSession: ").append(toIndentedString(createNonAuthenticatedSession)).append("\n");
    sb.append("    discoverLoginForm: ").append(toIndentedString(discoverLoginForm)).append("\n");
    sb.append("    discoveryDepth: ").append(toIndentedString(discoveryDepth)).append("\n");
    sb.append("    discoveryMaxLinks: ").append(toIndentedString(discoveryMaxLinks)).append("\n");
    sb.append("    discoveryPrioritization: ").append(toIndentedString(discoveryPrioritization)).append("\n");
    sb.append("    httpAuth: ").append(toIndentedString(httpAuth)).append("\n");
    sb.append("    httpHeaderWithTokenReplacement: ").append(toIndentedString(httpHeaderWithTokenReplacement)).append("\n");
    sb.append("    loggedInHeaderRegex: ").append(toIndentedString(loggedInHeaderRegex)).append("\n");
    sb.append("    loggedInRegex: ").append(toIndentedString(loggedInRegex)).append("\n");
    sb.append("    loginDepth: ").append(toIndentedString(loginDepth)).append("\n");
    sb.append("    loginLinkRegex: ").append(toIndentedString(loginLinkRegex)).append("\n");
    sb.append("    loginMaxLinks: ").append(toIndentedString(loginMaxLinks)).append("\n");
    sb.append("    loginPrioritization: ").append(toIndentedString(loginPrioritization)).append("\n");
    sb.append("    logoutDetection: ").append(toIndentedString(logoutDetection)).append("\n");
    sb.append("    logoutDetectionFrequency: ").append(toIndentedString(logoutDetectionFrequency)).append("\n");
    sb.append("    logoutLinkRegex: ").append(toIndentedString(logoutLinkRegex)).append("\n");
    sb.append("    logoutPostBodyRegex: ").append(toIndentedString(logoutPostBodyRegex)).append("\n");
    sb.append("    macroFile: ").append(toIndentedString(macroFile)).append("\n");
    sb.append("    maxMacroReloginAttempts: ").append(toIndentedString(maxMacroReloginAttempts)).append("\n");
    sb.append("    oauth: ").append(toIndentedString(oauth)).append("\n");
    sb.append("    oauthConfig: ").append(toIndentedString(oauthConfig)).append("\n");
    sb.append("    passwordForm: ").append(toIndentedString(passwordForm)).append("\n");
    sb.append("    passwordHttp: ").append(toIndentedString(passwordHttp)).append("\n");
    sb.append("    postSessionTokenRegex: ").append(toIndentedString(postSessionTokenRegex)).append("\n");
    sb.append("    postponeLoginAction: ").append(toIndentedString(postponeLoginAction)).append("\n");
    sb.append("    reloginAfterSessionLoss: ").append(toIndentedString(reloginAfterSessionLoss)).append("\n");
    sb.append("    resetCookies: ").append(toIndentedString(resetCookies)).append("\n");
    sb.append("    responseBodyTokenRegex: ").append(toIndentedString(responseBodyTokenRegex)).append("\n");
    sb.append("    restartProxyBeforeRelogin: ").append(toIndentedString(restartProxyBeforeRelogin)).append("\n");
    sb.append("    scopeConstraintList: ").append(toIndentedString(scopeConstraintList)).append("\n");
    sb.append("    seedLink: ").append(toIndentedString(seedLink)).append("\n");
    sb.append("    sessionCookieLifespan: ").append(toIndentedString(sessionCookieLifespan)).append("\n");
    sb.append("    sessionCookieRegex: ").append(toIndentedString(sessionCookieRegex)).append("\n");
    sb.append("    sessionLossHeaderRegex: ").append(toIndentedString(sessionLossHeaderRegex)).append("\n");
    sb.append("    sessionLossRegex: ").append(toIndentedString(sessionLossRegex)).append("\n");
    sb.append("    trafficFile: ").append(toIndentedString(trafficFile)).append("\n");
    sb.append("    treatFailedReloginAsError: ").append(toIndentedString(treatFailedReloginAsError)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    urlSessionTokenRegex: ").append(toIndentedString(urlSessionTokenRegex)).append("\n");
    sb.append("    useBrowserFormLogin: ").append(toIndentedString(useBrowserFormLogin)).append("\n");
    sb.append("    usernameForm: ").append(toIndentedString(usernameForm)).append("\n");
    sb.append("    usernameHttp: ").append(toIndentedString(usernameHttp)).append("\n");
    sb.append("    verifyNotLoggedin: ").append(toIndentedString(verifyNotLoggedin)).append("\n");
    sb.append("    webServiceAuthConfig: ").append(toIndentedString(webServiceAuthConfig)).append("\n");
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

