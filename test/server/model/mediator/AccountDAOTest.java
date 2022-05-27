package server.model.mediator;

import org.junit.Test;
import shared.transferobjects.Manager;
import shared.transferobjects.Salesperson;

import java.sql.SQLException;

import static org.junit.Assert.*;

//SQLException is thrown by all the methods, as it is not part of this unit test to handle it
public class AccountDAOTest
{
  @Test public void loginWithCorrectCredentials() throws SQLException
  {
    AccountDAOImplementation.getInstance().addAccount(new Manager("S2G2"), "1234");
    assertNotNull(AccountDAOImplementation.getInstance().login("S2G2", "1234"));
  }

  @Test public void loginWithIncorrectUsername() throws SQLException
  {
    AccountDAOImplementation.getInstance().addAccount(new Manager ("S2G2"), "1234");
    assertNull(AccountDAOImplementation.getInstance().login("NotS2G2", "1234"));
  }

  @Test public void loginWithIncorrectPassword() throws SQLException
  {
    AccountDAOImplementation.getInstance().addAccount(new Manager ("S2G2"), "1234");
    assertNull(AccountDAOImplementation.getInstance().login("S2G2", "9876"));
  }

  @Test public void loginWithIncorrectCredentials() throws SQLException
  {
    AccountDAOImplementation.getInstance().addAccount(new Manager ("S2G2"), "1234");
    assertNull(AccountDAOImplementation.getInstance().login("NotS2G2", "9876"));
  }

  @Test public void loginWithCorrectCredentialsTwice() throws SQLException
  {
    AccountDAOImplementation.getInstance().addAccount(new Manager ("S2G2"), "1234");
    boolean firstLoginSuccessful = AccountDAOImplementation.getInstance().login("S2G2", "1234") != null;

    AccountDAOImplementation.getInstance().addAccount(new Salesperson("Salesperson"), "1111");
    boolean secondLoginSuccessful = AccountDAOImplementation.getInstance().login("Salesperson", "1111") != null;

    assertTrue(firstLoginSuccessful && secondLoginSuccessful);
  }

  @Test public void loginWithIncorrectCredentialsTwice() throws SQLException
  {
    AccountDAOImplementation.getInstance().addAccount(new Manager ("S2G2"), "1234");
    boolean firstLoginSuccessful = AccountDAOImplementation.getInstance().login("NotS2G2", "9876") != null;

    AccountDAOImplementation.getInstance().addAccount(new Salesperson("Salesperson"), "1111");
    boolean secondLoginSuccessful = AccountDAOImplementation.getInstance().login("NotSalesperson", "9999") != null;

    assertTrue(!firstLoginSuccessful && !secondLoginSuccessful);
  }
}