package atmcontroller;

public interface Bank {
  boolean isPinCorrect(int[] atmCard, int pin);

  boolean isAccountCorrect(int[] atmCard);

  int getBalance();

  void sendBalance(int balance);
}
