

# CalculationStatus

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**status** | [**StatusEnum**](#StatusEnum) |  |  [optional]
**units** | **Integer** | Number of calculation units in batch. |  [optional]
**pa** | [**java.util.Map&lt;String, CalculationUnitStatus&gt;**](CalculationUnitStatus.md) | List of statuses for PA calculation units. |  [optional]
**spar** | [**java.util.Map&lt;String, CalculationUnitStatus&gt;**](CalculationUnitStatus.md) | List of statuses for SPAR calculation units. |  [optional]
**vault** | [**java.util.Map&lt;String, CalculationUnitStatus&gt;**](CalculationUnitStatus.md) | List of statuses for Vault calculation units. |  [optional]
**pub** | [**java.util.Map&lt;String, CalculationUnitStatus&gt;**](CalculationUnitStatus.md) | List of statuses for Publisher calculation units. |  [optional]



## Enum: StatusEnum

Name | Value
---- | -----
QUEUED | &quot;Queued&quot;
EXECUTING | &quot;Executing&quot;
COMPLETED | &quot;Completed&quot;
CANCELLED | &quot;Cancelled&quot;


## Implemented Interfaces

* Serializable


