package client.model;

import client.networking.Client;
import server.model.mediator.ReceiptDAOImplementation;
import shared.transferobjects.Product;
import shared.transferobjects.Receipt;
import shared.transferobjects.Basket;
import shared.transferobjects.Salesperson;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import java.sql.SQLException;

/**
 * Class that implements the SaleModel interface
 * @author S2G2
 * @version 1.0
 */
public class SMImplementation implements SaleModel
{
  private Client client;
  private final PropertyChangeSupport support;

  /**
   * One-argument constructor initializing the SaleModelImplementation class
   * @param client object that will pass the necessary information
   */
  public SMImplementation(Client client)
  {
    this.client = client;
    this.support = new PropertyChangeSupport(this);
    client.addListener("ProductDataChanged",this::onProductDataChange);
  }

  @Override public void onProductDataChange(
      PropertyChangeEvent evt)
  {
    support.firePropertyChange(evt);
  }

  @Override public Receipt finaliseSale(Basket basket, Salesperson salesperson)
  {
    return client.finaliseSale(basket, salesperson);
  }

  @Override public Product addProductToBasket(Product product, int quantity, boolean alreadyInBasket)
  {
    return client.addProductToBasket(product, quantity, alreadyInBasket);
  }

  @Override public Product removeProductFromBasket(Product product)
  {
    return client.removeProductFromBasket(product);
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
