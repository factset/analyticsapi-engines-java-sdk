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
 * ConfigurationAccount
 */
@JsonPropertyOrder({
  ConfigurationAccount.JSON_PROPERTY_BENCHMARK_CODE,
  ConfigurationAccount.JSON_PROPERTY_BENCHMARK_NAME,
  ConfigurationAccount.JSON_PROPERTY_MAX_END_DATE,
  ConfigurationAccount.JSON_PROPERTY_MIN_START_DATE,
  ConfigurationAccount.JSON_PROPERTY_LOCKING_DATE,
  ConfigurationAccount.JSON_PROPERTY_NAME
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class ConfigurationAccount implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_BENCHMARK_CODE = "benchmarkCode";
  private String benchmarkCode;

  public static final String JSON_PROPERTY_BENCHMARK_NAME = "benchmarkName";
  private String benchmarkName;

  public static final String JSON_PROPERTY_MAX_END_DATE = "maxEndDate";
  private String maxEndDate;

  public static final String JSON_PROPERTY_MIN_START_DATE = "minStartDate";
  private String minStartDate;

  public static final String JSON_PROPERTY_LOCKING_DATE = "lockingDate";
  private String lockingDate;

  public static final String JSON_PROPERTY_NAME = "name";
  private String name;


  public ConfigurationAccount benchmarkCode(String benchmarkCode) {
    this.benchmarkCode = benchmarkCode;
    return this;
  }

   /**
   * Benchmark code.
   * @return benchmarkCode
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Benchmark code.")
  @JsonProperty(JSON_PROPERTY_BENCHMARK_CODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getBenchmarkCode() {
    return benchmarkCode;
  }


  public void setBenchmarkCode(String benchmarkCode) {
    this.benchmarkCode = benchmarkCode;
  }


  public ConfigurationAccount benchmarkName(String benchmarkName) {
    this.benchmarkName = benchmarkName;
    return this;
  }

   /**
   * Benchmark name.
   * @return benchmarkName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Benchmark name.")
  @JsonProperty(JSON_PROPERTY_BENCHMARK_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getBenchmarkName() {
    return benchmarkName;
  }


  public void setBenchmarkName(String benchmarkName) {
    this.benchmarkName = benchmarkName;
  }


  public ConfigurationAccount maxEndDate(String maxEndDate) {
    this.maxEndDate = maxEndDate;
    return this;
  }

   /**
   * Maximum end date.
   * @return maxEndDate
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Maximum end date.")
  @JsonProperty(JSON_PROPERTY_MAX_END_DATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getMaxEndDate() {
    return maxEndDate;
  }


  public void setMaxEndDate(String maxEndDate) {
    this.maxEndDate = maxEndDate;
  }


  public ConfigurationAccount minStartDate(String minStartDate) {
    this.minStartDate = minStartDate;
    return this;
  }

   /**
   * Minimum start date.
   * @return minStartDate
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Minimum start date.")
  @JsonProperty(JSON_PROPERTY_MIN_START_DATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getMinStartDate() {
    return minStartDate;
  }


  public void setMinStartDate(String minStartDate) {
    this.minStartDate = minStartDate;
  }


  public ConfigurationAccount lockingDate(String lockingDate) {
    this.lockingDate = lockingDate;
    return this;
  }

   /**
   * Locking date.
   * @return lockingDate
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Locking date.")
  @JsonProperty(JSON_PROPERTY_LOCKING_DATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getLockingDate() {
    return lockingDate;
  }


  public void setLockingDate(String lockingDate) {
    this.lockingDate = lockingDate;
  }


  public ConfigurationAccount name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Account name.
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Account name.")
  @JsonProperty(JSON_PROPERTY_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  /**
   * Return true if this ConfigurationAccount object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConfigurationAccount configurationAccount = (ConfigurationAccount) o;
    return Objects.equals(this.benchmarkCode, configurationAccount.benchmarkCode) &&
        Objects.equals(this.benchmarkName, configurationAccount.benchmarkName) &&
        Objects.equals(this.maxEndDate, configurationAccount.maxEndDate) &&
        Objects.equals(this.minStartDate, configurationAccount.minStartDate) &&
        Objects.equals(this.lockingDate, configurationAccount.lockingDate) &&
        Objects.equals(this.name, configurationAccount.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(benchmarkCode, benchmarkName, maxEndDate, minStartDate, lockingDate, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConfigurationAccount {\n");
    sb.append("    benchmarkCode: ").append(toIndentedString(benchmarkCode)).append("\n");
    sb.append("    benchmarkName: ").append(toIndentedString(benchmarkName)).append("\n");
    sb.append("    maxEndDate: ").append(toIndentedString(maxEndDate)).append("\n");
    sb.append("    minStartDate: ").append(toIndentedString(minStartDate)).append("\n");
    sb.append("    lockingDate: ").append(toIndentedString(lockingDate)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

