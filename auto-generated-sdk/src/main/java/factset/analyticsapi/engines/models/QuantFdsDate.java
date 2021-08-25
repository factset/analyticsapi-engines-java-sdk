/*
 * Engines API
 * Allow clients to fetch Analytics through APIs.
 *
 * The version of the OpenAPI document: v3:[pa,spar,vault,pub,quant,fi,axp,afi,npo,bpm,fpo,others],v1:[fiab]
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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * QuantFdsDate
 */
@JsonPropertyOrder({
  QuantFdsDate.JSON_PROPERTY_START_DATE,
  QuantFdsDate.JSON_PROPERTY_END_DATE,
  QuantFdsDate.JSON_PROPERTY_FREQUENCY,
  QuantFdsDate.JSON_PROPERTY_CALENDAR
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class QuantFdsDate implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_START_DATE = "startDate";
  private String startDate;

  public static final String JSON_PROPERTY_END_DATE = "endDate";
  private String endDate;

  public static final String JSON_PROPERTY_FREQUENCY = "frequency";
  private String frequency;

  public static final String JSON_PROPERTY_CALENDAR = "calendar";
  private String calendar;


  public QuantFdsDate startDate(String startDate) {
    this.startDate = startDate;
    return this;
  }

   /**
   * Get startDate
   * @return startDate
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_START_DATE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getStartDate() {
    return startDate;
  }


  @JsonProperty(JSON_PROPERTY_START_DATE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }


  public QuantFdsDate endDate(String endDate) {
    this.endDate = endDate;
    return this;
  }

   /**
   * Get endDate
   * @return endDate
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_END_DATE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getEndDate() {
    return endDate;
  }


  @JsonProperty(JSON_PROPERTY_END_DATE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }


  public QuantFdsDate frequency(String frequency) {
    this.frequency = frequency;
    return this;
  }

   /**
   * Get frequency
   * @return frequency
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_FREQUENCY)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getFrequency() {
    return frequency;
  }


  @JsonProperty(JSON_PROPERTY_FREQUENCY)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setFrequency(String frequency) {
    this.frequency = frequency;
  }


  public QuantFdsDate calendar(String calendar) {
    this.calendar = calendar;
    return this;
  }

   /**
   * Get calendar
   * @return calendar
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_CALENDAR)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getCalendar() {
    return calendar;
  }


  @JsonProperty(JSON_PROPERTY_CALENDAR)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setCalendar(String calendar) {
    this.calendar = calendar;
  }


  /**
   * Return true if this QuantFdsDate object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuantFdsDate quantFdsDate = (QuantFdsDate) o;
    return Objects.equals(this.startDate, quantFdsDate.startDate) &&
        Objects.equals(this.endDate, quantFdsDate.endDate) &&
        Objects.equals(this.frequency, quantFdsDate.frequency) &&
        Objects.equals(this.calendar, quantFdsDate.calendar);
  }

  @Override
  public int hashCode() {
    return Objects.hash(startDate, endDate, frequency, calendar);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuantFdsDate {\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    frequency: ").append(toIndentedString(frequency)).append("\n");
    sb.append("    calendar: ").append(toIndentedString(calendar)).append("\n");
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

