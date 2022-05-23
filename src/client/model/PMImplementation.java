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
  private final PropertyChangeSupport support;
  private final Client client;

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
    client.addListener("ProductDataChanged",this::onProductDataChange);
  }

  @Override public void onProductDataChange(PropertyChangeEvent evt)
  {
    support.firePropertyChange(evt);
  }

  @Override public Product addProduct(String name, String desc, double price)
  {
    return client.addProduct(new Product(name, desc, price));
  }

  @Override public ArrayList<Product> getAllProducts(char role)
  {
    return client.getAllProducts(role);
  }

  @Override public Product changeStock(int id, int quantity)
  {
    return client.changeStock(id, quantity);
  }

  @Override public void registerStockViewer()
  {
    client.registerStockViewer();
  }

  @Override public void deregisterStockViewer()
  {
    client.deregisterStockViewer();
  }

  @Override public void addListener(String propertyName,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(propertyName, listener);
  }

  @Override public void removeListener(String propertyName,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(propertyName, listener);
  }
}
