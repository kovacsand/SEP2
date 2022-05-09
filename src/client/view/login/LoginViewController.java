package client.view.login;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginViewController implements ViewController
{
  private LoginViewModel viewModel;
  private ViewHandler vh;

  @FXML private TextField usernameField;
  @FXML private TextField passwordField;
  @FXML private Label errorLabel;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    this.viewModel = vmf.getLoginViewModel();
    errorLabel.setText("");
    viewModel.usernameProperty().bindBidirectional(usernameField.textProperty());
    viewModel.passwordProperty().bindBidirectional(passwordField.textProperty());
    viewModel.errorMessageProperty().bindBidirectional(errorLabel.textProperty());
  }

  @FXML private void onLoginButton()
  {
    System.out.println(usernameField.getText() +  ", " + passwordField.getText());
    viewModel.login();
  }
}
