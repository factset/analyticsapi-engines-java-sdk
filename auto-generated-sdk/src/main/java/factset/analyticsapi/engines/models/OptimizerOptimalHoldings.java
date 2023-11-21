/*
 * Engines API
 * Allow clients to fetch Analytics through APIs.
 *
 * The version of the OpenAPI document: v3:[pa,spar,vault,pub,quant,fi,axp,afi,npo,bpm,fpo],v1:[fiab]
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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * OptimizerOptimalHoldings
 */
@JsonPropertyOrder({
  OptimizerOptimalHoldings.JSON_PROPERTY_IDENTIFIER_TYPE,
  OptimizerOptimalHoldings.JSON_PROPERTY_INCLUDE_CASH,
  OptimizerOptimalHoldings.JSON_PROPERTY_EXCLUDE_ZERO
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class OptimizerOptimalHoldings implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * Identifier type
   */
  public enum IdentifierTypeEnum {
    ASSET("Asset"),
    
    CUSIP("Cusip"),
    
    ISIN("Isin"),
    
    RISKMODEL("RiskModel"),
    
    SEDOLCHK("SedolChk"),
    
    SEDOL("Sedol"),
    
    SYMBOLOGYCUSIP("SymbologyCusip"),
    
    TICKER("Ticker"),
    
    TICKERREGION("TickerRegion"),
    
    USER("User");

    private String value;

    IdentifierTypeEnum(String value) {
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
    public static IdentifierTypeEnum fromValue(String value) {
      for (IdentifierTypeEnum b : IdentifierTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_IDENTIFIER_TYPE = "identifierType";
  private IdentifierTypeEnum identifierType;

  public static final String JSON_PROPERTY_INCLUDE_CASH = "includeCash";
  private Boolean includeCash;

  public static final String JSON_PROPERTY_EXCLUDE_ZERO = "excludeZero";
  private Boolean excludeZero;


  public OptimizerOptimalHoldings identifierType(IdentifierTypeEnum identifierType) {
    this.identifierType = identifierType;
    return this;
  }

   /**
   * Identifier type
   * @return identifierType
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Identifier type")
  @JsonProperty(JSON_PROPERTY_IDENTIFIER_TYPE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public IdentifierTypeEnum getIdentifierType() {
    return identifierType;
  }


  public void setIdentifierType(IdentifierTypeEnum identifierType) {
    this.identifierType = identifierType;
  }


  public OptimizerOptimalHoldings includeCash(Boolean includeCash) {
    this.includeCash = includeCash;
    return this;
  }

   /**
   * Include cash
   * @return includeCash
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Include cash")
  @JsonProperty(JSON_PROPERTY_INCLUDE_CASH)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getIncludeCash() {
    return includeCash;
  }


  public void setIncludeCash(Boolean includeCash) {
    this.includeCash = includeCash;
  }


  public OptimizerOptimalHoldings excludeZero(Boolean excludeZero) {
    this.excludeZero = excludeZero;
    return this;
  }

   /**
   * Exclude zero
   * @return excludeZero
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Exclude zero")
  @JsonProperty(JSON_PROPERTY_EXCLUDE_ZERO)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getExcludeZero() {
    return excludeZero;
  }


  public void setExcludeZero(Boolean excludeZero) {
    this.excludeZero = excludeZero;
  }


  /**
   * Return true if this OptimizerOptimalHoldings object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OptimizerOptimalHoldings optimizerOptimalHoldings = (OptimizerOptimalHoldings) o;
    return Objects.equals(this.identifierType, optimizerOptimalHoldings.identifierType) &&
        Objects.equals(this.includeCash, optimizerOptimalHoldings.includeCash) &&
        Objects.equals(this.excludeZero, optimizerOptimalHoldings.excludeZero);
  }

  @Override
  public int hashCode() {
    return Objects.hash(identifierType, includeCash, excludeZero);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OptimizerOptimalHoldings {\n");
    sb.append("    identifierType: ").append(toIndentedString(identifierType)).append("\n");
    sb.append("    includeCash: ").append(toIndentedString(includeCash)).append("\n");
    sb.append("    excludeZero: ").append(toIndentedString(excludeZero)).append("\n");
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

