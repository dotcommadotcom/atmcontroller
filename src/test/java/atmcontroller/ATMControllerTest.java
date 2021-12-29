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
    when(atmMock.getCashReaderAmount()).thenReturn(10);
    when(atmMock.getCashBinAmount()).thenReturn(500);
    controller.setATM(atmMock);

    bankMock = mock(Bank.class);
  }

  @Test
  public void canaryTest() {
    assertTrue(true);
  }

  @Test
  public void showBalanceIsZero() {
    controller.setBalance(0);

    assertEquals(0, controller.showBalance());
  }

  @Test
  public void showBalanceIsTen() {
    controller.setBalance(10);

    assertEquals(10, controller.showBalance());
  }

  @Test
  public void depositTenDollarsVerifyCorrectAmountDeposited() {
    controller.deposit(10);

    assertEquals(10, controller.showBalance());
  }

  @Test
  public void depositTwentyDollarsVerifyIncorrectAmountDeposited() {
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
  public void withdrawMoreMoneyThanBankAmountThrowsException() {
    Exception exception = assertThrows(RuntimeException.class, () -> controller.withdraw(100));

    assertAll(
            () -> assertEquals("Withdrawal amount exceeds bank balance.", exception.getMessage()),
            () -> assertEquals(0, controller.showBalance())
    );
  }

  @Test
  public void withdrawMoreCashThanCashBinThrowsException() {
    Exception exception = assertThrows(RuntimeException.class, () -> controller.withdraw(10000));

    assertEquals("Withdrawal amount exceeds cash amount in the cash bin", exception.getMessage());
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
  public void verifyPinEnteredMatchesAccount() {
    when(bankMock.isPinCorrect(controller.getAtmCard(), controller.getAtmPinEntered())).thenReturn(true);
    controller.setBank(bankMock);

    assertTrue(controller.isPinVerified());
  }

  @Test
  public void verifyPinEnteredDoesNotMatchAccount() {
    when(bankMock.isPinCorrect(controller.getAtmCard(), controller.getAtmPinEntered())).thenReturn(false);
    controller.setBank(bankMock);

    assertFalse(controller.isPinVerified());
  }


//  @Test
//  public void setBankBalance() {
//    when(bankMock.getBalance()).thenReturn(250);
//    controller.setBank(bankMock);
//
//    assertEquals(250, controller.showBalance());
//  }

}
