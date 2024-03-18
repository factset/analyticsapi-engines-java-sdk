6.0.0 (18/03/2024)

Supported API versions:
* PA, SPAR, VAULT, PUB, AXP, BPM, FPO - V3
* FIAB - V1

Summmary:
* Supporting new functionalities in pa,spar,vault,pub,quant and fi.

Functionality Additions:
* New Parameters are added in the FI Request.
* 413 status code is added to Quant Response.
* 'Warnings' are added as an enhancement to quant unit status response object.
* Added code and title as two new fields in the Error Response object.
* Added new Get Component details by Id endpoint for SPAR.

Breaking changes:
* Updated the response schema for GetAllCalculations End Point for pa,spar,vault,pub and quant.

Bug Fixes:
* Removed requiredfield attribute for pagenumber field parameter in GetAllCalculations End Point for pa,spar,vault,pub and quant.
* Removed default values when the parameter field is required for lookup end points for pa,spar,vault and strategy document end points for optimizers.

-----------------------

5.5.0 (12/14/2022)

Supported API versions:
* PA, SPAR, VAULT, PUB, AXP, BPM, FPO - V3
* FIAB - V1

Summary:
* Supporting new functionalities in Quant and FI.

Breaking changes:
* No changes.

Functionality Additions:
* Added new property "IsArrayReturnType" to FQL expression for Qaunt Request.
* Added new property "Structured Products" for FI Request.

Bug Fixes:
* No changes.

-----------------------


5.4.0 (27/06/2022)

Supported API versions:
* PA, SPAR, VAULT, PUB, AXP, BPM, FPO - V3
* FIAB - V1

Summary:
* New Functionality Additions.

Breaking changes:
* No changes.

Functionality Additions:
* Supporting new features/functionalities of the FI API.
* Added new end point for GroupingFrequencies.
* Added new endpoint for Pricing Sources.

Bug Fixes:
* No changes.

-----------------------

5.3.0 (24/03/2022)

Supported API versions:
* PA, SPAR, VAULT, PUB, AXP, BPM, FPO - V3
* FIAB - V1

Summary:
* New Functionality Additions.

Breaking changes:
* No changes.

Functionality Additions:
* Added support for MarketEnviornment in the FI calculation parameters.
* Added FI Discount curves endpoint.
* Added support for overrideUniversalScreenCalendar for Quant Dates.

Bug Fixes:
* No changes.

-----------------------

5.2.1 (24/02/2022)

Supported API versions:
* PA, SPAR, VAULT, PUB, AXP, BPM, FPO - V3
* FIAB - V1

Summary:
* Using latest stach-extensions package(internal dependency).

Breaking changes:
* No changes.

Functionality Additions:
* No changes.

Bug Fixes:
* No changes.

-----------------------

5.2.0 (01/07/2022)

Supported API versions:
* PA, SPAR, VAULT, PUB, AXP, BPM, FPO - V3
* FIAB - V1

Summary:
* Supporting new functionalities in SPAR, FI, Quant APIs.

Breaking changes:
* No changes.

Functionality Additions:
* Supporting new features/functionalities of the FI API.
* Supporting Currency Iso in the SPAR API.
* Supporting Component Manager API.

Bug Fixes:
* Fixing the FPO POST body.
* Fixing the Quant API.

-----------------------

5.1.0 (02/08/2021)

Supported API versions:
* PA, SPAR, VAULT, PUB, AXP, BPM, FPO - V3
* FIAB - V1

Summary:
* Functionality additions and bug fixes.

Breaking changes:
* No changes.

Functionality Additions:
* Adding Quant and AFI API.
* Adding support bearer token to support OAuth.
* Fix to ignore unknown fields in the de-serialization of API response.

Bug Fixes:
* Fixing the FIAB invalid response data types.
* Adding calculationid property to CalculationInfo response.

-----------------------

5.0.0 (25/05/2021)

Supported API versions:
* PA, SPAR, VAULT, PUB, AXP, BPM, FPO - V3
* FIAB - V1

Summary:
* Adding support for caching options

Breaking changes:
* Added support for caching options with V3 API.

Functionality Additions:
* Added support for caching options with V3 API.

Bug Fixes:
* No changes

-----------------------

4.0.0 (10/08/2020)

Supported API versions:
* v2

Summary:
* Adding support for new features

Breaking changes:
* Additional parameter in calculation object constructor
* Remove points property from CalculationStatus and CalculationStatusSummary

Functionality Additions:
* Publisher API calculation and document lookup
* New componentdetail parameter for PA and Vault calculation
* Interactive endpoints for PA, SPAR and Vault APIs

Bug Fixes:
* No changes

-----------------------

3.0.0 (12/02/2019)

Supported API versions:
* v2

Summary:
* Making SDK independent of the API version.

Breaking changes:
* API version is removed from the package name.

Functionality Additions:
* No changes

Bug Fixes:
* No changes

-----------------------

v2-2.0.0 (09/26/2019)

Supported API versions:
* v2

Summary:
* Update class and property names.

Breaking changes:
* Class Name
  * OutstandingCalculation -> CalculationStatusSummary
  * CalculationParameters -> Calculation
  * OutstandingCalculations -> CalculationStatus (class)
  * CalculationStatus (enum) -> UnitStatus
  * Calculation -> CalculationUnitStatus
  * AccountList -> AccountDirectories
  * ComponentListEntity -> ComponentSummary
  * PAComponentEntity -> PAComponent
  * VaultComponentEntity -> VaultComponent
  * ConfigurationItem -> VaultConfigurationSummary
  * ConfigurationRoot -> VaultConfiguration
  * DateSettings -> DateParametersSummary
  * DocumentList -> DocumentDirectories
  * ComponentDateSettings (pa) -> PADateParameters
  * ComponentDateSettings (vault) -> VaultDateParameters
  * Column -> ColumnSummary
  * Component -> ComponentSummary
  * VaultCalculationAccount -> VaultIdentifier
  * ComponentBenchmark -> PAIdentifier
  * ComponentAccount -> PAIdentifier
* Properties
  * pointscount -> points
  * defaultAccounts -> accounts
  * defaultBenchmarks -> benchmarks
  * defaultAccount -> accounts
  * defaultBenchmark -> benchmark
  * currency -> currencyisocode
* Methods
  * getAllCalculationStatusSummaries -> getCalculationStatusSummaries
  * getAllPAColumnStatistics -> getPAColumnStatistics
  * getAllPAColumns -> getPAColumns
  * getPAColumn -> getPAColumnById
  * getAllPACurrencies -> getPACurrencies
  * getAllPAFrequencies -> getPAFrequencies
  * getAllSPARFrequencies -> getSPARFrequencies
  * getAllVaultFrequencies -> getVaultFrequencies
  * getAllPAGroups -> getPAGroups
  * getAllCalculations -> getAllCalculationStatusSummaries
  * getCalculationById -> getCalculationStatusById
* Headers
  * X-FactSet-Api-PointsCount-Limit -> X-FactSet-Api-Points-Limit
  * X-FactSet-Api-PointsCount-Remaining -> X-FactSet-Api-Points-Remaining
* Currencies
  * Change response from List to Dictionary with currencyisocode as key

Functionality Additions:
* New Property snapshot in PAComponent & VaultComponent.


Bug Fixes:
* No changes.

-----------------------

v2-1.0.0 (08/30/2019)

Supported API versions:
* v2
 
Summary:
* Releasing first version of Engines API(v2).
 
Breaking changes:
* No changes
 
Functionality Additions:
* Supports PA, SPAR and Vault calculations.
 
Bug Fixes:
* No changes