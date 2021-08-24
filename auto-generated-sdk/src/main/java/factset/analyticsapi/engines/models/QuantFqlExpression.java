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
import factset.analyticsapi.engines.models.QuantFormula;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * QuantFqlExpression
 */
@JsonPropertyOrder({
  QuantFqlExpression.JSON_PROPERTY_EXPR,
  QuantFqlExpression.JSON_PROPERTY_NAME,
  QuantFqlExpression.JSON_PROPERTY_SOURCE
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class QuantFqlExpression implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_EXPR = "expr";
  private String expr;

  public static final String JSON_PROPERTY_NAME = "name";
  private String name;

  /**
   * Gets or Sets source
   */
  public enum SourceEnum {
    SCREENINGEXPRESSION("ScreeningExpression"),
    
    FQLEXPRESSION("FqlExpression"),
    
    UNIVERSALSCREENPARAMETER("UniversalScreenParameter"),
    
    ALLUNIVERSALSCREENPARAMETERS("AllUniversalScreenParameters");

    private String value;

    SourceEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static SourceEnum fromValue(String value) {
      for (SourceEnum b : SourceEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_SOURCE = "source";
  private SourceEnum source;


  public QuantFqlExpression expr(String expr) {
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


  public QuantFqlExpression name(String name) {
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


  public QuantFqlExpression source(SourceEnum source) {
    this.source = source;
    return this;
  }

   /**
   * Get source
   * @return source
  **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_SOURCE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public SourceEnum getSource() {
    return source;
  }


  public void setSource(SourceEnum source) {
    this.source = source;
  }


  /**
   * Return true if this QuantFqlExpression object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuantFqlExpression quantFqlExpression = (QuantFqlExpression) o;
    return Objects.equals(this.expr, quantFqlExpression.expr) &&
        Objects.equals(this.name, quantFqlExpression.name) &&
        Objects.equals(this.source, quantFqlExpression.source);
  }

  @Override
  public int hashCode() {
    return Objects.hash(expr, name, source);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuantFqlExpression {\n");
    sb.append("    expr: ").append(toIndentedString(expr)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    source: ").append(toIndentedString(source)).append("\n");
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

