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
 * QuantIdentifierUniverse
 */
@JsonPropertyOrder({
  QuantIdentifierUniverse.JSON_PROPERTY_UNIVERSE_TYPE,
  QuantIdentifierUniverse.JSON_PROPERTY_IDENTIFIERS
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class QuantIdentifierUniverse implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * Gets or Sets universeType
   */
  public enum UniverseTypeEnum {
    EQUITY("Equity"),
    
    DEBT("Debt");

    private String value;

    UniverseTypeEnum(String value) {
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
    public static UniverseTypeEnum fromValue(String value) {
      for (UniverseTypeEnum b : UniverseTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_UNIVERSE_TYPE = "universeType";
  private UniverseTypeEnum universeType;

  public static final String JSON_PROPERTY_IDENTIFIERS = "identifiers";
  private java.util.List<String> identifiers = new java.util.ArrayList<String>();


  public QuantIdentifierUniverse universeType(UniverseTypeEnum universeType) {
    this.universeType = universeType;
    return this;
  }

   /**
   * Get universeType
   * @return universeType
  **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_UNIVERSE_TYPE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public UniverseTypeEnum getUniverseType() {
    return universeType;
  }


  public void setUniverseType(UniverseTypeEnum universeType) {
    this.universeType = universeType;
  }


  public QuantIdentifierUniverse identifiers(java.util.List<String> identifiers) {
    this.identifiers = identifiers;
    return this;
  }

  public QuantIdentifierUniverse addIdentifiersItem(String identifiersItem) {
    this.identifiers.add(identifiersItem);
    return this;
  }

   /**
   * Get identifiers
   * @return identifiers
  **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_IDENTIFIERS)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public java.util.List<String> getIdentifiers() {
    return identifiers;
  }


  public void setIdentifiers(java.util.List<String> identifiers) {
    this.identifiers = identifiers;
  }


  /**
   * Return true if this QuantIdentifierUniverse object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuantIdentifierUniverse quantIdentifierUniverse = (QuantIdentifierUniverse) o;
    return Objects.equals(this.universeType, quantIdentifierUniverse.universeType) &&
        Objects.equals(this.identifiers, quantIdentifierUniverse.identifiers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(universeType, identifiers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuantIdentifierUniverse {\n");
    sb.append("    universeType: ").append(toIndentedString(universeType)).append("\n");
    sb.append("    identifiers: ").append(toIndentedString(identifiers)).append("\n");
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

