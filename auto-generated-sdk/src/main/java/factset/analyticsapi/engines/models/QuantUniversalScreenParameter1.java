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
 * QuantUniversalScreenParameter1
 */
@JsonPropertyOrder({
  QuantUniversalScreenParameter1.JSON_PROPERTY_REFERENCE_NAME,
  QuantUniversalScreenParameter1.JSON_PROPERTY_NAME
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class QuantUniversalScreenParameter1 implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_REFERENCE_NAME = "referenceName";
  private String referenceName;

  public static final String JSON_PROPERTY_NAME = "name";
  private String name;


  public QuantUniversalScreenParameter1 referenceName(String referenceName) {
    this.referenceName = referenceName;
    return this;
  }

   /**
   * Get referenceName
   * @return referenceName
  **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_REFERENCE_NAME)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getReferenceName() {
    return referenceName;
  }


  public void setReferenceName(String referenceName) {
    this.referenceName = referenceName;
  }


  public QuantUniversalScreenParameter1 name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_NAME)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  /**
   * Return true if this QuantUniversalScreenParameter1 object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuantUniversalScreenParameter1 quantUniversalScreenParameter1 = (QuantUniversalScreenParameter1) o;
    return Objects.equals(this.referenceName, quantUniversalScreenParameter1.referenceName) &&
        Objects.equals(this.name, quantUniversalScreenParameter1.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(referenceName, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuantUniversalScreenParameter1 {\n");
    sb.append("    referenceName: ").append(toIndentedString(referenceName)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

