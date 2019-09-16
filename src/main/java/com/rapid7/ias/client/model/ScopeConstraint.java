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
 * ScopeConstraint
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-11-27T23:32:49.618-05:00")
public class ScopeConstraint {
  /**
   * Gets or Sets exclusion
   */
  @JsonAdapter(ExclusionEnum.Adapter.class)
  public enum ExclusionEnum {
    INCLUDE("INCLUDE"),
    
    EXCLUDE("EXCLUDE");

    private String value;

    ExclusionEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static ExclusionEnum fromValue(String text) {
      for (ExclusionEnum b : ExclusionEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<ExclusionEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final ExclusionEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public ExclusionEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return ExclusionEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("exclusion")
  private ExclusionEnum exclusion = null;

  @SerializedName("http_parameter_list")
  private java.util.List<HttpParameterList> httpParameterList = null;

  /**
   * Gets or Sets matchCriteria
   */
  @JsonAdapter(MatchCriteriaEnum.Adapter.class)
  public enum MatchCriteriaEnum {
    LITERAL("LITERAL"),
    
    WILDCARD("WILDCARD"),
    
    REGEX("REGEX");

    private String value;

    MatchCriteriaEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static MatchCriteriaEnum fromValue(String text) {
      for (MatchCriteriaEnum b : MatchCriteriaEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<MatchCriteriaEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final MatchCriteriaEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public MatchCriteriaEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return MatchCriteriaEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("match_criteria")
  private MatchCriteriaEnum matchCriteria = null;

  /**
   * Gets or Sets method
   */
  @JsonAdapter(MethodEnum.Adapter.class)
  public enum MethodEnum {
    ALL("ALL"),
    
    GET("GET"),
    
    POST("POST"),
    
    HEAD("HEAD"),
    
    PUT("PUT"),
    
    PATCH("PATCH"),
    
    DELETE("DELETE"),
    
    TRACE("TRACE"),
    
    CONNECT("CONNECT"),
    
    OPTIONS("OPTIONS");

    private String value;

    MethodEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static MethodEnum fromValue(String text) {
      for (MethodEnum b : MethodEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<MethodEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final MethodEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public MethodEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return MethodEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("method")
  private MethodEnum method = null;

  @SerializedName("url")
  private String url = null;

  public ScopeConstraint exclusion(ExclusionEnum exclusion) {
    this.exclusion = exclusion;
    return this;
  }

   /**
   * Get exclusion
   * @return exclusion
  **/
  @ApiModelProperty(value = "")
  public ExclusionEnum getExclusion() {
    return exclusion;
  }

  public void setExclusion(ExclusionEnum exclusion) {
    this.exclusion = exclusion;
  }

  public ScopeConstraint httpParameterList(java.util.List<HttpParameterList> httpParameterList) {
    this.httpParameterList = httpParameterList;
    return this;
  }

  public ScopeConstraint addHttpParameterListItem(HttpParameterList httpParameterListItem) {
    if (this.httpParameterList == null) {
      this.httpParameterList = new java.util.ArrayList<HttpParameterList>();
    }
    this.httpParameterList.add(httpParameterListItem);
    return this;
  }

   /**
   * Get httpParameterList
   * @return httpParameterList
  **/
  @ApiModelProperty(value = "")
  public java.util.List<HttpParameterList> getHttpParameterList() {
    return httpParameterList;
  }

  public void setHttpParameterList(java.util.List<HttpParameterList> httpParameterList) {
    this.httpParameterList = httpParameterList;
  }

  public ScopeConstraint matchCriteria(MatchCriteriaEnum matchCriteria) {
    this.matchCriteria = matchCriteria;
    return this;
  }

   /**
   * Get matchCriteria
   * @return matchCriteria
  **/
  @ApiModelProperty(value = "")
  public MatchCriteriaEnum getMatchCriteria() {
    return matchCriteria;
  }

  public void setMatchCriteria(MatchCriteriaEnum matchCriteria) {
    this.matchCriteria = matchCriteria;
  }

  public ScopeConstraint method(MethodEnum method) {
    this.method = method;
    return this;
  }

   /**
   * Get method
   * @return method
  **/
  @ApiModelProperty(value = "")
  public MethodEnum getMethod() {
    return method;
  }

  public void setMethod(MethodEnum method) {
    this.method = method;
  }

  public ScopeConstraint url(String url) {
    this.url = url;
    return this;
  }

   /**
   * Get url
   * @return url
  **/
  @ApiModelProperty(value = "")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ScopeConstraint scopeConstraint = (ScopeConstraint) o;
    return Objects.equals(this.exclusion, scopeConstraint.exclusion) &&
        Objects.equals(this.httpParameterList, scopeConstraint.httpParameterList) &&
        Objects.equals(this.matchCriteria, scopeConstraint.matchCriteria) &&
        Objects.equals(this.method, scopeConstraint.method) &&
        Objects.equals(this.url, scopeConstraint.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(exclusion, httpParameterList, matchCriteria, method, url);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScopeConstraint {\n");

    sb.append("    exclusion: ").append(toIndentedString(exclusion)).append("\n");
    sb.append("    httpParameterList: ").append(toIndentedString(httpParameterList)).append("\n");
    sb.append("    matchCriteria: ").append(toIndentedString(matchCriteria)).append("\n");
    sb.append("    method: ").append(toIndentedString(method)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
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

