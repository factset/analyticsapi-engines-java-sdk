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
import factset.analyticsapi.engines.models.TemplateContentTypes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * LinkedPATemplate
 */
@JsonPropertyOrder({
  LinkedPATemplate.JSON_PROPERTY_DIRECTORY,
  LinkedPATemplate.JSON_PROPERTY_SNAPSHOT,
  LinkedPATemplate.JSON_PROPERTY_CONTENT,
  LinkedPATemplate.JSON_PROPERTY_ID,
  LinkedPATemplate.JSON_PROPERTY_DESCRIPTION,
  LinkedPATemplate.JSON_PROPERTY_NAME,
  LinkedPATemplate.JSON_PROPERTY_PARENT_COMPONENT_ID
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class LinkedPATemplate implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_DIRECTORY = "directory";
  private String directory;

  public static final String JSON_PROPERTY_SNAPSHOT = "snapshot";
  private Boolean snapshot;

  public static final String JSON_PROPERTY_CONTENT = "content";
  private TemplateContentTypes content;

  public static final String JSON_PROPERTY_ID = "id";
  private String id;

  public static final String JSON_PROPERTY_DESCRIPTION = "description";
  private String description;

  public static final String JSON_PROPERTY_NAME = "name";
  private String name;

  public static final String JSON_PROPERTY_PARENT_COMPONENT_ID = "parentComponentId";
  private String parentComponentId;


  public LinkedPATemplate directory(String directory) {
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


  public LinkedPATemplate snapshot(Boolean snapshot) {
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


  public LinkedPATemplate content(TemplateContentTypes content) {
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


  public LinkedPATemplate id(String id) {
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


  public LinkedPATemplate description(String description) {
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


  public LinkedPATemplate name(String name) {
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


  public LinkedPATemplate parentComponentId(String parentComponentId) {
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
   * Return true if this LinkedPATemplate object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LinkedPATemplate linkedPATemplate = (LinkedPATemplate) o;
    return Objects.equals(this.directory, linkedPATemplate.directory) &&
        Objects.equals(this.snapshot, linkedPATemplate.snapshot) &&
        Objects.equals(this.content, linkedPATemplate.content) &&
        Objects.equals(this.id, linkedPATemplate.id) &&
        Objects.equals(this.description, linkedPATemplate.description) &&
        Objects.equals(this.name, linkedPATemplate.name) &&
        Objects.equals(this.parentComponentId, linkedPATemplate.parentComponentId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(directory, snapshot, content, id, description, name, parentComponentId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LinkedPATemplate {\n");
    sb.append("    directory: ").append(toIndentedString(directory)).append("\n");
    sb.append("    snapshot: ").append(toIndentedString(snapshot)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

