# analyticsapi-engines-java-sdk

## Overview
API client library to leverage FactSet's PA Engine, SPAR Engine and Vault API in Java.

**`Engines`** - Java library for Engines API. It is developed using [open-api-generator](https://github.com/OpenAPITools/openapi-generator).

**`Utilities`** - Contains the Engines's OpenAPI schema(openapi-schema.json), configuration file(openapi-generator-config.json), custom OpenAPI templates, examples and End-to-end tests of library. 

#### Recommended framework
* Java 1.7, Java 1.8

#### Current versions
* API_VERSION - v2
* ARTIFACT_VERSION - 4.0.0

## To install the API client library
Add this dependency to project's POM.
```
<dependency>
  <groupId>com.factset.analyticsapi</groupId>
  <artifactId>engines-sdk</artifactId>
  <version>ARTIFACT_VERSION</version>
</dependency>
```

## Generating the Java library
To customize OpenAPI generator options and generate the library. Please go through [Open API](https://swagger.io/docs/specification/about/) and [open-api-generator](https://github.com/OpenAPITools/openapi-generator) for more details.

### Pre-requisites
* Install [Java SDK8 64 bit version](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
* Clone this `analyticsapi-engines-java-sdk` repository.
* Move into the `analyticsapi-engines-java-sdk/Utilities/codegen` directory and run the `download-codegen.bat` file by double clicking it (for downloading the openapi-generator-cli.jar).

### To update and build the library
* Move to the `analyticsapi-engines-java-sdk` location.
* Increment the artifact version in `Utilities/codegen/openapi-generator-config.json`.
* Delete all the files in the Engines directory excluding `.openapi-generator-ignore` file.
* Replace ARTIFACT_VERSION in the below command with its latest value and run it.
```
javac -classpath Utilities/codegen/*; Utilities/codegen/CustomJavaClientCodegen.java
java -DapiTests=false -DmodelTests=false -classpath Utilities/codegen/;Utilities/codegen/*; org.openapitools.codegen.OpenAPIGenerator generate --generator-name CustomJavaClientCodegen --input-spec Utilities/codegen/openapi-schema.json --output Engines --config Utilities/codegen/openapi-generator-config.json --http-user-agent engines-api/ARTIFACT_VERSION/java -t Utilities/codegen/templates --skip-validate-spec
```
* Move to the `analyticsapi-engines-java-sdk/Engines` location, and run `mvn clean package` which generates the library(engines-sdk-\*.\*.\*.jar) and its documentation.

### Run End-to-end tests

#### Running the Test Cases
* Move to the `analyticsapi-engines-java-sdk/Engines` location and build the library.
* Now, move to the `Utilities/java-sdk-tests` location and run the below commands.
```
mvn clean package
```
* Set the below environment variables.
```
ANALYTICS_API_USERNAME_SERIAL = "username-serial" 
ANALYTICS_API_PASSWORD = "apikey" // Generate using developer portal
```
* Now run the test cases using the command below.
```
mvn -q test -Dtest=ApiTestsRunner
```