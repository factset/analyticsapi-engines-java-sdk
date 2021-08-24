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
import factset.analyticsapi.engines.models.OneOfQuantDateQuantFdsDateQuantDateList;
import factset.analyticsapi.engines.models.OneOfQuantFormulaQuantScreeningExpressionQuantFqlExpressionQuantUniversalScreenParameterQuantAllUniversalScreenParameters;
import factset.analyticsapi.engines.models.OneOfQuantUniversalScreenUniverseQuantScreeningExpressionUniverseQuantIdentifierUniverse;
import factset.analyticsapi.engines.models.QuantDateList1;
import factset.analyticsapi.engines.models.QuantFdsDate1;
import factset.analyticsapi.engines.models.QuantFqlExpression1;
import factset.analyticsapi.engines.models.QuantIdentifierUniverse1;
import factset.analyticsapi.engines.models.QuantScreeningExpression1;
import factset.analyticsapi.engines.models.QuantScreeningExpressionUniverse1;
import factset.analyticsapi.engines.models.QuantUniversalScreenParameter1;
import factset.analyticsapi.engines.models.QuantUniversalScreenUniverse1;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import factset.analyticsapi.engines.JSON;


/**
 * QuantCalculationParameters
 */
@JsonPropertyOrder({
  QuantCalculationParameters.JSON_PROPERTY_UNIVERSE,
  QuantCalculationParameters.JSON_PROPERTY_DATES,
  QuantCalculationParameters.JSON_PROPERTY_FORMULAS,
  QuantCalculationParameters.JSON_PROPERTY_SCREENING_EXPRESSION_UNIVERSE,
  QuantCalculationParameters.JSON_PROPERTY_UNIVERSAL_SCREEN_UNIVERSE,
  QuantCalculationParameters.JSON_PROPERTY_IDENTIFIER_UNIVERSE,
  QuantCalculationParameters.JSON_PROPERTY_FDS_DATE,
  QuantCalculationParameters.JSON_PROPERTY_DATE_LIST,
  QuantCalculationParameters.JSON_PROPERTY_SCREENING_EXPRESSION,
  QuantCalculationParameters.JSON_PROPERTY_FQL_EXPRESSION,
  QuantCalculationParameters.JSON_PROPERTY_UNIVERSAL_SCREEN_PARAMETER,
  QuantCalculationParameters.JSON_PROPERTY_ALL_UNIVERSAL_SCREEN_PARAMETERS
})
@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class QuantCalculationParameters implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String JSON_PROPERTY_UNIVERSE = "universe";
  private OneOfQuantUniversalScreenUniverseQuantScreeningExpressionUniverseQuantIdentifierUniverse universe = null;

  public static final String JSON_PROPERTY_DATES = "dates";
  private OneOfQuantDateQuantFdsDateQuantDateList dates = null;

  public static final String JSON_PROPERTY_FORMULAS = "formulas";
  private java.util.List<OneOfQuantFormulaQuantScreeningExpressionQuantFqlExpressionQuantUniversalScreenParameterQuantAllUniversalScreenParameters> formulas = null;

  public static final String JSON_PROPERTY_SCREENING_EXPRESSION_UNIVERSE = "screeningExpressionUniverse";
  private QuantScreeningExpressionUniverse1 screeningExpressionUniverse;

  public static final String JSON_PROPERTY_UNIVERSAL_SCREEN_UNIVERSE = "universalScreenUniverse";
  private QuantUniversalScreenUniverse1 universalScreenUniverse;

  public static final String JSON_PROPERTY_IDENTIFIER_UNIVERSE = "identifierUniverse";
  private QuantIdentifierUniverse1 identifierUniverse;

  public static final String JSON_PROPERTY_FDS_DATE = "fdsDate";
  private QuantFdsDate1 fdsDate;

  public static final String JSON_PROPERTY_DATE_LIST = "dateList";
  private QuantDateList1 dateList;

  public static final String JSON_PROPERTY_SCREENING_EXPRESSION = "screeningExpression";
  private java.util.List<QuantScreeningExpression1> screeningExpression = null;

  public static final String JSON_PROPERTY_FQL_EXPRESSION = "fqlExpression";
  private java.util.List<QuantFqlExpression1> fqlExpression = null;

  public static final String JSON_PROPERTY_UNIVERSAL_SCREEN_PARAMETER = "universalScreenParameter";
  private java.util.List<QuantUniversalScreenParameter1> universalScreenParameter = null;

  public static final String JSON_PROPERTY_ALL_UNIVERSAL_SCREEN_PARAMETERS = "allUniversalScreenParameters";
  private java.util.List<Object> allUniversalScreenParameters = null;


  public QuantCalculationParameters universe(OneOfQuantUniversalScreenUniverseQuantScreeningExpressionUniverseQuantIdentifierUniverse universe) {
    this.universe = universe;
    return this;
  }

   /**
   * Get universe
   * @return universe
  **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_UNIVERSE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public OneOfQuantUniversalScreenUniverseQuantScreeningExpressionUniverseQuantIdentifierUniverse getUniverse() {
    return universe;
  }


  public void setUniverse(OneOfQuantUniversalScreenUniverseQuantScreeningExpressionUniverseQuantIdentifierUniverse universe) {
    this.universe = universe;
  }


  public QuantCalculationParameters dates(OneOfQuantDateQuantFdsDateQuantDateList dates) {
    this.dates = dates;
    return this;
  }

   /**
   * Get dates
   * @return dates
  **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty(JSON_PROPERTY_DATES)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public OneOfQuantDateQuantFdsDateQuantDateList getDates() {
    return dates;
  }


  public void setDates(OneOfQuantDateQuantFdsDateQuantDateList dates) {
    this.dates = dates;
  }


  public QuantCalculationParameters formulas(java.util.List<OneOfQuantFormulaQuantScreeningExpressionQuantFqlExpressionQuantUniversalScreenParameterQuantAllUniversalScreenParameters> formulas) {
    this.formulas = formulas;
    return this;
  }

  public QuantCalculationParameters addFormulasItem(OneOfQuantFormulaQuantScreeningExpressionQuantFqlExpressionQuantUniversalScreenParameterQuantAllUniversalScreenParameters formulasItem) {
    if (this.formulas == null) {
      this.formulas = new java.util.ArrayList<OneOfQuantFormulaQuantScreeningExpressionQuantFqlExpressionQuantUniversalScreenParameterQuantAllUniversalScreenParameters>();
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

  public java.util.List<OneOfQuantFormulaQuantScreeningExpressionQuantFqlExpressionQuantUniversalScreenParameterQuantAllUniversalScreenParameters> getFormulas() {
    return formulas;
  }


  public void setFormulas(java.util.List<OneOfQuantFormulaQuantScreeningExpressionQuantFqlExpressionQuantUniversalScreenParameterQuantAllUniversalScreenParameters> formulas) {
    this.formulas = formulas;
  }


  public QuantCalculationParameters screeningExpressionUniverse(QuantScreeningExpressionUniverse1 screeningExpressionUniverse) {
    this.screeningExpressionUniverse = screeningExpressionUniverse;
    return this;
  }

   /**
   * Get screeningExpressionUniverse
   * @return screeningExpressionUniverse
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_SCREENING_EXPRESSION_UNIVERSE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public QuantScreeningExpressionUniverse1 getScreeningExpressionUniverse() {
    return screeningExpressionUniverse;
  }


  public void setScreeningExpressionUniverse(QuantScreeningExpressionUniverse1 screeningExpressionUniverse) {
    this.screeningExpressionUniverse = screeningExpressionUniverse;
  }


  public QuantCalculationParameters universalScreenUniverse(QuantUniversalScreenUniverse1 universalScreenUniverse) {
    this.universalScreenUniverse = universalScreenUniverse;
    return this;
  }

   /**
   * Get universalScreenUniverse
   * @return universalScreenUniverse
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_UNIVERSAL_SCREEN_UNIVERSE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public QuantUniversalScreenUniverse1 getUniversalScreenUniverse() {
    return universalScreenUniverse;
  }


  public void setUniversalScreenUniverse(QuantUniversalScreenUniverse1 universalScreenUniverse) {
    this.universalScreenUniverse = universalScreenUniverse;
  }


  public QuantCalculationParameters identifierUniverse(QuantIdentifierUniverse1 identifierUniverse) {
    this.identifierUniverse = identifierUniverse;
    return this;
  }

   /**
   * Get identifierUniverse
   * @return identifierUniverse
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_IDENTIFIER_UNIVERSE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public QuantIdentifierUniverse1 getIdentifierUniverse() {
    return identifierUniverse;
  }


  public void setIdentifierUniverse(QuantIdentifierUniverse1 identifierUniverse) {
    this.identifierUniverse = identifierUniverse;
  }


  public QuantCalculationParameters fdsDate(QuantFdsDate1 fdsDate) {
    this.fdsDate = fdsDate;
    return this;
  }

   /**
   * Get fdsDate
   * @return fdsDate
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_FDS_DATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public QuantFdsDate1 getFdsDate() {
    return fdsDate;
  }


  public void setFdsDate(QuantFdsDate1 fdsDate) {
    this.fdsDate = fdsDate;
  }


  public QuantCalculationParameters dateList(QuantDateList1 dateList) {
    this.dateList = dateList;
    return this;
  }

   /**
   * Get dateList
   * @return dateList
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_DATE_LIST)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public QuantDateList1 getDateList() {
    return dateList;
  }


  public void setDateList(QuantDateList1 dateList) {
    this.dateList = dateList;
  }


  public QuantCalculationParameters screeningExpression(java.util.List<QuantScreeningExpression1> screeningExpression) {
    this.screeningExpression = screeningExpression;
    return this;
  }

  public QuantCalculationParameters addScreeningExpressionItem(QuantScreeningExpression1 screeningExpressionItem) {
    if (this.screeningExpression == null) {
      this.screeningExpression = new java.util.ArrayList<QuantScreeningExpression1>();
    }
    this.screeningExpression.add(screeningExpressionItem);
    return this;
  }

   /**
   * Get screeningExpression
   * @return screeningExpression
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_SCREENING_EXPRESSION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public java.util.List<QuantScreeningExpression1> getScreeningExpression() {
    return screeningExpression;
  }


  public void setScreeningExpression(java.util.List<QuantScreeningExpression1> screeningExpression) {
    this.screeningExpression = screeningExpression;
  }


  public QuantCalculationParameters fqlExpression(java.util.List<QuantFqlExpression1> fqlExpression) {
    this.fqlExpression = fqlExpression;
    return this;
  }

  public QuantCalculationParameters addFqlExpressionItem(QuantFqlExpression1 fqlExpressionItem) {
    if (this.fqlExpression == null) {
      this.fqlExpression = new java.util.ArrayList<QuantFqlExpression1>();
    }
    this.fqlExpression.add(fqlExpressionItem);
    return this;
  }

   /**
   * Get fqlExpression
   * @return fqlExpression
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_FQL_EXPRESSION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public java.util.List<QuantFqlExpression1> getFqlExpression() {
    return fqlExpression;
  }


  public void setFqlExpression(java.util.List<QuantFqlExpression1> fqlExpression) {
    this.fqlExpression = fqlExpression;
  }


  public QuantCalculationParameters universalScreenParameter(java.util.List<QuantUniversalScreenParameter1> universalScreenParameter) {
    this.universalScreenParameter = universalScreenParameter;
    return this;
  }

  public QuantCalculationParameters addUniversalScreenParameterItem(QuantUniversalScreenParameter1 universalScreenParameterItem) {
    if (this.universalScreenParameter == null) {
      this.universalScreenParameter = new java.util.ArrayList<QuantUniversalScreenParameter1>();
    }
    this.universalScreenParameter.add(universalScreenParameterItem);
    return this;
  }

   /**
   * Get universalScreenParameter
   * @return universalScreenParameter
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_UNIVERSAL_SCREEN_PARAMETER)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public java.util.List<QuantUniversalScreenParameter1> getUniversalScreenParameter() {
    return universalScreenParameter;
  }


  public void setUniversalScreenParameter(java.util.List<QuantUniversalScreenParameter1> universalScreenParameter) {
    this.universalScreenParameter = universalScreenParameter;
  }


  public QuantCalculationParameters allUniversalScreenParameters(java.util.List<Object> allUniversalScreenParameters) {
    this.allUniversalScreenParameters = allUniversalScreenParameters;
    return this;
  }

  public QuantCalculationParameters addAllUniversalScreenParametersItem(Object allUniversalScreenParametersItem) {
    if (this.allUniversalScreenParameters == null) {
      this.allUniversalScreenParameters = new java.util.ArrayList<Object>();
    }
    this.allUniversalScreenParameters.add(allUniversalScreenParametersItem);
    return this;
  }

   /**
   * Get allUniversalScreenParameters
   * @return allUniversalScreenParameters
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_ALL_UNIVERSAL_SCREEN_PARAMETERS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public java.util.List<Object> getAllUniversalScreenParameters() {
    return allUniversalScreenParameters;
  }


  public void setAllUniversalScreenParameters(java.util.List<Object> allUniversalScreenParameters) {
    this.allUniversalScreenParameters = allUniversalScreenParameters;
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
        Objects.equals(this.dates, quantCalculationParameters.dates) &&
        Objects.equals(this.formulas, quantCalculationParameters.formulas) &&
        Objects.equals(this.screeningExpressionUniverse, quantCalculationParameters.screeningExpressionUniverse) &&
        Objects.equals(this.universalScreenUniverse, quantCalculationParameters.universalScreenUniverse) &&
        Objects.equals(this.identifierUniverse, quantCalculationParameters.identifierUniverse) &&
        Objects.equals(this.fdsDate, quantCalculationParameters.fdsDate) &&
        Objects.equals(this.dateList, quantCalculationParameters.dateList) &&
        Objects.equals(this.screeningExpression, quantCalculationParameters.screeningExpression) &&
        Objects.equals(this.fqlExpression, quantCalculationParameters.fqlExpression) &&
        Objects.equals(this.universalScreenParameter, quantCalculationParameters.universalScreenParameter) &&
        Objects.equals(this.allUniversalScreenParameters, quantCalculationParameters.allUniversalScreenParameters);
  }

  @Override
  public int hashCode() {
    return Objects.hash(universe, dates, formulas, screeningExpressionUniverse, universalScreenUniverse, identifierUniverse, fdsDate, dateList, screeningExpression, fqlExpression, universalScreenParameter, allUniversalScreenParameters);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuantCalculationParameters {\n");
    sb.append("    universe: ").append(toIndentedString(universe)).append("\n");
    sb.append("    dates: ").append(toIndentedString(dates)).append("\n");
    sb.append("    formulas: ").append(toIndentedString(formulas)).append("\n");
    sb.append("    screeningExpressionUniverse: ").append(toIndentedString(screeningExpressionUniverse)).append("\n");
    sb.append("    universalScreenUniverse: ").append(toIndentedString(universalScreenUniverse)).append("\n");
    sb.append("    identifierUniverse: ").append(toIndentedString(identifierUniverse)).append("\n");
    sb.append("    fdsDate: ").append(toIndentedString(fdsDate)).append("\n");
    sb.append("    dateList: ").append(toIndentedString(dateList)).append("\n");
    sb.append("    screeningExpression: ").append(toIndentedString(screeningExpression)).append("\n");
    sb.append("    fqlExpression: ").append(toIndentedString(fqlExpression)).append("\n");
    sb.append("    universalScreenParameter: ").append(toIndentedString(universalScreenParameter)).append("\n");
    sb.append("    allUniversalScreenParameters: ").append(toIndentedString(allUniversalScreenParameters)).append("\n");
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

