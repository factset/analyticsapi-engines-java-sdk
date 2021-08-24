/*
 * Engines API
 * Allow clients to fetch Engines Analytics through APIs.
 *
 * The version of the OpenAPI document: 3
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
 * LinkedPATemplateSummary
 */
@JsonPropertyOrder({
  LinkedPATemplateSummary.JSON_PROPERTY_DESCRIPTION,
  LinkedPATemplateSummary.JSON_PROPERTY_NAME,
  LinkedPATemplateSummary.JSON_PROPERTY_PARENT_COMPONENT_ID
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class LinkedPATemplateSummary implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_DESCRIPTION = "description";
  private String description;

  public static final String JSON_PROPERTY_NAME = "name";
  private String name;

  public static final String JSON_PROPERTY_PARENT_COMPONENT_ID = "parentComponentId";
  private String parentComponentId;


  public LinkedPATemplateSummary description(String description) {
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


  public LinkedPATemplateSummary name(String name) {
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


  public LinkedPATemplateSummary parentComponentId(String parentComponentId) {
    this.parentComponentId = parentComponentId;
    return this;
  }

   /**
   * Template parent tile.
   * @return parentComponentId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Template parent tile.")
  @JsonProperty(JSON_PROPERTY_PARENT_COMPONENT_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getParentComponentId() {
    return parentComponentId;
  }


  public void setParentComponentId(String parentComponentId) {
    this.parentComponentId = parentComponentId;
  }


  /**
   * Return true if this LinkedPATemplateSummary object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LinkedPATemplateSummary linkedPATemplateSummary = (LinkedPATemplateSummary) o;
    return Objects.equals(this.description, linkedPATemplateSummary.description) &&
        Objects.equals(this.name, linkedPATemplateSummary.name) &&
        Objects.equals(this.parentComponentId, linkedPATemplateSummary.parentComponentId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, name, parentComponentId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LinkedPATemplateSummary {\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    parentComponentId: ").append(toIndentedString(parentComponentId)).append("\n");
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

