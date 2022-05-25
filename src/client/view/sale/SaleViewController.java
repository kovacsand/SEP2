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

  @FXML private TableColumn<Product, String> basketProductName;
  @FXML private TableColumn<Product, String> basketProductDescription;
  @FXML private TableColumn<Product, String> basketProductPrice;
  @FXML private TableColumn<Product, String> basketProductQuantity;

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
    productsDescColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    productsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    productsQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

    basketProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
    basketProductDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    basketProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    basketProductQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));


    fillProductsTable();
  }

  private void fillProductsTable()
  {
    stockTable.getItems().clear();
    productsInStock = viewModel.getAllProducts('s');
    for (Product product : productsInStock)
      stockTable.getItems().add(product);
    stockTable.getSortOrder().add(productsIdColumn);
  }

  private void fillSaleTable()
  {
    saleTable.getItems().clear();
    for (Product product : productsInBasket)
      saleTable.getItems().add(product);
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
          //TODO check if already in basket

          boolean alreadyInBasket = false;
          for (int i = 0; i < productsInBasket.size(); i++)
          {
            Product product = productsInBasket.get(i);
            if (productCopy.getId() == product.getId())
            {
              alreadyInBasket = true;
              Product increasedProduct = new Product(product.getId(), product.getName(), product.getDescription(), product.getPrice(),product.getQuantity() + inputNumber);
              productsInBasket.remove(i);
              productsInBasket.add(i, increasedProduct);
            }
          }

          viewModel.addProductToBasket(productCopy, inputNumber, alreadyInBasket);
          if (! alreadyInBasket)
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
      fillSaleTable();;
    }
  }

  public void onRemoveButton()
  {
    viewModel.removeProductFromBasket(saleTable.getSelectionModel().getSelectedItem());
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

