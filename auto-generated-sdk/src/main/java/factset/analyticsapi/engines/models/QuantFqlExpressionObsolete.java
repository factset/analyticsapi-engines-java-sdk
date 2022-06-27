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
 * QuantFqlExpressionObsolete
 */
@JsonPropertyOrder({
  QuantFqlExpressionObsolete.JSON_PROPERTY_EXPR,
  QuantFqlExpressionObsolete.JSON_PROPERTY_NAME,
  QuantFqlExpressionObsolete.JSON_PROPERTY_DATE_OFFSET
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class QuantFqlExpressionObsolete implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_EXPR = "expr";
  private String expr;

  public static final String JSON_PROPERTY_NAME = "name";
  private String name;

  public static final String JSON_PROPERTY_DATE_OFFSET = "dateOffset";
  private String dateOffset;


  public QuantFqlExpressionObsolete expr(String expr) {
    this.expr = expr;
    return this;
  }

   /**
   * Get expr
   * @return expr
  **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_EXPR)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getExpr() {
    return expr;
  }


  public void setExpr(String expr) {
    this.expr = expr;
  }


  public QuantFqlExpressionObsolete name(String name) {
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


  public QuantFqlExpressionObsolete dateOffset(String dateOffset) {
    this.dateOffset = dateOffset;
    return this;
  }

   /**
   * Get dateOffset
   * @return dateOffset
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_DATE_OFFSET)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getDateOffset() {
    return dateOffset;
  }


  public void setDateOffset(String dateOffset) {
    this.dateOffset = dateOffset;
  }


  /**
   * Return true if this QuantFqlExpressionObsolete object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuantFqlExpressionObsolete quantFqlExpressionObsolete = (QuantFqlExpressionObsolete) o;
    return Objects.equals(this.expr, quantFqlExpressionObsolete.expr) &&
        Objects.equals(this.name, quantFqlExpressionObsolete.name) &&
        Objects.equals(this.dateOffset, quantFqlExpressionObsolete.dateOffset);
  }

  @Override
  public int hashCode() {
    return Objects.hash(expr, name, dateOffset);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuantFqlExpressionObsolete {\n");
    sb.append("    expr: ").append(toIndentedString(expr)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    dateOffset: ").append(toIndentedString(dateOffset)).append("\n");
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

