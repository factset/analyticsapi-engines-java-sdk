/*
 * Engines API
 * Allow clients to fetch Analytics through APIs.
 *
 * The version of the OpenAPI document: v2:[pa,spar,vault,pub],v1:[fiab,fi,axp,afi,npo,bpm,fpo]
 * Contact: analytics.api.support@factset.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package factset.analyticsapi.engines.models;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import factset.analyticsapi.engines.models.Optimization;
import factset.analyticsapi.engines.models.OptimizerAccount;
import factset.analyticsapi.engines.models.OptimizerOutputTypes;
import factset.analyticsapi.engines.models.OptimizerStrategy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * AxiomaEquityOptimizationParameters
 */
@JsonPropertyOrder({
  AxiomaEquityOptimizationParameters.JSON_PROPERTY_STRATEGY,
  AxiomaEquityOptimizationParameters.JSON_PROPERTY_ACCOUNT,
  AxiomaEquityOptimizationParameters.JSON_PROPERTY_OPTIMIZATION,
  AxiomaEquityOptimizationParameters.JSON_PROPERTY_OUTPUTTYPES
})

public class AxiomaEquityOptimizationParameters implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_STRATEGY = "strategy";
  private OptimizerStrategy strategy;

  public static final String JSON_PROPERTY_ACCOUNT = "account";
  private OptimizerAccount account;

  public static final String JSON_PROPERTY_OPTIMIZATION = "optimization";
  private Optimization optimization;

  public static final String JSON_PROPERTY_OUTPUTTYPES = "outputtypes";
  private OptimizerOutputTypes outputtypes;


  public AxiomaEquityOptimizationParameters strategy(OptimizerStrategy strategy) {
    
    this.strategy = strategy;
    return this;
  }

   /**
   * Get strategy
   * @return strategy
  **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_STRATEGY)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public OptimizerStrategy getStrategy() {
    return strategy;
  }


  public void setStrategy(OptimizerStrategy strategy) {
    this.strategy = strategy;
  }


  public AxiomaEquityOptimizationParameters account(OptimizerAccount account) {
    
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

  public OptimizerAccount getAccount() {
    return account;
  }


  public void setAccount(OptimizerAccount account) {
    this.account = account;
  }


  public AxiomaEquityOptimizationParameters optimization(Optimization optimization) {
    
    this.optimization = optimization;
    return this;
  }

   /**
   * Get optimization
   * @return optimization
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_OPTIMIZATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Optimization getOptimization() {
    return optimization;
  }


  public void setOptimization(Optimization optimization) {
    this.optimization = optimization;
  }


  public AxiomaEquityOptimizationParameters outputtypes(OptimizerOutputTypes outputtypes) {
    
    this.outputtypes = outputtypes;
    return this;
  }

   /**
   * Get outputtypes
   * @return outputtypes
  **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_OUTPUTTYPES)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public OptimizerOutputTypes getOutputtypes() {
    return outputtypes;
  }


  public void setOutputtypes(OptimizerOutputTypes outputtypes) {
    this.outputtypes = outputtypes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AxiomaEquityOptimizationParameters axiomaEquityOptimizationParameters = (AxiomaEquityOptimizationParameters) o;
    return Objects.equals(this.strategy, axiomaEquityOptimizationParameters.strategy) &&
        Objects.equals(this.account, axiomaEquityOptimizationParameters.account) &&
        Objects.equals(this.optimization, axiomaEquityOptimizationParameters.optimization) &&
        Objects.equals(this.outputtypes, axiomaEquityOptimizationParameters.outputtypes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(strategy, account, optimization, outputtypes);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AxiomaEquityOptimizationParameters {\n");
    sb.append("    strategy: ").append(toIndentedString(strategy)).append("\n");
    sb.append("    account: ").append(toIndentedString(account)).append("\n");
    sb.append("    optimization: ").append(toIndentedString(optimization)).append("\n");
    sb.append("    outputtypes: ").append(toIndentedString(outputtypes)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

