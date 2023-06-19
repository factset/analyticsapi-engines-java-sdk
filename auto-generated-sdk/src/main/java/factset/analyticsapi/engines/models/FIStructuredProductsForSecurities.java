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
import factset.analyticsapi.engines.models.FIBalloonExtension;
import factset.analyticsapi.engines.models.FICashflows;
import factset.analyticsapi.engines.models.FIPrepayLockout;
import factset.analyticsapi.engines.models.FIServicerAdvancesForSecurities;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * FIStructuredProductsForSecurities
 */
@JsonPropertyOrder({
  FIStructuredProductsForSecurities.JSON_PROPERTY_SERVICER_ADVANCES,
  FIStructuredProductsForSecurities.JSON_PROPERTY_IGNORE_FINANCIAL_GUARANTEE,
  FIStructuredProductsForSecurities.JSON_PROPERTY_CLEAN_UP_CALL_METHOD,
  FIStructuredProductsForSecurities.JSON_PROPERTY_DO_O_P_T_REDEEM,
  FIStructuredProductsForSecurities.JSON_PROPERTY_PREPAY_LOCKOUT,
  FIStructuredProductsForSecurities.JSON_PROPERTY_CASHFLOWS,
  FIStructuredProductsForSecurities.JSON_PROPERTY_BALLOON_EXTENSION
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class FIStructuredProductsForSecurities implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_SERVICER_ADVANCES = "servicerAdvances";
  private FIServicerAdvancesForSecurities servicerAdvances;

  public static final String JSON_PROPERTY_IGNORE_FINANCIAL_GUARANTEE = "ignoreFinancialGuarantee";
  private String ignoreFinancialGuarantee;

  public static final String JSON_PROPERTY_CLEAN_UP_CALL_METHOD = "cleanUpCallMethod";
  private Boolean cleanUpCallMethod;

  public static final String JSON_PROPERTY_DO_O_P_T_REDEEM = "doOPTRedeem";
  private String doOPTRedeem;

  public static final String JSON_PROPERTY_PREPAY_LOCKOUT = "prepayLockout";
  private FIPrepayLockout prepayLockout;

  public static final String JSON_PROPERTY_CASHFLOWS = "cashflows";
  private FICashflows cashflows;

  public static final String JSON_PROPERTY_BALLOON_EXTENSION = "balloonExtension";
  private FIBalloonExtension balloonExtension;


  public FIStructuredProductsForSecurities servicerAdvances(FIServicerAdvancesForSecurities servicerAdvances) {
    this.servicerAdvances = servicerAdvances;
    return this;
  }

   /**
   * Get servicerAdvances
   * @return servicerAdvances
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_SERVICER_ADVANCES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public FIServicerAdvancesForSecurities getServicerAdvances() {
    return servicerAdvances;
  }


  public void setServicerAdvances(FIServicerAdvancesForSecurities servicerAdvances) {
    this.servicerAdvances = servicerAdvances;
  }


  public FIStructuredProductsForSecurities ignoreFinancialGuarantee(String ignoreFinancialGuarantee) {
    this.ignoreFinancialGuarantee = ignoreFinancialGuarantee;
    return this;
  }

   /**
   * Ignore Financial Guarantee
   * @return ignoreFinancialGuarantee
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Ignore Financial Guarantee")
  @JsonProperty(JSON_PROPERTY_IGNORE_FINANCIAL_GUARANTEE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getIgnoreFinancialGuarantee() {
    return ignoreFinancialGuarantee;
  }


  public void setIgnoreFinancialGuarantee(String ignoreFinancialGuarantee) {
    this.ignoreFinancialGuarantee = ignoreFinancialGuarantee;
  }


  public FIStructuredProductsForSecurities cleanUpCallMethod(Boolean cleanUpCallMethod) {
    this.cleanUpCallMethod = cleanUpCallMethod;
    return this;
  }

   /**
   * Cleanup Call Method
   * @return cleanUpCallMethod
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Cleanup Call Method")
  @JsonProperty(JSON_PROPERTY_CLEAN_UP_CALL_METHOD)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getCleanUpCallMethod() {
    return cleanUpCallMethod;
  }


  public void setCleanUpCallMethod(Boolean cleanUpCallMethod) {
    this.cleanUpCallMethod = cleanUpCallMethod;
  }


  public FIStructuredProductsForSecurities doOPTRedeem(String doOPTRedeem) {
    this.doOPTRedeem = doOPTRedeem;
    return this;
  }

   /**
   * Do OPT Redeem
   * @return doOPTRedeem
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Do OPT Redeem")
  @JsonProperty(JSON_PROPERTY_DO_O_P_T_REDEEM)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getDoOPTRedeem() {
    return doOPTRedeem;
  }


  public void setDoOPTRedeem(String doOPTRedeem) {
    this.doOPTRedeem = doOPTRedeem;
  }


  public FIStructuredProductsForSecurities prepayLockout(FIPrepayLockout prepayLockout) {
    this.prepayLockout = prepayLockout;
    return this;
  }

   /**
   * Get prepayLockout
   * @return prepayLockout
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_PREPAY_LOCKOUT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public FIPrepayLockout getPrepayLockout() {
    return prepayLockout;
  }


  public void setPrepayLockout(FIPrepayLockout prepayLockout) {
    this.prepayLockout = prepayLockout;
  }


  public FIStructuredProductsForSecurities cashflows(FICashflows cashflows) {
    this.cashflows = cashflows;
    return this;
  }

   /**
   * Get cashflows
   * @return cashflows
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_CASHFLOWS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public FICashflows getCashflows() {
    return cashflows;
  }


  public void setCashflows(FICashflows cashflows) {
    this.cashflows = cashflows;
  }


  public FIStructuredProductsForSecurities balloonExtension(FIBalloonExtension balloonExtension) {
    this.balloonExtension = balloonExtension;
    return this;
  }

   /**
   * Get balloonExtension
   * @return balloonExtension
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_BALLOON_EXTENSION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public FIBalloonExtension getBalloonExtension() {
    return balloonExtension;
  }


  public void setBalloonExtension(FIBalloonExtension balloonExtension) {
    this.balloonExtension = balloonExtension;
  }


  /**
   * Return true if this FIStructuredProductsForSecurities object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FIStructuredProductsForSecurities fiStructuredProductsForSecurities = (FIStructuredProductsForSecurities) o;
    return Objects.equals(this.servicerAdvances, fiStructuredProductsForSecurities.servicerAdvances) &&
        Objects.equals(this.ignoreFinancialGuarantee, fiStructuredProductsForSecurities.ignoreFinancialGuarantee) &&
        Objects.equals(this.cleanUpCallMethod, fiStructuredProductsForSecurities.cleanUpCallMethod) &&
        Objects.equals(this.doOPTRedeem, fiStructuredProductsForSecurities.doOPTRedeem) &&
        Objects.equals(this.prepayLockout, fiStructuredProductsForSecurities.prepayLockout) &&
        Objects.equals(this.cashflows, fiStructuredProductsForSecurities.cashflows) &&
        Objects.equals(this.balloonExtension, fiStructuredProductsForSecurities.balloonExtension);
  }

  @Override
  public int hashCode() {
    return Objects.hash(servicerAdvances, ignoreFinancialGuarantee, cleanUpCallMethod, doOPTRedeem, prepayLockout, cashflows, balloonExtension);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FIStructuredProductsForSecurities {\n");
    sb.append("    servicerAdvances: ").append(toIndentedString(servicerAdvances)).append("\n");
    sb.append("    ignoreFinancialGuarantee: ").append(toIndentedString(ignoreFinancialGuarantee)).append("\n");
    sb.append("    cleanUpCallMethod: ").append(toIndentedString(cleanUpCallMethod)).append("\n");
    sb.append("    doOPTRedeem: ").append(toIndentedString(doOPTRedeem)).append("\n");
    sb.append("    prepayLockout: ").append(toIndentedString(prepayLockout)).append("\n");
    sb.append("    cashflows: ").append(toIndentedString(cashflows)).append("\n");
    sb.append("    balloonExtension: ").append(toIndentedString(balloonExtension)).append("\n");
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

