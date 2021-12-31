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

  public void setBalance(int _balance) {
    balance = _balance;
  }

  public int showBalance() {
    return balance;
  }

  public void deposit(int depositAmount) {
    if (atm.getAmountDeposited() != depositAmount) {
      throw new RuntimeException("Deposit amount does not match cash deposit.");
    }

    balance += depositAmount;
  }

  public void withdraw(int withdrawAmount) {
    if (withdrawAmount > balance) {
      throw new RuntimeException("Withdrawal amount exceeds bank balance.");
    }

    if (withdrawAmount > atm.getCashBinAmount()) {
      throw new RuntimeException("Withdrawal amount exceeds cash amount in the cash bin.");
    }

    balance -= withdrawAmount;
  }

  public int[] getAtmCard() {
    return atm.getCardNumber();
  }

  public int getAtmPinEntered() {
    return atm.getPinEntered();
  }

  public boolean isAccountVerified(int[] card) {
    return bank.isAccountCorrect(card);
  }

  public boolean isPinVerified(int[] card, int pin) {
    return bank.isPinCorrect(card, pin);
  }

  public int getBankAccountBalance() {
    return bank.getBalance();
  }
}
