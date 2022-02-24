

# FISecurity


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**settlement** | **String** | Settlement date |  [optional]
**callMethod** | [**CallMethodEnum**](#CallMethodEnum) | Call Method |  [optional]
**calcFromMethod** | **String** | Calculation from method |  [optional]
**calcFromValue** | **Double** | Calculation from value | 
**face** | **Double** | Face |  [optional]
**faceType** | [**FaceTypeEnum**](#FaceTypeEnum) | Face type |  [optional]
**symbol** | **String** | Symbol | 
**discountCurve** | **String** | Discount curve |  [optional]



## Enum: CallMethodEnum

Name | Value
---- | -----
NO_CALL | &quot;No Call&quot;
INTRINSIC_VALUE | &quot;Intrinsic Value&quot;
FIRST_CALL | &quot;First Call&quot;
FIRST_PAR | &quot;First Par&quot;



## Enum: FaceTypeEnum

Name | Value
---- | -----
CURRENT | &quot;Current&quot;
ORIGINAL | &quot;Original&quot;


## Implemented Interfaces

* Serializable


