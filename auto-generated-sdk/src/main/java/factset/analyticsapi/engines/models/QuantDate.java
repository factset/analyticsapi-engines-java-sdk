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
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import factset.analyticsapi.engines.models.QuantDateList;
import factset.analyticsapi.engines.models.QuantFdsDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * QuantDate
 */
@JsonPropertyOrder({
  QuantDate.JSON_PROPERTY_$_TYPE,
  QuantDate.JSON_PROPERTY_SOURCE,
  QuantDate.JSON_PROPERTY_FREQUENCY,
  QuantDate.JSON_PROPERTY_CALENDAR
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "$type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = QuantDateList.class, name = "QuantDateList"),
  @JsonSubTypes.Type(value = QuantFdsDate.class, name = "QuantFdsDate"),
})

public class QuantDate implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_$_TYPE = "$type";
  private String $type;

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


  public QuantDate $type(String $type) {
    this.$type = $type;
    return this;
  }

   /**
   * Get $type
   * @return $type
  **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_$_TYPE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String get$Type() {
    return $type;
  }


  public void set$Type(String $type) {
    this.$type = $type;
  }


  public QuantDate source(SourceEnum source) {
    this.source = source;
    return this;
  }

   /**
   * Get source
   * @return source
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_SOURCE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public SourceEnum getSource() {
    return source;
  }


  public void setSource(SourceEnum source) {
    this.source = source;
  }


  public QuantDate frequency(String frequency) {
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


  public QuantDate calendar(String calendar) {
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


  /**
   * Return true if this QuantDate object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuantDate quantDate = (QuantDate) o;
    return Objects.equals(this.$type, quantDate.$type) &&
        Objects.equals(this.source, quantDate.source) &&
        Objects.equals(this.frequency, quantDate.frequency) &&
        Objects.equals(this.calendar, quantDate.calendar);
  }

  @Override
  public int hashCode() {
    return Objects.hash($type, source, frequency, calendar);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuantDate {\n");
    sb.append("    $type: ").append(toIndentedString($type)).append("\n");
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

static {
  // Initialize and register the discriminator mappings.
  Map<String, Class<?>> mappings = new HashMap<String, Class<?>>();
  mappings.put("QuantDateList", QuantDateList.class);
  mappings.put("QuantFdsDate", QuantFdsDate.class);
  mappings.put("QuantDate", QuantDate.class);
  JSON.registerDiscriminator(QuantDate.class, "$type", mappings);
}
}

