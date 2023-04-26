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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * Column
 */
@JsonPropertyOrder({
  Column.JSON_PROPERTY_DEFAULTSTATISTICSIDS,
  Column.JSON_PROPERTY_NAME,
  Column.JSON_PROPERTY_DIRECTORY,
  Column.JSON_PROPERTY_CATEGORY
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class Column implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_DEFAULTSTATISTICSIDS = "defaultstatisticsids";
  private java.util.List<String> defaultstatisticsids = null;

  public static final String JSON_PROPERTY_NAME = "name";
  private String name;

  public static final String JSON_PROPERTY_DIRECTORY = "directory";
  private String directory;

  public static final String JSON_PROPERTY_CATEGORY = "category";
  private String category;


  public Column defaultstatisticsids(java.util.List<String> defaultstatisticsids) {
    this.defaultstatisticsids = defaultstatisticsids;
    return this;
  }

  public Column addDefaultstatisticsidsItem(String defaultstatisticsidsItem) {
    if (this.defaultstatisticsids == null) {
      this.defaultstatisticsids = new java.util.ArrayList<String>();
    }
    this.defaultstatisticsids.add(defaultstatisticsidsItem);
    return this;
  }

   /**
   * Column statistic Id
   * @return defaultstatisticsids
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Column statistic Id")
  @JsonProperty(JSON_PROPERTY_DEFAULTSTATISTICSIDS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public java.util.List<String> getDefaultstatisticsids() {
    return defaultstatisticsids;
  }


  public void setDefaultstatisticsids(java.util.List<String> defaultstatisticsids) {
    this.defaultstatisticsids = defaultstatisticsids;
  }


  public Column name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Column Name
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Column Name")
  @JsonProperty(JSON_PROPERTY_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public Column directory(String directory) {
    this.directory = directory;
    return this;
  }

   /**
   * Column Directory
   * @return directory
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Column Directory")
  @JsonProperty(JSON_PROPERTY_DIRECTORY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getDirectory() {
    return directory;
  }


  public void setDirectory(String directory) {
    this.directory = directory;
  }


  public Column category(String category) {
    this.category = category;
    return this;
  }

   /**
   * Column Category
   * @return category
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Column Category")
  @JsonProperty(JSON_PROPERTY_CATEGORY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCategory() {
    return category;
  }


  public void setCategory(String category) {
    this.category = category;
  }


  /**
   * Return true if this Column object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Column column = (Column) o;
    return Objects.equals(this.defaultstatisticsids, column.defaultstatisticsids) &&
        Objects.equals(this.name, column.name) &&
        Objects.equals(this.directory, column.directory) &&
        Objects.equals(this.category, column.category);
  }

  @Override
  public int hashCode() {
    return Objects.hash(defaultstatisticsids, name, directory, category);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Column {\n");
    sb.append("    defaultstatisticsids: ").append(toIndentedString(defaultstatisticsids)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    directory: ").append(toIndentedString(directory)).append("\n");
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

