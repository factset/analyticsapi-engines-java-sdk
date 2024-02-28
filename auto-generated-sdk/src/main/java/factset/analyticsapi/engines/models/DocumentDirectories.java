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
 * DocumentDirectories
 */
@JsonPropertyOrder({
  DocumentDirectories.JSON_PROPERTY_DOCUMENTS,
  DocumentDirectories.JSON_PROPERTY_DIRECTORIES
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class DocumentDirectories implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_DOCUMENTS = "documents";
  private java.util.List<String> documents = null;

  public static final String JSON_PROPERTY_DIRECTORIES = "directories";
  private java.util.List<String> directories = null;


  public DocumentDirectories documents(java.util.List<String> documents) {
    this.documents = documents;
    return this;
  }

  public DocumentDirectories addDocumentsItem(String documentsItem) {
    if (this.documents == null) {
      this.documents = new java.util.ArrayList<String>();
    }
    this.documents.add(documentsItem);
    return this;
  }

   /**
   * List of documents
   * @return documents
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "List of documents")
  @JsonProperty(JSON_PROPERTY_DOCUMENTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public java.util.List<String> getDocuments() {
    return documents;
  }


  public void setDocuments(java.util.List<String> documents) {
    this.documents = documents;
  }


  public DocumentDirectories directories(java.util.List<String> directories) {
    this.directories = directories;
    return this;
  }

  public DocumentDirectories addDirectoriesItem(String directoriesItem) {
    if (this.directories == null) {
      this.directories = new java.util.ArrayList<String>();
    }
    this.directories.add(directoriesItem);
    return this;
  }

   /**
   * List of directories.
   * @return directories
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "List of directories.")
  @JsonProperty(JSON_PROPERTY_DIRECTORIES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public java.util.List<String> getDirectories() {
    return directories;
  }


  public void setDirectories(java.util.List<String> directories) {
    this.directories = directories;
  }


  /**
   * Return true if this DocumentDirectories object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DocumentDirectories documentDirectories = (DocumentDirectories) o;
    return Objects.equals(this.documents, documentDirectories.documents) &&
        Objects.equals(this.directories, documentDirectories.directories);
  }

  @Override
  public int hashCode() {
    return Objects.hash(documents, directories);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DocumentDirectories {\n");
    sb.append("    documents: ").append(toIndentedString(documents)).append("\n");
    sb.append("    directories: ").append(toIndentedString(directories)).append("\n");
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

