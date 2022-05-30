package client.view.sale;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.transferobjects.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Optional;

/**
 * A class that controls the GUI for making a sale
 * It also uses Observer Pattern to live update the ProductsTable
 * @author S2G2
 * @version 1.1
 */
public class SaleViewController implements ViewController, PropertyChangeListener
{
  @FXML private TableView<Product> productsTable;
  @FXML private TableView<Product> basketTable;
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
  private SaleViewModel viewModel;
  private User user;
  private ArrayList<Product> productsInStock;
  private ArrayList<Product> productsInBasket;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    this.viewModel = vmf.getSaleViewModel();
    this.user = vh.getUser();
    viewModel.addListener("ProductDataChanged",this);
    viewModel.registerStockViewer();

    productsInStock = new ArrayList<>();
    productsInBasket = new ArrayList<>();

    viewModel.totalPriceProperty().bindBidirectional(totalPriceLabel.textProperty());

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

  /**
   * Populates the product table with data from the database
   */
  private void fillProductsTable()
  {
    productsTable.getItems().clear();
    for (Product product : productsInStock)
      productsTable.getItems().add(product);
    productsTable.getSortOrder().add(productsIdColumn);
  }

  /**
   * Populates the basket table
   */
  private void fillBasketTable()
  {
    basketTable.getItems().clear();
    for (Product product : productsInBasket)
      basketTable.getItems().add(product);
    basketTable.getSortOrder().add(productsIdColumn);
  }

  /**
   * On Back button press, empties the basket and opens the Main window
   */
  public void onBackButton()
  {
    onCancelButton();
    viewModel.deregisterStockViewer();
    vh.openView("Main");
  }

  /**
   * On Add button press, it asks for the quantity and adds the product to the basket
   * If the product is already in the basket, it increases its quantity
   */
  public void onAddButton()
  {
    Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
    if (selectedProduct == null)
    {
      showErrorWindow("No product selected");
      return;
    }

    TextInputDialog quantity = new TextInputDialog();
    quantity.setTitle("Insert amount to be added to basket");
    quantity.setHeaderText("Adding " + selectedProduct.getName() + " to basket");
    quantity.setContentText("Amount");
    TextField input = quantity.getEditor();

    Optional<String> amount = quantity.showAndWait();
    if (amount.isPresent())
    {
      try
      {
        int inputNumber = Integer.parseInt(input.getText());
        if (inputNumber > 0 && inputNumber <= selectedProduct.getQuantity())
        {
          int productId = selectedProduct.getId();
          String productName = selectedProduct.getName();
          String productDesc = selectedProduct.getDescription();
          double productPrice = selectedProduct.getPrice();

          Product productToBasket = new Product(productId, productName, productDesc, productPrice, inputNumber);
          boolean alreadyInBasket = false;
          for (int i = 0; i < productsInBasket.size(); i++)
          {
            Product product = productsInBasket.get(i);
            if (productToBasket.getId() == product.getId())
            {
              alreadyInBasket = true;
              Product increasedProduct = new Product(product.getId(), product.getName(), product.getDescription(), product.getPrice(),product.getQuantity() + inputNumber);
              productsInBasket.remove(i);
              productsInBasket.add(i, increasedProduct);
            }
          }

          viewModel.addProductToBasket(productToBasket, inputNumber, alreadyInBasket);
          if (!alreadyInBasket)
            productsInBasket.add(productToBasket);
        }
        else
          throw new NumberFormatException();
      }
      catch (NumberFormatException e)
      {
        showErrorWindow("Invalid amount");
      }
    }
    fillBasketTable();
  }

  /**
   * On Remove button press, it removes the product from the basket
   * and changes back the stock in the database
   */
  public void onRemoveButton()
  {
    Product productToBeRemoved = basketTable.getSelectionModel().getSelectedItem();
    productsInBasket.remove(productToBeRemoved);
    viewModel.removeProductFromBasket(productToBeRemoved);
    fillBasketTable();
  }

  /**
   * On Complete button press, it completes the sale
   */
  public void onCompleteButton()
  {
    Basket basket = new Basket();
    Salesperson salesperson = (Salesperson) user;
    for (Product product : productsInBasket)
      basket.addProduct(product, product.getQuantity());
    if (!basket.getProducts().isEmpty())
      viewModel.finaliseSale(basket, salesperson);
    productsInBasket.clear();
    fillBasketTable();
  }

  /**
   * On Cancel button press, it empties the basket
   */
  public void onCancelButton()
  {
    for (Product product: productsInBasket)
        viewModel.removeProductFromBasket(product);
    productsInBasket.clear();
    fillBasketTable();
  }

  /**
   * On Search button it filters the products table
   */
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
    Platform.runLater(() ->
    {
      productsInStock = viewModel.getAllProducts('s');
      fillProductsTable();
    });
  }
}