# ATM Controller
ATM Controller is a controller that will allow the user to access their bank account with an ATM machine. It receives a card and verifies the user using a secure PIN number. Once verified, the user can select 1) Show balance, 2) Make a deposit, or 3) Make a withdrawal. The controller will display the balance, and update the bank account's balance according to deposit or withdrawal actions. 

This program is designed using test-driven development and continuous integration in mind. It uses JUnit and Mockito for testing and Gradle for build automation. Its functions are limited to a controller's, leaving out implementing bank integration, ATM hardware, and user interface.
