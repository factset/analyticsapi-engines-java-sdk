/*
 * Engines API
 * Allow clients to fetch Analytics through APIs.
 *
 * The version of the OpenAPI document: 2
 * Contact: analytics.api.support@factset.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package factset.analyticsapi.engines.models;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * DateParametersSummary
 */
@JsonPropertyOrder({
  DateParametersSummary.JSON_PROPERTY_STARTDATE,
  DateParametersSummary.JSON_PROPERTY_ENDDATE
})

public class DateParametersSummary implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_STARTDATE = "startdate";
  private String startdate;

  public static final String JSON_PROPERTY_ENDDATE = "enddate";
  private String enddate;


  public DateParametersSummary startdate(String startdate) {
    
    this.startdate = startdate;
    return this;
  }

   /**
   * Start date in YYYYMMDD format.
   * @return startdate
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Start date in YYYYMMDD format.")
  @JsonProperty(JSON_PROPERTY_STARTDATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getStartdate() {
    return startdate;
  }


  public void setStartdate(String startdate) {
    this.startdate = startdate;
  }


  public DateParametersSummary enddate(String enddate) {
    
    this.enddate = enddate;
    return this;
  }

   /**
   * End date in YYYYMMDD format.
   * @return enddate
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "End date in YYYYMMDD format.")
  @JsonProperty(JSON_PROPERTY_ENDDATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getEnddate() {
    return enddate;
  }


  public void setEnddate(String enddate) {
    this.enddate = enddate;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DateParametersSummary dateParametersSummary = (DateParametersSummary) o;
    return Objects.equals(this.startdate, dateParametersSummary.startdate) &&
        Objects.equals(this.enddate, dateParametersSummary.enddate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(startdate, enddate);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DateParametersSummary {\n");
    sb.append("    startdate: ").append(toIndentedString(startdate)).append("\n");
    sb.append("    enddate: ").append(toIndentedString(enddate)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

