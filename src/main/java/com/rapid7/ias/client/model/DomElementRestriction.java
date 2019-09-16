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
 * DomElementRestriction
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-11-27T23:32:49.618-05:00")
public class DomElementRestriction {
  @SerializedName("cardinality_max")
  private Long cardinalityMax = null;

  @SerializedName("cardinality_min")
  private Long cardinalityMin = null;

  @SerializedName("dom_element_path")
  private String domElementPath = null;

  /**
   * Gets or Sets domElementPathType
   */
  @JsonAdapter(DomElementPathTypeEnum.Adapter.class)
  public enum DomElementPathTypeEnum {
    XPATH("XPATH"),
    
    CSS_SELECTOR("CSS_SELECTOR");

    private String value;

    DomElementPathTypeEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static DomElementPathTypeEnum fromValue(String text) {
      for (DomElementPathTypeEnum b : DomElementPathTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<DomElementPathTypeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final DomElementPathTypeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public DomElementPathTypeEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return DomElementPathTypeEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("dom_element_path_type")
  private DomElementPathTypeEnum domElementPathType = null;

  @SerializedName("enabled")
  private Boolean enabled = null;

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

  @SerializedName("name")
  private String name = null;

  @SerializedName("recursive")
  private Boolean recursive = null;

  public DomElementRestriction cardinalityMax(Long cardinalityMax) {
    this.cardinalityMax = cardinalityMax;
    return this;
  }

   /**
   * Get cardinalityMax
   * @return cardinalityMax
  **/
  @ApiModelProperty(value = "")
  public Long getCardinalityMax() {
    return cardinalityMax;
  }

  public void setCardinalityMax(Long cardinalityMax) {
    this.cardinalityMax = cardinalityMax;
  }

  public DomElementRestriction cardinalityMin(Long cardinalityMin) {
    this.cardinalityMin = cardinalityMin;
    return this;
  }

   /**
   * Get cardinalityMin
   * @return cardinalityMin
  **/
  @ApiModelProperty(value = "")
  public Long getCardinalityMin() {
    return cardinalityMin;
  }

  public void setCardinalityMin(Long cardinalityMin) {
    this.cardinalityMin = cardinalityMin;
  }

  public DomElementRestriction domElementPath(String domElementPath) {
    this.domElementPath = domElementPath;
    return this;
  }

   /**
   * Get domElementPath
   * @return domElementPath
  **/
  @ApiModelProperty(value = "")
  public String getDomElementPath() {
    return domElementPath;
  }

  public void setDomElementPath(String domElementPath) {
    this.domElementPath = domElementPath;
  }

  public DomElementRestriction domElementPathType(DomElementPathTypeEnum domElementPathType) {
    this.domElementPathType = domElementPathType;
    return this;
  }

   /**
   * Get domElementPathType
   * @return domElementPathType
  **/
  @ApiModelProperty(value = "")
  public DomElementPathTypeEnum getDomElementPathType() {
    return domElementPathType;
  }

  public void setDomElementPathType(DomElementPathTypeEnum domElementPathType) {
    this.domElementPathType = domElementPathType;
  }

  public DomElementRestriction enabled(Boolean enabled) {
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

  public DomElementRestriction exclusion(ExclusionEnum exclusion) {
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

  public DomElementRestriction name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public DomElementRestriction recursive(Boolean recursive) {
    this.recursive = recursive;
    return this;
  }

   /**
   * Get recursive
   * @return recursive
  **/
  @ApiModelProperty(value = "")
  public Boolean isRecursive() {
    return recursive;
  }

  public void setRecursive(Boolean recursive) {
    this.recursive = recursive;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DomElementRestriction domElementRestriction = (DomElementRestriction) o;
    return Objects.equals(this.cardinalityMax, domElementRestriction.cardinalityMax) &&
        Objects.equals(this.cardinalityMin, domElementRestriction.cardinalityMin) &&
        Objects.equals(this.domElementPath, domElementRestriction.domElementPath) &&
        Objects.equals(this.domElementPathType, domElementRestriction.domElementPathType) &&
        Objects.equals(this.enabled, domElementRestriction.enabled) &&
        Objects.equals(this.exclusion, domElementRestriction.exclusion) &&
        Objects.equals(this.name, domElementRestriction.name) &&
        Objects.equals(this.recursive, domElementRestriction.recursive);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cardinalityMax, cardinalityMin, domElementPath, domElementPathType, enabled, exclusion, name, recursive);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DomElementRestriction {\n");

    sb.append("    cardinalityMax: ").append(toIndentedString(cardinalityMax)).append("\n");
    sb.append("    cardinalityMin: ").append(toIndentedString(cardinalityMin)).append("\n");
    sb.append("    domElementPath: ").append(toIndentedString(domElementPath)).append("\n");
    sb.append("    domElementPathType: ").append(toIndentedString(domElementPathType)).append("\n");
    sb.append("    enabled: ").append(toIndentedString(enabled)).append("\n");
    sb.append("    exclusion: ").append(toIndentedString(exclusion)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    recursive: ").append(toIndentedString(recursive)).append("\n");
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

