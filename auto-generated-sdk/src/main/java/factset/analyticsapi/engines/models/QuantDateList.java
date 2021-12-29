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
import factset.analyticsapi.engines.models.QuantDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * QuantDateList
 */
@JsonPropertyOrder({
  QuantDateList.JSON_PROPERTY_DATES,
  QuantDateList.JSON_PROPERTY_SOURCE,
  QuantDateList.JSON_PROPERTY_FREQUENCY,
  QuantDateList.JSON_PROPERTY_CALENDAR
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class QuantDateList implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_DATES = "dates";
  private java.util.List<String> dates = null;

  /**
   * Gets or Sets source
   */
  public enum SourceEnum {
    FDSDATE("FdsDate"),
    
    DATELIST("DateList");

    private String value;

    SourceEnum(String value) {
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
    public static SourceEnum fromValue(String value) {
      for (SourceEnum b : SourceEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_SOURCE = "source";
  private SourceEnum source;

  public static final String JSON_PROPERTY_FREQUENCY = "frequency";
  private String frequency;

  public static final String JSON_PROPERTY_CALENDAR = "calendar";
  private String calendar;

  public QuantDateList() { 
  }

  public QuantDateList dates(java.util.List<String> dates) {
    this.dates = dates;
    return this;
  }

  public QuantDateList addDatesItem(String datesItem) {
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


  @JsonProperty(JSON_PROPERTY_DATES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setDates(java.util.List<String> dates) {
    this.dates = dates;
  }


  public QuantDateList source(SourceEnum source) {
    this.source = source;
    return this;
  }

   /**
   * Get source
   * @return source
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_SOURCE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public SourceEnum getSource() {
    return source;
  }


  @JsonProperty(JSON_PROPERTY_SOURCE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setSource(SourceEnum source) {
    this.source = source;
  }


  public QuantDateList frequency(String frequency) {
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


  public QuantDateList calendar(String calendar) {
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
   * Return true if this QuantDateList object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuantDateList quantDateList = (QuantDateList) o;
    return Objects.equals(this.dates, quantDateList.dates) &&
        Objects.equals(this.source, quantDateList.source) &&
        Objects.equals(this.frequency, quantDateList.frequency) &&
        Objects.equals(this.calendar, quantDateList.calendar);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dates, source, frequency, calendar);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuantDateList {\n");
    sb.append("    dates: ").append(toIndentedString(dates)).append("\n");
    sb.append("    source: ").append(toIndentedString(source)).append("\n");
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

