/*
 * Engines API
 * Allow clients to fetch Analytics through API.
 *
 * The version of the OpenAPI document: v3:[pa,spar,vault,pub,quant,fi,axp,afi,npo,bpm,fpo,others],v1:[fiab]
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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * VaultAttributionLoaderResponse
 */
@JsonPropertyOrder({
  VaultAttributionLoaderResponse.JSON_PROPERTY_PROCESS_ID,
  VaultAttributionLoaderResponse.JSON_PROPERTY_GROUP_ID,
  VaultAttributionLoaderResponse.JSON_PROPERTY_OBJECT_ID
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class VaultAttributionLoaderResponse implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_PROCESS_ID = "processId";
  private String processId;

  public static final String JSON_PROPERTY_GROUP_ID = "groupId";
  private String groupId;

  public static final String JSON_PROPERTY_OBJECT_ID = "objectId";
  private String objectId;


  public VaultAttributionLoaderResponse processId(String processId) {
    this.processId = processId;
    return this;
  }

   /**
   * ProcessId.
   * @return processId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "ProcessId.")
  @JsonProperty(JSON_PROPERTY_PROCESS_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getProcessId() {
    return processId;
  }


  public void setProcessId(String processId) {
    this.processId = processId;
  }


  public VaultAttributionLoaderResponse groupId(String groupId) {
    this.groupId = groupId;
    return this;
  }

   /**
   * GroupId.
   * @return groupId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "GroupId.")
  @JsonProperty(JSON_PROPERTY_GROUP_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getGroupId() {
    return groupId;
  }


  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }


  public VaultAttributionLoaderResponse objectId(String objectId) {
    this.objectId = objectId;
    return this;
  }

   /**
   * ObjectId.
   * @return objectId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "ObjectId.")
  @JsonProperty(JSON_PROPERTY_OBJECT_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getObjectId() {
    return objectId;
  }


  public void setObjectId(String objectId) {
    this.objectId = objectId;
  }


  /**
   * Return true if this VaultAttributionLoaderResponse object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VaultAttributionLoaderResponse vaultAttributionLoaderResponse = (VaultAttributionLoaderResponse) o;
    return Objects.equals(this.processId, vaultAttributionLoaderResponse.processId) &&
        Objects.equals(this.groupId, vaultAttributionLoaderResponse.groupId) &&
        Objects.equals(this.objectId, vaultAttributionLoaderResponse.objectId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(processId, groupId, objectId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VaultAttributionLoaderResponse {\n");
    sb.append("    processId: ").append(toIndentedString(processId)).append("\n");
    sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
    sb.append("    objectId: ").append(toIndentedString(objectId)).append("\n");
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

