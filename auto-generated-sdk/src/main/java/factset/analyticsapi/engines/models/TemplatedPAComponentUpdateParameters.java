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
import factset.analyticsapi.engines.models.PAComponentData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * TemplatedPAComponentUpdateParameters
 */
@JsonPropertyOrder({
  TemplatedPAComponentUpdateParameters.JSON_PROPERTY_PARENT_TEMPLATE_ID,
  TemplatedPAComponentUpdateParameters.JSON_PROPERTY_DESCRIPTION,
  TemplatedPAComponentUpdateParameters.JSON_PROPERTY_COMPONENT_DATA
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class TemplatedPAComponentUpdateParameters implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_PARENT_TEMPLATE_ID = "parentTemplateId";
  private String parentTemplateId;

  public static final String JSON_PROPERTY_DESCRIPTION = "description";
  private String description;

  public static final String JSON_PROPERTY_COMPONENT_DATA = "componentData";
  private PAComponentData componentData;


  public TemplatedPAComponentUpdateParameters parentTemplateId(String parentTemplateId) {
    this.parentTemplateId = parentTemplateId;
    return this;
  }

   /**
   * Parent template id
   * @return parentTemplateId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Parent template id")
  @JsonProperty(JSON_PROPERTY_PARENT_TEMPLATE_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getParentTemplateId() {
    return parentTemplateId;
  }


  public void setParentTemplateId(String parentTemplateId) {
    this.parentTemplateId = parentTemplateId;
  }


  public TemplatedPAComponentUpdateParameters description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Component description.
   * @return description
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Component description.")
  @JsonProperty(JSON_PROPERTY_DESCRIPTION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    this.description = description;
  }


  public TemplatedPAComponentUpdateParameters componentData(PAComponentData componentData) {
    this.componentData = componentData;
    return this;
  }

   /**
   * Get componentData
   * @return componentData
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_COMPONENT_DATA)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public PAComponentData getComponentData() {
    return componentData;
  }


  public void setComponentData(PAComponentData componentData) {
    this.componentData = componentData;
  }


  /**
   * Return true if this TemplatedPAComponentUpdateParameters object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TemplatedPAComponentUpdateParameters templatedPAComponentUpdateParameters = (TemplatedPAComponentUpdateParameters) o;
    return Objects.equals(this.parentTemplateId, templatedPAComponentUpdateParameters.parentTemplateId) &&
        Objects.equals(this.description, templatedPAComponentUpdateParameters.description) &&
        Objects.equals(this.componentData, templatedPAComponentUpdateParameters.componentData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(parentTemplateId, description, componentData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TemplatedPAComponentUpdateParameters {\n");
    sb.append("    parentTemplateId: ").append(toIndentedString(parentTemplateId)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    componentData: ").append(toIndentedString(componentData)).append("\n");
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

