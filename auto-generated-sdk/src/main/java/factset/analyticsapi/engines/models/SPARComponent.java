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
import factset.analyticsapi.engines.models.SPARIdentifier;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * SPARComponent
 */
@JsonPropertyOrder({
  SPARComponent.JSON_PROPERTY_ID,
  SPARComponent.JSON_PROPERTY_ACCOUNTS,
  SPARComponent.JSON_PROPERTY_BENCHMARKS,
  SPARComponent.JSON_PROPERTY_CURRENCY_ISO_CODE,
  SPARComponent.JSON_PROPERTY_PATH,
  SPARComponent.JSON_PROPERTY_NAME,
  SPARComponent.JSON_PROPERTY_CATEGORY
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class SPARComponent implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_ID = "id";
  private String id;

  public static final String JSON_PROPERTY_ACCOUNTS = "accounts";
  private java.util.List<SPARIdentifier> accounts = null;

  public static final String JSON_PROPERTY_BENCHMARKS = "benchmarks";
  private SPARIdentifier benchmarks;

  public static final String JSON_PROPERTY_CURRENCY_ISO_CODE = "currencyIsoCode";
  private String currencyIsoCode;

  public static final String JSON_PROPERTY_PATH = "path";
  private String path;

  public static final String JSON_PROPERTY_NAME = "name";
  private String name;

  public static final String JSON_PROPERTY_CATEGORY = "category";
  private String category;


  public SPARComponent id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Component identifier.
   * @return id
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Component identifier.")
  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
  }


  public SPARComponent accounts(java.util.List<SPARIdentifier> accounts) {
    this.accounts = accounts;
    return this;
  }

  public SPARComponent addAccountsItem(SPARIdentifier accountsItem) {
    if (this.accounts == null) {
      this.accounts = new java.util.ArrayList<SPARIdentifier>();
    }
    this.accounts.add(accountsItem);
    return this;
  }

   /**
   * List of accounts in SPAR document.
   * @return accounts
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "List of accounts in SPAR document.")
  @JsonProperty(JSON_PROPERTY_ACCOUNTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public java.util.List<SPARIdentifier> getAccounts() {
    return accounts;
  }


  public void setAccounts(java.util.List<SPARIdentifier> accounts) {
    this.accounts = accounts;
  }


  public SPARComponent benchmarks(SPARIdentifier benchmarks) {
    this.benchmarks = benchmarks;
    return this;
  }

   /**
   * Get benchmarks
   * @return benchmarks
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_BENCHMARKS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public SPARIdentifier getBenchmarks() {
    return benchmarks;
  }


  public void setBenchmarks(SPARIdentifier benchmarks) {
    this.benchmarks = benchmarks;
  }


  public SPARComponent currencyIsoCode(String currencyIsoCode) {
    this.currencyIsoCode = currencyIsoCode;
    return this;
  }

   /**
   * CurrencyCode in SPAR document.
   * @return currencyIsoCode
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "CurrencyCode in SPAR document.")
  @JsonProperty(JSON_PROPERTY_CURRENCY_ISO_CODE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCurrencyIsoCode() {
    return currencyIsoCode;
  }


  public void setCurrencyIsoCode(String currencyIsoCode) {
    this.currencyIsoCode = currencyIsoCode;
  }


  public SPARComponent path(String path) {
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


  public SPARComponent name(String name) {
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


  public SPARComponent category(String category) {
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
   * Return true if this SPARComponent object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SPARComponent spARComponent = (SPARComponent) o;
    return Objects.equals(this.id, spARComponent.id) &&
        Objects.equals(this.accounts, spARComponent.accounts) &&
        Objects.equals(this.benchmarks, spARComponent.benchmarks) &&
        Objects.equals(this.currencyIsoCode, spARComponent.currencyIsoCode) &&
        Objects.equals(this.path, spARComponent.path) &&
        Objects.equals(this.name, spARComponent.name) &&
        Objects.equals(this.category, spARComponent.category);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, accounts, benchmarks, currencyIsoCode, path, name, category);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SPARComponent {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    accounts: ").append(toIndentedString(accounts)).append("\n");
    sb.append("    benchmarks: ").append(toIndentedString(benchmarks)).append("\n");
    sb.append("    currencyIsoCode: ").append(toIndentedString(currencyIsoCode)).append("\n");
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

