package atmcontroller;

public class ATMController {
  private int balance;
  private int pin;

  public ATMController() {
    balance = 0;
    pin = 1004;
  }

  public ATMController(int _balance) {
    balance = _balance;
  }

  public int showBalance() {
    return balance;
  }

  public void deposit(int depositAmount) {
    balance += depositAmount;
  }

  public void withdraw(int withdrawAmount) {
    if (withdrawAmount <= balance) {
      balance -= withdrawAmount;
    }
  }

  public boolean isPinVerified(int pinEntered) {
    return pin == pinEntered;
  }
}
