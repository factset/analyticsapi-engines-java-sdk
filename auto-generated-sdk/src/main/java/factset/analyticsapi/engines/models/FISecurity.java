/*
 * Engines API
 * Allow clients to fetch Analytics through API.
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
import factset.analyticsapi.engines.models.FIAttributionForSecurities;
import factset.analyticsapi.engines.models.FIBankLoans;
import factset.analyticsapi.engines.models.FILoss;
import factset.analyticsapi.engines.models.FIMunicipalBonds;
import factset.analyticsapi.engines.models.FIPrepay;
import factset.analyticsapi.engines.models.FIReferenceSecurity;
import factset.analyticsapi.engines.models.FIStructuredProductsForSecurities;
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
  FISecurity.JSON_PROPERTY_CALL_METHOD,
  FISecurity.JSON_PROPERTY_REFERENCE_SECURITY,
  FISecurity.JSON_PROPERTY_BANK_LOANS,
  FISecurity.JSON_PROPERTY_MUNICIPAL_BONDS,
  FISecurity.JSON_PROPERTY_LOSS,
  FISecurity.JSON_PROPERTY_PREPAY,
  FISecurity.JSON_PROPERTY_MATRIX_SPREAD_ADJUSTMENT,
  FISecurity.JSON_PROPERTY_MATRIX_MULTIPLIER,
  FISecurity.JSON_PROPERTY_STRUCTURED_PRODUCTS,
  FISecurity.JSON_PROPERTY_ATTRIBUTION,
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

  /**
   * Call Method
   */
  public enum CallMethodEnum {
    NO_CALL("No Call"),
    
    INTRINSIC_VALUE("Intrinsic Value"),
    
    FIRST_CALL("First Call"),
    
    FIRST_PAR("First Par");

    private String value;

    CallMethodEnum(String value) {
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
    public static CallMethodEnum fromValue(String value) {
      for (CallMethodEnum b : CallMethodEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_CALL_METHOD = "callMethod";
  private CallMethodEnum callMethod;

  public static final String JSON_PROPERTY_REFERENCE_SECURITY = "referenceSecurity";
  private FIReferenceSecurity referenceSecurity;

  public static final String JSON_PROPERTY_BANK_LOANS = "bankLoans";
  private FIBankLoans bankLoans;

  public static final String JSON_PROPERTY_MUNICIPAL_BONDS = "municipalBonds";
  private FIMunicipalBonds municipalBonds;

  public static final String JSON_PROPERTY_LOSS = "loss";
  private FILoss loss;

  public static final String JSON_PROPERTY_PREPAY = "prepay";
  private FIPrepay prepay;

  public static final String JSON_PROPERTY_MATRIX_SPREAD_ADJUSTMENT = "matrixSpreadAdjustment";
  private Double matrixSpreadAdjustment;

  public static final String JSON_PROPERTY_MATRIX_MULTIPLIER = "matrixMultiplier";
  private Double matrixMultiplier;

  public static final String JSON_PROPERTY_STRUCTURED_PRODUCTS = "structuredProducts";
  private FIStructuredProductsForSecurities structuredProducts;

  public static final String JSON_PROPERTY_ATTRIBUTION = "attribution";
  private FIAttributionForSecurities attribution;

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


  public FISecurity callMethod(CallMethodEnum callMethod) {
    this.callMethod = callMethod;
    return this;
  }

   /**
   * Call Method
   * @return callMethod
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Call Method")
  @JsonProperty(JSON_PROPERTY_CALL_METHOD)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public CallMethodEnum getCallMethod() {
    return callMethod;
  }


  public void setCallMethod(CallMethodEnum callMethod) {
    this.callMethod = callMethod;
  }


  public FISecurity referenceSecurity(FIReferenceSecurity referenceSecurity) {
    this.referenceSecurity = referenceSecurity;
    return this;
  }

   /**
   * Get referenceSecurity
   * @return referenceSecurity
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_REFERENCE_SECURITY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public FIReferenceSecurity getReferenceSecurity() {
    return referenceSecurity;
  }


  public void setReferenceSecurity(FIReferenceSecurity referenceSecurity) {
    this.referenceSecurity = referenceSecurity;
  }


  public FISecurity bankLoans(FIBankLoans bankLoans) {
    this.bankLoans = bankLoans;
    return this;
  }

   /**
   * Get bankLoans
   * @return bankLoans
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_BANK_LOANS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public FIBankLoans getBankLoans() {
    return bankLoans;
  }


  public void setBankLoans(FIBankLoans bankLoans) {
    this.bankLoans = bankLoans;
  }


  public FISecurity municipalBonds(FIMunicipalBonds municipalBonds) {
    this.municipalBonds = municipalBonds;
    return this;
  }

   /**
   * Get municipalBonds
   * @return municipalBonds
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_MUNICIPAL_BONDS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public FIMunicipalBonds getMunicipalBonds() {
    return municipalBonds;
  }


  public void setMunicipalBonds(FIMunicipalBonds municipalBonds) {
    this.municipalBonds = municipalBonds;
  }


  public FISecurity loss(FILoss loss) {
    this.loss = loss;
    return this;
  }

   /**
   * Get loss
   * @return loss
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_LOSS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public FILoss getLoss() {
    return loss;
  }


  public void setLoss(FILoss loss) {
    this.loss = loss;
  }


  public FISecurity prepay(FIPrepay prepay) {
    this.prepay = prepay;
    return this;
  }

   /**
   * Get prepay
   * @return prepay
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_PREPAY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public FIPrepay getPrepay() {
    return prepay;
  }


  public void setPrepay(FIPrepay prepay) {
    this.prepay = prepay;
  }


  public FISecurity matrixSpreadAdjustment(Double matrixSpreadAdjustment) {
    this.matrixSpreadAdjustment = matrixSpreadAdjustment;
    return this;
  }

   /**
   * Matrix Spread Adjustment
   * @return matrixSpreadAdjustment
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Matrix Spread Adjustment")
  @JsonProperty(JSON_PROPERTY_MATRIX_SPREAD_ADJUSTMENT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Double getMatrixSpreadAdjustment() {
    return matrixSpreadAdjustment;
  }


  public void setMatrixSpreadAdjustment(Double matrixSpreadAdjustment) {
    this.matrixSpreadAdjustment = matrixSpreadAdjustment;
  }


  public FISecurity matrixMultiplier(Double matrixMultiplier) {
    this.matrixMultiplier = matrixMultiplier;
    return this;
  }

   /**
   * Matrix Multiplier
   * @return matrixMultiplier
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Matrix Multiplier")
  @JsonProperty(JSON_PROPERTY_MATRIX_MULTIPLIER)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Double getMatrixMultiplier() {
    return matrixMultiplier;
  }


  public void setMatrixMultiplier(Double matrixMultiplier) {
    this.matrixMultiplier = matrixMultiplier;
  }


  public FISecurity structuredProducts(FIStructuredProductsForSecurities structuredProducts) {
    this.structuredProducts = structuredProducts;
    return this;
  }

   /**
   * Get structuredProducts
   * @return structuredProducts
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_STRUCTURED_PRODUCTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public FIStructuredProductsForSecurities getStructuredProducts() {
    return structuredProducts;
  }


  public void setStructuredProducts(FIStructuredProductsForSecurities structuredProducts) {
    this.structuredProducts = structuredProducts;
  }


  public FISecurity attribution(FIAttributionForSecurities attribution) {
    this.attribution = attribution;
    return this;
  }

   /**
   * Get attribution
   * @return attribution
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_ATTRIBUTION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public FIAttributionForSecurities getAttribution() {
    return attribution;
  }


  public void setAttribution(FIAttributionForSecurities attribution) {
    this.attribution = attribution;
  }


  public FISecurity calcFromMethod(String calcFromMethod) {
    this.calcFromMethod = calcFromMethod;
    return this;
  }

   /**
   * Calculation Method.  Methods : Active Spread, Actual Spread, Actual Spread To Worst Call, OAS, Price, Yield, Yield To No Call, Act/Act Yield To No Call, Bond Equivalent Yield,  Yield To Worst Call, Discount Yield, Discount Margin, Implied Volatility, Bullet Spread, Bullet Spread To Worst Call, Pricing Matrix
   * @return calcFromMethod
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Calculation Method.  Methods : Active Spread, Actual Spread, Actual Spread To Worst Call, OAS, Price, Yield, Yield To No Call, Act/Act Yield To No Call, Bond Equivalent Yield,  Yield To Worst Call, Discount Yield, Discount Margin, Implied Volatility, Bullet Spread, Bullet Spread To Worst Call, Pricing Matrix")
  @JsonProperty(JSON_PROPERTY_CALC_FROM_METHOD)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

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
        Objects.equals(this.callMethod, fiSecurity.callMethod) &&
        Objects.equals(this.referenceSecurity, fiSecurity.referenceSecurity) &&
        Objects.equals(this.bankLoans, fiSecurity.bankLoans) &&
        Objects.equals(this.municipalBonds, fiSecurity.municipalBonds) &&
        Objects.equals(this.loss, fiSecurity.loss) &&
        Objects.equals(this.prepay, fiSecurity.prepay) &&
        Objects.equals(this.matrixSpreadAdjustment, fiSecurity.matrixSpreadAdjustment) &&
        Objects.equals(this.matrixMultiplier, fiSecurity.matrixMultiplier) &&
        Objects.equals(this.structuredProducts, fiSecurity.structuredProducts) &&
        Objects.equals(this.attribution, fiSecurity.attribution) &&
        Objects.equals(this.calcFromMethod, fiSecurity.calcFromMethod) &&
        Objects.equals(this.calcFromValue, fiSecurity.calcFromValue) &&
        Objects.equals(this.face, fiSecurity.face) &&
        Objects.equals(this.faceType, fiSecurity.faceType) &&
        Objects.equals(this.symbol, fiSecurity.symbol) &&
        Objects.equals(this.discountCurve, fiSecurity.discountCurve);
  }

  @Override
  public int hashCode() {
    return Objects.hash(settlement, callMethod, referenceSecurity, bankLoans, municipalBonds, loss, prepay, matrixSpreadAdjustment, matrixMultiplier, structuredProducts, attribution, calcFromMethod, calcFromValue, face, faceType, symbol, discountCurve);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FISecurity {\n");
    sb.append("    settlement: ").append(toIndentedString(settlement)).append("\n");
    sb.append("    callMethod: ").append(toIndentedString(callMethod)).append("\n");
    sb.append("    referenceSecurity: ").append(toIndentedString(referenceSecurity)).append("\n");
    sb.append("    bankLoans: ").append(toIndentedString(bankLoans)).append("\n");
    sb.append("    municipalBonds: ").append(toIndentedString(municipalBonds)).append("\n");
    sb.append("    loss: ").append(toIndentedString(loss)).append("\n");
    sb.append("    prepay: ").append(toIndentedString(prepay)).append("\n");
    sb.append("    matrixSpreadAdjustment: ").append(toIndentedString(matrixSpreadAdjustment)).append("\n");
    sb.append("    matrixMultiplier: ").append(toIndentedString(matrixMultiplier)).append("\n");
    sb.append("    structuredProducts: ").append(toIndentedString(structuredProducts)).append("\n");
    sb.append("    attribution: ").append(toIndentedString(attribution)).append("\n");
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

