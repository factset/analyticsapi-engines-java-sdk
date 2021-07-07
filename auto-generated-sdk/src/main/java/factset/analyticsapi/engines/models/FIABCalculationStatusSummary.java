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
import org.threeten.bp.OffsetDateTime;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * FIABCalculationStatusSummary
 */
@JsonPropertyOrder({
  FIABCalculationStatusSummary.JSON_PROPERTY_REQUESTTIME,
  FIABCalculationStatusSummary.JSON_PROPERTY_LASTPOLLTIME
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class FIABCalculationStatusSummary implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_REQUESTTIME = "requesttime";
  private OffsetDateTime requesttime;

  public static final String JSON_PROPERTY_LASTPOLLTIME = "lastpolltime";
  private OffsetDateTime lastpolltime;


  public FIABCalculationStatusSummary requesttime(OffsetDateTime requesttime) {
    this.requesttime = requesttime;
    return this;
  }

   /**
   * Request time of calculation.
   * @return requesttime
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Request time of calculation.")
  @JsonProperty(JSON_PROPERTY_REQUESTTIME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public OffsetDateTime getRequesttime() {
    return requesttime;
  }


  public void setRequesttime(OffsetDateTime requesttime) {
    this.requesttime = requesttime;
  }


  public FIABCalculationStatusSummary lastpolltime(OffsetDateTime lastpolltime) {
    this.lastpolltime = lastpolltime;
    return this;
  }

   /**
   * Last poll time of calculation.
   * @return lastpolltime
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Last poll time of calculation.")
  @JsonProperty(JSON_PROPERTY_LASTPOLLTIME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public OffsetDateTime getLastpolltime() {
    return lastpolltime;
  }


  public void setLastpolltime(OffsetDateTime lastpolltime) {
    this.lastpolltime = lastpolltime;
  }


  /**
   * Return true if this FIABCalculationStatusSummary object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FIABCalculationStatusSummary fiABCalculationStatusSummary = (FIABCalculationStatusSummary) o;
    return Objects.equals(this.requesttime, fiABCalculationStatusSummary.requesttime) &&
        Objects.equals(this.lastpolltime, fiABCalculationStatusSummary.lastpolltime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requesttime, lastpolltime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FIABCalculationStatusSummary {\n");
    sb.append("    requesttime: ").append(toIndentedString(requesttime)).append("\n");
    sb.append("    lastpolltime: ").append(toIndentedString(lastpolltime)).append("\n");
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

