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
import factset.analyticsapi.engines.models.NPOOptimizerStrategyOverrides;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * NPOOptimizerStrategy
 */
@JsonPropertyOrder({
  NPOOptimizerStrategy.JSON_PROPERTY_OVERRIDES,
  NPOOptimizerStrategy.JSON_PROPERTY_ID
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class NPOOptimizerStrategy implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_OVERRIDES = "overrides";
  private NPOOptimizerStrategyOverrides overrides;

  public static final String JSON_PROPERTY_ID = "id";
  private String id;


  public NPOOptimizerStrategy overrides(NPOOptimizerStrategyOverrides overrides) {
    this.overrides = overrides;
    return this;
  }

   /**
   * Get overrides
   * @return overrides
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_OVERRIDES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public NPOOptimizerStrategyOverrides getOverrides() {
    return overrides;
  }


  public void setOverrides(NPOOptimizerStrategyOverrides overrides) {
    this.overrides = overrides;
  }


  public NPOOptimizerStrategy id(String id) {
    this.id = id;
    return this;
  }

   /**
   * OptimizerStrategy document path
   * @return id
  **/
  @ApiModelProperty(required = true, value = "OptimizerStrategy document path")
  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
  }


  /**
   * Return true if this NPOOptimizerStrategy object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NPOOptimizerStrategy npOOptimizerStrategy = (NPOOptimizerStrategy) o;
    return Objects.equals(this.overrides, npOOptimizerStrategy.overrides) &&
        Objects.equals(this.id, npOOptimizerStrategy.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(overrides, id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NPOOptimizerStrategy {\n");
    sb.append("    overrides: ").append(toIndentedString(overrides)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

