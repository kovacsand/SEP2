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
   * A method that activates when the confirm button is pressed while creating a new product. It calls a method in AddProductViewModel
   */
  @FXML private void onAddProductButton ()
  {
    viewModel.addProduct();
  }

  /**
   * A method that activates when the cancel button is pressed while creating a new product. It opens the Main window.
   */
  @FXML private void onBackButton()
  {
    vh.openView("Main");
  }
}