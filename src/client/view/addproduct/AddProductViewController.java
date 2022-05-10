package client.view.addproduct;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import client.view.addproduct.AddProductViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * A class that controls the GUI when adding a new product.
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

  @Override public void init (ViewHandler vh, ViewModelFactory vmf)
  {
    /**
     * An argument constructor that creates the viewModel, and binds all textFields
     */
    this.vh=vh;
    viewModel=vmf.getAddProductViewModel();
    viewModel.nameProperty().bindBidirectional(nameField.textProperty());
    viewModel.descriptionProperty().bindBidirectional(descriptionField.textProperty());
    viewModel.priceProperty().bindBidirectional(priceField.textProperty());
  }
  @FXML private void onAddProductButton ()
  {
    /**
     * A method that activates when the confirm button is pressed while creating a new product. It calls a method in AddProductViewModel
     */
    viewModel.addProduct();
  }
  @FXML private void onCancelButton ()
  {
    /**
     * A method that activates when the cancel button is pressed while creating a new product. It calls a method in AddProductViewModel
     */
    viewModel.cancel();
  }
}