package client.view.login;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * ViewController class of the GUI for logging in
 * @author S2G2
 * @version 1.0
 */
public class LoginViewController implements ViewController,
    PropertyChangeListener
{
  private LoginViewModel viewModel;
  private ViewHandler vh;
  private String role;

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
    viewModel.addListener("LoginSuccess",this::propertyChange);
  }

  /**
   * On Login button press
   */
  @FXML private void onLoginButton()
  {
    viewModel.login();
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    role = evt.getNewValue().toString();
    vh.setRole(role);
    vh.openView("Main");
  }
}
