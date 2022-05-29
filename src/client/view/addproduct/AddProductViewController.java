package client.view.addproduct;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * A class that controls the GUI for adding a new product.
 * @author S2G2
 * @version 1.0
 */
public class AddProductViewController implements ViewController
{
  private AddProductViewModel viewModel;
  private ViewHandler vh;

  @FXML private TextField nameField;
  @FXML private TextField descriptionField;
  @FXML private TextField priceField;
  private double tempPrice;

  /**
   * Initializing ViewHandler, ViewModel and binding bidirectionally text properties with text fields
   * @param vh to change views accordingly on the button press
   * @param vmf to get viewModel, so that view does not directly talk to the model, but everything goes through view-model (MVVM)
   */
  @Override public void init (ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh=vh;
    viewModel=vmf.getAddProductViewModel();
    viewModel.nameProperty().bindBidirectional(nameField.textProperty());
    viewModel.descriptionProperty().bindBidirectional(descriptionField.textProperty());
    viewModel.priceProperty().bindBidirectional(priceField.textProperty());
  }

  /**
   * Onclick method for Add button
   */
  @FXML private void onAddProductButton()
  {
    boolean inputSufficient = true;

    String name = nameField.getText();
    String description = descriptionField.getText();
    String price = priceField.getText();

    if (name == null || description == null || price == null || name.trim().equals("") || description.trim().equals("") || price.trim().equals(""))
    {
      showErrorWindow("One or several fields are empty");
      inputSufficient = false;
    }

    if (inputSufficient)
    {
      try
      {
        tempPrice = Double.parseDouble(priceField.getText());
      }
      catch (NumberFormatException nfe)
      {
        showErrorWindow("Price is not a number");
        inputSufficient = false;
      }
    }

    if (inputSufficient && nameField.getText().length() > 100
        || descriptionField.getText().length() > 280 || tempPrice > 1000000000)
    {
      showErrorWindow("Input too long");
      inputSufficient = false;
    }

    if (inputSufficient)
      viewModel.addProduct();

    viewModel.nameProperty().setValue(null);
    viewModel.descriptionProperty().setValue(null);
    viewModel.priceProperty().setValue(null);
    nameField.requestFocus();
  }
  /**
   * On Back button press, it opens the Main window
   */
  @FXML private void onBackButton()
  {
    vh.openView("Main");
  }
}