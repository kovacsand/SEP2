package client.view.sale;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.transferobjects.Manager;
import shared.transferobjects.Product;
import shared.transferobjects.Salesperson;
import shared.transferobjects.User;

import java.util.ArrayList;
import java.util.Optional;

public class SaleViewController implements ViewController
{
  @FXML private TableView<Product> stockTable;
  @FXML private TableView<Product> saleTable;
  @FXML private TableColumn<Product, String> productsIdColumn;
  @FXML private TableColumn<Product, String> productsNameColumn;
  @FXML private TableColumn<Product, String> productsDescColumn;
  @FXML private TableColumn<Product, String> productsPriceColumn;
  @FXML private TableColumn<Product, String> productsQuantityColumn;

  private ViewHandler vh;
  private ViewModelFactory vmf;
  private SaleViewModel viewModel;
  private User user;
  private ArrayList<Product> productsInStock;
  private ArrayList<Product> productsInBasket;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    this.vmf = vmf;
    this.viewModel = vmf.getSaleViewModel();
    this.user = vh.getUser();

    viewModel.registerStockViewer();

    productsInStock = new ArrayList<>();
    productsInBasket = new ArrayList<>();

    productsIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    productsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    productsDescColumn.setCellValueFactory(
        new PropertyValueFactory<>("description"));
    productsPriceColumn.setCellValueFactory(
        new PropertyValueFactory<>("price"));
    productsQuantityColumn.setCellValueFactory(
        new PropertyValueFactory<>("quantity"));

    fillProductsTable();
  }

  private void populateTable(ArrayList<Product> products)
  {
    for (Product product : products)
      stockTable.getItems().add(product);
  }

  private void fillProductsTable()
  {
    stockTable.getItems().clear();

    if (user instanceof Manager)
      productsInStock = viewModel.getAllProducts('m');
    if (user instanceof Salesperson)
      productsInStock = viewModel.getAllProducts('s');

    populateTable(productsInStock);
    stockTable.getSortOrder().add(productsIdColumn);
  }

  private void fillSaleTable()
  {
    saleTable.getItems().clear();
    populateTable(productsInBasket);
    saleTable.getSortOrder().add(productsIdColumn);
  }

  public void onBackButton()
  {
    viewModel.deregisterStockViewer();
    vh.openView("Main");
  }

  public void onAddButton()
  {
    TextInputDialog quantity = new TextInputDialog();
    quantity.setTitle("Insert amount to be added to basket");
    quantity.setHeaderText(
        "Product added to basket: " + stockTable.getSelectionModel()
            .getSelectedItem().getName());

    quantity.setContentText("Amount");
    TextField input = quantity.getEditor();

    Optional<String> amount = quantity.showAndWait();
    if (amount.isPresent())
    {
      try
      {
        int inputNumber = Integer.parseInt(input.getText());
        if (inputNumber > 0
            || stockTable.getSelectionModel().getSelectedItem().getQuantity()
            < inputNumber)
        {
          int id = stockTable.getSelectionModel().getSelectedItem().getId();
          String productName = stockTable.getSelectionModel().getSelectedItem()
              .getName();
          String productDesc = stockTable.getSelectionModel().getSelectedItem()
              .getDescription();
          Double productPrice = stockTable.getSelectionModel().getSelectedItem()
              .getPrice();

          Product productCopy = new Product(id, productName, productDesc,
              productPrice, inputNumber);
          viewModel.addProductToBasket(productCopy, inputNumber);
          productsInBasket.add(productCopy);
        }
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

      fillSaleTable();
    }

  }

  public void onRemoveButton()
  {
    viewModel.removeProductFromBasket(
        saleTable.getSelectionModel().getSelectedItem());
  }

  public void onCompleteButton()
  {

  }

  public void onCancelButton(ActionEvent actionEvent)
  {
  }

  public void onSearchButton(ActionEvent actionEvent)
  {
  }
}

