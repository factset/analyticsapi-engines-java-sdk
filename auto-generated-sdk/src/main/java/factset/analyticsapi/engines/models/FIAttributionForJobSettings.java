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
 * FIAttributionForJobSettings
 */
@JsonPropertyOrder({
  FIAttributionForJobSettings.JSON_PROPERTY_START_DATE,
  FIAttributionForJobSettings.JSON_PROPERTY_END_DATE
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class FIAttributionForJobSettings implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_START_DATE = "startDate";
  private String startDate;

  public static final String JSON_PROPERTY_END_DATE = "endDate";
  private String endDate;


  public FIAttributionForJobSettings startDate(String startDate) {
    this.startDate = startDate;
    return this;
  }

   /**
   * Start Date
   * @return startDate
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Start Date")
  @JsonProperty(JSON_PROPERTY_START_DATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getStartDate() {
    return startDate;
  }


  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }


  public FIAttributionForJobSettings endDate(String endDate) {
    this.endDate = endDate;
    return this;
  }

   /**
   * End Date
   * @return endDate
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "End Date")
  @JsonProperty(JSON_PROPERTY_END_DATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getEndDate() {
    return endDate;
  }


  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }


  /**
   * Return true if this FIAttributionForJobSettings object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FIAttributionForJobSettings fiAttributionForJobSettings = (FIAttributionForJobSettings) o;
    return Objects.equals(this.startDate, fiAttributionForJobSettings.startDate) &&
        Objects.equals(this.endDate, fiAttributionForJobSettings.endDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(startDate, endDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FIAttributionForJobSettings {\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
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

