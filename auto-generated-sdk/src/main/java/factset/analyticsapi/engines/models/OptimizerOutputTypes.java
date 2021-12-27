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
import factset.analyticsapi.engines.models.OptimalPortfolio;
import factset.analyticsapi.engines.models.OptimizerOptimalHoldings;
import factset.analyticsapi.engines.models.OptimizerTradesList;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * OptimizerOutputTypes
 */
@JsonPropertyOrder({
  OptimizerOutputTypes.JSON_PROPERTY_TRADES,
  OptimizerOutputTypes.JSON_PROPERTY_OPTIMAL,
  OptimizerOutputTypes.JSON_PROPERTY_ACCOUNT,
  OptimizerOutputTypes.JSON_PROPERTY_STATS
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class OptimizerOutputTypes implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_TRADES = "trades";
  private OptimizerTradesList trades;

  public static final String JSON_PROPERTY_OPTIMAL = "optimal";
  private OptimizerOptimalHoldings optimal;

  public static final String JSON_PROPERTY_ACCOUNT = "account";
  private OptimalPortfolio account;

  public static final String JSON_PROPERTY_STATS = "stats";
  private Object stats;

  public OptimizerOutputTypes() { 
  }

  public OptimizerOutputTypes trades(OptimizerTradesList trades) {
    this.trades = trades;
    return this;
  }

   /**
   * Get trades
   * @return trades
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_TRADES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public OptimizerTradesList getTrades() {
    return trades;
  }


  @JsonProperty(JSON_PROPERTY_TRADES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setTrades(OptimizerTradesList trades) {
    this.trades = trades;
  }


  public OptimizerOutputTypes optimal(OptimizerOptimalHoldings optimal) {
    this.optimal = optimal;
    return this;
  }

   /**
   * Get optimal
   * @return optimal
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_OPTIMAL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public OptimizerOptimalHoldings getOptimal() {
    return optimal;
  }


  @JsonProperty(JSON_PROPERTY_OPTIMAL)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setOptimal(OptimizerOptimalHoldings optimal) {
    this.optimal = optimal;
  }


  public OptimizerOutputTypes account(OptimalPortfolio account) {
    this.account = account;
    return this;
  }

   /**
   * Get account
   * @return account
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_ACCOUNT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public OptimalPortfolio getAccount() {
    return account;
  }


  @JsonProperty(JSON_PROPERTY_ACCOUNT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setAccount(OptimalPortfolio account) {
    this.account = account;
  }


  public OptimizerOutputTypes stats(Object stats) {
    this.stats = stats;
    return this;
  }

   /**
   * Get stats
   * @return stats
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_STATS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Object getStats() {
    return stats;
  }


  @JsonProperty(JSON_PROPERTY_STATS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setStats(Object stats) {
    this.stats = stats;
  }


  /**
   * Return true if this OptimizerOutputTypes object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OptimizerOutputTypes optimizerOutputTypes = (OptimizerOutputTypes) o;
    return Objects.equals(this.trades, optimizerOutputTypes.trades) &&
        Objects.equals(this.optimal, optimizerOutputTypes.optimal) &&
        Objects.equals(this.account, optimizerOutputTypes.account) &&
        Objects.equals(this.stats, optimizerOutputTypes.stats);
  }

  @Override
  public int hashCode() {
    return Objects.hash(trades, optimal, account, stats);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OptimizerOutputTypes {\n");
    sb.append("    trades: ").append(toIndentedString(trades)).append("\n");
    sb.append("    optimal: ").append(toIndentedString(optimal)).append("\n");
    sb.append("    account: ").append(toIndentedString(account)).append("\n");
    sb.append("    stats: ").append(toIndentedString(stats)).append("\n");
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

