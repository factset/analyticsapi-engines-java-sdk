

# FIABCalculationStatus


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | Calculation&#39;s identifier |  [optional]
**status** | [**StatusEnum**](#StatusEnum) | Calculation&#39;s status |  [optional]
**startdatetime** | **OffsetDateTime** | Start time |  [optional]
**completiondatetime** | **OffsetDateTime** | Completion time |  [optional]
**progress** | **Integer** | Calculation&#39;s progress |  [optional]
**batchevents** | [**java.util.List&lt;EventSummary&gt;**](EventSummary.md) | List of batch events |  [optional]



## Enum: StatusEnum

Name | Value
---- | -----
PENDING | &quot;Pending&quot;
INPROGRESS | &quot;InProgress&quot;
DONE | &quot;Done&quot;
PAUSED | &quot;Paused&quot;
CANCELLED | &quot;Cancelled&quot;
ERROR | &quot;Error&quot;


## Implemented Interfaces

* Serializable


