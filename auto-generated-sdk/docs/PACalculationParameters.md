

# PACalculationParameters


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**componentid** | **String** | The PA Engine component identifier to analyze. | 
**accounts** | [**java.util.List&lt;PAIdentifier&gt;**](PAIdentifier.md) | List of accounts. |  [optional]
**benchmarks** | [**java.util.List&lt;PAIdentifier&gt;**](PAIdentifier.md) | List of benchmarks. |  [optional]
**dates** | [**PADateParameters**](PADateParameters.md) |  |  [optional]
**groups** | [**java.util.List&lt;PACalculationGroup&gt;**](PACalculationGroup.md) | List of groupings for the PA calculation. This will take precedence over the groupings saved in the PA document. |  [optional]
**currencyisocode** | **String** | Currency ISO code for calculation. |  [optional]
**columns** | [**java.util.List&lt;PACalculationColumn&gt;**](PACalculationColumn.md) | List of columns for the PA calculation. This will take precedence over the columns saved in the PA document. |  [optional]
**datasources** | [**PACalculationDataSources**](PACalculationDataSources.md) |  |  [optional]
**componentdetail** | **String** | Component detail type for the PA component. It can be GROUPS or TOTALS or SECURITIES. |  [optional]
**periodicMultipliers** | **java.util.List&lt;Double&gt;** |  |  [optional]
**nperiodicMultipliers** | **java.util.List&lt;Object&gt;** |  |  [optional]
**dhistRcvAssumpRates** | **java.util.List&lt;Double&gt;** |  |  [optional]
**ihistRcvAssumpRates** | **java.util.List&lt;Integer&gt;** |  |  [optional]
**iperiodicMultipliers** | **java.util.List&lt;Integer&gt;** |  |  [optional]
**inperiodicMultipliers** | **java.util.List&lt;Object&gt;** |  |  [optional]


## Implemented Interfaces

* Serializable


