package shared.networking;

import org.junit.Before;
import org.junit.Test;
import server.networking.ServerImplementation;
import shared.transferobjects.Manager;
import shared.transferobjects.Salesperson;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

//RemoteException is thrown by all the methods, as it is not part of this unit test to handle it
public class ServerTest
{
  private static boolean setUpDone = false;

  private static Server server;

  public static void setUpServer() throws Exception
  {
    if (setUpDone)
      return;
    server = new ServerImplementation();
    ((ServerImplementation)server).startServer();
    setUpDone = true;
  }

  //Because we only want the server to run once, we call a method on ServerTest
  @Before public void setUp() throws Exception
  {
    ServerTest.setUpServer();
  }

  @Test public void loginWithCorrectCredentials() throws RemoteException
  {
    server.addAccount(new Manager("S2G2"), "1234");
    assertNotNull(server.login("S2G2", "1234"));
  }

  @Test public void loginWithIncorrectUsername() throws RemoteException
  {
    server.addAccount(new Manager ("S2G2"), "1234");
    assertNull(server.login("NotS2G2", "1234"));
  }

  @Test public void loginWithIncorrectPassword() throws RemoteException
  {
    server.addAccount(new Manager ("S2G2"), "1234");
    assertNull(server.login("S2G2", "9876"));
  }

  @Test public void loginWithIncorrectCredentials() throws RemoteException
  {
    server.addAccount(new Manager ("S2G2"), "1234");
    assertNull(server.login("NotS2G2", "9876"));
  }

  @Test public void loginWithCorrectCredentialsTwice() throws RemoteException
  {
    server.addAccount(new Manager ("S2G2"), "1234");
    boolean firstLoginSuccessful = server.login("S2G2", "1234") != null;

    server.addAccount(new Salesperson("Salesperson"), "1111");
    boolean secondLoginSuccessful = server.login("Salesperson", "1111") != null;

    assertTrue(firstLoginSuccessful && secondLoginSuccessful);
  }

  @Test public void loginWithIncorrectCredentialsTwice() throws RemoteException
  {
    server.addAccount(new Manager ("S2G2"), "1234");
    boolean firstLoginSuccessful = server.login("NotS2G2", "9876") != null;

    server.addAccount(new Salesperson("Salesperson"), "1111");
    boolean secondLoginSuccessful = server.login("NotSalesperson", "9999") != null;

    assertTrue(!firstLoginSuccessful && !secondLoginSuccessful);
  }
}