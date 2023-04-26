/*
 * Engines API
 * Allow clients to fetch Analytics through APIs.
 *
 * The version of the OpenAPI document: v3:[pa,vault,pub,quant,fi,axp,afi,npo,bpm,fpo,security-modeling,others],v1:[fiab]
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
import factset.analyticsapi.engines.models.ConstraintAction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * NPOOptimizerStrategyOverrides
 */
@JsonPropertyOrder({
  NPOOptimizerStrategyOverrides.JSON_PROPERTY_OBJECTIVE,
  NPOOptimizerStrategyOverrides.JSON_PROPERTY_CONSTRAINTS,
  NPOOptimizerStrategyOverrides.JSON_PROPERTY_TAX,
  NPOOptimizerStrategyOverrides.JSON_PROPERTY_TRANSACTION_COST,
  NPOOptimizerStrategyOverrides.JSON_PROPERTY_ALPHA
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class NPOOptimizerStrategyOverrides implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_OBJECTIVE = "objective";
  private java.util.Map<String, Object> objective = null;

  public static final String JSON_PROPERTY_CONSTRAINTS = "constraints";
  private java.util.List<ConstraintAction> constraints = null;

  public static final String JSON_PROPERTY_TAX = "tax";
  private String tax;

  public static final String JSON_PROPERTY_TRANSACTION_COST = "transactionCost";
  private String transactionCost;

  public static final String JSON_PROPERTY_ALPHA = "alpha";
  private String alpha;


  public NPOOptimizerStrategyOverrides objective(java.util.Map<String, Object> objective) {
    this.objective = objective;
    return this;
  }

  public NPOOptimizerStrategyOverrides putObjectiveItem(String key, Object objectiveItem) {
    if (this.objective == null) {
      this.objective = new java.util.HashMap<String, Object>();
    }
    this.objective.put(key, objectiveItem);
    return this;
  }

   /**
   * Objective parameters
   * @return objective
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Objective parameters")
  @JsonProperty(JSON_PROPERTY_OBJECTIVE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public java.util.Map<String, Object> getObjective() {
    return objective;
  }


  public void setObjective(java.util.Map<String, Object> objective) {
    this.objective = objective;
  }


  public NPOOptimizerStrategyOverrides constraints(java.util.List<ConstraintAction> constraints) {
    this.constraints = constraints;
    return this;
  }

  public NPOOptimizerStrategyOverrides addConstraintsItem(ConstraintAction constraintsItem) {
    if (this.constraints == null) {
      this.constraints = new java.util.ArrayList<ConstraintAction>();
    }
    this.constraints.add(constraintsItem);
    return this;
  }

   /**
   * List of constraints
   * @return constraints
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "List of constraints")
  @JsonProperty(JSON_PROPERTY_CONSTRAINTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public java.util.List<ConstraintAction> getConstraints() {
    return constraints;
  }


  public void setConstraints(java.util.List<ConstraintAction> constraints) {
    this.constraints = constraints;
  }


  public NPOOptimizerStrategyOverrides tax(String tax) {
    this.tax = tax;
    return this;
  }

   /**
   * Tax  Can be set to \&quot;\&quot; for local
   * @return tax
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Tax  Can be set to \"\" for local")
  @JsonProperty(JSON_PROPERTY_TAX)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getTax() {
    return tax;
  }


  public void setTax(String tax) {
    this.tax = tax;
  }


  public NPOOptimizerStrategyOverrides transactionCost(String transactionCost) {
    this.transactionCost = transactionCost;
    return this;
  }

   /**
   * Transaction cost  Can be set to \&quot;\&quot; for local
   * @return transactionCost
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Transaction cost  Can be set to \"\" for local")
  @JsonProperty(JSON_PROPERTY_TRANSACTION_COST)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getTransactionCost() {
    return transactionCost;
  }


  public void setTransactionCost(String transactionCost) {
    this.transactionCost = transactionCost;
  }


  public NPOOptimizerStrategyOverrides alpha(String alpha) {
    this.alpha = alpha;
    return this;
  }

   /**
   * Alpha
   * @return alpha
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Alpha")
  @JsonProperty(JSON_PROPERTY_ALPHA)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getAlpha() {
    return alpha;
  }


  public void setAlpha(String alpha) {
    this.alpha = alpha;
  }


  /**
   * Return true if this NPOOptimizerStrategyOverrides object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NPOOptimizerStrategyOverrides npOOptimizerStrategyOverrides = (NPOOptimizerStrategyOverrides) o;
    return Objects.equals(this.objective, npOOptimizerStrategyOverrides.objective) &&
        Objects.equals(this.constraints, npOOptimizerStrategyOverrides.constraints) &&
        Objects.equals(this.tax, npOOptimizerStrategyOverrides.tax) &&
        Objects.equals(this.transactionCost, npOOptimizerStrategyOverrides.transactionCost) &&
        Objects.equals(this.alpha, npOOptimizerStrategyOverrides.alpha);
  }

  @Override
  public int hashCode() {
    return Objects.hash(objective, constraints, tax, transactionCost, alpha);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NPOOptimizerStrategyOverrides {\n");
    sb.append("    objective: ").append(toIndentedString(objective)).append("\n");
    sb.append("    constraints: ").append(toIndentedString(constraints)).append("\n");
    sb.append("    tax: ").append(toIndentedString(tax)).append("\n");
    sb.append("    transactionCost: ").append(toIndentedString(transactionCost)).append("\n");
    sb.append("    alpha: ").append(toIndentedString(alpha)).append("\n");
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

