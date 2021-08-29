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
 * QuantCalculationMeta
 */
@JsonPropertyOrder({
  QuantCalculationMeta.JSON_PROPERTY_ALLOW_ARRAY_DATA,
  QuantCalculationMeta.JSON_PROPERTY_CONTENTORGANIZATION,
  QuantCalculationMeta.JSON_PROPERTY_STACH_CONTENT_ORGANIZATION,
  QuantCalculationMeta.JSON_PROPERTY_CONTENTTYPE,
  QuantCalculationMeta.JSON_PROPERTY_FORMAT
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class QuantCalculationMeta implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_ALLOW_ARRAY_DATA = "allowArrayData";
  private Boolean allowArrayData;

  /**
   * Gets or Sets contentorganization
   */
  public enum ContentorganizationEnum {
    NONE("None"),
    
    ROW("Row"),
    
    COLUMN("Column"),
    
    SIMPLIFIEDROW("SimplifiedRow");

    private String value;

    ContentorganizationEnum(String value) {
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
    public static ContentorganizationEnum fromValue(String value) {
      for (ContentorganizationEnum b : ContentorganizationEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_CONTENTORGANIZATION = "contentorganization";
  private ContentorganizationEnum contentorganization = ContentorganizationEnum.SIMPLIFIEDROW;

  /**
   * Gets or Sets stachContentOrganization
   */
  public enum StachContentOrganizationEnum {
    NONE("None"),
    
    ROW("Row"),
    
    COLUMN("Column"),
    
    SIMPLIFIEDROW("SimplifiedRow");

    private String value;

    StachContentOrganizationEnum(String value) {
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
    public static StachContentOrganizationEnum fromValue(String value) {
      for (StachContentOrganizationEnum b : StachContentOrganizationEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_STACH_CONTENT_ORGANIZATION = "stachContentOrganization";
  private StachContentOrganizationEnum stachContentOrganization = StachContentOrganizationEnum.SIMPLIFIEDROW;

  /**
   * Gets or Sets contenttype
   */
  public enum ContenttypeEnum {
    JSON("Json"),
    
    BINARY("Binary");

    private String value;

    ContenttypeEnum(String value) {
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
    public static ContenttypeEnum fromValue(String value) {
      for (ContenttypeEnum b : ContenttypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_CONTENTTYPE = "contenttype";
  private ContenttypeEnum contenttype = ContenttypeEnum.JSON;

  /**
   * Gets or Sets format
   */
  public enum FormatEnum {
    JSONSTACH("JsonStach"),
    
    TABLE("Table"),
    
    TABLEAU("Tableau"),
    
    BINARYSTACH("BinaryStach"),
    
    BISON("Bison"),
    
    BINARY("Binary"),
    
    PDF("Pdf"),
    
    PPTX("Pptx"),
    
    FEATHER("Feather");

    private String value;

    FormatEnum(String value) {
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
    public static FormatEnum fromValue(String value) {
      for (FormatEnum b : FormatEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_FORMAT = "format";
  private FormatEnum format = FormatEnum.JSONSTACH;


  public QuantCalculationMeta allowArrayData(Boolean allowArrayData) {
    this.allowArrayData = allowArrayData;
    return this;
  }

   /**
   * Get allowArrayData
   * @return allowArrayData
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_ALLOW_ARRAY_DATA)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getAllowArrayData() {
    return allowArrayData;
  }


  @JsonProperty(JSON_PROPERTY_ALLOW_ARRAY_DATA)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setAllowArrayData(Boolean allowArrayData) {
    this.allowArrayData = allowArrayData;
  }


  public QuantCalculationMeta contentorganization(ContentorganizationEnum contentorganization) {
    this.contentorganization = contentorganization;
    return this;
  }

   /**
   * Get contentorganization
   * @return contentorganization
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_CONTENTORGANIZATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public ContentorganizationEnum getContentorganization() {
    return contentorganization;
  }


  @JsonProperty(JSON_PROPERTY_CONTENTORGANIZATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setContentorganization(ContentorganizationEnum contentorganization) {
    this.contentorganization = contentorganization;
  }


  public QuantCalculationMeta stachContentOrganization(StachContentOrganizationEnum stachContentOrganization) {
    this.stachContentOrganization = stachContentOrganization;
    return this;
  }

   /**
   * Get stachContentOrganization
   * @return stachContentOrganization
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_STACH_CONTENT_ORGANIZATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public StachContentOrganizationEnum getStachContentOrganization() {
    return stachContentOrganization;
  }


  @JsonProperty(JSON_PROPERTY_STACH_CONTENT_ORGANIZATION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setStachContentOrganization(StachContentOrganizationEnum stachContentOrganization) {
    this.stachContentOrganization = stachContentOrganization;
  }


  public QuantCalculationMeta contenttype(ContenttypeEnum contenttype) {
    this.contenttype = contenttype;
    return this;
  }

   /**
   * Get contenttype
   * @return contenttype
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_CONTENTTYPE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public ContenttypeEnum getContenttype() {
    return contenttype;
  }


  @JsonProperty(JSON_PROPERTY_CONTENTTYPE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setContenttype(ContenttypeEnum contenttype) {
    this.contenttype = contenttype;
  }


  public QuantCalculationMeta format(FormatEnum format) {
    this.format = format;
    return this;
  }

   /**
   * Get format
   * @return format
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_FORMAT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public FormatEnum getFormat() {
    return format;
  }


  @JsonProperty(JSON_PROPERTY_FORMAT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setFormat(FormatEnum format) {
    this.format = format;
  }


  /**
   * Return true if this QuantCalculationMeta object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuantCalculationMeta quantCalculationMeta = (QuantCalculationMeta) o;
    return Objects.equals(this.allowArrayData, quantCalculationMeta.allowArrayData) &&
        Objects.equals(this.contentorganization, quantCalculationMeta.contentorganization) &&
        Objects.equals(this.stachContentOrganization, quantCalculationMeta.stachContentOrganization) &&
        Objects.equals(this.contenttype, quantCalculationMeta.contenttype) &&
        Objects.equals(this.format, quantCalculationMeta.format);
  }

  @Override
  public int hashCode() {
    return Objects.hash(allowArrayData, contentorganization, stachContentOrganization, contenttype, format);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuantCalculationMeta {\n");
    sb.append("    allowArrayData: ").append(toIndentedString(allowArrayData)).append("\n");
    sb.append("    contentorganization: ").append(toIndentedString(contentorganization)).append("\n");
    sb.append("    stachContentOrganization: ").append(toIndentedString(stachContentOrganization)).append("\n");
    sb.append("    contenttype: ").append(toIndentedString(contenttype)).append("\n");
    sb.append("    format: ").append(toIndentedString(format)).append("\n");
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

