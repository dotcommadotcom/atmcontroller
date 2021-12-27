package atmcontroller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ATMControllerTest {
  private ATMController atm;

  @BeforeEach
  public void setUp() {
    atm = new ATMController();
  }

  @Test
  public void canaryTest() {
    assertTrue(true);
  }

  @Test
  public void showBalanceIsZero() {
    assertEquals(atm.showBalance(), 0);
  }

  @Test
  public void showBalanceIsTen() {
    ATMController atm = new ATMController(10);

    assertEquals(atm.showBalance(), 10);
  }

  @Test
  public void depositTenDollars() {
    atm.deposit(10);

    assertEquals(atm.showBalance(), 10);
  }

  @Test
  public void depositTwentyDollars() {
    atm.deposit(20);

    assertEquals(atm.showBalance(), 20);
  }

  @Test
  public void withdrawTenDollarsGetZeroBalance() {
    atm.deposit(10);

    atm.withdraw(10);

    assertEquals(atm.showBalance(), 0);
  }

  @Test
  public void withdrawTenDollarsGetTenBalance() {
    atm.deposit(20);

    atm.withdraw(10);

    assertEquals(atm.showBalance(), 10);
  }

  @Test
  public void withdraw100ForZeroBalance() {
    atm.withdraw(100);

    assertEquals(atm.showBalance(), 0);
  }

  @Test
  public void verifyPinNumberIsTrue() {
    assertTrue(atm.isPinVerified(1004));
  }

  @Test
  public void verifyPinNumber() {
    assertFalse(atm.isPinVerified(1005));
  }
}
