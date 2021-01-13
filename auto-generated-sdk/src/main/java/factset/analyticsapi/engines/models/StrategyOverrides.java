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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * StrategyOverrides
 */
@JsonPropertyOrder({
  StrategyOverrides.JSON_PROPERTY_OBJECTIVE,
  StrategyOverrides.JSON_PROPERTY_CONSTRAINTS,
  StrategyOverrides.JSON_PROPERTY_ALPHA,
  StrategyOverrides.JSON_PROPERTY_TRANSACTIONCOST,
  StrategyOverrides.JSON_PROPERTY_TAX
})

public class StrategyOverrides implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_OBJECTIVE = "objective";
  private String objective;

  /**
   * Gets or Sets inner
   */
  public enum InnerEnum {
    DISABLE("Disable"),
    
    ENABLE("Enable");

    private String value;

    InnerEnum(String value) {
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
    public static InnerEnum fromValue(String value) {
      for (InnerEnum b : InnerEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_CONSTRAINTS = "constraints";
  private java.util.Map<String, InnerEnum> constraints = null;

  public static final String JSON_PROPERTY_ALPHA = "alpha";
  private String alpha;

  public static final String JSON_PROPERTY_TRANSACTIONCOST = "transactioncost";
  private String transactioncost;

  public static final String JSON_PROPERTY_TAX = "tax";
  private String tax;


  public StrategyOverrides objective(String objective) {
    
    this.objective = objective;
    return this;
  }

   /**
   * Objective
   * @return objective
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Objective")
  @JsonProperty(JSON_PROPERTY_OBJECTIVE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getObjective() {
    return objective;
  }


  public void setObjective(String objective) {
    this.objective = objective;
  }


  public StrategyOverrides constraints(java.util.Map<String, InnerEnum> constraints) {
    
    this.constraints = constraints;
    return this;
  }

  public StrategyOverrides putConstraintsItem(String key, InnerEnum constraintsItem) {
    if (this.constraints == null) {
      this.constraints = new java.util.HashMap<String, InnerEnum>();
    }
    this.constraints.put(key, constraintsItem);
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

  public java.util.Map<String, InnerEnum> getConstraints() {
    return constraints;
  }


  public void setConstraints(java.util.Map<String, InnerEnum> constraints) {
    this.constraints = constraints;
  }


  public StrategyOverrides alpha(String alpha) {
    
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


  public StrategyOverrides transactioncost(String transactioncost) {
    
    this.transactioncost = transactioncost;
    return this;
  }

   /**
   * Transaction cost
   * @return transactioncost
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Transaction cost")
  @JsonProperty(JSON_PROPERTY_TRANSACTIONCOST)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getTransactioncost() {
    return transactioncost;
  }


  public void setTransactioncost(String transactioncost) {
    this.transactioncost = transactioncost;
  }


  public StrategyOverrides tax(String tax) {
    
    this.tax = tax;
    return this;
  }

   /**
   * Tax
   * @return tax
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Tax")
  @JsonProperty(JSON_PROPERTY_TAX)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getTax() {
    return tax;
  }


  public void setTax(String tax) {
    this.tax = tax;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StrategyOverrides strategyOverrides = (StrategyOverrides) o;
    return Objects.equals(this.objective, strategyOverrides.objective) &&
        Objects.equals(this.constraints, strategyOverrides.constraints) &&
        Objects.equals(this.alpha, strategyOverrides.alpha) &&
        Objects.equals(this.transactioncost, strategyOverrides.transactioncost) &&
        Objects.equals(this.tax, strategyOverrides.tax);
  }

  @Override
  public int hashCode() {
    return Objects.hash(objective, constraints, alpha, transactioncost, tax);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StrategyOverrides {\n");
    sb.append("    objective: ").append(toIndentedString(objective)).append("\n");
    sb.append("    constraints: ").append(toIndentedString(constraints)).append("\n");
    sb.append("    alpha: ").append(toIndentedString(alpha)).append("\n");
    sb.append("    transactioncost: ").append(toIndentedString(transactioncost)).append("\n");
    sb.append("    tax: ").append(toIndentedString(tax)).append("\n");
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

