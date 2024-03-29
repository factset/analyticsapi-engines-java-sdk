/*
 * Engines API
 * Allow clients to fetch Analytics through APIs.
 *
 * The version of the OpenAPI document: v3:[pa,spar,vault,pub,quant,fi,axp,afi,npo,bpm,fpo,others],v1:[fiab]
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
 * UnlinkedPATemplate
 */
@JsonPropertyOrder({
  UnlinkedPATemplate.JSON_PROPERTY_ID,
  UnlinkedPATemplate.JSON_PROPERTY_DIRECTORY,
  UnlinkedPATemplate.JSON_PROPERTY_TEMPLATE_TYPE_ID,
  UnlinkedPATemplate.JSON_PROPERTY_SNAPSHOT,
  UnlinkedPATemplate.JSON_PROPERTY_ACCOUNTS,
  UnlinkedPATemplate.JSON_PROPERTY_BENCHMARKS,
  UnlinkedPATemplate.JSON_PROPERTY_COLUMNS,
  UnlinkedPATemplate.JSON_PROPERTY_DATES,
  UnlinkedPATemplate.JSON_PROPERTY_GROUPS,
  UnlinkedPATemplate.JSON_PROPERTY_DATASOURCES,
  UnlinkedPATemplate.JSON_PROPERTY_CURRENCYISOCODE,
  UnlinkedPATemplate.JSON_PROPERTY_COMPONENTDETAIL,
  UnlinkedPATemplate.JSON_PROPERTY_CONTENT,
  UnlinkedPATemplate.JSON_PROPERTY_DESCRIPTION,
  UnlinkedPATemplate.JSON_PROPERTY_NAME,
  UnlinkedPATemplate.JSON_PROPERTY_CATEGORY
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class UnlinkedPATemplate implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_ID = "id";
  private String id;

  public static final String JSON_PROPERTY_DIRECTORY = "directory";
  private String directory;

  public static final String JSON_PROPERTY_TEMPLATE_TYPE_ID = "templateTypeId";
  private String templateTypeId;

  public static final String JSON_PROPERTY_SNAPSHOT = "snapshot";
  private Boolean snapshot;

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

  public static final String JSON_PROPERTY_DESCRIPTION = "description";
  private String description;

  public static final String JSON_PROPERTY_NAME = "name";
  private String name;

  public static final String JSON_PROPERTY_CATEGORY = "category";
  private String category;


  public UnlinkedPATemplate id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Template id.
   * @return id
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Template id.")
  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
  }


  public UnlinkedPATemplate directory(String directory) {
    this.directory = directory;
    return this;
  }

   /**
   * Template directory.
   * @return directory
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Template directory.")
  @JsonProperty(JSON_PROPERTY_DIRECTORY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getDirectory() {
    return directory;
  }


  public void setDirectory(String directory) {
    this.directory = directory;
  }


  public UnlinkedPATemplate templateTypeId(String templateTypeId) {
    this.templateTypeId = templateTypeId;
    return this;
  }

   /**
   * Template type id
   * @return templateTypeId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Template type id")
  @JsonProperty(JSON_PROPERTY_TEMPLATE_TYPE_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getTemplateTypeId() {
    return templateTypeId;
  }


  public void setTemplateTypeId(String templateTypeId) {
    this.templateTypeId = templateTypeId;
  }


  public UnlinkedPATemplate snapshot(Boolean snapshot) {
    this.snapshot = snapshot;
    return this;
  }

   /**
   * snapshot.
   * @return snapshot
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "snapshot.")
  @JsonProperty(JSON_PROPERTY_SNAPSHOT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getSnapshot() {
    return snapshot;
  }


  public void setSnapshot(Boolean snapshot) {
    this.snapshot = snapshot;
  }


  public UnlinkedPATemplate accounts(java.util.List<PAIdentifier> accounts) {
    this.accounts = accounts;
    return this;
  }

  public UnlinkedPATemplate addAccountsItem(PAIdentifier accountsItem) {
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


  public UnlinkedPATemplate benchmarks(java.util.List<PAIdentifier> benchmarks) {
    this.benchmarks = benchmarks;
    return this;
  }

  public UnlinkedPATemplate addBenchmarksItem(PAIdentifier benchmarksItem) {
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


  public UnlinkedPATemplate columns(java.util.List<PACalculationColumn> columns) {
    this.columns = columns;
    return this;
  }

  public UnlinkedPATemplate addColumnsItem(PACalculationColumn columnsItem) {
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


  public UnlinkedPATemplate dates(PADateParameters dates) {
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


  public UnlinkedPATemplate groups(java.util.List<PACalculationGroup> groups) {
    this.groups = groups;
    return this;
  }

  public UnlinkedPATemplate addGroupsItem(PACalculationGroup groupsItem) {
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


  public UnlinkedPATemplate datasources(PACalculationDataSources datasources) {
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


  public UnlinkedPATemplate currencyisocode(String currencyisocode) {
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


  public UnlinkedPATemplate componentdetail(String componentdetail) {
    this.componentdetail = componentdetail;
    return this;
  }

   /**
   * PA storage type. It can be GROUPS or GROUPSALL or TOTALS or SECURITIES.
   * @return componentdetail
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "PA storage type. It can be GROUPS or GROUPSALL or TOTALS or SECURITIES.")
  @JsonProperty(JSON_PROPERTY_COMPONENTDETAIL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getComponentdetail() {
    return componentdetail;
  }


  public void setComponentdetail(String componentdetail) {
    this.componentdetail = componentdetail;
  }


  public UnlinkedPATemplate content(TemplateContentTypes content) {
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


  public UnlinkedPATemplate description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Template description.
   * @return description
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Template description.")
  @JsonProperty(JSON_PROPERTY_DESCRIPTION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    this.description = description;
  }


  public UnlinkedPATemplate name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Template name.
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Template name.")
  @JsonProperty(JSON_PROPERTY_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public UnlinkedPATemplate category(String category) {
    this.category = category;
    return this;
  }

   /**
   * Unlinked template category
   * @return category
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Unlinked template category")
  @JsonProperty(JSON_PROPERTY_CATEGORY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCategory() {
    return category;
  }


  public void setCategory(String category) {
    this.category = category;
  }


  /**
   * Return true if this UnlinkedPATemplate object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UnlinkedPATemplate unlinkedPATemplate = (UnlinkedPATemplate) o;
    return Objects.equals(this.id, unlinkedPATemplate.id) &&
        Objects.equals(this.directory, unlinkedPATemplate.directory) &&
        Objects.equals(this.templateTypeId, unlinkedPATemplate.templateTypeId) &&
        Objects.equals(this.snapshot, unlinkedPATemplate.snapshot) &&
        Objects.equals(this.accounts, unlinkedPATemplate.accounts) &&
        Objects.equals(this.benchmarks, unlinkedPATemplate.benchmarks) &&
        Objects.equals(this.columns, unlinkedPATemplate.columns) &&
        Objects.equals(this.dates, unlinkedPATemplate.dates) &&
        Objects.equals(this.groups, unlinkedPATemplate.groups) &&
        Objects.equals(this.datasources, unlinkedPATemplate.datasources) &&
        Objects.equals(this.currencyisocode, unlinkedPATemplate.currencyisocode) &&
        Objects.equals(this.componentdetail, unlinkedPATemplate.componentdetail) &&
        Objects.equals(this.content, unlinkedPATemplate.content) &&
        Objects.equals(this.description, unlinkedPATemplate.description) &&
        Objects.equals(this.name, unlinkedPATemplate.name) &&
        Objects.equals(this.category, unlinkedPATemplate.category);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, directory, templateTypeId, snapshot, accounts, benchmarks, columns, dates, groups, datasources, currencyisocode, componentdetail, content, description, name, category);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UnlinkedPATemplate {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    directory: ").append(toIndentedString(directory)).append("\n");
    sb.append("    templateTypeId: ").append(toIndentedString(templateTypeId)).append("\n");
    sb.append("    snapshot: ").append(toIndentedString(snapshot)).append("\n");
    sb.append("    accounts: ").append(toIndentedString(accounts)).append("\n");
    sb.append("    benchmarks: ").append(toIndentedString(benchmarks)).append("\n");
    sb.append("    columns: ").append(toIndentedString(columns)).append("\n");
    sb.append("    dates: ").append(toIndentedString(dates)).append("\n");
    sb.append("    groups: ").append(toIndentedString(groups)).append("\n");
    sb.append("    datasources: ").append(toIndentedString(datasources)).append("\n");
    sb.append("    currencyisocode: ").append(toIndentedString(currencyisocode)).append("\n");
    sb.append("    componentdetail: ").append(toIndentedString(componentdetail)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
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

