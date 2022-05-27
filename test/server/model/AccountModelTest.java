package server.model;

import org.junit.Before;
import org.junit.Test;
import shared.transferobjects.Manager;
import shared.transferobjects.Salesperson;

import static org.junit.Assert.*;

public class AccountModelTest
{
  private AccountModel accountModel;

  @Before public void setUp()
  {
    accountModel = new AMImplementation();
  }

  @Test public void loginWithCorrectCredentials()
  {
    accountModel.addAccount(new Manager("S2G2"), "1234");
    assertNotNull(accountModel.login("S2G2", "1234"));
  }

  @Test public void loginWithIncorrectUsername()
  {
    accountModel.addAccount(new Manager ("S2G2"), "1234");
    assertNull(accountModel.login("NotS2G2", "1234"));
  }

  @Test public void loginWithIncorrectPassword()
  {
    accountModel.addAccount(new Manager ("S2G2"), "1234");
    assertNull(accountModel.login("S2G2", "9876"));
  }

  @Test public void loginWithIncorrectCredentials()
  {
    accountModel.addAccount(new Manager ("S2G2"), "1234");
    assertNull(accountModel.login("NotS2G2", "9876"));
  }

  @Test public void loginWithCorrectCredentialsTwice()
  {
    accountModel.addAccount(new Manager ("S2G2"), "1234");
    boolean firstLoginSuccessful = accountModel.login("S2G2", "1234") != null;

    accountModel.addAccount(new Salesperson("Salesperson"), "1111");
    boolean secondLoginSuccessful = accountModel.login("Salesperson", "1111") != null;

    assertTrue(firstLoginSuccessful && secondLoginSuccessful);
  }

  @Test public void loginWithIncorrectCredentialsTwice()
  {
    accountModel.addAccount(new Manager ("S2G2"), "1234");
    boolean firstLoginSuccessful = accountModel.login("NotS2G2", "9876") != null;

    accountModel.addAccount(new Salesperson("Salesperson"), "1111");
    boolean secondLoginSuccessful = accountModel.login("NotSalesperson", "9999") != null;

    assertTrue(!firstLoginSuccessful && !secondLoginSuccessful);
  }
}