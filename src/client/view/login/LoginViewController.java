package client.view.login;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import shared.transferobjects.User;

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
  private User user;

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
    usernameField.setText(null);
    passwordField.setText(null);
    errorLabel.setText(null);
    viewModel.usernameProperty().bindBidirectional(usernameField.textProperty());
    viewModel.passwordProperty().bindBidirectional(passwordField.textProperty());
    viewModel.errorMessageProperty().bindBidirectional(errorLabel.textProperty());
    viewModel.addListener("LoginSuccess", this);
  }

  /**
   * On Login button press
   */
  @FXML private void onLoginButton()
  {
    if (viewModel.usernameProperty().getValue() == null || viewModel.passwordProperty().getValue() == null)
      createAlertWindow();
    else
      viewModel.login();
  }

  private void createAlertWindow()
  {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("An error has been encountered");
    alert.setContentText("One of the fields is empty");
    alert.showAndWait();
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    user = (User) evt.getNewValue();
    vh.setUser(user);
    vh.openView("Main");
  }
}
