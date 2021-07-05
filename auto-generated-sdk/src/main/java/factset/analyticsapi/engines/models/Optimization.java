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
 * Optimization
 */
@JsonPropertyOrder({
  Optimization.JSON_PROPERTY_RISK_MODEL_DATE,
  Optimization.JSON_PROPERTY_BACKTEST_DATE,
  Optimization.JSON_PROPERTY_CASHFLOW
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class Optimization implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_RISK_MODEL_DATE = "riskModelDate";
  private String riskModelDate;

  public static final String JSON_PROPERTY_BACKTEST_DATE = "backtestDate";
  private String backtestDate;

  public static final String JSON_PROPERTY_CASHFLOW = "cashflow";
  private String cashflow;


  public Optimization riskModelDate(String riskModelDate) {
    this.riskModelDate = riskModelDate;
    return this;
  }

   /**
   * Risk model date
   * @return riskModelDate
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Risk model date")
  @JsonProperty(JSON_PROPERTY_RISK_MODEL_DATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getRiskModelDate() {
    return riskModelDate;
  }


  public void setRiskModelDate(String riskModelDate) {
    this.riskModelDate = riskModelDate;
  }


  public Optimization backtestDate(String backtestDate) {
    this.backtestDate = backtestDate;
    return this;
  }

   /**
   * Backtest date
   * @return backtestDate
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Backtest date")
  @JsonProperty(JSON_PROPERTY_BACKTEST_DATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getBacktestDate() {
    return backtestDate;
  }


  public void setBacktestDate(String backtestDate) {
    this.backtestDate = backtestDate;
  }


  public Optimization cashflow(String cashflow) {
    this.cashflow = cashflow;
    return this;
  }

   /**
   * Cash flow
   * @return cashflow
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Cash flow")
  @JsonProperty(JSON_PROPERTY_CASHFLOW)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCashflow() {
    return cashflow;
  }


  public void setCashflow(String cashflow) {
    this.cashflow = cashflow;
  }


  /**
   * Return true if this Optimization object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Optimization optimization = (Optimization) o;
    return Objects.equals(this.riskModelDate, optimization.riskModelDate) &&
        Objects.equals(this.backtestDate, optimization.backtestDate) &&
        Objects.equals(this.cashflow, optimization.cashflow);
  }

  @Override
  public int hashCode() {
    return Objects.hash(riskModelDate, backtestDate, cashflow);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Optimization {\n");
    sb.append("    riskModelDate: ").append(toIndentedString(riskModelDate)).append("\n");
    sb.append("    backtestDate: ").append(toIndentedString(backtestDate)).append("\n");
    sb.append("    cashflow: ").append(toIndentedString(cashflow)).append("\n");
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

