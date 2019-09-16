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
 * AttackerConfig
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-11-27T23:32:49.618-05:00")
public class AttackerConfig {
  @SerializedName("apply_crawler_constraints")
  private Boolean applyCrawlerConstraints = null;

  @SerializedName("default_do_not_attack_param_list")
  private java.util.List<DefaultDoNotAttackParam> defaultDoNotAttackParamList = null;

  @SerializedName("exclude_low_confidence_findings")
  private Boolean excludeLowConfidenceFindings = null;

  @SerializedName("links_to_attack_before_limiting_attacks")
  private Long linksToAttackBeforeLimitingAttacks = null;

  @SerializedName("max_normalized_same_name_parameter_attack_points_per_link")
  private Long maxNormalizedSameNameParameterAttackPointsPerLink = null;

  @SerializedName("max_number_of_scheduled_passive_attacks")
  private Long maxNumberOfScheduledPassiveAttacks = null;

  @SerializedName("max_parameter_attack_points_per_link")
  private Long maxParameterAttackPointsPerLink = null;

  @SerializedName("max_same_cookie_parameter_attack_points")
  private Long maxSameCookieParameterAttackPoints = null;

  @SerializedName("max_same_name_parameter_attack_points")
  private Long maxSameNameParameterAttackPoints = null;

  @SerializedName("max_same_name_parameter_attack_points_per_link")
  private Long maxSameNameParameterAttackPointsPerLink = null;

  @SerializedName("min_cookie_lifetime_for_attacks")
  private Long minCookieLifetimeForAttacks = null;

  @SerializedName("parameters_to_attack_before_limiting_attacks")
  private Long parametersToAttackBeforeLimitingAttacks = null;

  @SerializedName("scope_constraint_list")
  private java.util.List<ScopeConstraint> scopeConstraintList = null;

  @SerializedName("user_do_not_attack_param_list")
  private java.util.List<UserDoNotAttackParam> userDoNotAttackParamList = null;

  public AttackerConfig applyCrawlerConstraints(Boolean applyCrawlerConstraints) {
    this.applyCrawlerConstraints = applyCrawlerConstraints;
    return this;
  }

   /**
   * Get applyCrawlerConstraints
   * @return applyCrawlerConstraints
  **/
  @ApiModelProperty(value = "")
  public Boolean isApplyCrawlerConstraints() {
    return applyCrawlerConstraints;
  }

  public void setApplyCrawlerConstraints(Boolean applyCrawlerConstraints) {
    this.applyCrawlerConstraints = applyCrawlerConstraints;
  }

  public AttackerConfig defaultDoNotAttackParamList(java.util.List<DefaultDoNotAttackParam> defaultDoNotAttackParamList) {
    this.defaultDoNotAttackParamList = defaultDoNotAttackParamList;
    return this;
  }

  public AttackerConfig addDefaultDoNotAttackParamListItem(DefaultDoNotAttackParam defaultDoNotAttackParamListItem) {
    if (this.defaultDoNotAttackParamList == null) {
      this.defaultDoNotAttackParamList = new java.util.ArrayList<DefaultDoNotAttackParam>();
    }
    this.defaultDoNotAttackParamList.add(defaultDoNotAttackParamListItem);
    return this;
  }

   /**
   * Get defaultDoNotAttackParamList
   * @return defaultDoNotAttackParamList
  **/
  @ApiModelProperty(value = "")
  public java.util.List<DefaultDoNotAttackParam> getDefaultDoNotAttackParamList() {
    return defaultDoNotAttackParamList;
  }

  public void setDefaultDoNotAttackParamList(java.util.List<DefaultDoNotAttackParam> defaultDoNotAttackParamList) {
    this.defaultDoNotAttackParamList = defaultDoNotAttackParamList;
  }

  public AttackerConfig excludeLowConfidenceFindings(Boolean excludeLowConfidenceFindings) {
    this.excludeLowConfidenceFindings = excludeLowConfidenceFindings;
    return this;
  }

   /**
   * Get excludeLowConfidenceFindings
   * @return excludeLowConfidenceFindings
  **/
  @ApiModelProperty(value = "")
  public Boolean isExcludeLowConfidenceFindings() {
    return excludeLowConfidenceFindings;
  }

  public void setExcludeLowConfidenceFindings(Boolean excludeLowConfidenceFindings) {
    this.excludeLowConfidenceFindings = excludeLowConfidenceFindings;
  }

