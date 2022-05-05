package client.view.login;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.fxml.FXML;

public class LoginViewController implements ViewController
{
  private LoginViewModel viewModel;
  private ViewHandler vh;


  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    this.viewModel = vmf.getLoginViewModel();
  }

  @FXML private void onLoginButton()
  {

  }
}
