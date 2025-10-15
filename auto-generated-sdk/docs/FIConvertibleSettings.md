

# FIConvertibleSettings


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**equityPrice** | **Double** | Equity Price |  [optional]
**equityIndexVolatility** | **Double** | Equity Index Volatility |  [optional]
**equityDividendYield** | **Double** | Equity Dividend Yield |  [optional]
**volatilityCapMethod** | [**VolatilityCapMethodEnum**](#VolatilityCapMethodEnum) | Volatility CapMethod |  [optional]
**volatilityCapMethodValue** | **Double** | Volatility CapMethod Value - (Required only when  volatilityCapMethod is specifiedRate or multiplier) |  [optional]
**equityExchangeRate** | **Double** | Equity Exchange Rate |  [optional]



## Enum: VolatilityCapMethodEnum

Name | Value
---- | -----
NOCAP | &quot;noCap&quot;
SPECIFIEDRATE | &quot;specifiedRate&quot;
MULTIPLIER | &quot;multiplier&quot;


## Implemented Interfaces

* Serializable


