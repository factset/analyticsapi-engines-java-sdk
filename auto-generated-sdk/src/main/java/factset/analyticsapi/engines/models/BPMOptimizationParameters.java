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
import factset.analyticsapi.engines.models.BPMOptimization;
import factset.analyticsapi.engines.models.BPMOptimizerStrategy;
import factset.analyticsapi.engines.models.OptimizerAccount;
import factset.analyticsapi.engines.models.OptimizerOutputTypes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * BPMOptimizationParameters
 */
@JsonPropertyOrder({
  BPMOptimizationParameters.JSON_PROPERTY_STRATEGY,
  BPMOptimizationParameters.JSON_PROPERTY_OPTIMIZATION,
  BPMOptimizationParameters.JSON_PROPERTY_ACCOUNT,
  BPMOptimizationParameters.JSON_PROPERTY_OUTPUT_TYPES
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class BPMOptimizationParameters implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_STRATEGY = "strategy";
  private BPMOptimizerStrategy strategy;

  public static final String JSON_PROPERTY_OPTIMIZATION = "optimization";
  private BPMOptimization optimization;

  public static final String JSON_PROPERTY_ACCOUNT = "account";
  private OptimizerAccount account;

  public static final String JSON_PROPERTY_OUTPUT_TYPES = "outputTypes";
  private OptimizerOutputTypes outputTypes;


  public BPMOptimizationParameters strategy(BPMOptimizerStrategy strategy) {
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

  public BPMOptimizerStrategy getStrategy() {
    return strategy;
  }


  public void setStrategy(BPMOptimizerStrategy strategy) {
    this.strategy = strategy;
  }


  public BPMOptimizationParameters optimization(BPMOptimization optimization) {
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

  public BPMOptimization getOptimization() {
    return optimization;
  }


  public void setOptimization(BPMOptimization optimization) {
    this.optimization = optimization;
  }


  public BPMOptimizationParameters account(OptimizerAccount account) {
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


  public BPMOptimizationParameters outputTypes(OptimizerOutputTypes outputTypes) {
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


  /**
   * Return true if this BPMOptimizationParameters object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BPMOptimizationParameters bpMOptimizationParameters = (BPMOptimizationParameters) o;
    return Objects.equals(this.strategy, bpMOptimizationParameters.strategy) &&
        Objects.equals(this.optimization, bpMOptimizationParameters.optimization) &&
        Objects.equals(this.account, bpMOptimizationParameters.account) &&
        Objects.equals(this.outputTypes, bpMOptimizationParameters.outputTypes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(strategy, optimization, account, outputTypes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BPMOptimizationParameters {\n");
    sb.append("    strategy: ").append(toIndentedString(strategy)).append("\n");
    sb.append("    optimization: ").append(toIndentedString(optimization)).append("\n");
    sb.append("    account: ").append(toIndentedString(account)).append("\n");
    sb.append("    outputTypes: ").append(toIndentedString(outputTypes)).append("\n");
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

