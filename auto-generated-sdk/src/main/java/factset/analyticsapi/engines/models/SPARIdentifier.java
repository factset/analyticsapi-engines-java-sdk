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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * The account/benchmark parameter for SPAR calculation.
 */
@ApiModel(description = "The account/benchmark parameter for SPAR calculation.")
@JsonPropertyOrder({
  SPARIdentifier.JSON_PROPERTY_ID,
  SPARIdentifier.JSON_PROPERTY_RETURNTYPE,
  SPARIdentifier.JSON_PROPERTY_PREFIX
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class SPARIdentifier implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_ID = "id";
  private String id;

  public static final String JSON_PROPERTY_RETURNTYPE = "returntype";
  private String returntype;

  public static final String JSON_PROPERTY_PREFIX = "prefix";
  private String prefix;


  public SPARIdentifier id(String id) {
    this.id = id;
    return this;
  }

   /**
   * User&#39;s FactSet account OR benchmark id.
   * @return id
  **/
  @ApiModelProperty(required = true, value = "User's FactSet account OR benchmark id.")
  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
  }


  public SPARIdentifier returntype(String returntype) {
    this.returntype = returntype;
    return this;
  }

   /**
   * Benchmark return type.
   * @return returntype
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Benchmark return type.")
  @JsonProperty(JSON_PROPERTY_RETURNTYPE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getReturntype() {
    return returntype;
  }


  public void setReturntype(String returntype) {
    this.returntype = returntype;
  }


  public SPARIdentifier prefix(String prefix) {
    this.prefix = prefix;
    return this;
  }

   /**
   * Benchmark prefix.
   * @return prefix
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Benchmark prefix.")
  @JsonProperty(JSON_PROPERTY_PREFIX)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getPrefix() {
    return prefix;
  }


  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }


  /**
   * Return true if this SPARIdentifier object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SPARIdentifier spARIdentifier = (SPARIdentifier) o;
    return Objects.equals(this.id, spARIdentifier.id) &&
        Objects.equals(this.returntype, spARIdentifier.returntype) &&
        Objects.equals(this.prefix, spARIdentifier.prefix);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, returntype, prefix);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SPARIdentifier {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    returntype: ").append(toIndentedString(returntype)).append("\n");
    sb.append("    prefix: ").append(toIndentedString(prefix)).append("\n");
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

