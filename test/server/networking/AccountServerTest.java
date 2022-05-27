package server.networking;

import org.junit.Before;
import org.junit.Test;
import server.model.AMImplementation;
import shared.transferobjects.Manager;
import shared.transferobjects.Salesperson;

import static org.junit.Assert.*;

public class AccountServerTest
{
  private AccountServer accountServer;

  @Before public void setUp()
  {
    accountServer = new AccountServerImplementation(new AMImplementation());
  }

  @Test public void loginWithCorrectCredentials()
  {
    accountServer.addAccount(new Manager("S2G2"), "1234");
    assertNotNull(accountServer.login("S2G2", "1234"));
  }

  @Test public void loginWithIncorrectUsername()
  {
    accountServer.addAccount(new Manager ("S2G2"), "1234");
    assertNull(accountServer.login("NotS2G2", "1234"));
  }

  @Test public void loginWithIncorrectPassword()
  {
    accountServer.addAccount(new Manager ("S2G2"), "1234");
    assertNull(accountServer.login("S2G2", "9876"));
  }

  @Test public void loginWithIncorrectCredentials()
  {
    accountServer.addAccount(new Manager ("S2G2"), "1234");
    assertNull(accountServer.login("NotS2G2", "9876"));
  }

  @Test public void loginWithCorrectCredentialsTwice()
  {
    accountServer.addAccount(new Manager ("S2G2"), "1234");
    boolean firstLoginSuccessful = accountServer.login("S2G2", "1234") != null;

    accountServer.addAccount(new Salesperson("Salesperson"), "1111");
    boolean secondLoginSuccessful = accountServer.login("Salesperson", "1111") != null;

    assertTrue(firstLoginSuccessful && secondLoginSuccessful);
  }

  @Test public void loginWithIncorrectCredentialsTwice()
  {
    accountServer.addAccount(new Manager ("S2G2"), "1234");
    boolean firstLoginSuccessful = accountServer.login("NotS2G2", "9876") != null;

    accountServer.addAccount(new Salesperson("Salesperson"), "1111");
    boolean secondLoginSuccessful = accountServer.login("NotSalesperson", "9999") != null;

    assertTrue(!firstLoginSuccessful && !secondLoginSuccessful);
  }
}