  public AttackerConfig linksToAttackBeforeLimitingAttacks(Long linksToAttackBeforeLimitingAttacks) {
    this.linksToAttackBeforeLimitingAttacks = linksToAttackBeforeLimitingAttacks;
    return this;
  }

   /**
   * Get linksToAttackBeforeLimitingAttacks
   * @return linksToAttackBeforeLimitingAttacks
  **/
  @ApiModelProperty(value = "")
  public Long getLinksToAttackBeforeLimitingAttacks() {
    return linksToAttackBeforeLimitingAttacks;
  }

  public void setLinksToAttackBeforeLimitingAttacks(Long linksToAttackBeforeLimitingAttacks) {
    this.linksToAttackBeforeLimitingAttacks = linksToAttackBeforeLimitingAttacks;
  }

  public AttackerConfig maxNormalizedSameNameParameterAttackPointsPerLink(Long maxNormalizedSameNameParameterAttackPointsPerLink) {
    this.maxNormalizedSameNameParameterAttackPointsPerLink = maxNormalizedSameNameParameterAttackPointsPerLink;
    return this;
  }

   /**
   * Get maxNormalizedSameNameParameterAttackPointsPerLink
   * @return maxNormalizedSameNameParameterAttackPointsPerLink
  **/
  @ApiModelProperty(value = "")
  public Long getMaxNormalizedSameNameParameterAttackPointsPerLink() {
    return maxNormalizedSameNameParameterAttackPointsPerLink;
  }

  public void setMaxNormalizedSameNameParameterAttackPointsPerLink(Long maxNormalizedSameNameParameterAttackPointsPerLink) {
    this.maxNormalizedSameNameParameterAttackPointsPerLink = maxNormalizedSameNameParameterAttackPointsPerLink;
  }

  public AttackerConfig maxNumberOfScheduledPassiveAttacks(Long maxNumberOfScheduledPassiveAttacks) {
    this.maxNumberOfScheduledPassiveAttacks = maxNumberOfScheduledPassiveAttacks;
    return this;
  }

   /**
   * Get maxNumberOfScheduledPassiveAttacks
   * @return maxNumberOfScheduledPassiveAttacks
  **/
  @ApiModelProperty(value = "")
  public Long getMaxNumberOfScheduledPassiveAttacks() {
    return maxNumberOfScheduledPassiveAttacks;
  }

  public void setMaxNumberOfScheduledPassiveAttacks(Long maxNumberOfScheduledPassiveAttacks) {
    this.maxNumberOfScheduledPassiveAttacks = maxNumberOfScheduledPassiveAttacks;
  }

  public AttackerConfig maxParameterAttackPointsPerLink(Long maxParameterAttackPointsPerLink) {
    this.maxParameterAttackPointsPerLink = maxParameterAttackPointsPerLink;
    return this;
  }

   /**
   * Get maxParameterAttackPointsPerLink
   * @return maxParameterAttackPointsPerLink
  **/
  @ApiModelProperty(value = "")
  public Long getMaxParameterAttackPointsPerLink() {
    return maxParameterAttackPointsPerLink;
  }

  public void setMaxParameterAttackPointsPerLink(Long maxParameterAttackPointsPerLink) {
    this.maxParameterAttackPointsPerLink = maxParameterAttackPointsPerLink;
  }

  public AttackerConfig maxSameCookieParameterAttackPoints(Long maxSameCookieParameterAttackPoints) {
    this.maxSameCookieParameterAttackPoints = maxSameCookieParameterAttackPoints;
    return this;
  }

   /**
   * Get maxSameCookieParameterAttackPoints
   * @return maxSameCookieParameterAttackPoints
  **/
  @ApiModelProperty(value = "")
  public Long getMaxSameCookieParameterAttackPoints() {
    return maxSameCookieParameterAttackPoints;
  }

  public void setMaxSameCookieParameterAttackPoints(Long maxSameCookieParameterAttackPoints) {
    this.maxSameCookieParameterAttackPoints = maxSameCookieParameterAttackPoints;
  }

  public AttackerConfig maxSameNameParameterAttackPoints(Long maxSameNameParameterAttackPoints) {
    this.maxSameNameParameterAttackPoints = maxSameNameParameterAttackPoints;
    return this;
  }

