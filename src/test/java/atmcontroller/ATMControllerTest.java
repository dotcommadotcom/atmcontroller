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
    when(atmMock.getCashReaderAmount()).thenReturn(10);

    controller.deposit(10);

    assertEquals(10, controller.showBalance());
  }

  @Test
  public void depositTwentyDollarsVerifyIncorrectAmountDeposited() {
    when(atmMock.getCashReaderAmount()).thenReturn(5);

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
    controller.setBalance(10000);

    Exception exception = assertThrows(RuntimeException.class, () -> controller.withdraw(10000));

    assertEquals("Withdrawal amount exceeds cash amount in the cash bin.", exception.getMessage());
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

    assertTrue(controller.isAccountVerified());
  }

  @Test
  public void verifyBankAccountDoesNotExistWithAtmCard() {
    when(bankMock.isAccountCorrect(atmMock.getCardNumber())).thenReturn(false);

    assertFalse(controller.isAccountVerified());
  }

  @Test
  public void verifyPinEnteredMatchesAccount() {
    when(bankMock.isPinCorrect(controller.getAtmCard(), controller.getAtmPinEntered())).thenReturn(true);

    assertTrue(controller.isPinVerified());
  }

  @Test
  public void verifyPinEnteredDoesNotMatchAccount() {
    when(bankMock.isPinCorrect(controller.getAtmCard(), controller.getAtmPinEntered())).thenReturn(false);

    assertFalse(controller.isPinVerified());
  }

//  @Test
//  public void updateBalanceWithBankBalance() {
//    when(bankMock.getBalance()).thenReturn(550);
//
//    controller.setBalance(bankMock.getBalance());
//
//    assertEquals(550, controller.showBalance());
//  }
//
//  @Test
//  public void updateBalanceAndGetFailedBankConnection() {
//    when(bankMock.getBalance()).thenThrow(new RuntimeException("Network failure"));
//
//    Exception exception = assertThrows(RuntimeException.class, () -> controller.setBalance(bankMock.getBalance()));
//
//    assertEquals("Network failure", exception.getMessage());
//  }

//

//  when(bankMock.isPinCorrect(controller.getAtmCard(), controller.getAtmPinEntered())).thenReturn(false);
//
//  assertFalse(controller.isPinVerified());
//  @Test
//  public void setBankBalance() {
//    when(bankMock.getBalance()).thenReturn(250);
//    controller.setBank(bankMock);
//
//    assertEquals(250, controller.showBalance());
//  }

}
