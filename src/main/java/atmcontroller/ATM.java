package atmcontroller;

public interface ATM {
  int[] getCardNumber();

  int getPinEntered();

  int getCashCounterAmount();

  int getCashBinAmount();
}
