/*
 * Engines API
 * Allow clients to fetch Analytics through APIs.
 *
 * The version of the OpenAPI document: v3:[pa,vault,pub,quant,fi,axp,afi,npo,bpm,fpo,security-modeling,others],v1:[fiab]
 * Contact: api@factset.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package factset.analyticsapi.engines.models;

import java.util.Objects;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import factset.analyticsapi.engines.models.PACalculationColumn;
import factset.analyticsapi.engines.models.PACalculationDataSources;
import factset.analyticsapi.engines.models.PACalculationGroup;
import factset.analyticsapi.engines.models.PADateParameters;
import factset.analyticsapi.engines.models.PAIdentifier;
import factset.analyticsapi.engines.models.TemplateContentTypes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * UnlinkedPATemplateUpdateParameters
 */
@JsonPropertyOrder({
  UnlinkedPATemplateUpdateParameters.JSON_PROPERTY_DESCRIPTION,
  UnlinkedPATemplateUpdateParameters.JSON_PROPERTY_ACCOUNTS,
  UnlinkedPATemplateUpdateParameters.JSON_PROPERTY_BENCHMARKS,
  UnlinkedPATemplateUpdateParameters.JSON_PROPERTY_COLUMNS,
  UnlinkedPATemplateUpdateParameters.JSON_PROPERTY_DATES,
  UnlinkedPATemplateUpdateParameters.JSON_PROPERTY_GROUPS,
  UnlinkedPATemplateUpdateParameters.JSON_PROPERTY_DATASOURCES,
  UnlinkedPATemplateUpdateParameters.JSON_PROPERTY_CURRENCYISOCODE,
  UnlinkedPATemplateUpdateParameters.JSON_PROPERTY_COMPONENTDETAIL,
  UnlinkedPATemplateUpdateParameters.JSON_PROPERTY_CONTENT
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class UnlinkedPATemplateUpdateParameters implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_DESCRIPTION = "description";
  private String description;

  public static final String JSON_PROPERTY_ACCOUNTS = "accounts";
  private java.util.List<PAIdentifier> accounts = null;

  public static final String JSON_PROPERTY_BENCHMARKS = "benchmarks";
  private java.util.List<PAIdentifier> benchmarks = null;

  public static final String JSON_PROPERTY_COLUMNS = "columns";
  private java.util.List<PACalculationColumn> columns = null;

  public static final String JSON_PROPERTY_DATES = "dates";
  private PADateParameters dates;

  public static final String JSON_PROPERTY_GROUPS = "groups";
  private java.util.List<PACalculationGroup> groups = null;

  public static final String JSON_PROPERTY_DATASOURCES = "datasources";
  private PACalculationDataSources datasources;

  public static final String JSON_PROPERTY_CURRENCYISOCODE = "currencyisocode";
  private String currencyisocode;

  public static final String JSON_PROPERTY_COMPONENTDETAIL = "componentdetail";
  private String componentdetail;

  public static final String JSON_PROPERTY_CONTENT = "content";
  private TemplateContentTypes content;


  public UnlinkedPATemplateUpdateParameters description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Template description
   * @return description
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Template description")
  @JsonProperty(JSON_PROPERTY_DESCRIPTION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    this.description = description;
  }


  public UnlinkedPATemplateUpdateParameters accounts(java.util.List<PAIdentifier> accounts) {
    this.accounts = accounts;
    return this;
  }

  public UnlinkedPATemplateUpdateParameters addAccountsItem(PAIdentifier accountsItem) {
    if (this.accounts == null) {
      this.accounts = new java.util.ArrayList<PAIdentifier>();
    }
    this.accounts.add(accountsItem);
    return this;
  }

   /**
   * List of accounts
   * @return accounts
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "List of accounts")
  @JsonProperty(JSON_PROPERTY_ACCOUNTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public java.util.List<PAIdentifier> getAccounts() {
    return accounts;
  }


  public void setAccounts(java.util.List<PAIdentifier> accounts) {
    this.accounts = accounts;
  }


  public UnlinkedPATemplateUpdateParameters benchmarks(java.util.List<PAIdentifier> benchmarks) {
    this.benchmarks = benchmarks;
    return this;
  }

  public UnlinkedPATemplateUpdateParameters addBenchmarksItem(PAIdentifier benchmarksItem) {
    if (this.benchmarks == null) {
      this.benchmarks = new java.util.ArrayList<PAIdentifier>();
    }
    this.benchmarks.add(benchmarksItem);
    return this;
  }

   /**
   * List of benchmarks
   * @return benchmarks
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "List of benchmarks")
  @JsonProperty(JSON_PROPERTY_BENCHMARKS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public java.util.List<PAIdentifier> getBenchmarks() {
    return benchmarks;
  }


  public void setBenchmarks(java.util.List<PAIdentifier> benchmarks) {
    this.benchmarks = benchmarks;
  }


  public UnlinkedPATemplateUpdateParameters columns(java.util.List<PACalculationColumn> columns) {
    this.columns = columns;
    return this;
  }

  public UnlinkedPATemplateUpdateParameters addColumnsItem(PACalculationColumn columnsItem) {
    if (this.columns == null) {
      this.columns = new java.util.ArrayList<PACalculationColumn>();
    }
    this.columns.add(columnsItem);
    return this;
  }

   /**
   * List of columns for the PA calculation
   * @return columns
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "List of columns for the PA calculation")
  @JsonProperty(JSON_PROPERTY_COLUMNS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public java.util.List<PACalculationColumn> getColumns() {
    return columns;
  }


  public void setColumns(java.util.List<PACalculationColumn> columns) {
    this.columns = columns;
  }


  public UnlinkedPATemplateUpdateParameters dates(PADateParameters dates) {
    this.dates = dates;
    return this;
  }

   /**
   * Get dates
   * @return dates
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_DATES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public PADateParameters getDates() {
    return dates;
  }


  public void setDates(PADateParameters dates) {
    this.dates = dates;
  }


  public UnlinkedPATemplateUpdateParameters groups(java.util.List<PACalculationGroup> groups) {
    this.groups = groups;
    return this;
  }

  public UnlinkedPATemplateUpdateParameters addGroupsItem(PACalculationGroup groupsItem) {
    if (this.groups == null) {
      this.groups = new java.util.ArrayList<PACalculationGroup>();
    }
    this.groups.add(groupsItem);
    return this;
  }

   /**
   * List of groupings for the PA calculation
   * @return groups
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "List of groupings for the PA calculation")
  @JsonProperty(JSON_PROPERTY_GROUPS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public java.util.List<PACalculationGroup> getGroups() {
    return groups;
  }


  public void setGroups(java.util.List<PACalculationGroup> groups) {
    this.groups = groups;
  }


  public UnlinkedPATemplateUpdateParameters datasources(PACalculationDataSources datasources) {
    this.datasources = datasources;
    return this;
  }

   /**
   * Get datasources
   * @return datasources
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_DATASOURCES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public PACalculationDataSources getDatasources() {
    return datasources;
  }


  public void setDatasources(PACalculationDataSources datasources) {
    this.datasources = datasources;
  }


  public UnlinkedPATemplateUpdateParameters currencyisocode(String currencyisocode) {
    this.currencyisocode = currencyisocode;
    return this;
  }

   /**
   * Currency ISO code for calculation.
   * @return currencyisocode
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Currency ISO code for calculation.")
  @JsonProperty(JSON_PROPERTY_CURRENCYISOCODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCurrencyisocode() {
    return currencyisocode;
  }


  public void setCurrencyisocode(String currencyisocode) {
    this.currencyisocode = currencyisocode;
  }


  public UnlinkedPATemplateUpdateParameters componentdetail(String componentdetail) {
    this.componentdetail = componentdetail;
    return this;
  }

   /**
   * PA storage type. It can be GROUPS or TOTALS or SECURITIES.
   * @return componentdetail
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "PA storage type. It can be GROUPS or TOTALS or SECURITIES.")
  @JsonProperty(JSON_PROPERTY_COMPONENTDETAIL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getComponentdetail() {
    return componentdetail;
  }


  public void setComponentdetail(String componentdetail) {
    this.componentdetail = componentdetail;
  }


  public UnlinkedPATemplateUpdateParameters content(TemplateContentTypes content) {
    this.content = content;
    return this;
  }

   /**
   * Get content
   * @return content
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_CONTENT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public TemplateContentTypes getContent() {
    return content;
  }


  public void setContent(TemplateContentTypes content) {
    this.content = content;
  }


  /**
   * Return true if this UnlinkedPATemplateUpdateParameters object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UnlinkedPATemplateUpdateParameters unlinkedPATemplateUpdateParameters = (UnlinkedPATemplateUpdateParameters) o;
    return Objects.equals(this.description, unlinkedPATemplateUpdateParameters.description) &&
        Objects.equals(this.accounts, unlinkedPATemplateUpdateParameters.accounts) &&
        Objects.equals(this.benchmarks, unlinkedPATemplateUpdateParameters.benchmarks) &&
        Objects.equals(this.columns, unlinkedPATemplateUpdateParameters.columns) &&
        Objects.equals(this.dates, unlinkedPATemplateUpdateParameters.dates) &&
        Objects.equals(this.groups, unlinkedPATemplateUpdateParameters.groups) &&
        Objects.equals(this.datasources, unlinkedPATemplateUpdateParameters.datasources) &&
        Objects.equals(this.currencyisocode, unlinkedPATemplateUpdateParameters.currencyisocode) &&
        Objects.equals(this.componentdetail, unlinkedPATemplateUpdateParameters.componentdetail) &&
        Objects.equals(this.content, unlinkedPATemplateUpdateParameters.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, accounts, benchmarks, columns, dates, groups, datasources, currencyisocode, componentdetail, content);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UnlinkedPATemplateUpdateParameters {\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    accounts: ").append(toIndentedString(accounts)).append("\n");
    sb.append("    benchmarks: ").append(toIndentedString(benchmarks)).append("\n");
    sb.append("    columns: ").append(toIndentedString(columns)).append("\n");
    sb.append("    dates: ").append(toIndentedString(dates)).append("\n");
    sb.append("    groups: ").append(toIndentedString(groups)).append("\n");
    sb.append("    datasources: ").append(toIndentedString(datasources)).append("\n");
    sb.append("    currencyisocode: ").append(toIndentedString(currencyisocode)).append("\n");
    sb.append("    componentdetail: ").append(toIndentedString(componentdetail)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
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

