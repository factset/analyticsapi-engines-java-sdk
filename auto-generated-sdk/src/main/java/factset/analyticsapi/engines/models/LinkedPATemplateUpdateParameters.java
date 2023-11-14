/*
 * Engines API
 * Allow clients to fetch Analytics through APIs.
 *
 * The version of the OpenAPI document: v3:[pa,spar,vault,pub,quant,fi,axp,afi,npo,bpm,fpo,others],v1:[fiab]
 * Contact: testapi@factset.com
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
import factset.analyticsapi.engines.models.TemplateContentTypes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * LinkedPATemplateUpdateParameters
 */
@JsonPropertyOrder({
  LinkedPATemplateUpdateParameters.JSON_PROPERTY_PARENT_COMPONENT_ID,
  LinkedPATemplateUpdateParameters.JSON_PROPERTY_DESCRIPTION,
  LinkedPATemplateUpdateParameters.JSON_PROPERTY_CONTENT
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class LinkedPATemplateUpdateParameters implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_PARENT_COMPONENT_ID = "parentComponentId";
  private String parentComponentId;

  public static final String JSON_PROPERTY_DESCRIPTION = "description";
  private String description;

  public static final String JSON_PROPERTY_CONTENT = "content";
  private TemplateContentTypes content;


  public LinkedPATemplateUpdateParameters parentComponentId(String parentComponentId) {
    this.parentComponentId = parentComponentId;
    return this;
  }

   /**
   * Parent component id
   * @return parentComponentId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Parent component id")
  @JsonProperty(JSON_PROPERTY_PARENT_COMPONENT_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getParentComponentId() {
    return parentComponentId;
  }


  public void setParentComponentId(String parentComponentId) {
    this.parentComponentId = parentComponentId;
  }


  public LinkedPATemplateUpdateParameters description(String description) {
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


  public LinkedPATemplateUpdateParameters content(TemplateContentTypes content) {
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
   * Return true if this LinkedPATemplateUpdateParameters object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LinkedPATemplateUpdateParameters linkedPATemplateUpdateParameters = (LinkedPATemplateUpdateParameters) o;
    return Objects.equals(this.parentComponentId, linkedPATemplateUpdateParameters.parentComponentId) &&
        Objects.equals(this.description, linkedPATemplateUpdateParameters.description) &&
        Objects.equals(this.content, linkedPATemplateUpdateParameters.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(parentComponentId, description, content);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LinkedPATemplateUpdateParameters {\n");
    sb.append("    parentComponentId: ").append(toIndentedString(parentComponentId)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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

