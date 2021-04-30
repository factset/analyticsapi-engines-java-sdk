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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * The date parameters for FIAB calculations.
 */
@ApiModel(description = "The date parameters for FIAB calculations.")
@JsonPropertyOrder({
  FIABDateParameters.JSON_PROPERTY_STARTDATE,
  FIABDateParameters.JSON_PROPERTY_ENDDATE
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class FIABDateParameters implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_STARTDATE = "startdate";
  private String startdate;

  public static final String JSON_PROPERTY_ENDDATE = "enddate";
  private String enddate;


  public FIABDateParameters startdate(String startdate) {
    this.startdate = startdate;
    return this;
  }

   /**
   * Calculation&#39;s start date.
   * @return startdate
  **/
  @ApiModelProperty(required = true, value = "Calculation's start date.")
  @JsonProperty(JSON_PROPERTY_STARTDATE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getStartdate() {
    return startdate;
  }


  public void setStartdate(String startdate) {
    this.startdate = startdate;
  }


  public FIABDateParameters enddate(String enddate) {
    this.enddate = enddate;
    return this;
  }

   /**
   * Calculation&#39;s end date.
   * @return enddate
  **/
  @ApiModelProperty(required = true, value = "Calculation's end date.")
  @JsonProperty(JSON_PROPERTY_ENDDATE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getEnddate() {
    return enddate;
  }


  public void setEnddate(String enddate) {
    this.enddate = enddate;
  }


  /**
   * Return true if this FIABDateParameters object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FIABDateParameters fiABDateParameters = (FIABDateParameters) o;
    return Objects.equals(this.startdate, fiABDateParameters.startdate) &&
        Objects.equals(this.enddate, fiABDateParameters.enddate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(startdate, enddate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FIABDateParameters {\n");
    sb.append("    startdate: ").append(toIndentedString(startdate)).append("\n");
    sb.append("    enddate: ").append(toIndentedString(enddate)).append("\n");
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

