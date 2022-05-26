package client.view.sale;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.transferobjects.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Optional;

public class SaleViewController implements ViewController,
    PropertyChangeListener
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

  @FXML private TextField searchField;
  @FXML private Label totalPriceLabel;

  private ViewHandler vh;
  private ViewModelFactory vmf;
  private SaleViewModel viewModel;
  private User user;
  private ArrayList<Product> productsInStock;
  private ArrayList<Product> productsInBasket;
  private PropertyChangeSupport support;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    this.vmf = vmf;
    this.viewModel = vmf.getSaleViewModel();
    this.user = vh.getUser();
    support=new PropertyChangeSupport(this);
    viewModel.addListener("ProductDataChanged",this);

    viewModel.totalPriceProperty().bindBidirectional(totalPriceLabel.textProperty());

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

    productsInStock = viewModel.getAllProducts('s');
    fillProductsTable();
  }

  private void fillProductsTable()
  {
    stockTable.getItems().clear();
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
    onCancelButton();
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

          Product productCopy = new Product(id, productName, productDesc, productPrice, inputNumber);

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
      fillSaleTable();
    }
  }

  public void onRemoveButton()
  {
    Product productToBeRemoved = saleTable.getSelectionModel().getSelectedItem();
    productsInBasket.remove(productToBeRemoved);
    viewModel.removeProductFromBasket(productToBeRemoved);
    fillSaleTable();
  }

  public void onCompleteButton()
  {
    Basket basket = new Basket();
    Salesperson salesperson = (Salesperson) user;
    for (Product product : productsInBasket)
      basket.addProduct(product, product.getQuantity());
    viewModel.finaliseSale(basket, salesperson);

    productsInBasket.clear();
    fillSaleTable();
  }

  public void onCancelButton()
  {
    for (Product product: productsInBasket)
        viewModel.removeProductFromBasket(product);
    productsInBasket.clear();
    fillSaleTable();
  }

  public void onSearchButton()
  {
    ArrayList<Product> searchedProductsInStock = new ArrayList<>();
    productsInStock = viewModel.getAllProducts('s');
    if (searchField.getText() != null)
      for (Product product : productsInStock)
        if (product.getName().contains(searchField.getText()))
          searchedProductsInStock.add(product);
    productsInStock = new ArrayList<>(searchedProductsInStock);

    if (searchField.getText() == null)
      productsInStock = viewModel.getAllProducts('s');


    searchField.setText(null);
    fillProductsTable();
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    productsInStock = viewModel.getAllProducts('s');
    fillProductsTable();
  }
}

