package client.view.stock;

import client.model.ProductModel;
import shared.transferobjects.Product;
import shared.utils.Subject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 * A class that determines the behaviour of the GUI while viewing stock.
 * It also uses Observer Pattern to live update the ProductsTable
 * @author S2G2
 * @version 1.1
 */
public class StockViewModel implements PropertyChangeListener, Subject
{
  private final ProductModel model;
  private final PropertyChangeSupport support;

  /**
   * A one-argument constructor that initializes all fields, and subscribing to the model
   * @param productModel the model to be used by the viewmodel
   */
  public StockViewModel (ProductModel productModel)
  {
    this.model = productModel;
    support = new PropertyChangeSupport(this);
    model.addListener("ProductDataChanged",this);
  }

  /**
   * Getting all the products from the model depending on the user's role
   * @param role of the logged-in user
   * @return an ArrayList of all the products
   */
  public ArrayList<Product> getAllProducts(char role)
  {
    return model.getAllProducts(role);
  }

  /**
   * Removing a product using the model
   * @param product to be removed
   * @return null, if removing was not possible, the same product, if it was successful
   */
  public Product removeProduct(Product product)
  {
    return model.removeProduct(product);
  }

  /**
   * Changing the stock of the product using the model
   * @param id of the product to be changed
   * @param quantity the amount the stock should be increased by
   * @return the updated product object, or null, if the process was unsuccessful
   */
  public Product changeStock(int id,int quantity)
  {
   return model.changeStock(id,quantity);
  }


  /**
   * Registering the client for the live update feature
   */
  public void registerStockViewer()
  {
    model.registerStockViewer();
  }

  /**
   * Deregistering the client from the live update feature
   */
  public void deregisterStockViewer()
  {
    model.deregisterStockViewer();
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    support.firePropertyChange(evt);
  }

  @Override public void addListener(String propertyName,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(propertyName,listener);
  }

  @Override public void removeListener(String propertyName,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(propertyName,listener);
  }
}