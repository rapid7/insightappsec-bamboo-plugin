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
 * ValidationErrors
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-11-27T23:32:49.618-05:00")
public class ValidationErrors {
  /**
   * A description of the type of error based on HTTP status code
   */
  @JsonAdapter(StatusEnum.Adapter.class)
  public enum StatusEnum {
    _100("100"),
    
    _101("101"),
    
    _102("102"),
    
    _103("103"),
    
    _200("200"),
    
    _201("201"),
    
    _202("202"),
    
    _203("203"),
    
    _204("204"),
    
    _205("205"),
    
    _206("206"),
    
    _207("207"),
    
    _208("208"),
    
    _226("226"),
    
    _300("300"),
    
    _301("301"),
    
    _302("302"),
    
    _303("303"),
    
    _304("304"),
    
    _305("305"),
    
    _307("307"),
    
    _308("308"),
    
    _400("400"),
    
    _401("401"),
    
    _402("402"),
    
    _403("403"),
    
    _404("404"),
    
    _405("405"),
    
    _406("406"),
    
    _407("407"),
    
    _408("408"),
    
    _409("409"),
    
    _410("410"),
    
    _411("411"),
    
    _412("412"),
    
    _413("413"),
    
    _414("414"),
    
    _415("415"),
    
    _416("416"),
    
    _417("417"),
    
    _418("418"),
    
    _419("419"),
    
    _420("420"),
    
    _421("421"),
    
    _422("422"),
    
    _423("423"),
    
    _424("424"),
    
    _426("426"),
    
    _428("428"),
    
    _429("429"),
    
    _431("431"),
    
    _451("451"),
    
    _500("500"),
    
    _501("501"),
    
    _502("502"),
    
    _503("503"),
    
    _504("504"),
    
    _505("505"),
    
    _506("506"),
    
    _507("507"),
    
    _508("508"),
    
    _509("509"),
    
    _510("510"),
    
    _511("511");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<StatusEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final StatusEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public StatusEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return StatusEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("status")
  private StatusEnum status = null;

  @SerializedName("message")
  private String message = null;

  /**
   * An optional code which may help support indicate the underlying cause of the error
   */
  @JsonAdapter(ErrorCodeEnum.Adapter.class)
  public enum ErrorCodeEnum {
    E1("E1"),
    
    E2("E2"),
    
    E3("E3"),
    
    E4("E4"),
    
    E5("E5"),
    
    E6("E6"),
    
    E7("E7"),
    
    E8("E8"),
    
    E9("E9"),
    
    E10("E10"),
    
    E11("E11"),
    
    E12("E12"),
    
    E13("E13"),
    
    E14("E14"),
    
    E15("E15"),
    
    E16("E16"),
    
    E17("E17"),
    
    E18("E18"),
    
    E19("E19"),
    
    E20("E20"),
    
    E22("E22"),
    
    E23("E23"),
    
    E24("E24"),
    
    E25("E25"),
    
    E26("E26"),
    
    E27("E27"),
    
    E28("E28"),
    
    E999("E999");

    private String value;

    ErrorCodeEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static ErrorCodeEnum fromValue(String text) {
      for (ErrorCodeEnum b : ErrorCodeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<ErrorCodeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final ErrorCodeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public ErrorCodeEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return ErrorCodeEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("error_code")
  private ErrorCodeEnum errorCode = null;

  @SerializedName("errors")
  private java.util.List<ValidationError> errors = null;

   /**
   * A description of the type of error based on HTTP status code
   * @return status
  **/
  @ApiModelProperty(value = "A description of the type of error based on HTTP status code")
  public StatusEnum getStatus() {
    return status;
  }

   /**
   * A description of the error
   * @return message
  **/
  @ApiModelProperty(value = "A description of the error")
  public String getMessage() {
    return message;
  }

   /**
   * An optional code which may help support indicate the underlying cause of the error
   * @return errorCode
  **/
  @ApiModelProperty(value = "An optional code which may help support indicate the underlying cause of the error")
  public ErrorCodeEnum getErrorCode() {
    return errorCode;
  }

  public ValidationErrors errors(java.util.List<ValidationError> errors) {
    this.errors = errors;
    return this;
  }

  public ValidationErrors addErrorsItem(ValidationError errorsItem) {
    if (this.errors == null) {
      this.errors = new java.util.ArrayList<ValidationError>();
    }
    this.errors.add(errorsItem);
    return this;
  }

   /**
   * A list of validation errors, when the HTTP status code is 422
   * @return errors
  **/
  @ApiModelProperty(value = "A list of validation errors, when the HTTP status code is 422")
  public java.util.List<ValidationError> getErrors() {
    return errors;
  }

  public void setErrors(java.util.List<ValidationError> errors) {
    this.errors = errors;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ValidationErrors validationErrors = (ValidationErrors) o;
    return Objects.equals(this.status, validationErrors.status) &&
        Objects.equals(this.message, validationErrors.message) &&
        Objects.equals(this.errorCode, validationErrors.errorCode) &&
        Objects.equals(this.errors, validationErrors.errors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, message, errorCode, errors);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ValidationErrors {\n");

    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    errorCode: ").append(toIndentedString(errorCode)).append("\n");
    sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
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

