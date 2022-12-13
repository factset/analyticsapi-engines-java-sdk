

# CalculationUnitStatus


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**status** | [**StatusEnum**](#StatusEnum) | The status of calculation unit. |  [optional]
**errors** | [**java.util.List&lt;Error&gt;**](Error.md) | The error in a calculation unit. |  [optional]
**result** | **String** | The result URL of the calculation. |  [optional]
**progress** | **String** | The progress of the calculation unit. |  [optional]
**points** | **Integer** | The points for the calculation unit. |  [optional]
**dhistRcvAssumpRates** | **java.util.List&lt;Double&gt;** |  |  [optional]
**ihistRcvAssumpRates** | **java.util.List&lt;Integer&gt;** |  |  [optional]



## Enum: StatusEnum

Name | Value
---- | -----
QUEUED | &quot;Queued&quot;
EXECUTING | &quot;Executing&quot;
SUCCESS | &quot;Success&quot;
FAILED | &quot;Failed&quot;
CANCELLED | &quot;Cancelled&quot;


## Implemented Interfaces

* Serializable