   /**
   * Get maxSameNameParameterAttackPoints
   * @return maxSameNameParameterAttackPoints
  **/
  @ApiModelProperty(value = "")
  public Long getMaxSameNameParameterAttackPoints() {
    return maxSameNameParameterAttackPoints;
  }

  public void setMaxSameNameParameterAttackPoints(Long maxSameNameParameterAttackPoints) {
    this.maxSameNameParameterAttackPoints = maxSameNameParameterAttackPoints;
  }

  public AttackerConfig maxSameNameParameterAttackPointsPerLink(Long maxSameNameParameterAttackPointsPerLink) {
    this.maxSameNameParameterAttackPointsPerLink = maxSameNameParameterAttackPointsPerLink;
    return this;
  }

   /**
   * Get maxSameNameParameterAttackPointsPerLink
   * @return maxSameNameParameterAttackPointsPerLink
  **/
  @ApiModelProperty(value = "")
  public Long getMaxSameNameParameterAttackPointsPerLink() {
    return maxSameNameParameterAttackPointsPerLink;
  }

  public void setMaxSameNameParameterAttackPointsPerLink(Long maxSameNameParameterAttackPointsPerLink) {
    this.maxSameNameParameterAttackPointsPerLink = maxSameNameParameterAttackPointsPerLink;
  }

  public AttackerConfig minCookieLifetimeForAttacks(Long minCookieLifetimeForAttacks) {
    this.minCookieLifetimeForAttacks = minCookieLifetimeForAttacks;
    return this;
  }

   /**
   * Get minCookieLifetimeForAttacks
   * @return minCookieLifetimeForAttacks
  **/
  @ApiModelProperty(value = "")
  public Long getMinCookieLifetimeForAttacks() {
    return minCookieLifetimeForAttacks;
  }

  public void setMinCookieLifetimeForAttacks(Long minCookieLifetimeForAttacks) {
    this.minCookieLifetimeForAttacks = minCookieLifetimeForAttacks;
  }

  public AttackerConfig parametersToAttackBeforeLimitingAttacks(Long parametersToAttackBeforeLimitingAttacks) {
    this.parametersToAttackBeforeLimitingAttacks = parametersToAttackBeforeLimitingAttacks;
    return this;
  }

   /**
   * Get parametersToAttackBeforeLimitingAttacks
   * @return parametersToAttackBeforeLimitingAttacks
  **/
  @ApiModelProperty(value = "")
  public Long getParametersToAttackBeforeLimitingAttacks() {
    return parametersToAttackBeforeLimitingAttacks;
  }

  public void setParametersToAttackBeforeLimitingAttacks(Long parametersToAttackBeforeLimitingAttacks) {
    this.parametersToAttackBeforeLimitingAttacks = parametersToAttackBeforeLimitingAttacks;
  }

  public AttackerConfig scopeConstraintList(java.util.List<ScopeConstraint> scopeConstraintList) {
    this.scopeConstraintList = scopeConstraintList;
    return this;
  }

