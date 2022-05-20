package client.view.addaccount;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * ViewController class of the GUI for adding an account
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

  private void createAlertWindow()
  {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("An error has been encountered");
    alert.setContentText("One of the fields is empty");
    alert.showAndWait();
    viewModel.getUsername().setValue(null);
    viewModel.getPassword().setValue(null);
    viewModel.getRole().setValue(null);
  }

  private void createAlertWindowOverLengthLimit()
  {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("An error has been encountered");
    alert.setContentText("Username or password too long");
    alert.showAndWait();
    viewModel.getUsername().setValue(null);
    viewModel.getPassword().setValue(null);
    viewModel.getRole().setValue(null);
  }

  /**
   * On Add button press
   */
  @FXML public void onAddButton()
  {
    if (usernameField.getText().length() < 60
        && passwordField.getText().length() < 80)
    {
      String username = viewModel.getUsername().getValue();
      String password = viewModel.getPassword().getValue();
      String role = viewModel.getRole().getValue();
      if (role == null || password == null || username == null)
        createAlertWindow();
      else
        viewModel.addAccount();
    }
    else
    {
        createAlertWindowOverLengthLimit();
    }
  }
  /**
   * On Cancel button press
   */
  @FXML public void onBackButton()
  {
    vh.openView("Main");
  }
}
