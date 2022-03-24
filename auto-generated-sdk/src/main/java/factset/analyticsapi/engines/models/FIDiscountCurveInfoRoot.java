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
import factset.analyticsapi.engines.models.FIDiscountCurveInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * FIDiscountCurveInfoRoot
 */
@JsonPropertyOrder({
  FIDiscountCurveInfoRoot.JSON_PROPERTY_DATA,
  FIDiscountCurveInfoRoot.JSON_PROPERTY_META
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class FIDiscountCurveInfoRoot implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_DATA = "data";
  private java.util.Map<String, FIDiscountCurveInfo> data = new java.util.HashMap<String, FIDiscountCurveInfo>();

  public static final String JSON_PROPERTY_META = "meta";
  private Object meta;


  public FIDiscountCurveInfoRoot data(java.util.Map<String, FIDiscountCurveInfo> data) {
    this.data = data;
    return this;
  }

  public FIDiscountCurveInfoRoot putDataItem(String key, FIDiscountCurveInfo dataItem) {
    this.data.put(key, dataItem);
    return this;
  }

   /**
   * Get data
   * @return data
  **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_DATA)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public java.util.Map<String, FIDiscountCurveInfo> getData() {
    return data;
  }


  public void setData(java.util.Map<String, FIDiscountCurveInfo> data) {
    this.data = data;
  }


  public FIDiscountCurveInfoRoot meta(Object meta) {
    this.meta = meta;
    return this;
  }

   /**
   * Get meta
   * @return meta
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_META)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Object getMeta() {
    return meta;
  }


  public void setMeta(Object meta) {
    this.meta = meta;
  }


  /**
   * Return true if this FIDiscountCurveInfoRoot object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FIDiscountCurveInfoRoot fiDiscountCurveInfoRoot = (FIDiscountCurveInfoRoot) o;
    return Objects.equals(this.data, fiDiscountCurveInfoRoot.data) &&
        Objects.equals(this.meta, fiDiscountCurveInfoRoot.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, meta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FIDiscountCurveInfoRoot {\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    meta: ").append(toIndentedString(meta)).append("\n");
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
