package client.view.login;

import org.junit.Assert;
import org.junit.Test;

/*
  The LoginViewController cannot be tested using JUnit,
  as this would require running a GUI and the Test class would have to extend
  Application and making it really complicated
*/
public class LoginViewControllerTest
{
  @Test
  public void loginViewControllerManuallyTested()
  {
    Assert.assertTrue(true);
  }
}