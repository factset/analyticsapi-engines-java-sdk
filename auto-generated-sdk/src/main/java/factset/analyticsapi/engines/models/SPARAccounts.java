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
import factset.analyticsapi.engines.models.ReturnType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * SPARAccounts
 */
@JsonPropertyOrder({
  SPARAccounts.JSON_PROPERTY_RETURNS_TYPE
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class SPARAccounts implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_RETURNS_TYPE = "returnsType";
  private java.util.List<ReturnType> returnsType = null;


  public SPARAccounts returnsType(java.util.List<ReturnType> returnsType) {
    this.returnsType = returnsType;
    return this;
  }

  public SPARAccounts addReturnsTypeItem(ReturnType returnsTypeItem) {
    if (this.returnsType == null) {
      this.returnsType = new java.util.ArrayList<ReturnType>();
    }
    this.returnsType.add(returnsTypeItem);
    return this;
  }

   /**
   * List of SPAR returnsType
   * @return returnsType
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "List of SPAR returnsType")
  @JsonProperty(JSON_PROPERTY_RETURNS_TYPE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public java.util.List<ReturnType> getReturnsType() {
    return returnsType;
  }


  public void setReturnsType(java.util.List<ReturnType> returnsType) {
    this.returnsType = returnsType;
  }


  /**
   * Return true if this SPARAccounts object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SPARAccounts spARAccounts = (SPARAccounts) o;
    return Objects.equals(this.returnsType, spARAccounts.returnsType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(returnsType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SPARAccounts {\n");
    sb.append("    returnsType: ").append(toIndentedString(returnsType)).append("\n");
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

