package client.model;

import client.networking.Client;
import shared.transferobjects.Product;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 * Class that implements the AddProduct interface
 *
 * @author S2G2
 * @version 1.0
 */
public class PMImplementation implements ProductModel
{
  private PropertyChangeSupport support;
  private Client client;

  /**
   * One-argument constructor initializing the AddProductModel implementation class,
   * also initializes the PropertyChangeSupport object
   *
   * @param client Client object that will pass the necessary information
   */
  public PMImplementation(Client client)
  {
    support = new PropertyChangeSupport(this);
    this.client = client;
    client.addListener("ProductAdded", this::onAddProductReply);
    client.addListener("ProductExists", this::onAddProductReply);
  }

  /**
   * Listens to the result of addProduct()
   *
   * @param evt event that's being listened to
   */
  public void onAddProductReply(PropertyChangeEvent evt)
  {
    addProductReply(evt.getNewValue() != null, (String) evt.getNewValue());
  }

  @Override public void addProduct(String name, String desc, double price)
  {
    client.addProduct(new Product(name, desc, price));
  }

  @Override public void addProductReply(boolean successful, String name)
  {
    if (successful)
      support.firePropertyChange("ProductAdded", null, name);
    else
      support.firePropertyChange("ProductExists", null, null);
  }

  @Override public void getAllProducts(char role)
  {
    client.getAllProducts(role);
  }

  @Override public void getAllProductsReply(ArrayList<Product> productList)
  {
      support.firePropertyChange("GetProducts",null,productList);
  }

  @Override public void increaseStock(int id, int quantity)
  {
    client.increaseStock(id,quantity);
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