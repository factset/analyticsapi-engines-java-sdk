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
 * BPMOptimization
 */
@JsonPropertyOrder({
  BPMOptimization.JSON_PROPERTY_MARKET,
  BPMOptimization.JSON_PROPERTY_INVEST_ALL_CASH,
  BPMOptimization.JSON_PROPERTY_RISK_MODEL_DATE,
  BPMOptimization.JSON_PROPERTY_BACKTEST_DATE,
  BPMOptimization.JSON_PROPERTY_CASHFLOW
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class BPMOptimization implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_MARKET = "market";
  private String market;

  public static final String JSON_PROPERTY_INVEST_ALL_CASH = "investAllCash";
  private Boolean investAllCash;

  public static final String JSON_PROPERTY_RISK_MODEL_DATE = "riskModelDate";
  private String riskModelDate;

  public static final String JSON_PROPERTY_BACKTEST_DATE = "backtestDate";
  private String backtestDate;

  public static final String JSON_PROPERTY_CASHFLOW = "cashflow";
  private String cashflow;

  public BPMOptimization() { 
  }

  public BPMOptimization market(String market) {
    this.market = market;
    return this;
  }

   /**
   * Optimization market
   * @return market
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Optimization market")
  @JsonProperty(JSON_PROPERTY_MARKET)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getMarket() {
    return market;
  }


  @JsonProperty(JSON_PROPERTY_MARKET)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setMarket(String market) {
    this.market = market;
  }


  public BPMOptimization investAllCash(Boolean investAllCash) {
    this.investAllCash = investAllCash;
    return this;
  }

   /**
   * Optimization invest all cash
   * @return investAllCash
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Optimization invest all cash")
  @JsonProperty(JSON_PROPERTY_INVEST_ALL_CASH)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getInvestAllCash() {
    return investAllCash;
  }


  @JsonProperty(JSON_PROPERTY_INVEST_ALL_CASH)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setInvestAllCash(Boolean investAllCash) {
    this.investAllCash = investAllCash;
  }


  public BPMOptimization riskModelDate(String riskModelDate) {
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


  @JsonProperty(JSON_PROPERTY_RISK_MODEL_DATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setRiskModelDate(String riskModelDate) {
    this.riskModelDate = riskModelDate;
  }


  public BPMOptimization backtestDate(String backtestDate) {
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


  @JsonProperty(JSON_PROPERTY_BACKTEST_DATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setBacktestDate(String backtestDate) {
    this.backtestDate = backtestDate;
  }


  public BPMOptimization cashflow(String cashflow) {
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


  @JsonProperty(JSON_PROPERTY_CASHFLOW)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCashflow(String cashflow) {
    this.cashflow = cashflow;
  }


  /**
   * Return true if this BPMOptimization object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BPMOptimization bpMOptimization = (BPMOptimization) o;
    return Objects.equals(this.market, bpMOptimization.market) &&
        Objects.equals(this.investAllCash, bpMOptimization.investAllCash) &&
        Objects.equals(this.riskModelDate, bpMOptimization.riskModelDate) &&
        Objects.equals(this.backtestDate, bpMOptimization.backtestDate) &&
        Objects.equals(this.cashflow, bpMOptimization.cashflow);
  }

  @Override
  public int hashCode() {
    return Objects.hash(market, investAllCash, riskModelDate, backtestDate, cashflow);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BPMOptimization {\n");
    sb.append("    market: ").append(toIndentedString(market)).append("\n");
    sb.append("    investAllCash: ").append(toIndentedString(investAllCash)).append("\n");
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

