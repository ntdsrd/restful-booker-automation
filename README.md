# Restful-booker - API automation test using Java (Maven), TestNG and generate Allure report
## Project structure
.
+-- reports
|    +-- allure-report
|    +-- allure-results
|    +-- allure-report.png
+-- src/test
|    +-- java
|    |    +-- actions
|    |    |    +-- commons
|    |    |    |    +-- GlobalConstants.java
|    |    |    +-- json
|    |    |    |    +-- CreateBookingJson.java
|    |    |    |    +-- GetBookingJson.java
|    |    |    |    +-- UpdateBookingJson.java
|    |    |    +-- xml
|    |    |    |    +-- CreateBookingXml.java
|    |    |    |    +-- GetBookingXml.java
|    |    |    |    +-- UpdateBookingXml.java
|    |    +-- properties
|    |    |    +-- Prod.properties
|    |    |    +-- TestData.properties
|    |    +-- schemas
|    |    |    +-- GetAndPutSchema.xsd
|    |    |    +-- PostSchema.xsd
|    |    |    +-- Schema.json
|    |    +-- testcases
|    |    |    +-- TestRequestJson.java
|    |    |    +-- TestRequestXml.java
|    +-- resources
|    |    +-- RunProd.xml
+-- pom.xml
