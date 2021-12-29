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
    controller.setATM(atmMock);
    when(atmMock.getPinEntered()).thenReturn(1004);
    controller.setATM(atmMock);

    bankMock = mock(Bank.class);
  }

  @Test
  public void canaryTest() {
    assertTrue(true);
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

  @Test
  public void setBankBalance() {
    when(bankMock.getBalance()).thenReturn(0);
    controller.setBank(bankMock);

    controller.setBalance();

    assertEquals(0, controller.showBalance());
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
  public void depositTenDollars() {
    controller.deposit(10);

    assertEquals(controller.showBalance(), 10);
  }

  @Test
  public void depositTwentyDollars() {
    controller.deposit(20);

    assertEquals(controller.showBalance(), 20);
  }

  @Test
  public void withdrawTenDollarsGetZeroBalance() {
    controller.deposit(10);

    controller.withdraw(10);

    assertEquals(controller.showBalance(), 0);
  }

  @Test
  public void withdrawTenDollarsGetTenBalance() {
    controller.deposit(20);

    controller.withdraw(10);

    assertEquals(controller.showBalance(), 10);
  }

  @Test
  public void withdraw100ForZeroBalance() {
    controller.withdraw(100);

    assertEquals(controller.showBalance(), 0);
  }

}
