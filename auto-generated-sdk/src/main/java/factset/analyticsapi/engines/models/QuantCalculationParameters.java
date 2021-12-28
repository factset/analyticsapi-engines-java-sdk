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
import factset.analyticsapi.engines.models.DummyUniverse;
import factset.analyticsapi.engines.models.OneOfQuantFdsDateQuantDateList;
import factset.analyticsapi.engines.models.OneOfQuantScreeningExpressionQuantFqlExpressionQuantUniversalScreenParameterQuantAllUniversalScreenParameters;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.openapitools.jackson.nullable.JsonNullable;
import java.util.NoSuchElementException;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * QuantCalculationParameters
 */
@JsonPropertyOrder({
  QuantCalculationParameters.JSON_PROPERTY_UNIVERSE,
  QuantCalculationParameters.JSON_PROPERTY_DATES,
  QuantCalculationParameters.JSON_PROPERTY_FORMULAS
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class QuantCalculationParameters implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_UNIVERSE = "universe";
  private DummyUniverse universe;

  public static final String JSON_PROPERTY_DATES = "dates";
  private JsonNullable<OneOfQuantFdsDateQuantDateList> dates = JsonNullable.<OneOfQuantFdsDateQuantDateList>undefined();

  public static final String JSON_PROPERTY_FORMULAS = "formulas";
  private java.util.List<OneOfQuantScreeningExpressionQuantFqlExpressionQuantUniversalScreenParameterQuantAllUniversalScreenParameters> formulas = null;

  public QuantCalculationParameters() { 
  }

  public QuantCalculationParameters universe(DummyUniverse universe) {
    this.universe = universe;
    return this;
  }

   /**
   * Get universe
   * @return universe
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_UNIVERSE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public DummyUniverse getUniverse() {
    return universe;
  }


  @JsonProperty(JSON_PROPERTY_UNIVERSE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setUniverse(DummyUniverse universe) {
    this.universe = universe;
  }


  public QuantCalculationParameters dates(OneOfQuantFdsDateQuantDateList dates) {
    this.dates = JsonNullable.<OneOfQuantFdsDateQuantDateList>of(dates);
    return this;
  }

   /**
   * Get dates
   * @return dates
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonIgnore

  public OneOfQuantFdsDateQuantDateList getDates() {
        return dates.orElse(null);
  }

  @JsonProperty(JSON_PROPERTY_DATES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public JsonNullable<OneOfQuantFdsDateQuantDateList> getDates_JsonNullable() {
    return dates;
  }
  
  @JsonProperty(JSON_PROPERTY_DATES)
  public void setDates_JsonNullable(JsonNullable<OneOfQuantFdsDateQuantDateList> dates) {
    this.dates = dates;
  }

  public void setDates(OneOfQuantFdsDateQuantDateList dates) {
    this.dates = JsonNullable.<OneOfQuantFdsDateQuantDateList>of(dates);
  }


  public QuantCalculationParameters formulas(java.util.List<OneOfQuantScreeningExpressionQuantFqlExpressionQuantUniversalScreenParameterQuantAllUniversalScreenParameters> formulas) {
    this.formulas = formulas;
    return this;
  }

  public QuantCalculationParameters addFormulasItem(OneOfQuantScreeningExpressionQuantFqlExpressionQuantUniversalScreenParameterQuantAllUniversalScreenParameters formulasItem) {
    if (this.formulas == null) {
      this.formulas = new java.util.ArrayList<OneOfQuantScreeningExpressionQuantFqlExpressionQuantUniversalScreenParameterQuantAllUniversalScreenParameters>();
    }
    this.formulas.add(formulasItem);
    return this;
  }

   /**
   * Get formulas
   * @return formulas
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_FORMULAS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public java.util.List<OneOfQuantScreeningExpressionQuantFqlExpressionQuantUniversalScreenParameterQuantAllUniversalScreenParameters> getFormulas() {
    return formulas;
  }


  @JsonProperty(JSON_PROPERTY_FORMULAS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setFormulas(java.util.List<OneOfQuantScreeningExpressionQuantFqlExpressionQuantUniversalScreenParameterQuantAllUniversalScreenParameters> formulas) {
    this.formulas = formulas;
  }


  /**
   * Return true if this QuantCalculationParameters object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuantCalculationParameters quantCalculationParameters = (QuantCalculationParameters) o;
    return Objects.equals(this.universe, quantCalculationParameters.universe) &&
        equalsNullable(this.dates, quantCalculationParameters.dates) &&
        Objects.equals(this.formulas, quantCalculationParameters.formulas);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(universe, hashCodeNullable(dates), formulas);
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuantCalculationParameters {\n");
    sb.append("    universe: ").append(toIndentedString(universe)).append("\n");
    sb.append("    dates: ").append(toIndentedString(dates)).append("\n");
    sb.append("    formulas: ").append(toIndentedString(formulas)).append("\n");
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

