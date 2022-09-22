# Beach
## Version 1.0.0

A spring boot project based on RestAssured and Wiremock for https://reqres.in/

## Dependencies
* Spring Boot (2.7.3)
* Java (1.8)
* TestNG (7.6.1)
* RestAssured (5.2.0)
* Lombok (1.18.24)
* Jackson Data Bind (2.13.4)
* Wiremock (2.34.0)

## Package Information
* common >> responsible for API request builder such as headers, body etc
* config >> responsible for bean creation for response builder and object mapper
* constants >> holds project specific constants
* init >> responsible for initializing test methods
* mocks >> responsible for setting up mock server such as wiremock
* provides >> responsible for providing API response
* tasks >> responsible for real API health check task
* validations >> responsible for API response validation

## Folder information
* mappings : this folder contains mapping files consists of request and response matching for wiremock

## How to run application
#### With maven command line : mvn test
#### Manual trigger : need to go to RunnerTest (inside init package) and run the class
