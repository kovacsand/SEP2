package client.view.stock;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.transferobjects.Manager;
import shared.transferobjects.Product;
import shared.transferobjects.Salesperson;
import shared.transferobjects.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Optional;

public class StockViewController implements ViewController, PropertyChangeListener
{
  public Button increaseStockButton;
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

    products = new ArrayList<>();

    viewModel.registerStockViewer();
    viewModel.addListener("ProductDataChanged", this);

    increaseStockButton.setVisible(false);
    if (user instanceof Manager)
      increaseStockButton.setVisible(true);

    productsIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    productsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    productsDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    productsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    productsStockColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

    fillProductsTable();
  }

  private void fillProductsTable()
  {
    productsTable.getItems().clear();

    if (user instanceof Manager)
     products = viewModel.getAllProducts('m');
    if (user instanceof Salesperson)
      products = viewModel.getAllProducts('s');

    populateTable(products);
    productsTable.getSortOrder().add(productsIdColumn);
  }

  @FXML private void onBackButtonPress ()
  {
    viewModel.deregisterStockViewer();
    vh.openView("Main");
  }

  public void onIncreaseStockButtonPress()
  {
    TextInputDialog quantity = new TextInputDialog();
    quantity.setTitle("Increase stock");
    quantity.setHeaderText(
        "Increase stock of: " + productsTable.getSelectionModel().getSelectedItem().getName());
    quantity.setContentText("Amount");
    TextField input = quantity.getEditor();

    Optional<String> amount = quantity.showAndWait();
    if (amount.isPresent())
    {
      try
      {
        int inputNumber = Integer.parseInt(input.getText());
        if(inputNumber>0)
          viewModel.changeStock(productsTable.getSelectionModel().getSelectedItem().getId(),inputNumber);
        else
          throw new NumberFormatException();
      }
      catch (NumberFormatException e)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An error has been encountered");
        alert.setContentText("Insert valid amount");
        alert.showAndWait();
      }
    }
  }
  private void populateTable(ArrayList<Product> products)
  {
    for(Product product : products)
      productsTable.getItems().add(product);
  }


  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(()-> fillProductsTable());
  }
}
