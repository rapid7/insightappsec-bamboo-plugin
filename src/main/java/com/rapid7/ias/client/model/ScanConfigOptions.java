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
 * ScanConfigOptions
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-11-27T23:32:49.618-05:00")
public class ScanConfigOptions {
  @SerializedName("attacker_config")
  private AttackerConfig attackerConfig = null;

  @SerializedName("auth_config")
  private AuthConfig authConfig = null;

  @SerializedName("auto_sequence_config")
  private AutoSequenceConfig autoSequenceConfig = null;

  @SerializedName("crawl_config")
  private CrawlConfig crawlConfig = null;

  @SerializedName("http_headers_config")
  private HttpHeadersConfig httpHeadersConfig = null;

  @SerializedName("include_traffic")
  private Boolean includeTraffic = null;

  /**
   * Gets or Sets javaScriptEngine
   */
  @JsonAdapter(JavaScriptEngineEnum.Adapter.class)
  public enum JavaScriptEngineEnum {
    DEFAULT("DEFAULT"),
    
    INTERNET_EXPLORER("INTERNET_EXPLORER"),
    
    CHROME("CHROME");

    private String value;

    JavaScriptEngineEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static JavaScriptEngineEnum fromValue(String text) {
      for (JavaScriptEngineEnum b : JavaScriptEngineEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<JavaScriptEngineEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final JavaScriptEngineEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public JavaScriptEngineEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return JavaScriptEngineEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("java_script_engine")
  private JavaScriptEngineEnum javaScriptEngine = null;

  @SerializedName("macro_config")
  private MacroConfig macroConfig = null;

  @SerializedName("manual_crawling_config")
  private ManualCrawlingConfig manualCrawlingConfig = null;

  @SerializedName("network_settings_config")
  private NetworkSettingsConfig networkSettingsConfig = null;

  @SerializedName("one_time_token_config")
  private OneTimeTokenConfig oneTimeTokenConfig = null;

  @SerializedName("parameter_parser_config")
  private ParameterParserConfig parameterParserConfig = null;

  @SerializedName("parameter_training_config")
  private ParameterTrainingConfig parameterTrainingConfig = null;

  @SerializedName("parameter_value_config")
  private ParameterValueConfig parameterValueConfig = null;

  @SerializedName("performance_config")
  private PerformanceConfig performanceConfig = null;

  @SerializedName("proxy_config")
  private ProxyConfig proxyConfig = null;

  @SerializedName("site_technology_config")
  private SiteTechnologyConfig siteTechnologyConfig = null;

  @SerializedName("web_service_config")
  private WebServiceConfig webServiceConfig = null;

  public ScanConfigOptions attackerConfig(AttackerConfig attackerConfig) {
    this.attackerConfig = attackerConfig;
    return this;
  }

   /**
   * Get attackerConfig
   * @return attackerConfig
  **/
  @ApiModelProperty(value = "")
  public AttackerConfig getAttackerConfig() {
    return attackerConfig;
  }

  public void setAttackerConfig(AttackerConfig attackerConfig) {
    this.attackerConfig = attackerConfig;
  }

  public ScanConfigOptions authConfig(AuthConfig authConfig) {
    this.authConfig = authConfig;
    return this;
  }

   /**
   * Get authConfig
   * @return authConfig
  **/
  @ApiModelProperty(value = "")
  public AuthConfig getAuthConfig() {
    return authConfig;
  }

  public void setAuthConfig(AuthConfig authConfig) {
    this.authConfig = authConfig;
  }

  public ScanConfigOptions autoSequenceConfig(AutoSequenceConfig autoSequenceConfig) {
    this.autoSequenceConfig = autoSequenceConfig;
    return this;
  }

   /**
   * Get autoSequenceConfig
   * @return autoSequenceConfig
  **/
  @ApiModelProperty(value = "")
  public AutoSequenceConfig getAutoSequenceConfig() {
    return autoSequenceConfig;
  }

  public void setAutoSequenceConfig(AutoSequenceConfig autoSequenceConfig) {
    this.autoSequenceConfig = autoSequenceConfig;
  }

  public ScanConfigOptions crawlConfig(CrawlConfig crawlConfig) {
    this.crawlConfig = crawlConfig;
    return this;
  }

   /**
   * Get crawlConfig
   * @return crawlConfig
  **/
  @ApiModelProperty(value = "")
  public CrawlConfig getCrawlConfig() {
    return crawlConfig;
  }

  public void setCrawlConfig(CrawlConfig crawlConfig) {
    this.crawlConfig = crawlConfig;
  }

  public ScanConfigOptions httpHeadersConfig(HttpHeadersConfig httpHeadersConfig) {
    this.httpHeadersConfig = httpHeadersConfig;
    return this;
  }

   /**
   * Get httpHeadersConfig
   * @return httpHeadersConfig
  **/
  @ApiModelProperty(value = "")
  public HttpHeadersConfig getHttpHeadersConfig() {
    return httpHeadersConfig;
  }

  public void setHttpHeadersConfig(HttpHeadersConfig httpHeadersConfig) {
    this.httpHeadersConfig = httpHeadersConfig;
  }

  public ScanConfigOptions includeTraffic(Boolean includeTraffic) {
    this.includeTraffic = includeTraffic;
    return this;
  }

   /**
   * Get includeTraffic
   * @return includeTraffic
  **/
  @ApiModelProperty(value = "")
  public Boolean isIncludeTraffic() {
    return includeTraffic;
  }

  public void setIncludeTraffic(Boolean includeTraffic) {
    this.includeTraffic = includeTraffic;
  }

  public ScanConfigOptions javaScriptEngine(JavaScriptEngineEnum javaScriptEngine) {
    this.javaScriptEngine = javaScriptEngine;
    return this;
  }

   /**
   * Get javaScriptEngine
   * @return javaScriptEngine
  **/
  @ApiModelProperty(value = "")
  public JavaScriptEngineEnum getJavaScriptEngine() {
    return javaScriptEngine;
  }

  public void setJavaScriptEngine(JavaScriptEngineEnum javaScriptEngine) {
    this.javaScriptEngine = javaScriptEngine;
  }

  public ScanConfigOptions macroConfig(MacroConfig macroConfig) {
    this.macroConfig = macroConfig;
    return this;
  }

   /**
   * Get macroConfig
   * @return macroConfig
  **/
  @ApiModelProperty(value = "")
  public MacroConfig getMacroConfig() {
    return macroConfig;
  }

  public void setMacroConfig(MacroConfig macroConfig) {
    this.macroConfig = macroConfig;
  }

  public ScanConfigOptions manualCrawlingConfig(ManualCrawlingConfig manualCrawlingConfig) {
    this.manualCrawlingConfig = manualCrawlingConfig;
    return this;
  }

   /**
   * Get manualCrawlingConfig
   * @return manualCrawlingConfig
  **/
  @ApiModelProperty(value = "")
  public ManualCrawlingConfig getManualCrawlingConfig() {
    return manualCrawlingConfig;
  }

  public void setManualCrawlingConfig(ManualCrawlingConfig manualCrawlingConfig) {
    this.manualCrawlingConfig = manualCrawlingConfig;
  }

  public ScanConfigOptions networkSettingsConfig(NetworkSettingsConfig networkSettingsConfig) {
    this.networkSettingsConfig = networkSettingsConfig;
    return this;
  }

   /**
   * Get networkSettingsConfig
   * @return networkSettingsConfig
  **/
  @ApiModelProperty(value = "")
  public NetworkSettingsConfig getNetworkSettingsConfig() {
    return networkSettingsConfig;
  }

  public void setNetworkSettingsConfig(NetworkSettingsConfig networkSettingsConfig) {
    this.networkSettingsConfig = networkSettingsConfig;
  }

  public ScanConfigOptions oneTimeTokenConfig(OneTimeTokenConfig oneTimeTokenConfig) {
    this.oneTimeTokenConfig = oneTimeTokenConfig;
    return this;
  }

   /**
   * Get oneTimeTokenConfig
   * @return oneTimeTokenConfig
  **/
  @ApiModelProperty(value = "")
  public OneTimeTokenConfig getOneTimeTokenConfig() {
    return oneTimeTokenConfig;
  }

  public void setOneTimeTokenConfig(OneTimeTokenConfig oneTimeTokenConfig) {
    this.oneTimeTokenConfig = oneTimeTokenConfig;
  }

  public ScanConfigOptions parameterParserConfig(ParameterParserConfig parameterParserConfig) {
    this.parameterParserConfig = parameterParserConfig;
    return this;
  }

   /**
   * Get parameterParserConfig
   * @return parameterParserConfig
  **/
  @ApiModelProperty(value = "")
  public ParameterParserConfig getParameterParserConfig() {
    return parameterParserConfig;
  }

  public void setParameterParserConfig(ParameterParserConfig parameterParserConfig) {
    this.parameterParserConfig = parameterParserConfig;
  }

  public ScanConfigOptions parameterTrainingConfig(ParameterTrainingConfig parameterTrainingConfig) {
    this.parameterTrainingConfig = parameterTrainingConfig;
    return this;
  }

   /**
   * Get parameterTrainingConfig
   * @return parameterTrainingConfig
  **/
  @ApiModelProperty(value = "")
  public ParameterTrainingConfig getParameterTrainingConfig() {
    return parameterTrainingConfig;
  }

  public void setParameterTrainingConfig(ParameterTrainingConfig parameterTrainingConfig) {
    this.parameterTrainingConfig = parameterTrainingConfig;
  }

  public ScanConfigOptions parameterValueConfig(ParameterValueConfig parameterValueConfig) {
    this.parameterValueConfig = parameterValueConfig;
    return this;
  }

   /**
   * Get parameterValueConfig
   * @return parameterValueConfig
  **/
  @ApiModelProperty(value = "")
  public ParameterValueConfig getParameterValueConfig() {
    return parameterValueConfig;
  }

  public void setParameterValueConfig(ParameterValueConfig parameterValueConfig) {
    this.parameterValueConfig = parameterValueConfig;
  }

  public ScanConfigOptions performanceConfig(PerformanceConfig performanceConfig) {
    this.performanceConfig = performanceConfig;
    return this;
  }

   /**
   * Get performanceConfig
   * @return performanceConfig
  **/
  @ApiModelProperty(value = "")
  public PerformanceConfig getPerformanceConfig() {
    return performanceConfig;
  }

  public void setPerformanceConfig(PerformanceConfig performanceConfig) {
    this.performanceConfig = performanceConfig;
  }

  public ScanConfigOptions proxyConfig(ProxyConfig proxyConfig) {
    this.proxyConfig = proxyConfig;
    return this;
  }

   /**
   * Get proxyConfig
   * @return proxyConfig
  **/
  @ApiModelProperty(value = "")
  public ProxyConfig getProxyConfig() {
    return proxyConfig;
  }

  public void setProxyConfig(ProxyConfig proxyConfig) {
    this.proxyConfig = proxyConfig;
  }

  public ScanConfigOptions siteTechnologyConfig(SiteTechnologyConfig siteTechnologyConfig) {
    this.siteTechnologyConfig = siteTechnologyConfig;
    return this;
  }

   /**
   * Get siteTechnologyConfig
   * @return siteTechnologyConfig
  **/
  @ApiModelProperty(value = "")
  public SiteTechnologyConfig getSiteTechnologyConfig() {
    return siteTechnologyConfig;
  }

  public void setSiteTechnologyConfig(SiteTechnologyConfig siteTechnologyConfig) {
    this.siteTechnologyConfig = siteTechnologyConfig;
  }

  public ScanConfigOptions webServiceConfig(WebServiceConfig webServiceConfig) {
    this.webServiceConfig = webServiceConfig;
    return this;
  }

   /**
   * Get webServiceConfig
   * @return webServiceConfig
  **/
  @ApiModelProperty(value = "")
  public WebServiceConfig getWebServiceConfig() {
    return webServiceConfig;
  }

  public void setWebServiceConfig(WebServiceConfig webServiceConfig) {
    this.webServiceConfig = webServiceConfig;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ScanConfigOptions scanConfigOptions = (ScanConfigOptions) o;
    return Objects.equals(this.attackerConfig, scanConfigOptions.attackerConfig) &&
        Objects.equals(this.authConfig, scanConfigOptions.authConfig) &&
        Objects.equals(this.autoSequenceConfig, scanConfigOptions.autoSequenceConfig) &&
        Objects.equals(this.crawlConfig, scanConfigOptions.crawlConfig) &&
        Objects.equals(this.httpHeadersConfig, scanConfigOptions.httpHeadersConfig) &&
        Objects.equals(this.includeTraffic, scanConfigOptions.includeTraffic) &&
        Objects.equals(this.javaScriptEngine, scanConfigOptions.javaScriptEngine) &&
        Objects.equals(this.macroConfig, scanConfigOptions.macroConfig) &&
        Objects.equals(this.manualCrawlingConfig, scanConfigOptions.manualCrawlingConfig) &&
        Objects.equals(this.networkSettingsConfig, scanConfigOptions.networkSettingsConfig) &&
        Objects.equals(this.oneTimeTokenConfig, scanConfigOptions.oneTimeTokenConfig) &&
        Objects.equals(this.parameterParserConfig, scanConfigOptions.parameterParserConfig) &&
        Objects.equals(this.parameterTrainingConfig, scanConfigOptions.parameterTrainingConfig) &&
        Objects.equals(this.parameterValueConfig, scanConfigOptions.parameterValueConfig) &&
        Objects.equals(this.performanceConfig, scanConfigOptions.performanceConfig) &&
        Objects.equals(this.proxyConfig, scanConfigOptions.proxyConfig) &&
        Objects.equals(this.siteTechnologyConfig, scanConfigOptions.siteTechnologyConfig) &&
        Objects.equals(this.webServiceConfig, scanConfigOptions.webServiceConfig);
  }

  @Override
  public int hashCode() {
    return Objects.hash(attackerConfig, authConfig, autoSequenceConfig, crawlConfig, httpHeadersConfig, includeTraffic, javaScriptEngine, macroConfig, manualCrawlingConfig, networkSettingsConfig, oneTimeTokenConfig, parameterParserConfig, parameterTrainingConfig, parameterValueConfig, performanceConfig, proxyConfig, siteTechnologyConfig, webServiceConfig);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScanConfigOptions {\n");

    sb.append("    attackerConfig: ").append(toIndentedString(attackerConfig)).append("\n");
    sb.append("    authConfig: ").append(toIndentedString(authConfig)).append("\n");
    sb.append("    autoSequenceConfig: ").append(toIndentedString(autoSequenceConfig)).append("\n");
    sb.append("    crawlConfig: ").append(toIndentedString(crawlConfig)).append("\n");
    sb.append("    httpHeadersConfig: ").append(toIndentedString(httpHeadersConfig)).append("\n");
    sb.append("    includeTraffic: ").append(toIndentedString(includeTraffic)).append("\n");
    sb.append("    javaScriptEngine: ").append(toIndentedString(javaScriptEngine)).append("\n");
    sb.append("    macroConfig: ").append(toIndentedString(macroConfig)).append("\n");
    sb.append("    manualCrawlingConfig: ").append(toIndentedString(manualCrawlingConfig)).append("\n");
    sb.append("    networkSettingsConfig: ").append(toIndentedString(networkSettingsConfig)).append("\n");
    sb.append("    oneTimeTokenConfig: ").append(toIndentedString(oneTimeTokenConfig)).append("\n");
    sb.append("    parameterParserConfig: ").append(toIndentedString(parameterParserConfig)).append("\n");
    sb.append("    parameterTrainingConfig: ").append(toIndentedString(parameterTrainingConfig)).append("\n");
    sb.append("    parameterValueConfig: ").append(toIndentedString(parameterValueConfig)).append("\n");
    sb.append("    performanceConfig: ").append(toIndentedString(performanceConfig)).append("\n");
    sb.append("    proxyConfig: ").append(toIndentedString(proxyConfig)).append("\n");
    sb.append("    siteTechnologyConfig: ").append(toIndentedString(siteTechnologyConfig)).append("\n");
    sb.append("    webServiceConfig: ").append(toIndentedString(webServiceConfig)).append("\n");
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

