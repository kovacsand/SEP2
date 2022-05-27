package client.networking;

import client.core.ClientFactory;
import org.junit.Before;
import org.junit.Test;
import shared.networking.ServerTest;
import shared.transferobjects.Manager;
import shared.transferobjects.Salesperson;

import static org.junit.Assert.*;

public class ClientTest
{
  private Client client;

  //Because we only want the server to run once, we call a method on ServerTest
  @Before public void setUp() throws Exception
  {
    ServerTest.setUpServer();

    ClientFactory clientFactory = new ClientFactory();
    client = clientFactory.getClient();
    client.startClient();
  }

  @Test public void loginWithCorrectCredentials()
  {
    client.addAccount(new Manager ("S2G2"), "1234");
    assertNotNull(client.login("S2G2", "1234"));
  }

  @Test public void loginWithIncorrectUsername()
  {
    client.addAccount(new Manager ("S2G2"), "1234");
    assertNull(client.login("NotS2G2", "1234"));
  }

  @Test public void loginWithIncorrectPassword()
  {
    client.addAccount(new Manager ("S2G2"), "1234");
    assertNull(client.login("S2G2", "9876"));
  }

  @Test public void loginWithIncorrectCredentials()
  {
    client.addAccount(new Manager ("S2G2"), "1234");
    assertNull(client.login("NotS2G2", "9876"));
  }

  @Test public void loginWithCorrectCredentialsTwice()
  {
    client.addAccount(new Manager ("S2G2"), "1234");
    boolean firstLoginSuccessful = client.login("S2G2", "1234") != null;

    client.addAccount(new Salesperson("Salesperson"), "1111");
    boolean secondLoginSuccessful = client.login("Salesperson", "1111") != null;

    assertTrue(firstLoginSuccessful && secondLoginSuccessful);
  }

  @Test public void loginWithIncorrectCredentialsTwice()
  {
    client.addAccount(new Manager ("S2G2"), "1234");
    boolean firstLoginSuccessful = client.login("NotS2G2", "9876") != null;

    client.addAccount(new Salesperson("Salesperson"), "1111");
    boolean secondLoginSuccessful = client.login("NotSalesperson", "9999") != null;

    assertTrue(!firstLoginSuccessful && !secondLoginSuccessful);
  }
}