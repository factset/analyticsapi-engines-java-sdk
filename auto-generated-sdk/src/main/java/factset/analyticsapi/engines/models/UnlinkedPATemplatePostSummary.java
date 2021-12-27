/*
 * Engines API
 * Allow clients to fetch Analytics through APIs.
 *
 * The version of the OpenAPI document: v3:[pa,spar,vault,pub,quant,fi,axp,afi,npo,bpm,fpo,others],v1:[fiab]
 * Contact: analytics.api.support@factset.com
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
 * UnlinkedPATemplatePostSummary
 */
@JsonPropertyOrder({
  UnlinkedPATemplatePostSummary.JSON_PROPERTY_ID,
  UnlinkedPATemplatePostSummary.JSON_PROPERTY_DESCRIPTION,
  UnlinkedPATemplatePostSummary.JSON_PROPERTY_NAME,
  UnlinkedPATemplatePostSummary.JSON_PROPERTY_CATEGORY
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class UnlinkedPATemplatePostSummary implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_ID = "id";
  private String id;

  public static final String JSON_PROPERTY_DESCRIPTION = "description";
  private String description;

  public static final String JSON_PROPERTY_NAME = "name";
  private String name;

  public static final String JSON_PROPERTY_CATEGORY = "category";
  private String category;

  public UnlinkedPATemplatePostSummary() { 
  }

  public UnlinkedPATemplatePostSummary id(String id) {
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


  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setId(String id) {
    this.id = id;
  }


  public UnlinkedPATemplatePostSummary description(String description) {
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


  @JsonProperty(JSON_PROPERTY_DESCRIPTION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setDescription(String description) {
    this.description = description;
  }


  public UnlinkedPATemplatePostSummary name(String name) {
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


  @JsonProperty(JSON_PROPERTY_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setName(String name) {
    this.name = name;
  }


  public UnlinkedPATemplatePostSummary category(String category) {
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


  @JsonProperty(JSON_PROPERTY_CATEGORY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCategory(String category) {
    this.category = category;
  }


  /**
   * Return true if this UnlinkedPATemplatePostSummary object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UnlinkedPATemplatePostSummary unlinkedPATemplatePostSummary = (UnlinkedPATemplatePostSummary) o;
    return Objects.equals(this.id, unlinkedPATemplatePostSummary.id) &&
        Objects.equals(this.description, unlinkedPATemplatePostSummary.description) &&
        Objects.equals(this.name, unlinkedPATemplatePostSummary.name) &&
        Objects.equals(this.category, unlinkedPATemplatePostSummary.category);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, description, name, category);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UnlinkedPATemplatePostSummary {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

