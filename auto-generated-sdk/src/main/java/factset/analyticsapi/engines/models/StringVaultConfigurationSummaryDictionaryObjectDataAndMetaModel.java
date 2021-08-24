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
import factset.analyticsapi.engines.models.VaultConfigurationSummary;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.openapitools.jackson.nullable.JsonNullable;
import java.util.NoSuchElementException;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * StringVaultConfigurationSummaryDictionaryObjectDataAndMetaModel
 */
@JsonPropertyOrder({
  StringVaultConfigurationSummaryDictionaryObjectDataAndMetaModel.JSON_PROPERTY_DATA,
  StringVaultConfigurationSummaryDictionaryObjectDataAndMetaModel.JSON_PROPERTY_META
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class StringVaultConfigurationSummaryDictionaryObjectDataAndMetaModel implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_DATA = "data";
  private java.util.Map<String, VaultConfigurationSummary> data = new java.util.HashMap<String, VaultConfigurationSummary>();

  public static final String JSON_PROPERTY_META = "meta";
  private JsonNullable<Object> meta = JsonNullable.<Object>of(null);


  public StringVaultConfigurationSummaryDictionaryObjectDataAndMetaModel data(java.util.Map<String, VaultConfigurationSummary> data) {
    this.data = data;
    return this;
  }

  public StringVaultConfigurationSummaryDictionaryObjectDataAndMetaModel putDataItem(String key, VaultConfigurationSummary dataItem) {
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

  public java.util.Map<String, VaultConfigurationSummary> getData() {
    return data;
  }


  public void setData(java.util.Map<String, VaultConfigurationSummary> data) {
    this.data = data;
  }


  public StringVaultConfigurationSummaryDictionaryObjectDataAndMetaModel meta(Object meta) {
    this.meta = JsonNullable.<Object>of(meta);
    return this;
  }

   /**
   * Get meta
   * @return meta
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonIgnore

  public Object getMeta() {
        return meta.orElse(null);
  }

  @JsonProperty(JSON_PROPERTY_META)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public JsonNullable<Object> getMeta_JsonNullable() {
    return meta;
  }
  
  @JsonProperty(JSON_PROPERTY_META)
  public void setMeta_JsonNullable(JsonNullable<Object> meta) {
    this.meta = meta;
  }

  public void setMeta(Object meta) {
    this.meta = JsonNullable.<Object>of(meta);
  }


  /**
   * Return true if this StringVaultConfigurationSummaryDictionaryObjectDataAndMetaModel object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StringVaultConfigurationSummaryDictionaryObjectDataAndMetaModel stringVaultConfigurationSummaryDictionaryObjectDataAndMetaModel = (StringVaultConfigurationSummaryDictionaryObjectDataAndMetaModel) o;
    return Objects.equals(this.data, stringVaultConfigurationSummaryDictionaryObjectDataAndMetaModel.data) &&
        Objects.equals(this.meta, stringVaultConfigurationSummaryDictionaryObjectDataAndMetaModel.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, meta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StringVaultConfigurationSummaryDictionaryObjectDataAndMetaModel {\n");
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