  public AttackerConfig addScopeConstraintListItem(ScopeConstraint scopeConstraintListItem) {
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

  public AttackerConfig userDoNotAttackParamList(java.util.List<UserDoNotAttackParam> userDoNotAttackParamList) {
    this.userDoNotAttackParamList = userDoNotAttackParamList;
    return this;
  }

  public AttackerConfig addUserDoNotAttackParamListItem(UserDoNotAttackParam userDoNotAttackParamListItem) {
    if (this.userDoNotAttackParamList == null) {
      this.userDoNotAttackParamList = new java.util.ArrayList<UserDoNotAttackParam>();
    }
    this.userDoNotAttackParamList.add(userDoNotAttackParamListItem);
    return this;
  }

   /**
   * Get userDoNotAttackParamList
   * @return userDoNotAttackParamList
  **/
  @ApiModelProperty(value = "")
  public java.util.List<UserDoNotAttackParam> getUserDoNotAttackParamList() {
    return userDoNotAttackParamList;
  }

  public void setUserDoNotAttackParamList(java.util.List<UserDoNotAttackParam> userDoNotAttackParamList) {
    this.userDoNotAttackParamList = userDoNotAttackParamList;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AttackerConfig attackerConfig = (AttackerConfig) o;
    return Objects.equals(this.applyCrawlerConstraints, attackerConfig.applyCrawlerConstraints) &&
        Objects.equals(this.defaultDoNotAttackParamList, attackerConfig.defaultDoNotAttackParamList) &&
        Objects.equals(this.excludeLowConfidenceFindings, attackerConfig.excludeLowConfidenceFindings) &&
        Objects.equals(this.linksToAttackBeforeLimitingAttacks, attackerConfig.linksToAttackBeforeLimitingAttacks) &&
        Objects.equals(this.maxNormalizedSameNameParameterAttackPointsPerLink, attackerConfig.maxNormalizedSameNameParameterAttackPointsPerLink) &&
        Objects.equals(this.maxNumberOfScheduledPassiveAttacks, attackerConfig.maxNumberOfScheduledPassiveAttacks) &&
        Objects.equals(this.maxParameterAttackPointsPerLink, attackerConfig.maxParameterAttackPointsPerLink) &&
        Objects.equals(this.maxSameCookieParameterAttackPoints, attackerConfig.maxSameCookieParameterAttackPoints) &&
        Objects.equals(this.maxSameNameParameterAttackPoints, attackerConfig.maxSameNameParameterAttackPoints) &&
        Objects.equals(this.maxSameNameParameterAttackPointsPerLink, attackerConfig.maxSameNameParameterAttackPointsPerLink) &&
        Objects.equals(this.minCookieLifetimeForAttacks, attackerConfig.minCookieLifetimeForAttacks) &&
        Objects.equals(this.parametersToAttackBeforeLimitingAttacks, attackerConfig.parametersToAttackBeforeLimitingAttacks) &&
        Objects.equals(this.scopeConstraintList, attackerConfig.scopeConstraintList) &&
        Objects.equals(this.userDoNotAttackParamList, attackerConfig.userDoNotAttackParamList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(applyCrawlerConstraints, defaultDoNotAttackParamList, excludeLowConfidenceFindings, linksToAttackBeforeLimitingAttacks, maxNormalizedSameNameParameterAttackPointsPerLink, maxNumberOfScheduledPassiveAttacks, maxParameterAttackPointsPerLink, maxSameCookieParameterAttackPoints, maxSameNameParameterAttackPoints, maxSameNameParameterAttackPointsPerLink, minCookieLifetimeForAttacks, parametersToAttackBeforeLimitingAttacks, scopeConstraintList, userDoNotAttackParamList);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AttackerConfig {\n");

    sb.append("    applyCrawlerConstraints: ").append(toIndentedString(applyCrawlerConstraints)).append("\n");
    sb.append("    defaultDoNotAttackParamList: ").append(toIndentedString(defaultDoNotAttackParamList)).append("\n");
    sb.append("    excludeLowConfidenceFindings: ").append(toIndentedString(excludeLowConfidenceFindings)).append("\n");
    sb.append("    linksToAttackBeforeLimitingAttacks: ").append(toIndentedString(linksToAttackBeforeLimitingAttacks)).append("\n");
    sb.append("    maxNormalizedSameNameParameterAttackPointsPerLink: ").append(toIndentedString(maxNormalizedSameNameParameterAttackPointsPerLink)).append("\n");
    sb.append("    maxNumberOfScheduledPassiveAttacks: ").append(toIndentedString(maxNumberOfScheduledPassiveAttacks)).append("\n");
    sb.append("    maxParameterAttackPointsPerLink: ").append(toIndentedString(maxParameterAttackPointsPerLink)).append("\n");
    sb.append("    maxSameCookieParameterAttackPoints: ").append(toIndentedString(maxSameCookieParameterAttackPoints)).append("\n");
    sb.append("    maxSameNameParameterAttackPoints: ").append(toIndentedString(maxSameNameParameterAttackPoints)).append("\n");
    sb.append("    maxSameNameParameterAttackPointsPerLink: ").append(toIndentedString(maxSameNameParameterAttackPointsPerLink)).append("\n");
    sb.append("    minCookieLifetimeForAttacks: ").append(toIndentedString(minCookieLifetimeForAttacks)).append("\n");
    sb.append("    parametersToAttackBeforeLimitingAttacks: ").append(toIndentedString(parametersToAttackBeforeLimitingAttacks)).append("\n");
    sb.append("    scopeConstraintList: ").append(toIndentedString(scopeConstraintList)).append("\n");
    sb.append("    userDoNotAttackParamList: ").append(toIndentedString(userDoNotAttackParamList)).append("\n");
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

