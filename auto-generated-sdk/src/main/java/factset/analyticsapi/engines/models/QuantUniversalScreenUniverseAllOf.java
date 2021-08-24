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
 * QuantUniversalScreenUniverseAllOf
 */
@JsonPropertyOrder({
  QuantUniversalScreenUniverseAllOf.JSON_PROPERTY_SCREEN
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class QuantUniversalScreenUniverseAllOf implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_SCREEN = "screen";
  private String screen;


  public QuantUniversalScreenUniverseAllOf screen(String screen) {
    this.screen = screen;
    return this;
  }

   /**
   * Get screen
   * @return screen
  **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_SCREEN)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getScreen() {
    return screen;
  }


  public void setScreen(String screen) {
    this.screen = screen;
  }


  /**
   * Return true if this QuantUniversalScreenUniverse_allOf object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuantUniversalScreenUniverseAllOf quantUniversalScreenUniverseAllOf = (QuantUniversalScreenUniverseAllOf) o;
    return Objects.equals(this.screen, quantUniversalScreenUniverseAllOf.screen);
  }

  @Override
  public int hashCode() {
    return Objects.hash(screen);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuantUniversalScreenUniverseAllOf {\n");
    sb.append("    screen: ").append(toIndentedString(screen)).append("\n");
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

