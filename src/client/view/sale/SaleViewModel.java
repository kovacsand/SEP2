package client.view.sale;

import client.model.ProductModel;
import client.model.SaleModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.Basket;
import shared.transferobjects.Product;
import shared.transferobjects.Receipt;
import shared.transferobjects.Salesperson;
import shared.utils.Subject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 * A class that determines the behaviour of the GUI while making a sale.
 * It also uses Observer Pattern to live update the ProductsTable
 * @author S2G2
 * @version 1.1
 */
public class SaleViewModel implements PropertyChangeListener, Subject
{
  private final SaleModel saleModel;
  private final ProductModel productModel;
  private final PropertyChangeSupport support;

  private double totalPrice;
  private final StringProperty totalPriceProperty;

  /**
   * A one-argument constructor that initializes all fields, and subscribing to the model
   * @param saleModel the SaleModel to be used by the viewmodel
   * @param productModel the ProductModel to be used by the viewmodel
   */
  public SaleViewModel(SaleModel saleModel, ProductModel productModel)
  {
    this.saleModel=saleModel;
    this.productModel=productModel;
    support=new PropertyChangeSupport(this);

    totalPrice = 0;
    totalPriceProperty = new SimpleStringProperty();
    saleModel.addListener("ProductDataChanged", this);
  }

  /**
   * Finalises the sale using the model
   * @param basket of the sale
   * @param salesperson making the sale
   * @return the newly generated Receipt, null if the process was unsuccessful
   */
  public Receipt finaliseSale(Basket basket, Salesperson salesperson)
  {
    totalPrice = 0;
    totalPriceProperty.setValue("Total price: " + totalPrice);
    return saleModel.finaliseSale(basket,salesperson);
  }

  /**
   * Adds a Product to a Basket
   * @param product to be added
   * @param quantity of the Product to be added
   * @param alreadyInBasket boolean stating whether the Product has already been in the basket
   * @return the newly changed Product, null if the process was unsuccessful
   */
  public Product addProductToBasket(Product product,int quantity, boolean alreadyInBasket)
  {
    totalPrice += product.getPrice() * quantity;
    totalPriceProperty.setValue("Total price: " + totalPrice);
    return saleModel.addProductToBasket(product,quantity, alreadyInBasket);
  }

  /**
   * Removes a Product to a Basket
   * @param product to be added
   * @return the newly changed Product, null if the process was unsuccessful
   */
  public Product removeProductFromBasket(Product product)
  {
    totalPrice -= product.getPrice() * product.getQuantity();
    totalPriceProperty.setValue("Total price: " + totalPrice);
    return saleModel.removeProductFromBasket(product);
  }

  /**
   * Getting all the products from the model depending on the user's role
   * @param role of the logged-in user
   * @return an ArrayList of all the products
   */
  public ArrayList<Product> getAllProducts(char role)
  {
    return productModel.getAllProducts(role);
  }

  /**
   * Changing the stock of the product using the model
   * @param id of the product to be changed
   * @param quantity the amount the stock should be increased by
   * @return the updated product object, or null, if the process was unsuccessful
   */
  public Product changeStock(int id,int quantity)
  {
    return productModel.changeStock(id,quantity);
  }

  /**
   * Returns the total price of the Product
   * @return the totalPriceProperty
   */
  public StringProperty totalPriceProperty()
  {
    return totalPriceProperty;
  }

  /**
   * Registering the client for the live update feature
   */
  public void registerStockViewer()
  {
    productModel.registerStockViewer();
  }

  /**
   * Deregistering the client from the live update feature
   */
  public void deregisterStockViewer()
  {
    productModel.deregisterStockViewer();
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
   support.firePropertyChange(evt);
  }

  @Override public void addListener(String propertyName, PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(propertyName, listener);
  }

  @Override public void removeListener(String propertyName, PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(propertyName, listener);
  }
}