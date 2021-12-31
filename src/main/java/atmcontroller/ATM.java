package atmcontroller;

public interface ATM {
  int[] getCardNumber();

  int getPinEntered();

  int getAmountDeposited();

  int getCashBinAmount();
}
