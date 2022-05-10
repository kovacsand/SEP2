package client.view.login;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * ViewController class of the GUI for logging in
 * @author S2G2
 * @version 1.0
 */
public class LoginViewController implements ViewController
{
  private LoginViewModel viewModel;
  private ViewHandler vh;

  @FXML private TextField usernameField;
  @FXML private TextField passwordField;
  @FXML private Label errorLabel;

  /**
   * Initializing ViewHandler, ViewModel and binding bidirectionally text properties with text fields
   * @param vh to change views accordingly on the button press
   * @param vmf to get viewModel, so that view does not directly talk to the model, but everything goes through view-model (MVVM)
   */
  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    this.viewModel = vmf.getLoginViewModel();
    errorLabel.setText("");
    viewModel.usernameProperty().bindBidirectional(usernameField.textProperty());
    viewModel.passwordProperty().bindBidirectional(passwordField.textProperty());
    viewModel.errorMessageProperty().bindBidirectional(errorLabel.textProperty());
  }

  /**
   * On Login button press
   */
  @FXML private void onLoginButton()
  {
    System.out.println(usernameField.getText() +  ", " + passwordField.getText());
    //viewModel.login();
    vh.openView("Temp");
  }
}
