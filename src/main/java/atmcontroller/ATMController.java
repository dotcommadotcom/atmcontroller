package atmcontroller;

public class ATMController {
  private ATM atm;
  private Bank bank;
  private int balance;

  public void setATM(ATM _atm) {
    atm = _atm;
  }

  public void setBank(Bank _bank) {
    bank = _bank;
  }

  public int[] getAtmCard() {
    return atm.getCardNumber();
  }

  public int getAtmPinEntered() {
    return atm.getPinEntered();
  }

  public boolean isPinVerified() {
    return bank.isPinCorrect(getAtmCard(), getAtmPinEntered());
  }

  public void setBalance() {
    if (isPinVerified()) {
      balance = bank.getBalance();
    }
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

  public void setBalance(int _balance) {
    balance = _balance;
  }
}
