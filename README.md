# ATM Controller

## Description
ATM Controller is a controller that will allow the user to access their bank account with an ATM machine. It receives a card and verifies the user using a secure PIN number. Once verified, the user can select actions such as: 1) show balance, 2) make a deposit, or 3) make a withdrawal. The controller will display the balance, and update the bank account's balance according to deposit or withdrawal actions. 

This program is designed with test-driven development and continuous integration in mind. It uses JUnit and Mockito for testing, JaCoCo for code coverage and test results, and Gradle for build automation. Its functions are limited to a controller's, leaving out implementing bank integration, ATM hardware, and user interface.


## Cloning
You can clone this project using the following command.

```
$ git clone https://github.com/dotcommadotcom/atmcontroller.git && cd atmcontroller
```

## Building and Running Tests


A Java version between 8 and 17 is required to execute Gradle. Java 18 and later versions are not yet supported.

## Code Coverage

Building will create a code coverage report with JaCoCo. The report can be found using the following command.

## and Running Tests
Building will create a test result report with JaCoCo. The report can be found using the following command.

