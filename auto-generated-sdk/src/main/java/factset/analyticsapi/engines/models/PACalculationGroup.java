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
 * PACalculationGroup
 */
@JsonPropertyOrder({
  PACalculationGroup.JSON_PROPERTY_ID,
  PACalculationGroup.JSON_PROPERTY_FREQUENCY
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class PACalculationGroup implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_ID = "id";
  private String id;

  public static final String JSON_PROPERTY_FREQUENCY = "frequency";
  private String frequency;


  public PACalculationGroup id(String id) {
    this.id = id;
    return this;
  }

   /**
   * FactSet-defined or User-defined Group identifier.
   * @return id
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "FactSet-defined or User-defined Group identifier.")
  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
  }


  public PACalculationGroup frequency(String frequency) {
    this.frequency = frequency;
    return this;
  }

   /**
   * Grouping frequency
   * @return frequency
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Grouping frequency")
  @JsonProperty(JSON_PROPERTY_FREQUENCY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getFrequency() {
    return frequency;
  }


  public void setFrequency(String frequency) {
    this.frequency = frequency;
  }


  /**
   * Return true if this PACalculationGroup object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PACalculationGroup paCalculationGroup = (PACalculationGroup) o;
    return Objects.equals(this.id, paCalculationGroup.id) &&
        Objects.equals(this.frequency, paCalculationGroup.frequency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, frequency);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PACalculationGroup {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    frequency: ").append(toIndentedString(frequency)).append("\n");
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

