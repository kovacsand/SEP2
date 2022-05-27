package client.view.login;

import client.core.ClientFactory;
import client.core.ModelFactory;
import org.junit.Before;
import org.junit.Test;
import shared.networking.ServerTest;

import static org.junit.Assert.*;

//Due to the LoginViewModel changing JavaFX objects,
// the Platform.RunLater() part has to be commented out
// when running this test class.
public class LoginViewModelTest
{
  private LoginViewModel loginViewModel;
  private ModelFactory mf;

  //Because we only want the server to run once, we call a method on ServerTest
  @Before public void setUp() throws Exception
  {
    ServerTest.setUpServer();

    ClientFactory clientFactory = new ClientFactory();
    mf = new ModelFactory(clientFactory);
    loginViewModel = new LoginViewModel(mf.getAccountModel());
  }

  @Test public void loginWithCorrectCredentials()
  {
    mf.getAccountModel().addAccount("S2G2", "1234", "Manager");
    loginViewModel.usernameProperty().setValue("S2G2");
    loginViewModel.passwordProperty().setValue("1234");
    assertNotNull(loginViewModel.login());
  }

  @Test public void loginWithIncorrectUsername()
  {
    mf.getAccountModel().addAccount("S2G2", "1234", "Manager");
    loginViewModel.usernameProperty().setValue("NotS2G2");
    loginViewModel.passwordProperty().setValue("1234");
    assertNull(loginViewModel.login());
  }

  @Test public void loginWithIncorrectPassword()
  {
    mf.getAccountModel().addAccount("S2G2", "1234", "Manager");
    loginViewModel.usernameProperty().setValue("S2G2");
    loginViewModel.passwordProperty().setValue("9876");
    assertNull(loginViewModel.login());
  }

  @Test public void loginWithIncorrectCredentials()
  {
    mf.getAccountModel().addAccount("S2G2", "1234", "Manager");
    loginViewModel.usernameProperty().setValue("NotS2G2");
    loginViewModel.passwordProperty().setValue("9876");
    assertNull(loginViewModel.login());
  }

  @Test public void loginWithCorrectCredentialsTwice()
  {
    mf.getAccountModel().addAccount("S2G2", "1234", "Manager");
    loginViewModel.usernameProperty().setValue("S2G2");
    loginViewModel.passwordProperty().setValue("1234");
    boolean firstLoginSuccessful = loginViewModel.login() != null;

    mf.getAccountModel().addAccount("Salesperson", "1111", "Salesperson");
    loginViewModel.usernameProperty().setValue("Salesperson");
    loginViewModel.passwordProperty().setValue("1111");
    boolean secondLoginSuccessful = loginViewModel.login() != null;

    assertTrue(firstLoginSuccessful && secondLoginSuccessful);
  }

  @Test public void loginWithIncorrectCredentialsTwice()
  {
    mf.getAccountModel().addAccount("S2G2", "1234", "Manager");
    loginViewModel.usernameProperty().setValue("NotS2G2");
    loginViewModel.passwordProperty().setValue("9876");
    boolean firstLoginSuccessful = loginViewModel.login() != null;

    mf.getAccountModel().addAccount("Salesperson", "1111", "Salesperson");
    loginViewModel.usernameProperty().setValue("NotSalesperson");
    loginViewModel.passwordProperty().setValue("9999");
    boolean secondLoginSuccessful = loginViewModel.login() != null;

    assertTrue(!firstLoginSuccessful && !secondLoginSuccessful);
  }
}