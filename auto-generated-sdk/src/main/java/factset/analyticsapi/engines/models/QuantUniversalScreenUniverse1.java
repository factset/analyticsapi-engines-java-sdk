/*
 * Engines API
 * Allow clients to fetch Engines Analytics through APIs.
 *
 * The version of the OpenAPI document: 3
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
 * QuantUniversalScreenUniverse1
 */
@JsonPropertyOrder({
  QuantUniversalScreenUniverse1.JSON_PROPERTY_SCREEN,
  QuantUniversalScreenUniverse1.JSON_PROPERTY_SOURCE
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class QuantUniversalScreenUniverse1 implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_SCREEN = "screen";
  private String screen;

  /**
   * Gets or Sets source
   */
  public enum SourceEnum {
    SCREENINGEXPRESSIONUNIVERSE("ScreeningExpressionUniverse"),
    
    UNIVERSALSCREENUNIVERSE("UniversalScreenUniverse"),
    
    IDENTIFIERUNIVERSE("IdentifierUniverse");

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


  public QuantUniversalScreenUniverse1 screen(String screen) {
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


  public QuantUniversalScreenUniverse1 source(SourceEnum source) {
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


  /**
   * Return true if this QuantUniversalScreenUniverse1 object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuantUniversalScreenUniverse1 quantUniversalScreenUniverse1 = (QuantUniversalScreenUniverse1) o;
    return Objects.equals(this.screen, quantUniversalScreenUniverse1.screen) &&
        Objects.equals(this.source, quantUniversalScreenUniverse1.source);
  }

  @Override
  public int hashCode() {
    return Objects.hash(screen, source);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuantUniversalScreenUniverse1 {\n");
    sb.append("    screen: ").append(toIndentedString(screen)).append("\n");
    sb.append("    source: ").append(toIndentedString(source)).append("\n");
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

