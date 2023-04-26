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
import factset.analyticsapi.engines.models.OneOfSMFields;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * SMCreateParameters
 */
@JsonPropertyOrder({
  SMCreateParameters.JSON_PROPERTY_SECURITY_NAME,
  SMCreateParameters.JSON_PROPERTY_LOCATION,
  SMCreateParameters.JSON_PROPERTY_ASOFDATE,
  SMCreateParameters.JSON_PROPERTY_FIELDS
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class SMCreateParameters implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_SECURITY_NAME = "securityName";
  private String securityName;

  public static final String JSON_PROPERTY_LOCATION = "location";
  private String location;

  public static final String JSON_PROPERTY_ASOFDATE = "asofdate";
  private String asofdate;

  public static final String JSON_PROPERTY_FIELDS = "fields";
  private OneOfSMFields fields = null;


  public SMCreateParameters securityName(String securityName) {
    this.securityName = securityName;
    return this;
  }

   /**
   * Get securityName
   * @return securityName
  **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_SECURITY_NAME)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getSecurityName() {
    return securityName;
  }


  public void setSecurityName(String securityName) {
    this.securityName = securityName;
  }


  public SMCreateParameters location(String location) {
    this.location = location;
    return this;
  }

   /**
   * Get location
   * @return location
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_LOCATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getLocation() {
    return location;
  }


  public void setLocation(String location) {
    this.location = location;
  }


  public SMCreateParameters asofdate(String asofdate) {
    this.asofdate = asofdate;
    return this;
  }

   /**
   * Get asofdate
   * @return asofdate
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_ASOFDATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getAsofdate() {
    return asofdate;
  }


  public void setAsofdate(String asofdate) {
    this.asofdate = asofdate;
  }


  public SMCreateParameters fields(OneOfSMFields fields) {
    this.fields = fields;
    return this;
  }

   /**
   * Get fields
   * @return fields
  **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_FIELDS)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public OneOfSMFields getFields() {
    return fields;
  }


  public void setFields(OneOfSMFields fields) {
    this.fields = fields;
  }


  /**
   * Return true if this SMCreateParameters object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SMCreateParameters smCreateParameters = (SMCreateParameters) o;
    return Objects.equals(this.securityName, smCreateParameters.securityName) &&
        Objects.equals(this.location, smCreateParameters.location) &&
        Objects.equals(this.asofdate, smCreateParameters.asofdate) &&
        Objects.equals(this.fields, smCreateParameters.fields);
  }

  @Override
  public int hashCode() {
    return Objects.hash(securityName, location, asofdate, fields);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SMCreateParameters {\n");
    sb.append("    securityName: ").append(toIndentedString(securityName)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    asofdate: ").append(toIndentedString(asofdate)).append("\n");
    sb.append("    fields: ").append(toIndentedString(fields)).append("\n");
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
