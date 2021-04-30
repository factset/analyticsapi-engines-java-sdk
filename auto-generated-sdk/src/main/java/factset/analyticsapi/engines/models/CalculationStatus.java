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
import java.util.Map;
import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import factset.analyticsapi.engines.models.CalculationUnitStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * CalculationStatus
 */
@JsonPropertyOrder({
  CalculationStatus.JSON_PROPERTY_CALCULATIONID,
  CalculationStatus.JSON_PROPERTY_STATUS,
  CalculationStatus.JSON_PROPERTY_UNITS
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class CalculationStatus implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_CALCULATIONID = "calculationid";
  private String calculationid;

  /**
   * Calculation&#39;s status
   */
  public enum StatusEnum {
    QUEUED("Queued"),
    
    EXECUTING("Executing"),
    
    COMPLETED("Completed"),
    
    CANCELLED("Cancelled");

    private String value;

    StatusEnum(String value) {
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
    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_STATUS = "status";
  private StatusEnum status;

  public static final String JSON_PROPERTY_UNITS = "units";
  private java.util.Map<String, CalculationUnitStatus> units = null;


  public CalculationStatus calculationid(String calculationid) {
    this.calculationid = calculationid;
    return this;
  }

   /**
   * Calculation&#39;s identifier
   * @return calculationid
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Calculation's identifier")
  @JsonProperty(JSON_PROPERTY_CALCULATIONID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCalculationid() {
    return calculationid;
  }


  public void setCalculationid(String calculationid) {
    this.calculationid = calculationid;
  }


  public CalculationStatus status(StatusEnum status) {
    this.status = status;
    return this;
  }

   /**
   * Calculation&#39;s status
   * @return status
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Calculation's status")
  @JsonProperty(JSON_PROPERTY_STATUS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public StatusEnum getStatus() {
    return status;
  }


  public void setStatus(StatusEnum status) {
    this.status = status;
  }


  public CalculationStatus units(java.util.Map<String, CalculationUnitStatus> units) {
    this.units = units;
    return this;
  }

  public CalculationStatus putUnitsItem(String key, CalculationUnitStatus unitsItem) {
    if (this.units == null) {
      this.units = new java.util.HashMap<String, CalculationUnitStatus>();
    }
    this.units.put(key, unitsItem);
    return this;
  }

   /**
   * Number of calculation units in batch.
   * @return units
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Number of calculation units in batch.")
  @JsonProperty(JSON_PROPERTY_UNITS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public java.util.Map<String, CalculationUnitStatus> getUnits() {
    return units;
  }


  public void setUnits(java.util.Map<String, CalculationUnitStatus> units) {
    this.units = units;
  }


  /**
   * Return true if this CalculationStatus object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CalculationStatus calculationStatus = (CalculationStatus) o;
    return Objects.equals(this.calculationid, calculationStatus.calculationid) &&
        Objects.equals(this.status, calculationStatus.status) &&
        Objects.equals(this.units, calculationStatus.units);
  }

  @Override
  public int hashCode() {
    return Objects.hash(calculationid, status, units);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CalculationStatus {\n");
    sb.append("    calculationid: ").append(toIndentedString(calculationid)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    units: ").append(toIndentedString(units)).append("\n");
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

