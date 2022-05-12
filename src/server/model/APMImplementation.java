package server.model;

import server.model.mediator.ProductDAOImplementation;
import shared.transferobjects.Product;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;

/**
 * Class that implements the AddProductModel interface on server side.
 * @author S2G2
 * @version 1.0
 */
public class APMImplementation implements AddProductModel
{
  private PropertyChangeSupport support;

  /**
   * Zero-argument constructor initializing the AccountModel implementation class
   */
  public APMImplementation()
  {
    support = new PropertyChangeSupport(this);
  }

  @Override public Product addProduct(Product product)
  {
    Product addedProduct = null;
    try
    {
      addedProduct = ProductDAOImplementation.getInstance().addProduct(product);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return addedProduct;
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
