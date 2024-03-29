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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * PAIdentifier
 */
@JsonPropertyOrder({
  PAIdentifier.JSON_PROPERTY_ID,
  PAIdentifier.JSON_PROPERTY_HOLDINGSMODE
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class PAIdentifier implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_ID = "id";
  private String id;

  public static final String JSON_PROPERTY_HOLDINGSMODE = "holdingsmode";
  private String holdingsmode;


  public PAIdentifier id(String id) {
    this.id = id;
    return this;
  }

   /**
   * User&#39;s FactSet account path OR benchmark.
   * @return id
  **/
  @ApiModelProperty(required = true, value = "User's FactSet account path OR benchmark.")
  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
  }


  public PAIdentifier holdingsmode(String holdingsmode) {
    this.holdingsmode = holdingsmode;
    return this;
  }

   /**
   * Holdings Mode can be B&amp;H, TBR, OMS , EXT or VLT.
   * @return holdingsmode
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Holdings Mode can be B&H, TBR, OMS , EXT or VLT.")
  @JsonProperty(JSON_PROPERTY_HOLDINGSMODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getHoldingsmode() {
    return holdingsmode;
  }


  public void setHoldingsmode(String holdingsmode) {
    this.holdingsmode = holdingsmode;
  }


  /**
   * Return true if this PAIdentifier object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PAIdentifier paIdentifier = (PAIdentifier) o;
    return Objects.equals(this.id, paIdentifier.id) &&
        Objects.equals(this.holdingsmode, paIdentifier.holdingsmode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, holdingsmode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PAIdentifier {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    holdingsmode: ").append(toIndentedString(holdingsmode)).append("\n");
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

