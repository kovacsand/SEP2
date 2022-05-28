package client.view.login;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import shared.transferobjects.User;

/**
 * ViewController class of the GUI for logging in
 * @author S2G2
 * @version 1.1
 */
public class LoginViewController implements ViewController
{
  private LoginViewModel viewModel;
  private ViewHandler vh;

  @FXML private TextField usernameField;
  @FXML private PasswordField passwordField;

  /**
   * Initializing ViewHandler, ViewModel and binding bidirectionally text properties with text fields
   * @param vh to change views accordingly on the button press
   * @param vmf to get viewModel, so that view does not directly talk to the model, but everything goes through view-model (MVVM)
   */
  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    this.viewModel = vmf.getLoginViewModel();
    usernameField.setText(null);
    passwordField.setText(null);
    viewModel.usernameProperty().bindBidirectional(usernameField.textProperty());
    viewModel.passwordProperty().bindBidirectional(passwordField.textProperty());
  }

  /**
   * On Login button press checks the input and calls the ViewModel
   */
  @FXML private void onLoginButton()
  {
    if (viewModel.usernameProperty().getValue() == null || viewModel.passwordProperty().getValue() == null
    || viewModel.usernameProperty().getValue().equals("") || viewModel.passwordProperty().getValue().equals(""))
    {
      showErrorWindow("Insufficient input", "One or several fields are empty");
      return;
    }
    User loggedInUser = viewModel.login();
    if (loggedInUser == null)
    {
      showErrorWindow("Incorrect credentials", "Your username or password is incorrect");
      return;
    }
    vh.setUser(loggedInUser);
    vh.openView("Main");
  }
}
