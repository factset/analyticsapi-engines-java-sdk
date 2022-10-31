/*
 * Engines API
 * Allow clients to fetch Analytics through APIs.
 *
 * The version of the OpenAPI document: v3:[pa,spar,vault,pub,quant,fi,axp,afi,npo,bpm,fpo,others],v1:[fiab]
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
 * QuantDateListObsolete
 */
@JsonPropertyOrder({
  QuantDateListObsolete.JSON_PROPERTY_DATES,
  QuantDateListObsolete.JSON_PROPERTY_FREQUENCY,
  QuantDateListObsolete.JSON_PROPERTY_CALENDAR,
  QuantDateListObsolete.JSON_PROPERTY_OVERRIDE_UNIVERSAL_SCREEN_CALENDAR
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class QuantDateListObsolete implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_DATES = "dates";
  private java.util.List<String> dates = null;

  public static final String JSON_PROPERTY_FREQUENCY = "frequency";
  private String frequency;

  public static final String JSON_PROPERTY_CALENDAR = "calendar";
  private String calendar;

  public static final String JSON_PROPERTY_OVERRIDE_UNIVERSAL_SCREEN_CALENDAR = "overrideUniversalScreenCalendar";
  private Boolean overrideUniversalScreenCalendar;


  public QuantDateListObsolete dates(java.util.List<String> dates) {
    this.dates = dates;
    return this;
  }

  public QuantDateListObsolete addDatesItem(String datesItem) {
    if (this.dates == null) {
      this.dates = new java.util.ArrayList<String>();
    }
    this.dates.add(datesItem);
    return this;
  }

   /**
   * Get dates
   * @return dates
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_DATES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public java.util.List<String> getDates() {
    return dates;
  }


  public void setDates(java.util.List<String> dates) {
    this.dates = dates;
  }


  public QuantDateListObsolete frequency(String frequency) {
    this.frequency = frequency;
    return this;
  }

   /**
   * Get frequency
   * @return frequency
  **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_FREQUENCY)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getFrequency() {
    return frequency;
  }


  public void setFrequency(String frequency) {
    this.frequency = frequency;
  }


  public QuantDateListObsolete calendar(String calendar) {
    this.calendar = calendar;
    return this;
  }

   /**
   * Get calendar
   * @return calendar
  **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_CALENDAR)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getCalendar() {
    return calendar;
  }


  public void setCalendar(String calendar) {
    this.calendar = calendar;
  }


  public QuantDateListObsolete overrideUniversalScreenCalendar(Boolean overrideUniversalScreenCalendar) {
    this.overrideUniversalScreenCalendar = overrideUniversalScreenCalendar;
    return this;
  }

   /**
   * Get overrideUniversalScreenCalendar
   * @return overrideUniversalScreenCalendar
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_OVERRIDE_UNIVERSAL_SCREEN_CALENDAR)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getOverrideUniversalScreenCalendar() {
    return overrideUniversalScreenCalendar;
  }


  public void setOverrideUniversalScreenCalendar(Boolean overrideUniversalScreenCalendar) {
    this.overrideUniversalScreenCalendar = overrideUniversalScreenCalendar;
  }


  /**
   * Return true if this QuantDateListObsolete object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuantDateListObsolete quantDateListObsolete = (QuantDateListObsolete) o;
    return Objects.equals(this.dates, quantDateListObsolete.dates) &&
        Objects.equals(this.frequency, quantDateListObsolete.frequency) &&
        Objects.equals(this.calendar, quantDateListObsolete.calendar) &&
        Objects.equals(this.overrideUniversalScreenCalendar, quantDateListObsolete.overrideUniversalScreenCalendar);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dates, frequency, calendar, overrideUniversalScreenCalendar);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuantDateListObsolete {\n");
    sb.append("    dates: ").append(toIndentedString(dates)).append("\n");
    sb.append("    frequency: ").append(toIndentedString(frequency)).append("\n");
    sb.append("    calendar: ").append(toIndentedString(calendar)).append("\n");
    sb.append("    overrideUniversalScreenCalendar: ").append(toIndentedString(overrideUniversalScreenCalendar)).append("\n");
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

