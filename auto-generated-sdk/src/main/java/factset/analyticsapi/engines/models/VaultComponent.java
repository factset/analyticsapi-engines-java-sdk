/*
 * Engines API
 * Allow clients to fetch Analytics through APIs.
 *
 * The version of the OpenAPI document: v3:[pa,spar,vault,pub,quant,fi,axp,afi,npo,bpm,fpo],v1:[fiab]
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
import factset.analyticsapi.engines.models.VaultDateParameters;
import factset.analyticsapi.engines.models.VaultIdentifier;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * VaultComponent
 */
@JsonPropertyOrder({
  VaultComponent.JSON_PROPERTY_ID,
  VaultComponent.JSON_PROPERTY_ACCOUNT,
  VaultComponent.JSON_PROPERTY_BENCHMARK,
  VaultComponent.JSON_PROPERTY_CURRENCYISOCODE,
  VaultComponent.JSON_PROPERTY_DATES,
  VaultComponent.JSON_PROPERTY_SNAPSHOT,
  VaultComponent.JSON_PROPERTY_PATH,
  VaultComponent.JSON_PROPERTY_NAME,
  VaultComponent.JSON_PROPERTY_CATEGORY
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class VaultComponent implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_ID = "id";
  private String id;

  public static final String JSON_PROPERTY_ACCOUNT = "account";
  private VaultIdentifier account;

  public static final String JSON_PROPERTY_BENCHMARK = "benchmark";
  private VaultIdentifier benchmark;

  public static final String JSON_PROPERTY_CURRENCYISOCODE = "currencyisocode";
  private String currencyisocode;

  public static final String JSON_PROPERTY_DATES = "dates";
  private VaultDateParameters dates;

  public static final String JSON_PROPERTY_SNAPSHOT = "snapshot";
  private Boolean snapshot;

  public static final String JSON_PROPERTY_PATH = "path";
  private String path;

  public static final String JSON_PROPERTY_NAME = "name";
  private String name;

  public static final String JSON_PROPERTY_CATEGORY = "category";
  private String category;


  public VaultComponent id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Vault component identifier
   * @return id
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Vault component identifier")
  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
  }


  public VaultComponent account(VaultIdentifier account) {
    this.account = account;
    return this;
  }

   /**
   * Get account
   * @return account
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_ACCOUNT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public VaultIdentifier getAccount() {
    return account;
  }


  public void setAccount(VaultIdentifier account) {
    this.account = account;
  }


  public VaultComponent benchmark(VaultIdentifier benchmark) {
    this.benchmark = benchmark;
    return this;
  }

   /**
   * Get benchmark
   * @return benchmark
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_BENCHMARK)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public VaultIdentifier getBenchmark() {
    return benchmark;
  }


  public void setBenchmark(VaultIdentifier benchmark) {
    this.benchmark = benchmark;
  }


  public VaultComponent currencyisocode(String currencyisocode) {
    this.currencyisocode = currencyisocode;
    return this;
  }

   /**
   * Currency iso code saved in the document
   * @return currencyisocode
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Currency iso code saved in the document")
  @JsonProperty(JSON_PROPERTY_CURRENCYISOCODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCurrencyisocode() {
    return currencyisocode;
  }


  public void setCurrencyisocode(String currencyisocode) {
    this.currencyisocode = currencyisocode;
  }


  public VaultComponent dates(VaultDateParameters dates) {
    this.dates = dates;
    return this;
  }

   /**
   * Get dates
   * @return dates
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_DATES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public VaultDateParameters getDates() {
    return dates;
  }


  public void setDates(VaultDateParameters dates) {
    this.dates = dates;
  }


  public VaultComponent snapshot(Boolean snapshot) {
    this.snapshot = snapshot;
    return this;
  }

   /**
   * Snapshot
   * @return snapshot
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Snapshot")
  @JsonProperty(JSON_PROPERTY_SNAPSHOT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Boolean getSnapshot() {
    return snapshot;
  }


  public void setSnapshot(Boolean snapshot) {
    this.snapshot = snapshot;
  }


  public VaultComponent path(String path) {
    this.path = path;
    return this;
  }

   /**
   * The path to the document
   * @return path
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The path to the document")
  @JsonProperty(JSON_PROPERTY_PATH)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getPath() {
    return path;
  }


  public void setPath(String path) {
    this.path = path;
  }


  public VaultComponent name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Component name.
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Component name.")
  @JsonProperty(JSON_PROPERTY_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public VaultComponent category(String category) {
    this.category = category;
    return this;
  }

   /**
   * Component category.
   * @return category
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Component category.")
  @JsonProperty(JSON_PROPERTY_CATEGORY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCategory() {
    return category;
  }


  public void setCategory(String category) {
    this.category = category;
  }


  /**
   * Return true if this VaultComponent object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VaultComponent vaultComponent = (VaultComponent) o;
    return Objects.equals(this.id, vaultComponent.id) &&
        Objects.equals(this.account, vaultComponent.account) &&
        Objects.equals(this.benchmark, vaultComponent.benchmark) &&
        Objects.equals(this.currencyisocode, vaultComponent.currencyisocode) &&
        Objects.equals(this.dates, vaultComponent.dates) &&
        Objects.equals(this.snapshot, vaultComponent.snapshot) &&
        Objects.equals(this.path, vaultComponent.path) &&
        Objects.equals(this.name, vaultComponent.name) &&
        Objects.equals(this.category, vaultComponent.category);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, account, benchmark, currencyisocode, dates, snapshot, path, name, category);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VaultComponent {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    account: ").append(toIndentedString(account)).append("\n");
    sb.append("    benchmark: ").append(toIndentedString(benchmark)).append("\n");
    sb.append("    currencyisocode: ").append(toIndentedString(currencyisocode)).append("\n");
    sb.append("    dates: ").append(toIndentedString(dates)).append("\n");
    sb.append("    snapshot: ").append(toIndentedString(snapshot)).append("\n");
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
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

