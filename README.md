# ATM Controller

## Description
ATM Controller is a simple controller that will allow the user to access their bank account with an ATM machine. The controller is a mediator between the ATM machine and the bank, processing and verifying input, manipulating data, and updating the database. It verifies the user using the card and pin entered into the ATM machine. Once verified, the user can select actions such as: 1) show balance, 2) make a deposit, or 3) make a withdrawal. The controller can display the balance and update the bank account's balance according to deposit or withdrawal actions. 

This program is designed with test-driven development and continuous integration in mind. The task-list file demonstrates what steps were taken in the development process. I used JUnit and Mockito for testing, JaCoCo for code coverage and test results, and Gradle for build automation. The program's functions are limited to a controller's, leaving out implementing bank integration, ATM hardware, and user interface.

## Cloning
You can clone this project using the following command.
```
$ git clone https://github.com/dotcommadotcom/atmcontroller.git && cd atmcontroller
```

## Building and Running Tests
You can execute the Gradle build using a Gradle Wrapper. By default, the build is configured to clean outputs, run all tests, and provide code coverage and test summary reports using JaCoCo. To get more information on what tasks are available, you can run the second command.
```
$ ./gradlew
$ ./gradlew tasks
```
Note: A Java version between 8 and 17 is required to execute Gradle. Java 18 and later versions are not yet supported.

## Code Coverage
JaCoCo will generate a code coverage report after running the build. The report can be opened using the following command.
```
$ open build/reports/jacoco/test/html/index.html
```

## Test Summary
JaCoCo will generate a test summary after running the build. The summary can be opened using the following command.
```
$ open build/reports/tests/test/index.html
```
