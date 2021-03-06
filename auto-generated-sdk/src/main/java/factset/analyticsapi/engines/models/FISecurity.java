/*
 * Engines API
 * Allow clients to fetch Analytics through APIs.
 *
 * The version of the OpenAPI document: v3:[pa,spar,vault,pub,fi,axp,afi,npo,bpm,fpo,others],v1:[fiab]
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
 * FISecurity
 */
@JsonPropertyOrder({
  FISecurity.JSON_PROPERTY_SETTLEMENT,
  FISecurity.JSON_PROPERTY_CALC_FROM_METHOD,
  FISecurity.JSON_PROPERTY_CALC_FROM_VALUE,
  FISecurity.JSON_PROPERTY_FACE,
  FISecurity.JSON_PROPERTY_FACE_TYPE,
  FISecurity.JSON_PROPERTY_SYMBOL,
  FISecurity.JSON_PROPERTY_DISCOUNT_CURVE
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class FISecurity implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_SETTLEMENT = "settlement";
  private String settlement;

  public static final String JSON_PROPERTY_CALC_FROM_METHOD = "calcFromMethod";
  private String calcFromMethod;

  public static final String JSON_PROPERTY_CALC_FROM_VALUE = "calcFromValue";
  private Double calcFromValue;

  public static final String JSON_PROPERTY_FACE = "face";
  private Double face = 1d;

  /**
   * Face type
   */
  public enum FaceTypeEnum {
    CURRENT("Current"),
    
    ORIGINAL("Original");

    private String value;

    FaceTypeEnum(String value) {
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
    public static FaceTypeEnum fromValue(String value) {
      for (FaceTypeEnum b : FaceTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_FACE_TYPE = "faceType";
  private FaceTypeEnum faceType = FaceTypeEnum.CURRENT;

  public static final String JSON_PROPERTY_SYMBOL = "symbol";
  private String symbol;

  public static final String JSON_PROPERTY_DISCOUNT_CURVE = "discountCurve";
  private String discountCurve;


  public FISecurity settlement(String settlement) {
    this.settlement = settlement;
    return this;
  }

   /**
   * Settlement date
   * @return settlement
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Settlement date")
  @JsonProperty(JSON_PROPERTY_SETTLEMENT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getSettlement() {
    return settlement;
  }


  public void setSettlement(String settlement) {
    this.settlement = settlement;
  }


  public FISecurity calcFromMethod(String calcFromMethod) {
    this.calcFromMethod = calcFromMethod;
    return this;
  }

   /**
   * Calculation from method
   * @return calcFromMethod
  **/
  @ApiModelProperty(required = true, value = "Calculation from method")
  @JsonProperty(JSON_PROPERTY_CALC_FROM_METHOD)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getCalcFromMethod() {
    return calcFromMethod;
  }


  public void setCalcFromMethod(String calcFromMethod) {
    this.calcFromMethod = calcFromMethod;
  }


  public FISecurity calcFromValue(Double calcFromValue) {
    this.calcFromValue = calcFromValue;
    return this;
  }

   /**
   * Calculation from value
   * @return calcFromValue
  **/
  @ApiModelProperty(required = true, value = "Calculation from value")
  @JsonProperty(JSON_PROPERTY_CALC_FROM_VALUE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public Double getCalcFromValue() {
    return calcFromValue;
  }


  public void setCalcFromValue(Double calcFromValue) {
    this.calcFromValue = calcFromValue;
  }


  public FISecurity face(Double face) {
    this.face = face;
    return this;
  }

   /**
   * Face
   * @return face
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Face")
  @JsonProperty(JSON_PROPERTY_FACE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Double getFace() {
    return face;
  }


  public void setFace(Double face) {
    this.face = face;
  }


  public FISecurity faceType(FaceTypeEnum faceType) {
    this.faceType = faceType;
    return this;
  }

   /**
   * Face type
   * @return faceType
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Face type")
  @JsonProperty(JSON_PROPERTY_FACE_TYPE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public FaceTypeEnum getFaceType() {
    return faceType;
  }


  public void setFaceType(FaceTypeEnum faceType) {
    this.faceType = faceType;
  }


  public FISecurity symbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

   /**
   * Symbol
   * @return symbol
  **/
  @ApiModelProperty(required = true, value = "Symbol")
  @JsonProperty(JSON_PROPERTY_SYMBOL)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getSymbol() {
    return symbol;
  }


  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }


  public FISecurity discountCurve(String discountCurve) {
    this.discountCurve = discountCurve;
    return this;
  }

   /**
   * Discount curve
   * @return discountCurve
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Discount curve")
  @JsonProperty(JSON_PROPERTY_DISCOUNT_CURVE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getDiscountCurve() {
    return discountCurve;
  }


  public void setDiscountCurve(String discountCurve) {
    this.discountCurve = discountCurve;
  }


  /**
   * Return true if this FISecurity object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FISecurity fiSecurity = (FISecurity) o;
    return Objects.equals(this.settlement, fiSecurity.settlement) &&
        Objects.equals(this.calcFromMethod, fiSecurity.calcFromMethod) &&
        Objects.equals(this.calcFromValue, fiSecurity.calcFromValue) &&
        Objects.equals(this.face, fiSecurity.face) &&
        Objects.equals(this.faceType, fiSecurity.faceType) &&
        Objects.equals(this.symbol, fiSecurity.symbol) &&
        Objects.equals(this.discountCurve, fiSecurity.discountCurve);
  }

  @Override
  public int hashCode() {
    return Objects.hash(settlement, calcFromMethod, calcFromValue, face, faceType, symbol, discountCurve);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FISecurity {\n");
    sb.append("    settlement: ").append(toIndentedString(settlement)).append("\n");
    sb.append("    calcFromMethod: ").append(toIndentedString(calcFromMethod)).append("\n");
    sb.append("    calcFromValue: ").append(toIndentedString(calcFromValue)).append("\n");
    sb.append("    face: ").append(toIndentedString(face)).append("\n");
    sb.append("    faceType: ").append(toIndentedString(faceType)).append("\n");
    sb.append("    symbol: ").append(toIndentedString(symbol)).append("\n");
    sb.append("    discountCurve: ").append(toIndentedString(discountCurve)).append("\n");
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

