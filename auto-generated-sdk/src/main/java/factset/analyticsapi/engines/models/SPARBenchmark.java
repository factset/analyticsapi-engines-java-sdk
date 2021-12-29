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
import factset.analyticsapi.engines.models.SPARIdentifier;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * SPARBenchmark
 */
@JsonPropertyOrder({
  SPARBenchmark.JSON_PROPERTY_ID,
  SPARBenchmark.JSON_PROPERTY_NAME,
  SPARBenchmark.JSON_PROPERTY_IDENTIFIERS
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class SPARBenchmark implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_ID = "id";
  private String id;

  public static final String JSON_PROPERTY_NAME = "name";
  private String name;

  public static final String JSON_PROPERTY_IDENTIFIERS = "identifiers";
  private java.util.List<SPARIdentifier> identifiers = null;

  public SPARBenchmark() { 
  }

  public SPARBenchmark id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Benchmark identifier
   * @return id
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Benchmark identifier")
  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getId() {
    return id;
  }


  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setId(String id) {
    this.id = id;
  }


  public SPARBenchmark name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Benchmark Name
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Benchmark Name")
  @JsonProperty(JSON_PROPERTY_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getName() {
    return name;
  }


  @JsonProperty(JSON_PROPERTY_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setName(String name) {
    this.name = name;
  }


  public SPARBenchmark identifiers(java.util.List<SPARIdentifier> identifiers) {
    this.identifiers = identifiers;
    return this;
  }

  public SPARBenchmark addIdentifiersItem(SPARIdentifier identifiersItem) {
    if (this.identifiers == null) {
      this.identifiers = new java.util.ArrayList<SPARIdentifier>();
    }
    this.identifiers.add(identifiersItem);
    return this;
  }

   /**
   * List of SPAR identifiers
   * @return identifiers
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "List of SPAR identifiers")
  @JsonProperty(JSON_PROPERTY_IDENTIFIERS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public java.util.List<SPARIdentifier> getIdentifiers() {
    return identifiers;
  }


  @JsonProperty(JSON_PROPERTY_IDENTIFIERS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setIdentifiers(java.util.List<SPARIdentifier> identifiers) {
    this.identifiers = identifiers;
  }


  /**
   * Return true if this SPARBenchmark object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SPARBenchmark spARBenchmark = (SPARBenchmark) o;
    return Objects.equals(this.id, spARBenchmark.id) &&
        Objects.equals(this.name, spARBenchmark.name) &&
        Objects.equals(this.identifiers, spARBenchmark.identifiers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, identifiers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SPARBenchmark {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

