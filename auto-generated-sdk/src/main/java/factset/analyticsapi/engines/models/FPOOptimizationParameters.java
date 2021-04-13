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
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import factset.analyticsapi.engines.models.FPOAccount;
import factset.analyticsapi.engines.models.Optimization;
import factset.analyticsapi.engines.models.OptimizerOutputTypes;
import factset.analyticsapi.engines.models.OptimizerStrategy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * FPOOptimizationParameters
 */
@JsonPropertyOrder({
  FPOOptimizationParameters.JSON_PROPERTY_ACCOUNT,
  FPOOptimizationParameters.JSON_PROPERTY_STRATEGY,
  FPOOptimizationParameters.JSON_PROPERTY_OPTIMIZATION,
  FPOOptimizationParameters.JSON_PROPERTY_OUTPUT_TYPES
})

public class FPOOptimizationParameters implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_ACCOUNT = "account";
  private FPOAccount account;

  public static final String JSON_PROPERTY_STRATEGY = "strategy";
  private OptimizerStrategy strategy;

  public static final String JSON_PROPERTY_OPTIMIZATION = "optimization";
  private Optimization optimization;

  public static final String JSON_PROPERTY_OUTPUT_TYPES = "outputTypes";
  private OptimizerOutputTypes outputTypes;


  public FPOOptimizationParameters account(FPOAccount account) {
    
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

  public FPOAccount getAccount() {
    return account;
  }


  public void setAccount(FPOAccount account) {
    this.account = account;
  }


  public FPOOptimizationParameters strategy(OptimizerStrategy strategy) {
    
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


  public FPOOptimizationParameters optimization(Optimization optimization) {
    
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


  public FPOOptimizationParameters outputTypes(OptimizerOutputTypes outputTypes) {
    
    this.outputTypes = outputTypes;
    return this;
  }

   /**
   * Get outputTypes
   * @return outputTypes
  **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_OUTPUT_TYPES)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public OptimizerOutputTypes getOutputTypes() {
    return outputTypes;
  }


  public void setOutputTypes(OptimizerOutputTypes outputTypes) {
    this.outputTypes = outputTypes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FPOOptimizationParameters fpOOptimizationParameters = (FPOOptimizationParameters) o;
    return Objects.equals(this.account, fpOOptimizationParameters.account) &&
        Objects.equals(this.strategy, fpOOptimizationParameters.strategy) &&
        Objects.equals(this.optimization, fpOOptimizationParameters.optimization) &&
        Objects.equals(this.outputTypes, fpOOptimizationParameters.outputTypes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(account, strategy, optimization, outputTypes);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FPOOptimizationParameters {\n");
    sb.append("    account: ").append(toIndentedString(account)).append("\n");
    sb.append("    strategy: ").append(toIndentedString(strategy)).append("\n");
    sb.append("    optimization: ").append(toIndentedString(optimization)).append("\n");
    sb.append("    outputTypes: ").append(toIndentedString(outputTypes)).append("\n");
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
