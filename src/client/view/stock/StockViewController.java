package client.view.stock;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.transferobjects.Manager;
import shared.transferobjects.Product;
import shared.transferobjects.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class StockViewController implements ViewController,
    PropertyChangeListener
{
  private ViewHandler vh;
  private StockViewModel viewModel;
  private User user;
  private ArrayList<Product> products;

  @FXML private TableView<Product> productsTable;
  @FXML private TableColumn<Product, String> productsIdColumn;
  @FXML private TableColumn<Product, String> productsNameColumn;
  @FXML private TableColumn<Product, String> productsDescriptionColumn;
  @FXML private TableColumn<Product, String> productsPriceColumn;
  @FXML private TableColumn<Product, String> productsStockColumn;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh=vh;
    this.viewModel = vmf.getStockViewModel();
    this.user = vh.getUser();

    viewModel.addListener("GetProducts", this);

    products = new ArrayList<>();

    productsTable.getItems().clear();
    productsIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    productsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    productsDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    productsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    productsStockColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

    if (user instanceof Manager)
      viewModel.getAllProducts('m');
    else
      viewModel.getAllProducts('s');
  }

  @FXML private void onBackButtonPress ()
  {
    vh.openView("Main");
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    products = (ArrayList<Product>) evt.getNewValue();
    for (int i = 0; i < products.size(); i++)
      productsTable.getItems().add(products.get(i));
  }
}
