package client.view.addaccount;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * ViewController class of the GUI for adding an account
 * @author S2G2
 * @version 1.1
 */
public class AddAccountViewController implements ViewController
{
  private ViewHandler vh;
  private AddAccountViewModel viewModel;
  @FXML private TextField usernameField;
  @FXML private TextField passwordField;
  @FXML private ComboBox<String> roleField;

  /**
   * Initializing ViewHandler, ViewModel and binding bidirectionally text properties with text fields
   * @param vh to change views accordingly on the button press
   * @param vmf to get viewModel, so that view does not directly talk to the model, but everything goes through view-model (MVVM)
   */
  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    initialiseComboBox();
    viewModel = vmf.getAddAccountViewModel();
    viewModel.getUsername().bindBidirectional(usernameField.textProperty());
    viewModel.getPassword().bindBidirectional(passwordField.textProperty());
    viewModel.getRole().bindBidirectional(roleField.valueProperty());
  }

  /**
   * Initializing values for the combo box for employee roles
   */
  private void initialiseComboBox()
  {
    ObservableList<String> roles = FXCollections.observableArrayList("Salesperson", "Accountant", "Manager");
    roleField.getItems().addAll(roles);
  }

  /**
   * On Add button press
   */
  @FXML public void onAddButton()
  {
    String username = usernameField.getText();
    String password = passwordField.getText();
    String role = roleField.getValue();
    viewModel.getUsername().setValue(null);
    viewModel.getPassword().setValue(null);
    viewModel.getRole().setValue(null);
    if (username == null || password == null || role == null)
    {
      showErrorWindow("An error has been encountered", "One or several fields are empty");
      return;
    }
    if (username.length() > 60 || password.length() > 80)
    {
      showErrorWindow("An error has been encountered", "Username or password is too long");
      return;
    }
    viewModel.addAccount();
  }
  /**
   * On Cancel button press
   */
  @FXML public void onBackButton()
  {
    vh.openView("Main");
  }
}
