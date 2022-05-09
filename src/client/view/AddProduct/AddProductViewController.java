package client.view.AddProduct;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddProductViewController implements ViewController
{
  private AddProductViewModel viewModel;
  private ViewHandler vh;

  @FXML private TextField nameField;
  @FXML private TextField descriptionField;
  @FXML private TextField priceField;

  @Override public void init (ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh=vh;
    viewModel=vmf.getAddProductViewModel();
    viewModel.nameProperty().bindBidirectional(nameField.textProperty());
    viewModel.descriptionProperty().bindBidirectional(descriptionField.textProperty());
    viewModel.priceProperty().bindBidirectional(priceField.textProperty());
  }
  @FXML private void onAddProductButton ()
  {
    viewModel.addProduct();
  }
  @FXML private void onCancelButton ()
  {
    viewModel.cancel();
  }
}
