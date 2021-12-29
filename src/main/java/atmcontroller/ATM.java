package atmcontroller;

public interface ATM {
  int[] getCardNumber();

  int getPinEntered();

  int getCashReaderAmount();

  int getCashBinAmount();
}
