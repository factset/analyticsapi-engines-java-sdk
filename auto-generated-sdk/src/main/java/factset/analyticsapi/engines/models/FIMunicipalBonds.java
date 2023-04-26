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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * FIMunicipalBonds
 */
@JsonPropertyOrder({
  FIMunicipalBonds.JSON_PROPERTY_IGNORE_SINKING_FUND,
  FIMunicipalBonds.JSON_PROPERTY_USE_ANTICIPATED_SINK_SCHEDULE
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class FIMunicipalBonds implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_IGNORE_SINKING_FUND = "ignoreSinkingFund";
  private Boolean ignoreSinkingFund;

  public static final String JSON_PROPERTY_USE_ANTICIPATED_SINK_SCHEDULE = "useAnticipatedSinkSchedule";
  private Boolean useAnticipatedSinkSchedule;


  public FIMunicipalBonds ignoreSinkingFund(Boolean ignoreSinkingFund) {
    this.ignoreSinkingFund = ignoreSinkingFund;
    return this;
  }

   /**
   * Ignore Sinking Fund Schedule
   * @return ignoreSinkingFund
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Ignore Sinking Fund Schedule")
  @JsonProperty(JSON_PROPERTY_IGNORE_SINKING_FUND)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getIgnoreSinkingFund() {
    return ignoreSinkingFund;
  }


  public void setIgnoreSinkingFund(Boolean ignoreSinkingFund) {
    this.ignoreSinkingFund = ignoreSinkingFund;
  }


  public FIMunicipalBonds useAnticipatedSinkSchedule(Boolean useAnticipatedSinkSchedule) {
    this.useAnticipatedSinkSchedule = useAnticipatedSinkSchedule;
    return this;
  }

   /**
   * Anticipate Sink Schedule
   * @return useAnticipatedSinkSchedule
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Anticipate Sink Schedule")
  @JsonProperty(JSON_PROPERTY_USE_ANTICIPATED_SINK_SCHEDULE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getUseAnticipatedSinkSchedule() {
    return useAnticipatedSinkSchedule;
  }


  public void setUseAnticipatedSinkSchedule(Boolean useAnticipatedSinkSchedule) {
    this.useAnticipatedSinkSchedule = useAnticipatedSinkSchedule;
  }


  /**
   * Return true if this FIMunicipalBonds object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FIMunicipalBonds fiMunicipalBonds = (FIMunicipalBonds) o;
    return Objects.equals(this.ignoreSinkingFund, fiMunicipalBonds.ignoreSinkingFund) &&
        Objects.equals(this.useAnticipatedSinkSchedule, fiMunicipalBonds.useAnticipatedSinkSchedule);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ignoreSinkingFund, useAnticipatedSinkSchedule);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FIMunicipalBonds {\n");
    sb.append("    ignoreSinkingFund: ").append(toIndentedString(ignoreSinkingFund)).append("\n");
    sb.append("    useAnticipatedSinkSchedule: ").append(toIndentedString(useAnticipatedSinkSchedule)).append("\n");
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

