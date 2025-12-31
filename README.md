<img alt="FactSet" src="https://www.factset.com/hubfs/Assets/images/factset-logo.svg" height="56" width="290">

# Analytics API Engines Java SDK

[![build](https://img.shields.io/github/workflow/status/factset/analyticsapi-engines-java-sdk/CI)](https://github.com/factset/analyticsapi-engines-java-sdk/actions?query=workflow%3ACI)
[![maven](https://img.shields.io/maven-central/v/com.factset.analyticsapi/engines-sdk)](https://central.sonatype.com/artifact/com.factset.analyticsapi/engines-sdk/6.3.1/overview)
![API version](https://img.shields.io/badge/API-v2-blue)
[![Apache-2 license](https://img.shields.io/badge/license-Apache2-brightgreen.svg)](https://www.apache.org/licenses/LICENSE-2.0)
![Deprecated](https://img.shields.io/badge/status-deprecated-red)

*```**Note: The Analytics SDK will be deprecated effective 31-Aug-2026. While we intend to provide limited security fixes till 31-Aug-2026, this SDK will be archived thereafter and will no longer receive updates or security patches. Please migrate to our Enterprise SDK, which is the long-term supported solution from FactSet. Please choose the appropriate API-specific Enterprise SDK from these search results:```
[enterprise-sdk](https://github.com/factset/enterprise-sdk/tree/main/code/java)*

Use this library to integrate with FactSet's Analytics APIs. Below APIs are supported by this SDK.

* [PA Engine API](https://developer.factset.com/api-catalog/pa-engine-api)
* [SPAR Engine API](https://developer.factset.com/api-catalog/spar-engine-api)
* [Vault API](https://developer.factset.com/api-catalog/vault-api)

## Contents

* [auto-generated-sdk](auto-generated-sdk) - Auto-generated code using [Analytics API Engines SDK Generator](https://github.com/factset/analyticsapi-engines-sdk-generator)
* [examples](examples) - Sample project containing code snippets to quickly get started with the SDK  
* [tests](tests) - Integration tests

## Requirements

* Java 1.7 or Java 1.8
* Maven to build and install the SDK

## Installation

* Install the SDK to your local Maven repository:

  ```sh
  mvn install -DskipTests
  ```

* Install the client dynamically by adding a dependency to the POM file:

  ```xml
  <dependency>
    <groupId>com.factset.analyticsapi</groupId>
    <artifactId>engines-sdk</artifactId>
    <version>ARTIFACT_VERSION</version>
  </dependency>
  ```

## Usage

Refer [examples](examples) project for sample code snippets to quickly get started with the SDK

## Tests

First, clone the repo locally and `cd` into the directory.

```sh
git clone https://github.com/factset/analyticsapi-engines-java-sdk.git
cd tests
```

Before running the tests, set the below environment variables. Use the [Developer Portal Manage API Keys page](https://developer.factset.com/manage-api-keys) to get these values.

```sh
export ANALYTICS_API_USERNAME_SERIAL = "username-serial"
export ANALYTICS_API_PASSWORD = "apikey"
```

Run the tests with below command.

```sh
mvn -q test -Dtest=ApiTestsRunner
```

## Contributing

* Files in [auto-generated-sdk](auto-generated-sdk) directory are auto-generated and should not be manually edited here. Refer [Analytics API Engines SDK Generator](https://github.com/factset/analyticsapi-engines-sdk-generator) for instructions on how to modify these files.
* Projects [examples](examples) and [tests](tests) are open to enhancements and bug fixes. Please create a pull requests with the proposed changes.
