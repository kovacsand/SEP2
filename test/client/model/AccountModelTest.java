package client.model;

import client.core.ClientFactory;
import client.core.ModelFactory;
import org.junit.Before;
import org.junit.Test;
import shared.networking.ServerTest;

import static org.junit.Assert.*;

public class AccountModelTest
{
  private AccountModel accountModel;

  //Because we only want the server to run once, we call a method on ServerTest
  @Before public void setUp() throws Exception
  {
    ServerTest.setUpServer();
    ClientFactory clientFactory = new ClientFactory();
    ModelFactory mf = new ModelFactory(clientFactory);
    accountModel = mf.getAccountModel();
  }

  @Test public void loginWithCorrectCredentials()
  {
    accountModel.addAccount("S2G2", "1234", "Manager");
    assertNotNull(accountModel.login("S2G2", "1234"));
  }

  @Test public void loginWithIncorrectUsername()
  {
    accountModel.addAccount("S2G2", "1234", "Manager");
    assertNull(accountModel.login("NotS2G2", "1234"));
  }

  @Test public void loginWithIncorrectPassword()
  {
    accountModel.addAccount("S2G2", "1234", "Manager");
    assertNull(accountModel.login("S2G2", "9876"));
  }

  @Test public void loginWithIncorrectCredentials()
  {
    accountModel.addAccount("S2G2", "1234", "Manager");
    assertNull(accountModel.login("NotS2G2", "9876"));
  }

  @Test public void loginWithCorrectCredentialsTwice()
  {
    accountModel.addAccount("S2G2", "1234", "Manager");
    boolean firstLoginSuccessful = accountModel.login("S2G2", "1234") != null;

    accountModel.addAccount("Salesperson", "1111", "Salesperson");
    boolean secondLoginSuccessful = accountModel.login("Salesperson", "1111") != null;

    assertTrue(firstLoginSuccessful && secondLoginSuccessful);
  }

  @Test public void loginWithIncorrectCredentialsTwice()
  {
    accountModel.addAccount("S2G2", "1234", "Manager");
    boolean firstLoginSuccessful = accountModel.login("NotS2G2", "9876") != null;

    accountModel.addAccount("Salesperson", "1111", "Salesperson");
    boolean secondLoginSuccessful = accountModel.login("NotSalesperson", "9999") != null;

    assertTrue(!firstLoginSuccessful && !secondLoginSuccessful);
  }
}