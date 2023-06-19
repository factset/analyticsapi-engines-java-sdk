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
import factset.analyticsapi.engines.models.Error;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * CalculationUnitStatus
 */
@JsonPropertyOrder({
  CalculationUnitStatus.JSON_PROPERTY_STATUS,
  CalculationUnitStatus.JSON_PROPERTY_ERRORS,
  CalculationUnitStatus.JSON_PROPERTY_RESULT,
  CalculationUnitStatus.JSON_PROPERTY_PROGRESS,
  CalculationUnitStatus.JSON_PROPERTY_POINTS,
  CalculationUnitStatus.JSON_PROPERTY_WARNINGS
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class CalculationUnitStatus implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * The status of calculation unit.
   */
  public enum StatusEnum {
    QUEUED("Queued"),
    
    EXECUTING("Executing"),
    
    SUCCESS("Success"),
    
    FAILED("Failed"),
    
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

  public static final String JSON_PROPERTY_ERRORS = "errors";
  private java.util.List<Error> errors = null;

  public static final String JSON_PROPERTY_RESULT = "result";
  private String result;

  public static final String JSON_PROPERTY_PROGRESS = "progress";
  private String progress;

  public static final String JSON_PROPERTY_POINTS = "points";
  private Integer points;

  public static final String JSON_PROPERTY_WARNINGS = "warnings";
  private java.util.List<String> warnings = null;


  public CalculationUnitStatus status(StatusEnum status) {
    this.status = status;
    return this;
  }

   /**
   * The status of calculation unit.
   * @return status
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The status of calculation unit.")
  @JsonProperty(JSON_PROPERTY_STATUS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public StatusEnum getStatus() {
    return status;
  }


  public void setStatus(StatusEnum status) {
    this.status = status;
  }


  public CalculationUnitStatus errors(java.util.List<Error> errors) {
    this.errors = errors;
    return this;
  }

  public CalculationUnitStatus addErrorsItem(Error errorsItem) {
    if (this.errors == null) {
      this.errors = new java.util.ArrayList<Error>();
    }
    this.errors.add(errorsItem);
    return this;
  }

   /**
   * The error in a calculation unit.
   * @return errors
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The error in a calculation unit.")
  @JsonProperty(JSON_PROPERTY_ERRORS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public java.util.List<Error> getErrors() {
    return errors;
  }


  public void setErrors(java.util.List<Error> errors) {
    this.errors = errors;
  }


  public CalculationUnitStatus result(String result) {
    this.result = result;
    return this;
  }

   /**
   * The result URL of the calculation.
   * @return result
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The result URL of the calculation.")
  @JsonProperty(JSON_PROPERTY_RESULT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getResult() {
    return result;
  }


  public void setResult(String result) {
    this.result = result;
  }


  public CalculationUnitStatus progress(String progress) {
    this.progress = progress;
    return this;
  }

   /**
   * The progress of the calculation unit.
   * @return progress
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The progress of the calculation unit.")
  @JsonProperty(JSON_PROPERTY_PROGRESS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getProgress() {
    return progress;
  }


  public void setProgress(String progress) {
    this.progress = progress;
  }


  public CalculationUnitStatus points(Integer points) {
    this.points = points;
    return this;
  }

   /**
   * The points for the calculation unit.
   * @return points
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The points for the calculation unit.")
  @JsonProperty(JSON_PROPERTY_POINTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getPoints() {
    return points;
  }


  public void setPoints(Integer points) {
    this.points = points;
  }


  public CalculationUnitStatus warnings(java.util.List<String> warnings) {
    this.warnings = warnings;
    return this;
  }

  public CalculationUnitStatus addWarningsItem(String warningsItem) {
    if (this.warnings == null) {
      this.warnings = new java.util.ArrayList<String>();
    }
    this.warnings.add(warningsItem);
    return this;
  }

   /**
   * The warnings in a calculation unit.
   * @return warnings
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The warnings in a calculation unit.")
  @JsonProperty(JSON_PROPERTY_WARNINGS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public java.util.List<String> getWarnings() {
    return warnings;
  }


  public void setWarnings(java.util.List<String> warnings) {
    this.warnings = warnings;
  }


  /**
   * Return true if this CalculationUnitStatus object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CalculationUnitStatus calculationUnitStatus = (CalculationUnitStatus) o;
    return Objects.equals(this.status, calculationUnitStatus.status) &&
        Objects.equals(this.errors, calculationUnitStatus.errors) &&
        Objects.equals(this.result, calculationUnitStatus.result) &&
        Objects.equals(this.progress, calculationUnitStatus.progress) &&
        Objects.equals(this.points, calculationUnitStatus.points) &&
        Objects.equals(this.warnings, calculationUnitStatus.warnings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, errors, result, progress, points, warnings);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CalculationUnitStatus {\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("    progress: ").append(toIndentedString(progress)).append("\n");
    sb.append("    points: ").append(toIndentedString(points)).append("\n");
    sb.append("    warnings: ").append(toIndentedString(warnings)).append("\n");
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

