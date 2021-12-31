package atmcontroller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ATMControllerTest {
  private ATMController controller;
  private ATM atmMock;
  private Bank bankMock;

  @BeforeEach
  public void setUp() {
    controller = new ATMController();

    atmMock = mock(ATM.class);
    when(atmMock.getCardNumber()).thenReturn(new int[]{1000, 1111, 2222, 3333});
    when(atmMock.getPinEntered()).thenReturn(1004);
    when(atmMock.getCashBinAmount()).thenReturn(500);
    controller.setATM(atmMock);

    bankMock = mock(Bank.class);
    controller.setBank(bankMock);
  }

  @Test
  public void canaryTest() {
    assertTrue(true);
  }

  @Test
  public void showBalanceIsZero() {
    assertEquals(0, controller.showBalance());
  }

  @Test
  public void showBalanceIsTen() {
    controller.setBalance(10);

    assertEquals(10, controller.showBalance());
  }

  @Test
  public void depositTenDollarsVerifyCorrectAmountDeposited() {
    when(atmMock.getAmountDeposited()).thenReturn(10);

    controller.deposit(10);

    assertEquals(10, controller.showBalance());
  }

  @Test
  public void depositTwentyDollarsVerifyIncorrectAmountDeposited() {
    when(atmMock.getAmountDeposited()).thenReturn(5);

    Exception exception = assertThrows(RuntimeException.class, () -> controller.deposit(20));

    assertAll(
            () -> assertEquals("Deposit amount does not match cash deposit.", exception.getMessage()),
            () -> assertEquals(0, controller.showBalance())
    );
  }

  @Test
  public void withdrawTenDollarsGetZeroBalance() {
    controller.setBalance(10);

    controller.withdraw(10);

    assertEquals(0, controller.showBalance());
  }

  @Test
  public void withdrawFifteenDollarsGetFiveBalance() {
    controller.setBalance(20);

    controller.withdraw(15);

    assertEquals(5, controller.showBalance());
  }

  @Test
  public void withdrawMoreMoneyThanBalanceThrowsException() {
    Exception exception = assertThrows(RuntimeException.class, () -> controller.withdraw(100));

    assertAll(
            () -> assertEquals("Withdrawal amount exceeds account balance.", exception.getMessage()),
            () -> assertEquals(0, controller.showBalance())
    );
  }

  @Test
  public void withdrawMoreCashThanCashBinThrowsException() {
    controller.setBalance(10000);

    Exception exception = assertThrows(RuntimeException.class, () -> controller.withdraw(10000));

    assertEquals("Withdrawal amount exceeds amount in cash bin.", exception.getMessage());
  }

  @Test
  public void getCardNumberFromATM() {
    assertArrayEquals(new int[]{1000, 1111, 2222, 3333}, controller.getAtmCard());
  }

  @Test
  public void getPinEnteredFromATM() {
    assertEquals(1004, controller.getAtmPinEntered());
  }

  @Test
  public void verifyBankAccountExistsWithAtmCard() {
    when(bankMock.isAccountCorrect(atmMock.getCardNumber())).thenReturn(true);

    assertTrue(controller.isAccountVerified(atmMock.getCardNumber()));
  }

  @Test
  public void verifyBankAccountDoesNotExistWithAtmCard() {
    when(bankMock.isAccountCorrect(atmMock.getCardNumber())).thenReturn(false);

    assertFalse(controller.isAccountVerified(atmMock.getCardNumber()));
  }

  @Test
  public void verifyPinEnteredMatchesAccount() {
    when(bankMock.isPinCorrect(atmMock.getCardNumber(), atmMock.getPinEntered())).thenReturn(true);

    assertTrue(controller.isPinVerified(atmMock.getCardNumber(), atmMock.getPinEntered()));
  }

  @Test
  public void verifyPinEnteredDoesNotMatchAccount() {
    when(bankMock.isPinCorrect(atmMock.getCardNumber(), atmMock.getPinEntered())).thenReturn(false);

    assertFalse(controller.isPinVerified(atmMock.getCardNumber(), atmMock.getPinEntered()));
  }

  @Test
  public void getBankAccountBalanceFromBank() {
    when(bankMock.getBalance()).thenReturn(550);

    assertEquals(550, controller.getBankAccountBalance());
  }

  @Test
  public void getBankAccountBalanceWithFailedBankConnection() {
    when(bankMock.getBalance()).thenThrow(new RuntimeException("Connection failed. Please notify bank."));

    Exception exception = assertThrows(RuntimeException.class, () -> controller.getBankAccountBalance());

    assertEquals("Connection failed. Please notify bank.", exception.getMessage());
  }

  @Test
  public void updateBalanceWithBankAccountBalance() {
    when(bankMock.getBalance()).thenReturn(550);

    controller.setBalance(controller.getBankAccountBalance());

    assertEquals(550, controller.showBalance());
  }

  @Test
  public void sendUpdatedBalanceToBankAfterDeposit() {
    when(bankMock.getBalance()).thenReturn(550, 600);
    when(atmMock.getAmountDeposited()).thenReturn(50);
    controller.setBalance(controller.getBankAccountBalance());

    controller.deposit(50);
    controller.sendBalance();

    assertEquals(600, controller.getBankAccountBalance());
  }

  @Test
  public void sendUpdatedBalanceToBankAfterWithdrawal() {
    when(bankMock.getBalance()).thenReturn(550, 525);
    controller.setBalance(controller.getBankAccountBalance());

    controller.withdraw(25);
    controller.sendBalance();

    assertEquals(525, controller.getBankAccountBalance());
  }
}
