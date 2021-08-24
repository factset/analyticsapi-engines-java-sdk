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
 * FIJobSettings
 */
@JsonPropertyOrder({
  FIJobSettings.JSON_PROPERTY_AS_OF_DATE,
  FIJobSettings.JSON_PROPERTY_PARTIAL_DURATION_MONTHS,
  FIJobSettings.JSON_PROPERTY_CALL_METHOD,
  FIJobSettings.JSON_PROPERTY_SETTLEMENT,
  FIJobSettings.JSON_PROPERTY_CALC_FROM_METHOD
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class FIJobSettings implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_AS_OF_DATE = "asOfDate";
  private String asOfDate;

  public static final String JSON_PROPERTY_PARTIAL_DURATION_MONTHS = "partialDurationMonths";
  private java.util.List<Integer> partialDurationMonths = null;

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

  public static final String JSON_PROPERTY_SETTLEMENT = "settlement";
  private String settlement;

  public static final String JSON_PROPERTY_CALC_FROM_METHOD = "calcFromMethod";
  private String calcFromMethod;


  public FIJobSettings asOfDate(String asOfDate) {
    this.asOfDate = asOfDate;
    return this;
  }

   /**
   * As of date
   * @return asOfDate
  **/
  @ApiModelProperty(required = true, value = "As of date")
  @JsonProperty(JSON_PROPERTY_AS_OF_DATE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getAsOfDate() {
    return asOfDate;
  }


  public void setAsOfDate(String asOfDate) {
    this.asOfDate = asOfDate;
  }


  public FIJobSettings partialDurationMonths(java.util.List<Integer> partialDurationMonths) {
    this.partialDurationMonths = partialDurationMonths;
    return this;
  }

  public FIJobSettings addPartialDurationMonthsItem(Integer partialDurationMonthsItem) {
    if (this.partialDurationMonths == null) {
      this.partialDurationMonths = new java.util.ArrayList<Integer>();
    }
    this.partialDurationMonths.add(partialDurationMonthsItem);
    return this;
  }

   /**
   * Partial duration months
   * @return partialDurationMonths
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Partial duration months")
  @JsonProperty(JSON_PROPERTY_PARTIAL_DURATION_MONTHS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public java.util.List<Integer> getPartialDurationMonths() {
    return partialDurationMonths;
  }


  public void setPartialDurationMonths(java.util.List<Integer> partialDurationMonths) {
    this.partialDurationMonths = partialDurationMonths;
  }


  public FIJobSettings callMethod(CallMethodEnum callMethod) {
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


  public FIJobSettings settlement(String settlement) {
    this.settlement = settlement;
    return this;
  }

   /**
   * Settlement Date
   * @return settlement
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Settlement Date")
  @JsonProperty(JSON_PROPERTY_SETTLEMENT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getSettlement() {
    return settlement;
  }


  public void setSettlement(String settlement) {
    this.settlement = settlement;
  }


  public FIJobSettings calcFromMethod(String calcFromMethod) {
    this.calcFromMethod = calcFromMethod;
    return this;
  }

   /**
   * Calculation from method
   * @return calcFromMethod
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Calculation from method")
  @JsonProperty(JSON_PROPERTY_CALC_FROM_METHOD)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCalcFromMethod() {
    return calcFromMethod;
  }


  public void setCalcFromMethod(String calcFromMethod) {
    this.calcFromMethod = calcFromMethod;
  }


  /**
   * Return true if this FIJobSettings object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FIJobSettings fiJobSettings = (FIJobSettings) o;
    return Objects.equals(this.asOfDate, fiJobSettings.asOfDate) &&
        Objects.equals(this.partialDurationMonths, fiJobSettings.partialDurationMonths) &&
        Objects.equals(this.callMethod, fiJobSettings.callMethod) &&
        Objects.equals(this.settlement, fiJobSettings.settlement) &&
        Objects.equals(this.calcFromMethod, fiJobSettings.calcFromMethod);
  }

  @Override
  public int hashCode() {
    return Objects.hash(asOfDate, partialDurationMonths, callMethod, settlement, calcFromMethod);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FIJobSettings {\n");
    sb.append("    asOfDate: ").append(toIndentedString(asOfDate)).append("\n");
    sb.append("    partialDurationMonths: ").append(toIndentedString(partialDurationMonths)).append("\n");
    sb.append("    callMethod: ").append(toIndentedString(callMethod)).append("\n");
    sb.append("    settlement: ").append(toIndentedString(settlement)).append("\n");
    sb.append("    calcFromMethod: ").append(toIndentedString(calcFromMethod)).append("\n");
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

