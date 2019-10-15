

# CalculationStatus

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**status** | [**StatusEnum**](#StatusEnum) |  |  [optional]
**points** | **Integer** |  |  [optional]
**pa** | [**java.util.Map&lt;String, CalculationUnitStatus&gt;**](CalculationUnitStatus.md) |  |  [optional]
**spar** | [**java.util.Map&lt;String, CalculationUnitStatus&gt;**](CalculationUnitStatus.md) |  |  [optional]
**vault** | [**java.util.Map&lt;String, CalculationUnitStatus&gt;**](CalculationUnitStatus.md) |  |  [optional]



## Enum: StatusEnum

Name | Value
---- | -----
QUEUED | &quot;Queued&quot;
EXECUTING | &quot;Executing&quot;
COMPLETED | &quot;Completed&quot;
CANCELLED | &quot;Cancelled&quot;



