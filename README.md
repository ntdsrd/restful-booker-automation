# Restful-booker - API automation test using Java (Maven), TestNG, and generate Allure report
This project used Java programming language, built-in Maven structure, following POM (Page Object Model). It used TestNG for automation testing and finally generated an Allure report.

> Open pom.xml for more details about plugins and dependencies
## Allure report preview
![allure report](https://github.com/ntdsrd/restful-booker-automation/blob/master/reports/allure-report.png)
## How to run the project
Clone project
```
https://github.com/ntdsrd/restful-booker-automation.git
```
Open project on IDE (Intellij IDEA recommended)

Test on `/src/test/java/testcases/TestRequest*.java`

or run an XML file on `/src/test/resources/RunProd.xml`

## Run project with Allure report
Install Maven (mvn) depends on OS (Windows, Mac, Linux)

Run
```
mvn clean test
```
> allure-results will be generated on the reports folder

Install allure-commandline (Install by npm)
```
npm install -g allure-commandline
``
Go to the folder which had allure-results (reports folder)

Generate allure report
```
allure generate allure-results
```
Open allure report
```
allure open allure-report
```
