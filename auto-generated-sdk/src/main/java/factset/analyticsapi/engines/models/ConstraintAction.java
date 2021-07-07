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
 * ConstraintAction
 */
@JsonPropertyOrder({
  ConstraintAction.JSON_PROPERTY_ITEM1,
  ConstraintAction.JSON_PROPERTY_ITEM2
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class ConstraintAction implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_ITEM1 = "item1";
  private String item1;

  /**
   * Gets or Sets item2
   */
  public enum Item2Enum {
    DISABLE("Disable"),
    
    ENABLE("Enable");

    private String value;

    Item2Enum(String value) {
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
    public static Item2Enum fromValue(String value) {
      for (Item2Enum b : Item2Enum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_ITEM2 = "item2";
  private Item2Enum item2;


  public ConstraintAction item1(String item1) {
    this.item1 = item1;
    return this;
  }

   /**
   * Get item1
   * @return item1
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_ITEM1)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getItem1() {
    return item1;
  }


  public void setItem1(String item1) {
    this.item1 = item1;
  }


  public ConstraintAction item2(Item2Enum item2) {
    this.item2 = item2;
    return this;
  }

   /**
   * Get item2
   * @return item2
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_ITEM2)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Item2Enum getItem2() {
    return item2;
  }


  public void setItem2(Item2Enum item2) {
    this.item2 = item2;
  }


  /**
   * Return true if this ConstraintAction object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConstraintAction constraintAction = (ConstraintAction) o;
    return Objects.equals(this.item1, constraintAction.item1) &&
        Objects.equals(this.item2, constraintAction.item2);
  }

  @Override
  public int hashCode() {
    return Objects.hash(item1, item2);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConstraintAction {\n");
    sb.append("    item1: ").append(toIndentedString(item1)).append("\n");
    sb.append("    item2: ").append(toIndentedString(item2)).append("\n");
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

