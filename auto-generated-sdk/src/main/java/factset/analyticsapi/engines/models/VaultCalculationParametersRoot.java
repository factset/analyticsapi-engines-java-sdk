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
import factset.analyticsapi.engines.models.CalculationMeta;
import factset.analyticsapi.engines.models.VaultCalculationParameters;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * VaultCalculationParametersRoot
 */
@JsonPropertyOrder({
  VaultCalculationParametersRoot.JSON_PROPERTY_DATA,
  VaultCalculationParametersRoot.JSON_PROPERTY_META
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class VaultCalculationParametersRoot implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_DATA = "data";
  private java.util.Map<String, VaultCalculationParameters> data = null;

  public static final String JSON_PROPERTY_META = "meta";
  private CalculationMeta meta;


  public VaultCalculationParametersRoot data(java.util.Map<String, VaultCalculationParameters> data) {
    this.data = data;
    return this;
  }

  public VaultCalculationParametersRoot putDataItem(String key, VaultCalculationParameters dataItem) {
    if (this.data == null) {
      this.data = new java.util.HashMap<String, VaultCalculationParameters>();
    }
    this.data.put(key, dataItem);
    return this;
  }

   /**
   * List of calculation parameters.
   * @return data
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "List of calculation parameters.")
  @JsonProperty(JSON_PROPERTY_DATA)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public java.util.Map<String, VaultCalculationParameters> getData() {
    return data;
  }


  public void setData(java.util.Map<String, VaultCalculationParameters> data) {
    this.data = data;
  }


  public VaultCalculationParametersRoot meta(CalculationMeta meta) {
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

  public CalculationMeta getMeta() {
    return meta;
  }


  public void setMeta(CalculationMeta meta) {
    this.meta = meta;
  }


  /**
   * Return true if this VaultCalculationParametersRoot object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VaultCalculationParametersRoot vaultCalculationParametersRoot = (VaultCalculationParametersRoot) o;
    return Objects.equals(this.data, vaultCalculationParametersRoot.data) &&
        Objects.equals(this.meta, vaultCalculationParametersRoot.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, meta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VaultCalculationParametersRoot {\n");
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

