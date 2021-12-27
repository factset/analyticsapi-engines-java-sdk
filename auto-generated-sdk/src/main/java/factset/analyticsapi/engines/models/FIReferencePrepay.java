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
 * FIReferencePrepay
 */
@JsonPropertyOrder({
  FIReferencePrepay.JSON_PROPERTY_PREPAY_NAME
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class FIReferencePrepay implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_PREPAY_NAME = "prepayName";
  private String prepayName;

  public FIReferencePrepay() { 
  }

  public FIReferencePrepay prepayName(String prepayName) {
    this.prepayName = prepayName;
    return this;
  }

   /**
   * Reference Prepay Name
   * @return prepayName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Reference Prepay Name")
  @JsonProperty(JSON_PROPERTY_PREPAY_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getPrepayName() {
    return prepayName;
  }


  @JsonProperty(JSON_PROPERTY_PREPAY_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setPrepayName(String prepayName) {
    this.prepayName = prepayName;
  }


  /**
   * Return true if this FIReferencePrepay object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FIReferencePrepay fiReferencePrepay = (FIReferencePrepay) o;
    return Objects.equals(this.prepayName, fiReferencePrepay.prepayName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(prepayName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FIReferencePrepay {\n");
    sb.append("    prepayName: ").append(toIndentedString(prepayName)).append("\n");
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

