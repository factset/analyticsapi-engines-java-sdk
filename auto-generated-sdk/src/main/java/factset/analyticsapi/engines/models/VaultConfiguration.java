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
import factset.analyticsapi.engines.models.ConfigurationAccount;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * VaultConfiguration
 */
@JsonPropertyOrder({
  VaultConfiguration.JSON_PROPERTY_NAME,
  VaultConfiguration.JSON_PROPERTY_ACCOUNTS
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class VaultConfiguration implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_NAME = "name";
  private String name;

  public static final String JSON_PROPERTY_ACCOUNTS = "accounts";
  private java.util.Map<String, ConfigurationAccount> accounts = null;


  public VaultConfiguration name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Configuration name.
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Configuration name.")
  @JsonProperty(JSON_PROPERTY_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public VaultConfiguration accounts(java.util.Map<String, ConfigurationAccount> accounts) {
    this.accounts = accounts;
    return this;
  }

  public VaultConfiguration putAccountsItem(String key, ConfigurationAccount accountsItem) {
    if (this.accounts == null) {
      this.accounts = new java.util.HashMap<String, ConfigurationAccount>();
    }
    this.accounts.put(key, accountsItem);
    return this;
  }

   /**
   * Get accounts
   * @return accounts
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_ACCOUNTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public java.util.Map<String, ConfigurationAccount> getAccounts() {
    return accounts;
  }


  public void setAccounts(java.util.Map<String, ConfigurationAccount> accounts) {
    this.accounts = accounts;
  }


  /**
   * Return true if this VaultConfiguration object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VaultConfiguration vaultConfiguration = (VaultConfiguration) o;
    return Objects.equals(this.name, vaultConfiguration.name) &&
        Objects.equals(this.accounts, vaultConfiguration.accounts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, accounts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VaultConfiguration {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    accounts: ").append(toIndentedString(accounts)).append("\n");
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

