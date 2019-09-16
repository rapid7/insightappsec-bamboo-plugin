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
 * NetworkSettingsConfig
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-11-27T23:32:49.618-05:00")
public class NetworkSettingsConfig {
  @SerializedName("assume_disconnected_timeout")
  private Long assumeDisconnectedTimeout = null;

  @SerializedName("close_connection")
  private Boolean closeConnection = null;

  @SerializedName("connect_timeout")
  private Long connectTimeout = null;

  @SerializedName("drip_delay_milli_seconds")
  private Long dripDelayMilliSeconds = null;

  @SerializedName("max_consecutive_failures")
  private Long maxConsecutiveFailures = null;

  @SerializedName("max_response_size")
  private Long maxResponseSize = null;

  @SerializedName("max_retries")
  private Long maxRetries = null;

  @SerializedName("read_timeout")
  private Long readTimeout = null;

  @SerializedName("resolve_timeout")
  private Long resolveTimeout = null;

  /**
   * Gets or Sets secureProtocols
   */
  @JsonAdapter(SecureProtocolsEnum.Adapter.class)
  public enum SecureProtocolsEnum {
    SSL2("SSL2"),
    
    SSL3("SSL3"),
    
    TLS1("TLS1"),
    
    TLS11("TLS11"),
    
    TLS12("TLS12");

    private String value;

    SecureProtocolsEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static SecureProtocolsEnum fromValue(String text) {
      for (SecureProtocolsEnum b : SecureProtocolsEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<SecureProtocolsEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final SecureProtocolsEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public SecureProtocolsEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return SecureProtocolsEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("secure_protocols")
  private java.util.List<SecureProtocolsEnum> secureProtocols = null;

  @SerializedName("write_timeout")
  private Long writeTimeout = null;

  public NetworkSettingsConfig assumeDisconnectedTimeout(Long assumeDisconnectedTimeout) {
    this.assumeDisconnectedTimeout = assumeDisconnectedTimeout;
    return this;
  }

   /**
   * Get assumeDisconnectedTimeout
   * @return assumeDisconnectedTimeout
  **/
  @ApiModelProperty(value = "")
  public Long getAssumeDisconnectedTimeout() {
    return assumeDisconnectedTimeout;
  }

  public void setAssumeDisconnectedTimeout(Long assumeDisconnectedTimeout) {
    this.assumeDisconnectedTimeout = assumeDisconnectedTimeout;
  }

  public NetworkSettingsConfig closeConnection(Boolean closeConnection) {
    this.closeConnection = closeConnection;
    return this;
  }

   /**
   * Get closeConnection
   * @return closeConnection
  **/
  @ApiModelProperty(value = "")
  public Boolean isCloseConnection() {
    return closeConnection;
  }

  public void setCloseConnection(Boolean closeConnection) {
    this.closeConnection = closeConnection;
  }

  public NetworkSettingsConfig connectTimeout(Long connectTimeout) {
    this.connectTimeout = connectTimeout;
    return this;
  }

   /**
   * Get connectTimeout
   * @return connectTimeout
  **/
  @ApiModelProperty(value = "")
  public Long getConnectTimeout() {
    return connectTimeout;
  }

  public void setConnectTimeout(Long connectTimeout) {
    this.connectTimeout = connectTimeout;
  }

  public NetworkSettingsConfig dripDelayMilliSeconds(Long dripDelayMilliSeconds) {
    this.dripDelayMilliSeconds = dripDelayMilliSeconds;
    return this;
  }

   /**
   * Get dripDelayMilliSeconds
   * @return dripDelayMilliSeconds
  **/
  @ApiModelProperty(value = "")
  public Long getDripDelayMilliSeconds() {
    return dripDelayMilliSeconds;
  }

  public void setDripDelayMilliSeconds(Long dripDelayMilliSeconds) {
    this.dripDelayMilliSeconds = dripDelayMilliSeconds;
  }

  public NetworkSettingsConfig maxConsecutiveFailures(Long maxConsecutiveFailures) {
    this.maxConsecutiveFailures = maxConsecutiveFailures;
    return this;
  }

   /**
   * Get maxConsecutiveFailures
   * @return maxConsecutiveFailures
  **/
  @ApiModelProperty(value = "")
  public Long getMaxConsecutiveFailures() {
    return maxConsecutiveFailures;
  }

  public void setMaxConsecutiveFailures(Long maxConsecutiveFailures) {
    this.maxConsecutiveFailures = maxConsecutiveFailures;
  }

  public NetworkSettingsConfig maxResponseSize(Long maxResponseSize) {
    this.maxResponseSize = maxResponseSize;
    return this;
  }

   /**
   * Get maxResponseSize
   * @return maxResponseSize
  **/
  @ApiModelProperty(value = "")
  public Long getMaxResponseSize() {
    return maxResponseSize;
  }

  public void setMaxResponseSize(Long maxResponseSize) {
    this.maxResponseSize = maxResponseSize;
  }

  public NetworkSettingsConfig maxRetries(Long maxRetries) {
    this.maxRetries = maxRetries;
    return this;
  }

   /**
   * Get maxRetries
   * @return maxRetries
  **/
  @ApiModelProperty(value = "")
  public Long getMaxRetries() {
    return maxRetries;
  }

  public void setMaxRetries(Long maxRetries) {
    this.maxRetries = maxRetries;
  }

  public NetworkSettingsConfig readTimeout(Long readTimeout) {
    this.readTimeout = readTimeout;
    return this;
  }

   /**
   * Get readTimeout
   * @return readTimeout
  **/
  @ApiModelProperty(value = "")
  public Long getReadTimeout() {
    return readTimeout;
  }

  public void setReadTimeout(Long readTimeout) {
    this.readTimeout = readTimeout;
  }

  public NetworkSettingsConfig resolveTimeout(Long resolveTimeout) {
    this.resolveTimeout = resolveTimeout;
    return this;
  }

   /**
   * Get resolveTimeout
   * @return resolveTimeout
  **/
  @ApiModelProperty(value = "")
  public Long getResolveTimeout() {
    return resolveTimeout;
  }

  public void setResolveTimeout(Long resolveTimeout) {
    this.resolveTimeout = resolveTimeout;
  }

  public NetworkSettingsConfig secureProtocols(java.util.List<SecureProtocolsEnum> secureProtocols) {
    this.secureProtocols = secureProtocols;
    return this;
  }

  public NetworkSettingsConfig addSecureProtocolsItem(SecureProtocolsEnum secureProtocolsItem) {
    if (this.secureProtocols == null) {
      this.secureProtocols = new java.util.ArrayList<SecureProtocolsEnum>();
    }
    this.secureProtocols.add(secureProtocolsItem);
    return this;
  }

   /**
   * Get secureProtocols
   * @return secureProtocols
  **/
  @ApiModelProperty(value = "")
  public java.util.List<SecureProtocolsEnum> getSecureProtocols() {
    return secureProtocols;
  }

  public void setSecureProtocols(java.util.List<SecureProtocolsEnum> secureProtocols) {
    this.secureProtocols = secureProtocols;
  }

  public NetworkSettingsConfig writeTimeout(Long writeTimeout) {
    this.writeTimeout = writeTimeout;
    return this;
  }

   /**
   * Get writeTimeout
   * @return writeTimeout
  **/
  @ApiModelProperty(value = "")
  public Long getWriteTimeout() {
    return writeTimeout;
  }

  public void setWriteTimeout(Long writeTimeout) {
    this.writeTimeout = writeTimeout;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NetworkSettingsConfig networkSettingsConfig = (NetworkSettingsConfig) o;
    return Objects.equals(this.assumeDisconnectedTimeout, networkSettingsConfig.assumeDisconnectedTimeout) &&
        Objects.equals(this.closeConnection, networkSettingsConfig.closeConnection) &&
        Objects.equals(this.connectTimeout, networkSettingsConfig.connectTimeout) &&
        Objects.equals(this.dripDelayMilliSeconds, networkSettingsConfig.dripDelayMilliSeconds) &&
        Objects.equals(this.maxConsecutiveFailures, networkSettingsConfig.maxConsecutiveFailures) &&
        Objects.equals(this.maxResponseSize, networkSettingsConfig.maxResponseSize) &&
        Objects.equals(this.maxRetries, networkSettingsConfig.maxRetries) &&
        Objects.equals(this.readTimeout, networkSettingsConfig.readTimeout) &&
        Objects.equals(this.resolveTimeout, networkSettingsConfig.resolveTimeout) &&
        Objects.equals(this.secureProtocols, networkSettingsConfig.secureProtocols) &&
        Objects.equals(this.writeTimeout, networkSettingsConfig.writeTimeout);
  }

  @Override
  public int hashCode() {
    return Objects.hash(assumeDisconnectedTimeout, closeConnection, connectTimeout, dripDelayMilliSeconds, maxConsecutiveFailures, maxResponseSize, maxRetries, readTimeout, resolveTimeout, secureProtocols, writeTimeout);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NetworkSettingsConfig {\n");

    sb.append("    assumeDisconnectedTimeout: ").append(toIndentedString(assumeDisconnectedTimeout)).append("\n");
    sb.append("    closeConnection: ").append(toIndentedString(closeConnection)).append("\n");
    sb.append("    connectTimeout: ").append(toIndentedString(connectTimeout)).append("\n");
    sb.append("    dripDelayMilliSeconds: ").append(toIndentedString(dripDelayMilliSeconds)).append("\n");
    sb.append("    maxConsecutiveFailures: ").append(toIndentedString(maxConsecutiveFailures)).append("\n");
    sb.append("    maxResponseSize: ").append(toIndentedString(maxResponseSize)).append("\n");
    sb.append("    maxRetries: ").append(toIndentedString(maxRetries)).append("\n");
    sb.append("    readTimeout: ").append(toIndentedString(readTimeout)).append("\n");
    sb.append("    resolveTimeout: ").append(toIndentedString(resolveTimeout)).append("\n");
    sb.append("    secureProtocols: ").append(toIndentedString(secureProtocols)).append("\n");
    sb.append("    writeTimeout: ").append(toIndentedString(writeTimeout)).append("\n");
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

