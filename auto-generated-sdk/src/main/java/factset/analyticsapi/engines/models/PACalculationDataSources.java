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
import factset.analyticsapi.engines.models.PACalculationPricingSource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * PACalculationDataSources
 */
@JsonPropertyOrder({
  PACalculationDataSources.JSON_PROPERTY_PORTFOLIOPRICINGSOURCES,
  PACalculationDataSources.JSON_PROPERTY_BENCHMARKPRICINGSOURCES,
  PACalculationDataSources.JSON_PROPERTY_USEPORTFOLIOPRICINGSOURCESFORBENCHMARK
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class PACalculationDataSources implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_PORTFOLIOPRICINGSOURCES = "portfoliopricingsources";
  private java.util.List<PACalculationPricingSource> portfoliopricingsources = null;

  public static final String JSON_PROPERTY_BENCHMARKPRICINGSOURCES = "benchmarkpricingsources";
  private java.util.List<PACalculationPricingSource> benchmarkpricingsources = null;

  public static final String JSON_PROPERTY_USEPORTFOLIOPRICINGSOURCESFORBENCHMARK = "useportfoliopricingsourcesforbenchmark";
  private Boolean useportfoliopricingsourcesforbenchmark;


  public PACalculationDataSources portfoliopricingsources(java.util.List<PACalculationPricingSource> portfoliopricingsources) {
    this.portfoliopricingsources = portfoliopricingsources;
    return this;
  }

  public PACalculationDataSources addPortfoliopricingsourcesItem(PACalculationPricingSource portfoliopricingsourcesItem) {
    if (this.portfoliopricingsources == null) {
      this.portfoliopricingsources = new java.util.ArrayList<PACalculationPricingSource>();
    }
    this.portfoliopricingsources.add(portfoliopricingsourcesItem);
    return this;
  }

   /**
   * List of portfilio pricing source for the PA calculation
   * @return portfoliopricingsources
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "List of portfilio pricing source for the PA calculation")
  @JsonProperty(JSON_PROPERTY_PORTFOLIOPRICINGSOURCES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public java.util.List<PACalculationPricingSource> getPortfoliopricingsources() {
    return portfoliopricingsources;
  }


  public void setPortfoliopricingsources(java.util.List<PACalculationPricingSource> portfoliopricingsources) {
    this.portfoliopricingsources = portfoliopricingsources;
  }


  public PACalculationDataSources benchmarkpricingsources(java.util.List<PACalculationPricingSource> benchmarkpricingsources) {
    this.benchmarkpricingsources = benchmarkpricingsources;
    return this;
  }

  public PACalculationDataSources addBenchmarkpricingsourcesItem(PACalculationPricingSource benchmarkpricingsourcesItem) {
    if (this.benchmarkpricingsources == null) {
      this.benchmarkpricingsources = new java.util.ArrayList<PACalculationPricingSource>();
    }
    this.benchmarkpricingsources.add(benchmarkpricingsourcesItem);
    return this;
  }

   /**
   * List of benchmark pricing source for the PA calculation
   * @return benchmarkpricingsources
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "List of benchmark pricing source for the PA calculation")
  @JsonProperty(JSON_PROPERTY_BENCHMARKPRICINGSOURCES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public java.util.List<PACalculationPricingSource> getBenchmarkpricingsources() {
    return benchmarkpricingsources;
  }


  public void setBenchmarkpricingsources(java.util.List<PACalculationPricingSource> benchmarkpricingsources) {
    this.benchmarkpricingsources = benchmarkpricingsources;
  }


  public PACalculationDataSources useportfoliopricingsourcesforbenchmark(Boolean useportfoliopricingsourcesforbenchmark) {
    this.useportfoliopricingsourcesforbenchmark = useportfoliopricingsourcesforbenchmark;
    return this;
  }

   /**
   * Use portfolio pricing sources for benchmark
   * @return useportfoliopricingsourcesforbenchmark
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Use portfolio pricing sources for benchmark")
  @JsonProperty(JSON_PROPERTY_USEPORTFOLIOPRICINGSOURCESFORBENCHMARK)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getUseportfoliopricingsourcesforbenchmark() {
    return useportfoliopricingsourcesforbenchmark;
  }


  public void setUseportfoliopricingsourcesforbenchmark(Boolean useportfoliopricingsourcesforbenchmark) {
    this.useportfoliopricingsourcesforbenchmark = useportfoliopricingsourcesforbenchmark;
  }


  /**
   * Return true if this PACalculationDataSources object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PACalculationDataSources paCalculationDataSources = (PACalculationDataSources) o;
    return Objects.equals(this.portfoliopricingsources, paCalculationDataSources.portfoliopricingsources) &&
        Objects.equals(this.benchmarkpricingsources, paCalculationDataSources.benchmarkpricingsources) &&
        Objects.equals(this.useportfoliopricingsourcesforbenchmark, paCalculationDataSources.useportfoliopricingsourcesforbenchmark);
  }

  @Override
  public int hashCode() {
    return Objects.hash(portfoliopricingsources, benchmarkpricingsources, useportfoliopricingsourcesforbenchmark);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PACalculationDataSources {\n");
    sb.append("    portfoliopricingsources: ").append(toIndentedString(portfoliopricingsources)).append("\n");
    sb.append("    benchmarkpricingsources: ").append(toIndentedString(benchmarkpricingsources)).append("\n");
    sb.append("    useportfoliopricingsourcesforbenchmark: ").append(toIndentedString(useportfoliopricingsourcesforbenchmark)).append("\n");
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
