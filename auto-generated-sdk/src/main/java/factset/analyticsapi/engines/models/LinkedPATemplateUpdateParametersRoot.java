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
import factset.analyticsapi.engines.models.LinkedPATemplateUpdateParameters;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.openapitools.jackson.nullable.JsonNullable;
import java.util.NoSuchElementException;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * LinkedPATemplateUpdateParametersRoot
 */
@JsonPropertyOrder({
  LinkedPATemplateUpdateParametersRoot.JSON_PROPERTY_DATA,
  LinkedPATemplateUpdateParametersRoot.JSON_PROPERTY_META
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class LinkedPATemplateUpdateParametersRoot implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_DATA = "data";
  private LinkedPATemplateUpdateParameters data;

  public static final String JSON_PROPERTY_META = "meta";
  private JsonNullable<Object> meta = JsonNullable.<Object>of(null);


  public LinkedPATemplateUpdateParametersRoot data(LinkedPATemplateUpdateParameters data) {
    this.data = data;
    return this;
  }

   /**
   * Get data
   * @return data
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_DATA)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public LinkedPATemplateUpdateParameters getData() {
    return data;
  }


  @JsonProperty(JSON_PROPERTY_DATA)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setData(LinkedPATemplateUpdateParameters data) {
    this.data = data;
  }


  public LinkedPATemplateUpdateParametersRoot meta(Object meta) {
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
   * Return true if this LinkedPATemplateUpdateParametersRoot object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LinkedPATemplateUpdateParametersRoot linkedPATemplateUpdateParametersRoot = (LinkedPATemplateUpdateParametersRoot) o;
    return Objects.equals(this.data, linkedPATemplateUpdateParametersRoot.data) &&
        equalsNullable(this.meta, linkedPATemplateUpdateParametersRoot.meta);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && a.get().getClass().isArray() ? Arrays.equals((T[])a.get(), (T[])b.get()) : Objects.equals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, hashCodeNullable(meta));
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent()
      ? (a.get().getClass().isArray() ? Arrays.hashCode((T[])a.get()) : Objects.hashCode(a.get()))
      : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LinkedPATemplateUpdateParametersRoot {\n");
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

