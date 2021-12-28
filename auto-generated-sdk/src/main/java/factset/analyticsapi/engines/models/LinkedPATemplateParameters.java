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
import factset.analyticsapi.engines.models.TemplateContentTypes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * LinkedPATemplateParameters
 */
@JsonPropertyOrder({
  LinkedPATemplateParameters.JSON_PROPERTY_DIRECTORY,
  LinkedPATemplateParameters.JSON_PROPERTY_PARENT_COMPONENT_ID,
  LinkedPATemplateParameters.JSON_PROPERTY_DESCRIPTION,
  LinkedPATemplateParameters.JSON_PROPERTY_CONTENT
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class LinkedPATemplateParameters implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_DIRECTORY = "directory";
  private String directory;

  public static final String JSON_PROPERTY_PARENT_COMPONENT_ID = "parentComponentId";
  private String parentComponentId;

  public static final String JSON_PROPERTY_DESCRIPTION = "description";
  private String description;

  public static final String JSON_PROPERTY_CONTENT = "content";
  private TemplateContentTypes content;

  public LinkedPATemplateParameters() { 
  }

  public LinkedPATemplateParameters directory(String directory) {
    this.directory = directory;
    return this;
  }

   /**
   * The directory to create a linked PA template
   * @return directory
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "The directory to create a linked PA template")
  @JsonProperty(JSON_PROPERTY_DIRECTORY)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getDirectory() {
    return directory;
  }


  @JsonProperty(JSON_PROPERTY_DIRECTORY)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setDirectory(String directory) {
    this.directory = directory;
  }


  public LinkedPATemplateParameters parentComponentId(String parentComponentId) {
    this.parentComponentId = parentComponentId;
    return this;
  }

   /**
   * Parent component id
   * @return parentComponentId
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Parent component id")
  @JsonProperty(JSON_PROPERTY_PARENT_COMPONENT_ID)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getParentComponentId() {
    return parentComponentId;
  }


  @JsonProperty(JSON_PROPERTY_PARENT_COMPONENT_ID)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setParentComponentId(String parentComponentId) {
    this.parentComponentId = parentComponentId;
  }


  public LinkedPATemplateParameters description(String description) {
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


  @JsonProperty(JSON_PROPERTY_DESCRIPTION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setDescription(String description) {
    this.description = description;
  }


  public LinkedPATemplateParameters content(TemplateContentTypes content) {
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


  @JsonProperty(JSON_PROPERTY_CONTENT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setContent(TemplateContentTypes content) {
    this.content = content;
  }


  /**
   * Return true if this LinkedPATemplateParameters object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LinkedPATemplateParameters linkedPATemplateParameters = (LinkedPATemplateParameters) o;
    return Objects.equals(this.directory, linkedPATemplateParameters.directory) &&
        Objects.equals(this.parentComponentId, linkedPATemplateParameters.parentComponentId) &&
        Objects.equals(this.description, linkedPATemplateParameters.description) &&
        Objects.equals(this.content, linkedPATemplateParameters.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(directory, parentComponentId, description, content);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LinkedPATemplateParameters {\n");
    sb.append("    directory: ").append(toIndentedString(directory)).append("\n");
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
