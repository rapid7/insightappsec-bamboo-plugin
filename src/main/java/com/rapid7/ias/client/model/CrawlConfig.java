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
 * CrawlConfig
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-11-27T23:32:49.618-05:00")
public class CrawlConfig {
  @SerializedName("binary_content_type_list")
  private java.util.List<BinaryContentType> binaryContentTypeList = null;

  @SerializedName("binary_extension_list")
  private java.util.List<BinaryExtension> binaryExtensionList = null;

  @SerializedName("black_list_extension_list")
  private java.util.List<BlackListExtension> blackListExtensionList = null;

  @SerializedName("browser_do_not_download_content_type_list")
  private java.util.List<BrowserDoNotDownloadContentType> browserDoNotDownloadContentTypeList = null;

  @SerializedName("browser_do_not_download_extension_list")
  private java.util.List<BrowserDoNotDownloadExtension> browserDoNotDownloadExtensionList = null;

  @SerializedName("browser_download_whitelist_list")
  private java.util.List<BrowserDownloadWhitelist> browserDownloadWhitelistList = null;

  /**
   * Gets or Sets caseSensitivity
   */
  @JsonAdapter(CaseSensitivityEnum.Adapter.class)
  public enum CaseSensitivityEnum {
    AUTO_DETECT("AUTO_DETECT"),
    
    CASE_SENSITIVE("CASE_SENSITIVE"),
    
    CASE_INSENSITIVE("CASE_INSENSITIVE");

    private String value;

    CaseSensitivityEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static CaseSensitivityEnum fromValue(String text) {
      for (CaseSensitivityEnum b : CaseSensitivityEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<CaseSensitivityEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final CaseSensitivityEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public CaseSensitivityEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return CaseSensitivityEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("case_sensitivity")
  private CaseSensitivityEnum caseSensitivity = null;

  @SerializedName("cookie_comma_separator")
  private Boolean cookieCommaSeparator = null;

  /**
   * Gets or Sets crawlPrioritization
   */
  @JsonAdapter(CrawlPrioritizationEnum.Adapter.class)
  public enum CrawlPrioritizationEnum {
    FIRST_IN_FIRST_OUT("FIRST_IN_FIRST_OUT"),
    
    SMART("SMART"),
    
    DIRECTORY_BREADTH_FIRST("DIRECTORY_BREADTH_FIRST"),
    
    FOUND_BREADTH_FIRST("FOUND_BREADTH_FIRST"),
    
    FOUND_DEPTH_FIRST("FOUND_DEPTH_FIRST"),
    
    JUICY("JUICY"),
    
    LOGIN_FORM_DISCOVERY("LOGIN_FORM_DISCOVERY"),
    
    LOGIN("LOGIN");

    private String value;

    CrawlPrioritizationEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static CrawlPrioritizationEnum fromValue(String text) {
      for (CrawlPrioritizationEnum b : CrawlPrioritizationEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<CrawlPrioritizationEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final CrawlPrioritizationEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public CrawlPrioritizationEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return CrawlPrioritizationEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("crawl_prioritization")
  private CrawlPrioritizationEnum crawlPrioritization = null;

  @SerializedName("discovery_depth")
  private Long discoveryDepth = null;

  @SerializedName("dom_restrictions_list")
  private java.util.List<DomRestrictions> domRestrictionsList = null;

  @SerializedName("enable_advanced_parsers")
  private Boolean enableAdvancedParsers = null;

  /**
   * Gets or Sets experimentalCrawling
   */
  @JsonAdapter(ExperimentalCrawlingEnum.Adapter.class)
  public enum ExperimentalCrawlingEnum {
    DISABLED("DISABLED"),
    
    ENABLED("ENABLED"),
    
    FRAMEWORK_MATCH("FRAMEWORK_MATCH");

    private String value;

    ExperimentalCrawlingEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static ExperimentalCrawlingEnum fromValue(String text) {
      for (ExperimentalCrawlingEnum b : ExperimentalCrawlingEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<ExperimentalCrawlingEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final ExperimentalCrawlingEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public ExperimentalCrawlingEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return ExperimentalCrawlingEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("experimental_crawling")
  private ExperimentalCrawlingEnum experimentalCrawling = null;

  @SerializedName("file_not_found_regex")
  private String fileNotFoundRegex = null;

  @SerializedName("flash")
  private Boolean flash = null;

  @SerializedName("frameworks_crawl_config")
  private FrameworksCrawlConfig frameworksCrawlConfig = null;

  @SerializedName("gray_list_extension_list")
  private java.util.List<GrayListExtension> grayListExtensionList = null;

  @SerializedName("html_content_type_list")
  private java.util.List<HtmlContentType> htmlContentTypeList = null;

  @SerializedName("import_cookies_from_traffic")
  private Boolean importCookiesFromTraffic = null;

  @SerializedName("invalid_url_regex_attack")
  private String invalidUrlRegexAttack = null;

  @SerializedName("invalid_url_regex_crawl")
  private String invalidUrlRegexCrawl = null;

  @SerializedName("lock_cookies")
  private Boolean lockCookies = null;

  @SerializedName("locked_cookie_list")
  private java.util.List<LockedCookie> lockedCookieList = null;

  @SerializedName("max_attack_feedback_links_count")
  private Long maxAttackFeedbackLinksCount = null;

  @SerializedName("max_black_list_ext_crawl_results")
  private Long maxBlackListExtCrawlResults = null;

  @SerializedName("max_browser_dom_depth")
  private Long maxBrowserDomDepth = null;

  @SerializedName("max_browser_events_per_crawl_result")
  private Long maxBrowserEventsPerCrawlResult = null;

  @SerializedName("max_browser_events_per_dom")
  private Long maxBrowserEventsPerDom = null;

  @SerializedName("max_browser_events_per_link")
  private Long maxBrowserEventsPerLink = null;

  @SerializedName("max_browser_no_new_resource_dom_count")
  private Long maxBrowserNoNewResourceDomCount = null;

  @SerializedName("max_browser_page_wait_timeout")
  private Long maxBrowserPageWaitTimeout = null;

  @SerializedName("max_browser_wait_till_request_timeout")
  private Long maxBrowserWaitTillRequestTimeout = null;

  @SerializedName("max_cookies_from_javascript")
  private Long maxCookiesFromJavascript = null;

  @SerializedName("max_cookies_same_name_from_javascript")
  private Long maxCookiesSameNameFromJavascript = null;

  @SerializedName("max_crawl_results")
  private Long maxCrawlResults = null;

  @SerializedName("max_dir_depth")
  private Long maxDirDepth = null;

  @SerializedName("max_domain")
  private Long maxDomain = null;

  @SerializedName("max_per_dir_child_nodes")
  private Long maxPerDirChildNodes = null;

  @SerializedName("max_per_dir_crawl_results")
  private Long maxPerDirCrawlResults = null;

  @SerializedName("max_per_file_name_crawl_results")
  private Long maxPerFileNameCrawlResults = null;

  @SerializedName("max_per_link_crawl_results")
  private Long maxPerLinkCrawlResults = null;

  @SerializedName("max_per_normalized_link_crawl_result")
  private Long maxPerNormalizedLinkCrawlResult = null;

  @SerializedName("max_per_web_site_crawl_results")
  private Long maxPerWebSiteCrawlResults = null;

  @SerializedName("max_reported_comments")
  private Long maxReportedComments = null;

  @SerializedName("max_reported_emails")
  private Long maxReportedEmails = null;

  @SerializedName("max_reported_forms")
  private Long maxReportedForms = null;

  @SerializedName("max_reported_images")
  private Long maxReportedImages = null;

  @SerializedName("max_reported_links")
  private Long maxReportedLinks = null;

  @SerializedName("max_reported_scripts")
  private Long maxReportedScripts = null;

  @SerializedName("not_inserted_link_count_threshold")
  private Long notInsertedLinkCountThreshold = null;

  @SerializedName("page_equal_threshhold")
  private Double pageEqualThreshhold = null;

  @SerializedName("page_similar_threshhold")
  private Double pageSimilarThreshhold = null;

  @SerializedName("priority_links_regex")
  private String priorityLinksRegex = null;

  @SerializedName("recursion_depth")
  private Long recursionDepth = null;

  @SerializedName("restrict_to_macro")
  private Boolean restrictToMacro = null;

  @SerializedName("restrict_to_manual_crawling")
  private Boolean restrictToManualCrawling = null;

  @SerializedName("restrict_to_seed_list")
  private Boolean restrictToSeedList = null;

  @SerializedName("restrict_to_swagger")
  private Boolean restrictToSwagger = null;

  @SerializedName("restrict_to_web_service")
  private Boolean restrictToWebService = null;

  @SerializedName("scope_constraint_list")
  private java.util.List<ScopeConstraint> scopeConstraintList = null;

  @SerializedName("search_for_urls")
  private Boolean searchForUrls = null;

  @SerializedName("seed_url_list")
  private java.util.List<SeedUrl> seedUrlList = null;

  @SerializedName("sequence_repetition_tolerance")
  private Long sequenceRepetitionTolerance = null;

  @SerializedName("server_error_regex")
  private String serverErrorRegex = null;

  @SerializedName("stay_on_port")
  private Boolean stayOnPort = null;

  @SerializedName("text_content_type_list")
  private java.util.List<TextContentType> textContentTypeList = null;

  @SerializedName("text_extension_list")
  private java.util.List<TextExtension> textExtensionList = null;

  @SerializedName("url_repetition_tolerance")
  private Long urlRepetitionTolerance = null;

  @SerializedName("xml_content_type_list")
  private java.util.List<XmlContentType> xmlContentTypeList = null;

  public CrawlConfig binaryContentTypeList(java.util.List<BinaryContentType> binaryContentTypeList) {
    this.binaryContentTypeList = binaryContentTypeList;
    return this;
  }

  public CrawlConfig addBinaryContentTypeListItem(BinaryContentType binaryContentTypeListItem) {
    if (this.binaryContentTypeList == null) {
      this.binaryContentTypeList = new java.util.ArrayList<BinaryContentType>();
    }
    this.binaryContentTypeList.add(binaryContentTypeListItem);
    return this;
  }

   /**
   * Get binaryContentTypeList
   * @return binaryContentTypeList
  **/
  @ApiModelProperty(value = "")
  public java.util.List<BinaryContentType> getBinaryContentTypeList() {
    return binaryContentTypeList;
  }

  public void setBinaryContentTypeList(java.util.List<BinaryContentType> binaryContentTypeList) {
    this.binaryContentTypeList = binaryContentTypeList;
  }

  public CrawlConfig binaryExtensionList(java.util.List<BinaryExtension> binaryExtensionList) {
    this.binaryExtensionList = binaryExtensionList;
    return this;
  }

  public CrawlConfig addBinaryExtensionListItem(BinaryExtension binaryExtensionListItem) {
    if (this.binaryExtensionList == null) {
      this.binaryExtensionList = new java.util.ArrayList<BinaryExtension>();
    }
    this.binaryExtensionList.add(binaryExtensionListItem);
    return this;
  }

   /**
   * Get binaryExtensionList
   * @return binaryExtensionList
  **/
  @ApiModelProperty(value = "")
  public java.util.List<BinaryExtension> getBinaryExtensionList() {
    return binaryExtensionList;
  }

  public void setBinaryExtensionList(java.util.List<BinaryExtension> binaryExtensionList) {
    this.binaryExtensionList = binaryExtensionList;
  }

  public CrawlConfig blackListExtensionList(java.util.List<BlackListExtension> blackListExtensionList) {
    this.blackListExtensionList = blackListExtensionList;
    return this;
  }

  public CrawlConfig addBlackListExtensionListItem(BlackListExtension blackListExtensionListItem) {
    if (this.blackListExtensionList == null) {
      this.blackListExtensionList = new java.util.ArrayList<BlackListExtension>();
    }
    this.blackListExtensionList.add(blackListExtensionListItem);
    return this;
  }

   /**
   * Get blackListExtensionList
   * @return blackListExtensionList
  **/
  @ApiModelProperty(value = "")
  public java.util.List<BlackListExtension> getBlackListExtensionList() {
    return blackListExtensionList;
  }

  public void setBlackListExtensionList(java.util.List<BlackListExtension> blackListExtensionList) {
    this.blackListExtensionList = blackListExtensionList;
  }

  public CrawlConfig browserDoNotDownloadContentTypeList(java.util.List<BrowserDoNotDownloadContentType> browserDoNotDownloadContentTypeList) {
    this.browserDoNotDownloadContentTypeList = browserDoNotDownloadContentTypeList;
    return this;
  }

  public CrawlConfig addBrowserDoNotDownloadContentTypeListItem(BrowserDoNotDownloadContentType browserDoNotDownloadContentTypeListItem) {
    if (this.browserDoNotDownloadContentTypeList == null) {
      this.browserDoNotDownloadContentTypeList = new java.util.ArrayList<BrowserDoNotDownloadContentType>();
    }
    this.browserDoNotDownloadContentTypeList.add(browserDoNotDownloadContentTypeListItem);
    return this;
  }

   /**
   * Get browserDoNotDownloadContentTypeList
   * @return browserDoNotDownloadContentTypeList
  **/
  @ApiModelProperty(value = "")
  public java.util.List<BrowserDoNotDownloadContentType> getBrowserDoNotDownloadContentTypeList() {
    return browserDoNotDownloadContentTypeList;
  }

  public void setBrowserDoNotDownloadContentTypeList(java.util.List<BrowserDoNotDownloadContentType> browserDoNotDownloadContentTypeList) {
    this.browserDoNotDownloadContentTypeList = browserDoNotDownloadContentTypeList;
  }

  public CrawlConfig browserDoNotDownloadExtensionList(java.util.List<BrowserDoNotDownloadExtension> browserDoNotDownloadExtensionList) {
    this.browserDoNotDownloadExtensionList = browserDoNotDownloadExtensionList;
    return this;
  }

  public CrawlConfig addBrowserDoNotDownloadExtensionListItem(BrowserDoNotDownloadExtension browserDoNotDownloadExtensionListItem) {
    if (this.browserDoNotDownloadExtensionList == null) {
      this.browserDoNotDownloadExtensionList = new java.util.ArrayList<BrowserDoNotDownloadExtension>();
    }
    this.browserDoNotDownloadExtensionList.add(browserDoNotDownloadExtensionListItem);
    return this;
  }

   /**
   * Get browserDoNotDownloadExtensionList
   * @return browserDoNotDownloadExtensionList
  **/
  @ApiModelProperty(value = "")
  public java.util.List<BrowserDoNotDownloadExtension> getBrowserDoNotDownloadExtensionList() {
    return browserDoNotDownloadExtensionList;
  }

  public void setBrowserDoNotDownloadExtensionList(java.util.List<BrowserDoNotDownloadExtension> browserDoNotDownloadExtensionList) {
    this.browserDoNotDownloadExtensionList = browserDoNotDownloadExtensionList;
  }

  public CrawlConfig browserDownloadWhitelistList(java.util.List<BrowserDownloadWhitelist> browserDownloadWhitelistList) {
    this.browserDownloadWhitelistList = browserDownloadWhitelistList;
    return this;
  }

  public CrawlConfig addBrowserDownloadWhitelistListItem(BrowserDownloadWhitelist browserDownloadWhitelistListItem) {
    if (this.browserDownloadWhitelistList == null) {
      this.browserDownloadWhitelistList = new java.util.ArrayList<BrowserDownloadWhitelist>();
    }
    this.browserDownloadWhitelistList.add(browserDownloadWhitelistListItem);
    return this;
  }

   /**
   * Get browserDownloadWhitelistList
   * @return browserDownloadWhitelistList
  **/
  @ApiModelProperty(value = "")
  public java.util.List<BrowserDownloadWhitelist> getBrowserDownloadWhitelistList() {
    return browserDownloadWhitelistList;
  }

  public void setBrowserDownloadWhitelistList(java.util.List<BrowserDownloadWhitelist> browserDownloadWhitelistList) {
    this.browserDownloadWhitelistList = browserDownloadWhitelistList;
  }

  public CrawlConfig caseSensitivity(CaseSensitivityEnum caseSensitivity) {
    this.caseSensitivity = caseSensitivity;
    return this;
  }

   /**
   * Get caseSensitivity
   * @return caseSensitivity
  **/
  @ApiModelProperty(value = "")
  public CaseSensitivityEnum getCaseSensitivity() {
    return caseSensitivity;
  }

  public void setCaseSensitivity(CaseSensitivityEnum caseSensitivity) {
    this.caseSensitivity = caseSensitivity;
  }

  public CrawlConfig cookieCommaSeparator(Boolean cookieCommaSeparator) {
    this.cookieCommaSeparator = cookieCommaSeparator;
    return this;
  }

   /**
   * Get cookieCommaSeparator
   * @return cookieCommaSeparator
  **/
  @ApiModelProperty(value = "")
  public Boolean isCookieCommaSeparator() {
    return cookieCommaSeparator;
  }

  public void setCookieCommaSeparator(Boolean cookieCommaSeparator) {
    this.cookieCommaSeparator = cookieCommaSeparator;
  }

  public CrawlConfig crawlPrioritization(CrawlPrioritizationEnum crawlPrioritization) {
    this.crawlPrioritization = crawlPrioritization;
    return this;
  }

   /**
   * Get crawlPrioritization
   * @return crawlPrioritization
  **/
  @ApiModelProperty(value = "")
  public CrawlPrioritizationEnum getCrawlPrioritization() {
    return crawlPrioritization;
  }

  public void setCrawlPrioritization(CrawlPrioritizationEnum crawlPrioritization) {
    this.crawlPrioritization = crawlPrioritization;
  }

  public CrawlConfig discoveryDepth(Long discoveryDepth) {
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

  public CrawlConfig domRestrictionsList(java.util.List<DomRestrictions> domRestrictionsList) {
    this.domRestrictionsList = domRestrictionsList;
    return this;
  }

  public CrawlConfig addDomRestrictionsListItem(DomRestrictions domRestrictionsListItem) {
    if (this.domRestrictionsList == null) {
      this.domRestrictionsList = new java.util.ArrayList<DomRestrictions>();
    }
    this.domRestrictionsList.add(domRestrictionsListItem);
    return this;
  }

   /**
   * Get domRestrictionsList
   * @return domRestrictionsList
  **/
  @ApiModelProperty(value = "")
  public java.util.List<DomRestrictions> getDomRestrictionsList() {
    return domRestrictionsList;
  }

  public void setDomRestrictionsList(java.util.List<DomRestrictions> domRestrictionsList) {
    this.domRestrictionsList = domRestrictionsList;
  }

  public CrawlConfig enableAdvancedParsers(Boolean enableAdvancedParsers) {
    this.enableAdvancedParsers = enableAdvancedParsers;
    return this;
  }

   /**
   * Get enableAdvancedParsers
   * @return enableAdvancedParsers
  **/
  @ApiModelProperty(value = "")
  public Boolean isEnableAdvancedParsers() {
    return enableAdvancedParsers;
  }

  public void setEnableAdvancedParsers(Boolean enableAdvancedParsers) {
    this.enableAdvancedParsers = enableAdvancedParsers;
  }

  public CrawlConfig experimentalCrawling(ExperimentalCrawlingEnum experimentalCrawling) {
    this.experimentalCrawling = experimentalCrawling;
    return this;
  }

   /**
   * Get experimentalCrawling
   * @return experimentalCrawling
  **/
  @ApiModelProperty(value = "")
  public ExperimentalCrawlingEnum getExperimentalCrawling() {
    return experimentalCrawling;
  }

  public void setExperimentalCrawling(ExperimentalCrawlingEnum experimentalCrawling) {
    this.experimentalCrawling = experimentalCrawling;
  }

  public CrawlConfig fileNotFoundRegex(String fileNotFoundRegex) {
    this.fileNotFoundRegex = fileNotFoundRegex;
    return this;
  }

   /**
   * Get fileNotFoundRegex
   * @return fileNotFoundRegex
  **/
  @ApiModelProperty(value = "")
  public String getFileNotFoundRegex() {
    return fileNotFoundRegex;
  }

  public void setFileNotFoundRegex(String fileNotFoundRegex) {
    this.fileNotFoundRegex = fileNotFoundRegex;
  }

  public CrawlConfig flash(Boolean flash) {
    this.flash = flash;
    return this;
  }

   /**
   * Get flash
   * @return flash
  **/
  @ApiModelProperty(value = "")
  public Boolean isFlash() {
    return flash;
  }

  public void setFlash(Boolean flash) {
    this.flash = flash;
  }

  public CrawlConfig frameworksCrawlConfig(FrameworksCrawlConfig frameworksCrawlConfig) {
    this.frameworksCrawlConfig = frameworksCrawlConfig;
    return this;
  }

   /**
   * Get frameworksCrawlConfig
   * @return frameworksCrawlConfig
  **/
  @ApiModelProperty(value = "")
  public FrameworksCrawlConfig getFrameworksCrawlConfig() {
    return frameworksCrawlConfig;
  }

  public void setFrameworksCrawlConfig(FrameworksCrawlConfig frameworksCrawlConfig) {
    this.frameworksCrawlConfig = frameworksCrawlConfig;
  }

  public CrawlConfig grayListExtensionList(java.util.List<GrayListExtension> grayListExtensionList) {
    this.grayListExtensionList = grayListExtensionList;
    return this;
  }

  public CrawlConfig addGrayListExtensionListItem(GrayListExtension grayListExtensionListItem) {
    if (this.grayListExtensionList == null) {
      this.grayListExtensionList = new java.util.ArrayList<GrayListExtension>();
    }
    this.grayListExtensionList.add(grayListExtensionListItem);
    return this;
  }

   /**
   * Get grayListExtensionList
   * @return grayListExtensionList
  **/
  @ApiModelProperty(value = "")
  public java.util.List<GrayListExtension> getGrayListExtensionList() {
    return grayListExtensionList;
  }

  public void setGrayListExtensionList(java.util.List<GrayListExtension> grayListExtensionList) {
    this.grayListExtensionList = grayListExtensionList;
  }

  public CrawlConfig htmlContentTypeList(java.util.List<HtmlContentType> htmlContentTypeList) {
    this.htmlContentTypeList = htmlContentTypeList;
    return this;
  }

  public CrawlConfig addHtmlContentTypeListItem(HtmlContentType htmlContentTypeListItem) {
    if (this.htmlContentTypeList == null) {
      this.htmlContentTypeList = new java.util.ArrayList<HtmlContentType>();
    }
    this.htmlContentTypeList.add(htmlContentTypeListItem);
    return this;
  }

   /**
   * Get htmlContentTypeList
   * @return htmlContentTypeList
  **/
  @ApiModelProperty(value = "")
  public java.util.List<HtmlContentType> getHtmlContentTypeList() {
    return htmlContentTypeList;
  }

  public void setHtmlContentTypeList(java.util.List<HtmlContentType> htmlContentTypeList) {
    this.htmlContentTypeList = htmlContentTypeList;
  }

  public CrawlConfig importCookiesFromTraffic(Boolean importCookiesFromTraffic) {
    this.importCookiesFromTraffic = importCookiesFromTraffic;
    return this;
  }

   /**
   * Get importCookiesFromTraffic
   * @return importCookiesFromTraffic
  **/
  @ApiModelProperty(value = "")
  public Boolean isImportCookiesFromTraffic() {
    return importCookiesFromTraffic;
  }

  public void setImportCookiesFromTraffic(Boolean importCookiesFromTraffic) {
    this.importCookiesFromTraffic = importCookiesFromTraffic;
  }

  public CrawlConfig invalidUrlRegexAttack(String invalidUrlRegexAttack) {
    this.invalidUrlRegexAttack = invalidUrlRegexAttack;
    return this;
  }

   /**
   * Get invalidUrlRegexAttack
   * @return invalidUrlRegexAttack
  **/
  @ApiModelProperty(value = "")
  public String getInvalidUrlRegexAttack() {
    return invalidUrlRegexAttack;
  }

  public void setInvalidUrlRegexAttack(String invalidUrlRegexAttack) {
    this.invalidUrlRegexAttack = invalidUrlRegexAttack;
  }

  public CrawlConfig invalidUrlRegexCrawl(String invalidUrlRegexCrawl) {
    this.invalidUrlRegexCrawl = invalidUrlRegexCrawl;
    return this;
  }

   /**
   * Get invalidUrlRegexCrawl
   * @return invalidUrlRegexCrawl
  **/
  @ApiModelProperty(value = "")
  public String getInvalidUrlRegexCrawl() {
    return invalidUrlRegexCrawl;
  }

  public void setInvalidUrlRegexCrawl(String invalidUrlRegexCrawl) {
    this.invalidUrlRegexCrawl = invalidUrlRegexCrawl;
  }

  public CrawlConfig lockCookies(Boolean lockCookies) {
    this.lockCookies = lockCookies;
    return this;
  }

   /**
   * Get lockCookies
   * @return lockCookies
  **/
  @ApiModelProperty(value = "")
  public Boolean isLockCookies() {
    return lockCookies;
  }

  public void setLockCookies(Boolean lockCookies) {
    this.lockCookies = lockCookies;
  }

  public CrawlConfig lockedCookieList(java.util.List<LockedCookie> lockedCookieList) {
    this.lockedCookieList = lockedCookieList;
    return this;
  }

  public CrawlConfig addLockedCookieListItem(LockedCookie lockedCookieListItem) {
    if (this.lockedCookieList == null) {
      this.lockedCookieList = new java.util.ArrayList<LockedCookie>();
    }
    this.lockedCookieList.add(lockedCookieListItem);
    return this;
  }

   /**
   * Get lockedCookieList
   * @return lockedCookieList
  **/
  @ApiModelProperty(value = "")
  public java.util.List<LockedCookie> getLockedCookieList() {
    return lockedCookieList;
  }

  public void setLockedCookieList(java.util.List<LockedCookie> lockedCookieList) {
    this.lockedCookieList = lockedCookieList;
  }

  public CrawlConfig maxAttackFeedbackLinksCount(Long maxAttackFeedbackLinksCount) {
    this.maxAttackFeedbackLinksCount = maxAttackFeedbackLinksCount;
    return this;
  }

   /**
   * Get maxAttackFeedbackLinksCount
   * @return maxAttackFeedbackLinksCount
  **/
  @ApiModelProperty(value = "")
  public Long getMaxAttackFeedbackLinksCount() {
    return maxAttackFeedbackLinksCount;
  }

  public void setMaxAttackFeedbackLinksCount(Long maxAttackFeedbackLinksCount) {
    this.maxAttackFeedbackLinksCount = maxAttackFeedbackLinksCount;
  }

  public CrawlConfig maxBlackListExtCrawlResults(Long maxBlackListExtCrawlResults) {
    this.maxBlackListExtCrawlResults = maxBlackListExtCrawlResults;
    return this;
  }

   /**
   * Get maxBlackListExtCrawlResults
   * @return maxBlackListExtCrawlResults
  **/
  @ApiModelProperty(value = "")
  public Long getMaxBlackListExtCrawlResults() {
    return maxBlackListExtCrawlResults;
  }

  public void setMaxBlackListExtCrawlResults(Long maxBlackListExtCrawlResults) {
    this.maxBlackListExtCrawlResults = maxBlackListExtCrawlResults;
  }

  public CrawlConfig maxBrowserDomDepth(Long maxBrowserDomDepth) {
    this.maxBrowserDomDepth = maxBrowserDomDepth;
    return this;
  }

   /**
   * Get maxBrowserDomDepth
   * @return maxBrowserDomDepth
  **/
  @ApiModelProperty(value = "")
  public Long getMaxBrowserDomDepth() {
    return maxBrowserDomDepth;
  }

  public void setMaxBrowserDomDepth(Long maxBrowserDomDepth) {
    this.maxBrowserDomDepth = maxBrowserDomDepth;
  }

  public CrawlConfig maxBrowserEventsPerCrawlResult(Long maxBrowserEventsPerCrawlResult) {
    this.maxBrowserEventsPerCrawlResult = maxBrowserEventsPerCrawlResult;
    return this;
  }

   /**
   * Get maxBrowserEventsPerCrawlResult
   * @return maxBrowserEventsPerCrawlResult
  **/
  @ApiModelProperty(value = "")
  public Long getMaxBrowserEventsPerCrawlResult() {
    return maxBrowserEventsPerCrawlResult;
  }

  public void setMaxBrowserEventsPerCrawlResult(Long maxBrowserEventsPerCrawlResult) {
    this.maxBrowserEventsPerCrawlResult = maxBrowserEventsPerCrawlResult;
  }

  public CrawlConfig maxBrowserEventsPerDom(Long maxBrowserEventsPerDom) {
    this.maxBrowserEventsPerDom = maxBrowserEventsPerDom;
    return this;
  }

   /**
   * Get maxBrowserEventsPerDom
   * @return maxBrowserEventsPerDom
  **/
  @ApiModelProperty(value = "")
  public Long getMaxBrowserEventsPerDom() {
    return maxBrowserEventsPerDom;
  }

  public void setMaxBrowserEventsPerDom(Long maxBrowserEventsPerDom) {
    this.maxBrowserEventsPerDom = maxBrowserEventsPerDom;
  }

  public CrawlConfig maxBrowserEventsPerLink(Long maxBrowserEventsPerLink) {
    this.maxBrowserEventsPerLink = maxBrowserEventsPerLink;
    return this;
  }

   /**
   * Get maxBrowserEventsPerLink
   * @return maxBrowserEventsPerLink
  **/
  @ApiModelProperty(value = "")
  public Long getMaxBrowserEventsPerLink() {
    return maxBrowserEventsPerLink;
  }

  public void setMaxBrowserEventsPerLink(Long maxBrowserEventsPerLink) {
    this.maxBrowserEventsPerLink = maxBrowserEventsPerLink;
  }

  public CrawlConfig maxBrowserNoNewResourceDomCount(Long maxBrowserNoNewResourceDomCount) {
    this.maxBrowserNoNewResourceDomCount = maxBrowserNoNewResourceDomCount;
    return this;
  }

   /**
   * Get maxBrowserNoNewResourceDomCount
   * @return maxBrowserNoNewResourceDomCount
  **/
  @ApiModelProperty(value = "")
  public Long getMaxBrowserNoNewResourceDomCount() {
    return maxBrowserNoNewResourceDomCount;
  }

  public void setMaxBrowserNoNewResourceDomCount(Long maxBrowserNoNewResourceDomCount) {
    this.maxBrowserNoNewResourceDomCount = maxBrowserNoNewResourceDomCount;
  }

  public CrawlConfig maxBrowserPageWaitTimeout(Long maxBrowserPageWaitTimeout) {
    this.maxBrowserPageWaitTimeout = maxBrowserPageWaitTimeout;
    return this;
  }

   /**
   * Get maxBrowserPageWaitTimeout
   * @return maxBrowserPageWaitTimeout
  **/
  @ApiModelProperty(value = "")
  public Long getMaxBrowserPageWaitTimeout() {
    return maxBrowserPageWaitTimeout;
  }

  public void setMaxBrowserPageWaitTimeout(Long maxBrowserPageWaitTimeout) {
    this.maxBrowserPageWaitTimeout = maxBrowserPageWaitTimeout;
  }

  public CrawlConfig maxBrowserWaitTillRequestTimeout(Long maxBrowserWaitTillRequestTimeout) {
    this.maxBrowserWaitTillRequestTimeout = maxBrowserWaitTillRequestTimeout;
    return this;
  }

   /**
   * Get maxBrowserWaitTillRequestTimeout
   * @return maxBrowserWaitTillRequestTimeout
  **/
  @ApiModelProperty(value = "")
  public Long getMaxBrowserWaitTillRequestTimeout() {
    return maxBrowserWaitTillRequestTimeout;
  }

  public void setMaxBrowserWaitTillRequestTimeout(Long maxBrowserWaitTillRequestTimeout) {
    this.maxBrowserWaitTillRequestTimeout = maxBrowserWaitTillRequestTimeout;
  }

  public CrawlConfig maxCookiesFromJavascript(Long maxCookiesFromJavascript) {
    this.maxCookiesFromJavascript = maxCookiesFromJavascript;
    return this;
  }

   /**
   * Get maxCookiesFromJavascript
   * @return maxCookiesFromJavascript
  **/
  @ApiModelProperty(value = "")
  public Long getMaxCookiesFromJavascript() {
    return maxCookiesFromJavascript;
  }

  public void setMaxCookiesFromJavascript(Long maxCookiesFromJavascript) {
    this.maxCookiesFromJavascript = maxCookiesFromJavascript;
  }

  public CrawlConfig maxCookiesSameNameFromJavascript(Long maxCookiesSameNameFromJavascript) {
    this.maxCookiesSameNameFromJavascript = maxCookiesSameNameFromJavascript;
    return this;
  }

   /**
   * Get maxCookiesSameNameFromJavascript
   * @return maxCookiesSameNameFromJavascript
  **/
  @ApiModelProperty(value = "")
  public Long getMaxCookiesSameNameFromJavascript() {
    return maxCookiesSameNameFromJavascript;
  }

  public void setMaxCookiesSameNameFromJavascript(Long maxCookiesSameNameFromJavascript) {
    this.maxCookiesSameNameFromJavascript = maxCookiesSameNameFromJavascript;
  }

  public CrawlConfig maxCrawlResults(Long maxCrawlResults) {
    this.maxCrawlResults = maxCrawlResults;
    return this;
  }

   /**
   * Get maxCrawlResults
   * @return maxCrawlResults
  **/
  @ApiModelProperty(value = "")
  public Long getMaxCrawlResults() {
    return maxCrawlResults;
  }

  public void setMaxCrawlResults(Long maxCrawlResults) {
    this.maxCrawlResults = maxCrawlResults;
  }

  public CrawlConfig maxDirDepth(Long maxDirDepth) {
    this.maxDirDepth = maxDirDepth;
    return this;
  }

   /**
   * Get maxDirDepth
   * @return maxDirDepth
  **/
  @ApiModelProperty(value = "")
  public Long getMaxDirDepth() {
    return maxDirDepth;
  }

  public void setMaxDirDepth(Long maxDirDepth) {
    this.maxDirDepth = maxDirDepth;
  }

  public CrawlConfig maxDomain(Long maxDomain) {
    this.maxDomain = maxDomain;
    return this;
  }

   /**
   * Get maxDomain
   * @return maxDomain
  **/
  @ApiModelProperty(value = "")
  public Long getMaxDomain() {
    return maxDomain;
  }

  public void setMaxDomain(Long maxDomain) {
    this.maxDomain = maxDomain;
  }

  public CrawlConfig maxPerDirChildNodes(Long maxPerDirChildNodes) {
    this.maxPerDirChildNodes = maxPerDirChildNodes;
    return this;
  }

   /**
   * Get maxPerDirChildNodes
   * @return maxPerDirChildNodes
  **/
  @ApiModelProperty(value = "")
  public Long getMaxPerDirChildNodes() {
    return maxPerDirChildNodes;
  }

  public void setMaxPerDirChildNodes(Long maxPerDirChildNodes) {
    this.maxPerDirChildNodes = maxPerDirChildNodes;
  }

  public CrawlConfig maxPerDirCrawlResults(Long maxPerDirCrawlResults) {
    this.maxPerDirCrawlResults = maxPerDirCrawlResults;
    return this;
  }

   /**
   * Get maxPerDirCrawlResults
   * @return maxPerDirCrawlResults
  **/
  @ApiModelProperty(value = "")
  public Long getMaxPerDirCrawlResults() {
    return maxPerDirCrawlResults;
  }

  public void setMaxPerDirCrawlResults(Long maxPerDirCrawlResults) {
    this.maxPerDirCrawlResults = maxPerDirCrawlResults;
  }

  public CrawlConfig maxPerFileNameCrawlResults(Long maxPerFileNameCrawlResults) {
    this.maxPerFileNameCrawlResults = maxPerFileNameCrawlResults;
    return this;
  }

   /**
   * Get maxPerFileNameCrawlResults
   * @return maxPerFileNameCrawlResults
  **/
  @ApiModelProperty(value = "")
  public Long getMaxPerFileNameCrawlResults() {
    return maxPerFileNameCrawlResults;
  }

  public void setMaxPerFileNameCrawlResults(Long maxPerFileNameCrawlResults) {
    this.maxPerFileNameCrawlResults = maxPerFileNameCrawlResults;
  }

  public CrawlConfig maxPerLinkCrawlResults(Long maxPerLinkCrawlResults) {
    this.maxPerLinkCrawlResults = maxPerLinkCrawlResults;
    return this;
  }

   /**
   * Get maxPerLinkCrawlResults
   * @return maxPerLinkCrawlResults
  **/
  @ApiModelProperty(value = "")
  public Long getMaxPerLinkCrawlResults() {
    return maxPerLinkCrawlResults;
  }

  public void setMaxPerLinkCrawlResults(Long maxPerLinkCrawlResults) {
    this.maxPerLinkCrawlResults = maxPerLinkCrawlResults;
  }

  public CrawlConfig maxPerNormalizedLinkCrawlResult(Long maxPerNormalizedLinkCrawlResult) {
    this.maxPerNormalizedLinkCrawlResult = maxPerNormalizedLinkCrawlResult;
    return this;
  }

   /**
   * Get maxPerNormalizedLinkCrawlResult
   * @return maxPerNormalizedLinkCrawlResult
  **/
  @ApiModelProperty(value = "")
  public Long getMaxPerNormalizedLinkCrawlResult() {
    return maxPerNormalizedLinkCrawlResult;
  }

  public void setMaxPerNormalizedLinkCrawlResult(Long maxPerNormalizedLinkCrawlResult) {
    this.maxPerNormalizedLinkCrawlResult = maxPerNormalizedLinkCrawlResult;
  }

  public CrawlConfig maxPerWebSiteCrawlResults(Long maxPerWebSiteCrawlResults) {
    this.maxPerWebSiteCrawlResults = maxPerWebSiteCrawlResults;
    return this;
  }

   /**
   * Get maxPerWebSiteCrawlResults
   * @return maxPerWebSiteCrawlResults
  **/
  @ApiModelProperty(value = "")
  public Long getMaxPerWebSiteCrawlResults() {
    return maxPerWebSiteCrawlResults;
  }

  public void setMaxPerWebSiteCrawlResults(Long maxPerWebSiteCrawlResults) {
    this.maxPerWebSiteCrawlResults = maxPerWebSiteCrawlResults;
  }

  public CrawlConfig maxReportedComments(Long maxReportedComments) {
    this.maxReportedComments = maxReportedComments;
    return this;
  }

   /**
   * Get maxReportedComments
   * @return maxReportedComments
  **/
  @ApiModelProperty(value = "")
  public Long getMaxReportedComments() {
    return maxReportedComments;
  }

  public void setMaxReportedComments(Long maxReportedComments) {
    this.maxReportedComments = maxReportedComments;
  }

  public CrawlConfig maxReportedEmails(Long maxReportedEmails) {
    this.maxReportedEmails = maxReportedEmails;
    return this;
  }

   /**
   * Get maxReportedEmails
   * @return maxReportedEmails
  **/
  @ApiModelProperty(value = "")
  public Long getMaxReportedEmails() {
    return maxReportedEmails;
  }

  public void setMaxReportedEmails(Long maxReportedEmails) {
    this.maxReportedEmails = maxReportedEmails;
  }

  public CrawlConfig maxReportedForms(Long maxReportedForms) {
    this.maxReportedForms = maxReportedForms;
    return this;
  }

   /**
   * Get maxReportedForms
   * @return maxReportedForms
  **/
  @ApiModelProperty(value = "")
  public Long getMaxReportedForms() {
    return maxReportedForms;
  }

  public void setMaxReportedForms(Long maxReportedForms) {
    this.maxReportedForms = maxReportedForms;
  }

  public CrawlConfig maxReportedImages(Long maxReportedImages) {
    this.maxReportedImages = maxReportedImages;
    return this;
  }

   /**
   * Get maxReportedImages
   * @return maxReportedImages
  **/
  @ApiModelProperty(value = "")
  public Long getMaxReportedImages() {
    return maxReportedImages;
  }

  public void setMaxReportedImages(Long maxReportedImages) {
    this.maxReportedImages = maxReportedImages;
  }

  public CrawlConfig maxReportedLinks(Long maxReportedLinks) {
    this.maxReportedLinks = maxReportedLinks;
    return this;
  }

   /**
   * Get maxReportedLinks
   * @return maxReportedLinks
  **/
  @ApiModelProperty(value = "")
  public Long getMaxReportedLinks() {
    return maxReportedLinks;
  }

  public void setMaxReportedLinks(Long maxReportedLinks) {
    this.maxReportedLinks = maxReportedLinks;
  }

  public CrawlConfig maxReportedScripts(Long maxReportedScripts) {
    this.maxReportedScripts = maxReportedScripts;
    return this;
  }

   /**
   * Get maxReportedScripts
   * @return maxReportedScripts
  **/
  @ApiModelProperty(value = "")
  public Long getMaxReportedScripts() {
    return maxReportedScripts;
  }

  public void setMaxReportedScripts(Long maxReportedScripts) {
    this.maxReportedScripts = maxReportedScripts;
  }

  public CrawlConfig notInsertedLinkCountThreshold(Long notInsertedLinkCountThreshold) {
    this.notInsertedLinkCountThreshold = notInsertedLinkCountThreshold;
    return this;
  }

   /**
   * Get notInsertedLinkCountThreshold
   * @return notInsertedLinkCountThreshold
  **/
  @ApiModelProperty(value = "")
  public Long getNotInsertedLinkCountThreshold() {
    return notInsertedLinkCountThreshold;
  }

  public void setNotInsertedLinkCountThreshold(Long notInsertedLinkCountThreshold) {
    this.notInsertedLinkCountThreshold = notInsertedLinkCountThreshold;
  }

  public CrawlConfig pageEqualThreshhold(Double pageEqualThreshhold) {
    this.pageEqualThreshhold = pageEqualThreshhold;
    return this;
  }

   /**
   * Get pageEqualThreshhold
   * @return pageEqualThreshhold
  **/
  @ApiModelProperty(value = "")
  public Double getPageEqualThreshhold() {
    return pageEqualThreshhold;
  }

  public void setPageEqualThreshhold(Double pageEqualThreshhold) {
    this.pageEqualThreshhold = pageEqualThreshhold;
  }

  public CrawlConfig pageSimilarThreshhold(Double pageSimilarThreshhold) {
    this.pageSimilarThreshhold = pageSimilarThreshhold;
    return this;
  }

   /**
   * Get pageSimilarThreshhold
   * @return pageSimilarThreshhold
  **/
  @ApiModelProperty(value = "")
  public Double getPageSimilarThreshhold() {
    return pageSimilarThreshhold;
  }

  public void setPageSimilarThreshhold(Double pageSimilarThreshhold) {
    this.pageSimilarThreshhold = pageSimilarThreshhold;
  }

  public CrawlConfig priorityLinksRegex(String priorityLinksRegex) {
    this.priorityLinksRegex = priorityLinksRegex;
    return this;
  }

   /**
   * Get priorityLinksRegex
   * @return priorityLinksRegex
  **/
  @ApiModelProperty(value = "")
  public String getPriorityLinksRegex() {
    return priorityLinksRegex;
  }

  public void setPriorityLinksRegex(String priorityLinksRegex) {
    this.priorityLinksRegex = priorityLinksRegex;
  }

  public CrawlConfig recursionDepth(Long recursionDepth) {
    this.recursionDepth = recursionDepth;
    return this;
  }

   /**
   * Get recursionDepth
   * @return recursionDepth
  **/
  @ApiModelProperty(value = "")
  public Long getRecursionDepth() {
    return recursionDepth;
  }

  public void setRecursionDepth(Long recursionDepth) {
    this.recursionDepth = recursionDepth;
  }

  public CrawlConfig restrictToMacro(Boolean restrictToMacro) {
    this.restrictToMacro = restrictToMacro;
    return this;
  }

   /**
   * Get restrictToMacro
   * @return restrictToMacro
  **/
  @ApiModelProperty(value = "")
  public Boolean isRestrictToMacro() {
    return restrictToMacro;
  }

  public void setRestrictToMacro(Boolean restrictToMacro) {
    this.restrictToMacro = restrictToMacro;
  }

  public CrawlConfig restrictToManualCrawling(Boolean restrictToManualCrawling) {
    this.restrictToManualCrawling = restrictToManualCrawling;
    return this;
  }

   /**
   * Get restrictToManualCrawling
   * @return restrictToManualCrawling
  **/
  @ApiModelProperty(value = "")
  public Boolean isRestrictToManualCrawling() {
    return restrictToManualCrawling;
  }

  public void setRestrictToManualCrawling(Boolean restrictToManualCrawling) {
    this.restrictToManualCrawling = restrictToManualCrawling;
  }

  public CrawlConfig restrictToSeedList(Boolean restrictToSeedList) {
    this.restrictToSeedList = restrictToSeedList;
    return this;
  }

   /**
   * Get restrictToSeedList
   * @return restrictToSeedList
  **/
  @ApiModelProperty(value = "")
  public Boolean isRestrictToSeedList() {
    return restrictToSeedList;
  }

  public void setRestrictToSeedList(Boolean restrictToSeedList) {
    this.restrictToSeedList = restrictToSeedList;
  }

  public CrawlConfig restrictToSwagger(Boolean restrictToSwagger) {
    this.restrictToSwagger = restrictToSwagger;
    return this;
  }

   /**
   * Get restrictToSwagger
   * @return restrictToSwagger
  **/
  @ApiModelProperty(value = "")
  public Boolean isRestrictToSwagger() {
    return restrictToSwagger;
  }

  public void setRestrictToSwagger(Boolean restrictToSwagger) {
    this.restrictToSwagger = restrictToSwagger;
  }

  public CrawlConfig restrictToWebService(Boolean restrictToWebService) {
    this.restrictToWebService = restrictToWebService;
    return this;
  }

   /**
   * Get restrictToWebService
   * @return restrictToWebService
  **/
  @ApiModelProperty(value = "")
  public Boolean isRestrictToWebService() {
    return restrictToWebService;
  }

  public void setRestrictToWebService(Boolean restrictToWebService) {
    this.restrictToWebService = restrictToWebService;
  }

  public CrawlConfig scopeConstraintList(java.util.List<ScopeConstraint> scopeConstraintList) {
    this.scopeConstraintList = scopeConstraintList;
    return this;
  }

  public CrawlConfig addScopeConstraintListItem(ScopeConstraint scopeConstraintListItem) {
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

  public CrawlConfig searchForUrls(Boolean searchForUrls) {
    this.searchForUrls = searchForUrls;
    return this;
  }

   /**
   * Get searchForUrls
   * @return searchForUrls
  **/
  @ApiModelProperty(value = "")
  public Boolean isSearchForUrls() {
    return searchForUrls;
  }

  public void setSearchForUrls(Boolean searchForUrls) {
    this.searchForUrls = searchForUrls;
  }

  public CrawlConfig seedUrlList(java.util.List<SeedUrl> seedUrlList) {
    this.seedUrlList = seedUrlList;
    return this;
  }

  public CrawlConfig addSeedUrlListItem(SeedUrl seedUrlListItem) {
    if (this.seedUrlList == null) {
      this.seedUrlList = new java.util.ArrayList<SeedUrl>();
    }
    this.seedUrlList.add(seedUrlListItem);
    return this;
  }

   /**
   * Get seedUrlList
   * @return seedUrlList
  **/
  @ApiModelProperty(value = "")
  public java.util.List<SeedUrl> getSeedUrlList() {
    return seedUrlList;
  }

  public void setSeedUrlList(java.util.List<SeedUrl> seedUrlList) {
    this.seedUrlList = seedUrlList;
  }

  public CrawlConfig sequenceRepetitionTolerance(Long sequenceRepetitionTolerance) {
    this.sequenceRepetitionTolerance = sequenceRepetitionTolerance;
    return this;
  }

   /**
   * Get sequenceRepetitionTolerance
   * @return sequenceRepetitionTolerance
  **/
  @ApiModelProperty(value = "")
  public Long getSequenceRepetitionTolerance() {
    return sequenceRepetitionTolerance;
  }

  public void setSequenceRepetitionTolerance(Long sequenceRepetitionTolerance) {
    this.sequenceRepetitionTolerance = sequenceRepetitionTolerance;
  }

  public CrawlConfig serverErrorRegex(String serverErrorRegex) {
    this.serverErrorRegex = serverErrorRegex;
    return this;
  }

   /**
   * Get serverErrorRegex
   * @return serverErrorRegex
  **/
  @ApiModelProperty(value = "")
  public String getServerErrorRegex() {
    return serverErrorRegex;
  }

  public void setServerErrorRegex(String serverErrorRegex) {
    this.serverErrorRegex = serverErrorRegex;
  }

  public CrawlConfig stayOnPort(Boolean stayOnPort) {
    this.stayOnPort = stayOnPort;
    return this;
  }

   /**
   * Get stayOnPort
   * @return stayOnPort
  **/
  @ApiModelProperty(value = "")
  public Boolean isStayOnPort() {
    return stayOnPort;
  }

  public void setStayOnPort(Boolean stayOnPort) {
    this.stayOnPort = stayOnPort;
  }

  public CrawlConfig textContentTypeList(java.util.List<TextContentType> textContentTypeList) {
    this.textContentTypeList = textContentTypeList;
    return this;
  }

  public CrawlConfig addTextContentTypeListItem(TextContentType textContentTypeListItem) {
    if (this.textContentTypeList == null) {
      this.textContentTypeList = new java.util.ArrayList<TextContentType>();
    }
    this.textContentTypeList.add(textContentTypeListItem);
    return this;
  }

   /**
   * Get textContentTypeList
   * @return textContentTypeList
  **/
  @ApiModelProperty(value = "")
  public java.util.List<TextContentType> getTextContentTypeList() {
    return textContentTypeList;
  }

  public void setTextContentTypeList(java.util.List<TextContentType> textContentTypeList) {
    this.textContentTypeList = textContentTypeList;
  }

  public CrawlConfig textExtensionList(java.util.List<TextExtension> textExtensionList) {
    this.textExtensionList = textExtensionList;
    return this;
  }

  public CrawlConfig addTextExtensionListItem(TextExtension textExtensionListItem) {
    if (this.textExtensionList == null) {
      this.textExtensionList = new java.util.ArrayList<TextExtension>();
    }
    this.textExtensionList.add(textExtensionListItem);
    return this;
  }

   /**
   * Get textExtensionList
   * @return textExtensionList
  **/
  @ApiModelProperty(value = "")
  public java.util.List<TextExtension> getTextExtensionList() {
    return textExtensionList;
  }

  public void setTextExtensionList(java.util.List<TextExtension> textExtensionList) {
    this.textExtensionList = textExtensionList;
  }

  public CrawlConfig urlRepetitionTolerance(Long urlRepetitionTolerance) {
    this.urlRepetitionTolerance = urlRepetitionTolerance;
    return this;
  }

   /**
   * Get urlRepetitionTolerance
   * @return urlRepetitionTolerance
  **/
  @ApiModelProperty(value = "")
  public Long getUrlRepetitionTolerance() {
    return urlRepetitionTolerance;
  }

  public void setUrlRepetitionTolerance(Long urlRepetitionTolerance) {
    this.urlRepetitionTolerance = urlRepetitionTolerance;
  }

  public CrawlConfig xmlContentTypeList(java.util.List<XmlContentType> xmlContentTypeList) {
    this.xmlContentTypeList = xmlContentTypeList;
    return this;
  }

  public CrawlConfig addXmlContentTypeListItem(XmlContentType xmlContentTypeListItem) {
    if (this.xmlContentTypeList == null) {
      this.xmlContentTypeList = new java.util.ArrayList<XmlContentType>();
    }
    this.xmlContentTypeList.add(xmlContentTypeListItem);
    return this;
  }

   /**
   * Get xmlContentTypeList
   * @return xmlContentTypeList
  **/
  @ApiModelProperty(value = "")
  public java.util.List<XmlContentType> getXmlContentTypeList() {
    return xmlContentTypeList;
  }

  public void setXmlContentTypeList(java.util.List<XmlContentType> xmlContentTypeList) {
    this.xmlContentTypeList = xmlContentTypeList;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CrawlConfig crawlConfig = (CrawlConfig) o;
    return Objects.equals(this.binaryContentTypeList, crawlConfig.binaryContentTypeList) &&
        Objects.equals(this.binaryExtensionList, crawlConfig.binaryExtensionList) &&
        Objects.equals(this.blackListExtensionList, crawlConfig.blackListExtensionList) &&
        Objects.equals(this.browserDoNotDownloadContentTypeList, crawlConfig.browserDoNotDownloadContentTypeList) &&
        Objects.equals(this.browserDoNotDownloadExtensionList, crawlConfig.browserDoNotDownloadExtensionList) &&
        Objects.equals(this.browserDownloadWhitelistList, crawlConfig.browserDownloadWhitelistList) &&
        Objects.equals(this.caseSensitivity, crawlConfig.caseSensitivity) &&
        Objects.equals(this.cookieCommaSeparator, crawlConfig.cookieCommaSeparator) &&
        Objects.equals(this.crawlPrioritization, crawlConfig.crawlPrioritization) &&
        Objects.equals(this.discoveryDepth, crawlConfig.discoveryDepth) &&
        Objects.equals(this.domRestrictionsList, crawlConfig.domRestrictionsList) &&
        Objects.equals(this.enableAdvancedParsers, crawlConfig.enableAdvancedParsers) &&
        Objects.equals(this.experimentalCrawling, crawlConfig.experimentalCrawling) &&
        Objects.equals(this.fileNotFoundRegex, crawlConfig.fileNotFoundRegex) &&
        Objects.equals(this.flash, crawlConfig.flash) &&
        Objects.equals(this.frameworksCrawlConfig, crawlConfig.frameworksCrawlConfig) &&
        Objects.equals(this.grayListExtensionList, crawlConfig.grayListExtensionList) &&
        Objects.equals(this.htmlContentTypeList, crawlConfig.htmlContentTypeList) &&
        Objects.equals(this.importCookiesFromTraffic, crawlConfig.importCookiesFromTraffic) &&
        Objects.equals(this.invalidUrlRegexAttack, crawlConfig.invalidUrlRegexAttack) &&
        Objects.equals(this.invalidUrlRegexCrawl, crawlConfig.invalidUrlRegexCrawl) &&
        Objects.equals(this.lockCookies, crawlConfig.lockCookies) &&
        Objects.equals(this.lockedCookieList, crawlConfig.lockedCookieList) &&
        Objects.equals(this.maxAttackFeedbackLinksCount, crawlConfig.maxAttackFeedbackLinksCount) &&
        Objects.equals(this.maxBlackListExtCrawlResults, crawlConfig.maxBlackListExtCrawlResults) &&
        Objects.equals(this.maxBrowserDomDepth, crawlConfig.maxBrowserDomDepth) &&
        Objects.equals(this.maxBrowserEventsPerCrawlResult, crawlConfig.maxBrowserEventsPerCrawlResult) &&
        Objects.equals(this.maxBrowserEventsPerDom, crawlConfig.maxBrowserEventsPerDom) &&
        Objects.equals(this.maxBrowserEventsPerLink, crawlConfig.maxBrowserEventsPerLink) &&
        Objects.equals(this.maxBrowserNoNewResourceDomCount, crawlConfig.maxBrowserNoNewResourceDomCount) &&
        Objects.equals(this.maxBrowserPageWaitTimeout, crawlConfig.maxBrowserPageWaitTimeout) &&
        Objects.equals(this.maxBrowserWaitTillRequestTimeout, crawlConfig.maxBrowserWaitTillRequestTimeout) &&
        Objects.equals(this.maxCookiesFromJavascript, crawlConfig.maxCookiesFromJavascript) &&
        Objects.equals(this.maxCookiesSameNameFromJavascript, crawlConfig.maxCookiesSameNameFromJavascript) &&
        Objects.equals(this.maxCrawlResults, crawlConfig.maxCrawlResults) &&
        Objects.equals(this.maxDirDepth, crawlConfig.maxDirDepth) &&
        Objects.equals(this.maxDomain, crawlConfig.maxDomain) &&
        Objects.equals(this.maxPerDirChildNodes, crawlConfig.maxPerDirChildNodes) &&
        Objects.equals(this.maxPerDirCrawlResults, crawlConfig.maxPerDirCrawlResults) &&
        Objects.equals(this.maxPerFileNameCrawlResults, crawlConfig.maxPerFileNameCrawlResults) &&
        Objects.equals(this.maxPerLinkCrawlResults, crawlConfig.maxPerLinkCrawlResults) &&
        Objects.equals(this.maxPerNormalizedLinkCrawlResult, crawlConfig.maxPerNormalizedLinkCrawlResult) &&
        Objects.equals(this.maxPerWebSiteCrawlResults, crawlConfig.maxPerWebSiteCrawlResults) &&
        Objects.equals(this.maxReportedComments, crawlConfig.maxReportedComments) &&
        Objects.equals(this.maxReportedEmails, crawlConfig.maxReportedEmails) &&
        Objects.equals(this.maxReportedForms, crawlConfig.maxReportedForms) &&
        Objects.equals(this.maxReportedImages, crawlConfig.maxReportedImages) &&
        Objects.equals(this.maxReportedLinks, crawlConfig.maxReportedLinks) &&
        Objects.equals(this.maxReportedScripts, crawlConfig.maxReportedScripts) &&
        Objects.equals(this.notInsertedLinkCountThreshold, crawlConfig.notInsertedLinkCountThreshold) &&
        Objects.equals(this.pageEqualThreshhold, crawlConfig.pageEqualThreshhold) &&
        Objects.equals(this.pageSimilarThreshhold, crawlConfig.pageSimilarThreshhold) &&
        Objects.equals(this.priorityLinksRegex, crawlConfig.priorityLinksRegex) &&
        Objects.equals(this.recursionDepth, crawlConfig.recursionDepth) &&
        Objects.equals(this.restrictToMacro, crawlConfig.restrictToMacro) &&
        Objects.equals(this.restrictToManualCrawling, crawlConfig.restrictToManualCrawling) &&
        Objects.equals(this.restrictToSeedList, crawlConfig.restrictToSeedList) &&
        Objects.equals(this.restrictToSwagger, crawlConfig.restrictToSwagger) &&
        Objects.equals(this.restrictToWebService, crawlConfig.restrictToWebService) &&
        Objects.equals(this.scopeConstraintList, crawlConfig.scopeConstraintList) &&
        Objects.equals(this.searchForUrls, crawlConfig.searchForUrls) &&
        Objects.equals(this.seedUrlList, crawlConfig.seedUrlList) &&
        Objects.equals(this.sequenceRepetitionTolerance, crawlConfig.sequenceRepetitionTolerance) &&
        Objects.equals(this.serverErrorRegex, crawlConfig.serverErrorRegex) &&
        Objects.equals(this.stayOnPort, crawlConfig.stayOnPort) &&
        Objects.equals(this.textContentTypeList, crawlConfig.textContentTypeList) &&
        Objects.equals(this.textExtensionList, crawlConfig.textExtensionList) &&
        Objects.equals(this.urlRepetitionTolerance, crawlConfig.urlRepetitionTolerance) &&
        Objects.equals(this.xmlContentTypeList, crawlConfig.xmlContentTypeList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(binaryContentTypeList, binaryExtensionList, blackListExtensionList, browserDoNotDownloadContentTypeList, browserDoNotDownloadExtensionList, browserDownloadWhitelistList, caseSensitivity, cookieCommaSeparator, crawlPrioritization, discoveryDepth, domRestrictionsList, enableAdvancedParsers, experimentalCrawling, fileNotFoundRegex, flash, frameworksCrawlConfig, grayListExtensionList, htmlContentTypeList, importCookiesFromTraffic, invalidUrlRegexAttack, invalidUrlRegexCrawl, lockCookies, lockedCookieList, maxAttackFeedbackLinksCount, maxBlackListExtCrawlResults, maxBrowserDomDepth, maxBrowserEventsPerCrawlResult, maxBrowserEventsPerDom, maxBrowserEventsPerLink, maxBrowserNoNewResourceDomCount, maxBrowserPageWaitTimeout, maxBrowserWaitTillRequestTimeout, maxCookiesFromJavascript, maxCookiesSameNameFromJavascript, maxCrawlResults, maxDirDepth, maxDomain, maxPerDirChildNodes, maxPerDirCrawlResults, maxPerFileNameCrawlResults, maxPerLinkCrawlResults, maxPerNormalizedLinkCrawlResult, maxPerWebSiteCrawlResults, maxReportedComments, maxReportedEmails, maxReportedForms, maxReportedImages, maxReportedLinks, maxReportedScripts, notInsertedLinkCountThreshold, pageEqualThreshhold, pageSimilarThreshhold, priorityLinksRegex, recursionDepth, restrictToMacro, restrictToManualCrawling, restrictToSeedList, restrictToSwagger, restrictToWebService, scopeConstraintList, searchForUrls, seedUrlList, sequenceRepetitionTolerance, serverErrorRegex, stayOnPort, textContentTypeList, textExtensionList, urlRepetitionTolerance, xmlContentTypeList);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CrawlConfig {\n");

    sb.append("    binaryContentTypeList: ").append(toIndentedString(binaryContentTypeList)).append("\n");
    sb.append("    binaryExtensionList: ").append(toIndentedString(binaryExtensionList)).append("\n");
    sb.append("    blackListExtensionList: ").append(toIndentedString(blackListExtensionList)).append("\n");
    sb.append("    browserDoNotDownloadContentTypeList: ").append(toIndentedString(browserDoNotDownloadContentTypeList)).append("\n");
    sb.append("    browserDoNotDownloadExtensionList: ").append(toIndentedString(browserDoNotDownloadExtensionList)).append("\n");
    sb.append("    browserDownloadWhitelistList: ").append(toIndentedString(browserDownloadWhitelistList)).append("\n");
    sb.append("    caseSensitivity: ").append(toIndentedString(caseSensitivity)).append("\n");
    sb.append("    cookieCommaSeparator: ").append(toIndentedString(cookieCommaSeparator)).append("\n");
    sb.append("    crawlPrioritization: ").append(toIndentedString(crawlPrioritization)).append("\n");
    sb.append("    discoveryDepth: ").append(toIndentedString(discoveryDepth)).append("\n");
    sb.append("    domRestrictionsList: ").append(toIndentedString(domRestrictionsList)).append("\n");
    sb.append("    enableAdvancedParsers: ").append(toIndentedString(enableAdvancedParsers)).append("\n");
    sb.append("    experimentalCrawling: ").append(toIndentedString(experimentalCrawling)).append("\n");
    sb.append("    fileNotFoundRegex: ").append(toIndentedString(fileNotFoundRegex)).append("\n");
    sb.append("    flash: ").append(toIndentedString(flash)).append("\n");
    sb.append("    frameworksCrawlConfig: ").append(toIndentedString(frameworksCrawlConfig)).append("\n");
    sb.append("    grayListExtensionList: ").append(toIndentedString(grayListExtensionList)).append("\n");
    sb.append("    htmlContentTypeList: ").append(toIndentedString(htmlContentTypeList)).append("\n");
    sb.append("    importCookiesFromTraffic: ").append(toIndentedString(importCookiesFromTraffic)).append("\n");
    sb.append("    invalidUrlRegexAttack: ").append(toIndentedString(invalidUrlRegexAttack)).append("\n");
    sb.append("    invalidUrlRegexCrawl: ").append(toIndentedString(invalidUrlRegexCrawl)).append("\n");
    sb.append("    lockCookies: ").append(toIndentedString(lockCookies)).append("\n");
    sb.append("    lockedCookieList: ").append(toIndentedString(lockedCookieList)).append("\n");
    sb.append("    maxAttackFeedbackLinksCount: ").append(toIndentedString(maxAttackFeedbackLinksCount)).append("\n");
    sb.append("    maxBlackListExtCrawlResults: ").append(toIndentedString(maxBlackListExtCrawlResults)).append("\n");
    sb.append("    maxBrowserDomDepth: ").append(toIndentedString(maxBrowserDomDepth)).append("\n");
    sb.append("    maxBrowserEventsPerCrawlResult: ").append(toIndentedString(maxBrowserEventsPerCrawlResult)).append("\n");
    sb.append("    maxBrowserEventsPerDom: ").append(toIndentedString(maxBrowserEventsPerDom)).append("\n");
    sb.append("    maxBrowserEventsPerLink: ").append(toIndentedString(maxBrowserEventsPerLink)).append("\n");
    sb.append("    maxBrowserNoNewResourceDomCount: ").append(toIndentedString(maxBrowserNoNewResourceDomCount)).append("\n");
    sb.append("    maxBrowserPageWaitTimeout: ").append(toIndentedString(maxBrowserPageWaitTimeout)).append("\n");
    sb.append("    maxBrowserWaitTillRequestTimeout: ").append(toIndentedString(maxBrowserWaitTillRequestTimeout)).append("\n");
    sb.append("    maxCookiesFromJavascript: ").append(toIndentedString(maxCookiesFromJavascript)).append("\n");
    sb.append("    maxCookiesSameNameFromJavascript: ").append(toIndentedString(maxCookiesSameNameFromJavascript)).append("\n");
    sb.append("    maxCrawlResults: ").append(toIndentedString(maxCrawlResults)).append("\n");
    sb.append("    maxDirDepth: ").append(toIndentedString(maxDirDepth)).append("\n");
    sb.append("    maxDomain: ").append(toIndentedString(maxDomain)).append("\n");
    sb.append("    maxPerDirChildNodes: ").append(toIndentedString(maxPerDirChildNodes)).append("\n");
    sb.append("    maxPerDirCrawlResults: ").append(toIndentedString(maxPerDirCrawlResults)).append("\n");
    sb.append("    maxPerFileNameCrawlResults: ").append(toIndentedString(maxPerFileNameCrawlResults)).append("\n");
    sb.append("    maxPerLinkCrawlResults: ").append(toIndentedString(maxPerLinkCrawlResults)).append("\n");
    sb.append("    maxPerNormalizedLinkCrawlResult: ").append(toIndentedString(maxPerNormalizedLinkCrawlResult)).append("\n");
    sb.append("    maxPerWebSiteCrawlResults: ").append(toIndentedString(maxPerWebSiteCrawlResults)).append("\n");
    sb.append("    maxReportedComments: ").append(toIndentedString(maxReportedComments)).append("\n");
    sb.append("    maxReportedEmails: ").append(toIndentedString(maxReportedEmails)).append("\n");
    sb.append("    maxReportedForms: ").append(toIndentedString(maxReportedForms)).append("\n");
    sb.append("    maxReportedImages: ").append(toIndentedString(maxReportedImages)).append("\n");
    sb.append("    maxReportedLinks: ").append(toIndentedString(maxReportedLinks)).append("\n");
    sb.append("    maxReportedScripts: ").append(toIndentedString(maxReportedScripts)).append("\n");
    sb.append("    notInsertedLinkCountThreshold: ").append(toIndentedString(notInsertedLinkCountThreshold)).append("\n");
    sb.append("    pageEqualThreshhold: ").append(toIndentedString(pageEqualThreshhold)).append("\n");
    sb.append("    pageSimilarThreshhold: ").append(toIndentedString(pageSimilarThreshhold)).append("\n");
    sb.append("    priorityLinksRegex: ").append(toIndentedString(priorityLinksRegex)).append("\n");
    sb.append("    recursionDepth: ").append(toIndentedString(recursionDepth)).append("\n");
    sb.append("    restrictToMacro: ").append(toIndentedString(restrictToMacro)).append("\n");
    sb.append("    restrictToManualCrawling: ").append(toIndentedString(restrictToManualCrawling)).append("\n");
    sb.append("    restrictToSeedList: ").append(toIndentedString(restrictToSeedList)).append("\n");
    sb.append("    restrictToSwagger: ").append(toIndentedString(restrictToSwagger)).append("\n");
    sb.append("    restrictToWebService: ").append(toIndentedString(restrictToWebService)).append("\n");
    sb.append("    scopeConstraintList: ").append(toIndentedString(scopeConstraintList)).append("\n");
    sb.append("    searchForUrls: ").append(toIndentedString(searchForUrls)).append("\n");
    sb.append("    seedUrlList: ").append(toIndentedString(seedUrlList)).append("\n");
    sb.append("    sequenceRepetitionTolerance: ").append(toIndentedString(sequenceRepetitionTolerance)).append("\n");
    sb.append("    serverErrorRegex: ").append(toIndentedString(serverErrorRegex)).append("\n");
    sb.append("    stayOnPort: ").append(toIndentedString(stayOnPort)).append("\n");
    sb.append("    textContentTypeList: ").append(toIndentedString(textContentTypeList)).append("\n");
    sb.append("    textExtensionList: ").append(toIndentedString(textExtensionList)).append("\n");
    sb.append("    urlRepetitionTolerance: ").append(toIndentedString(urlRepetitionTolerance)).append("\n");
    sb.append("    xmlContentTypeList: ").append(toIndentedString(xmlContentTypeList)).append("\n");
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

