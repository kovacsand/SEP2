package server.model;

import server.model.mediator.ProductDAOImplementation;
import shared.transferobjects.Product;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;

public class APMImplementation implements AddProductModel
{
  private PropertyChangeSupport support;

  public APMImplementation()
  {
    support = new PropertyChangeSupport(this);
  }

  @Override public void addProduct(Product product)
  {
    try
    {
      ProductDAOImplementation.getInstance().addProduct(product);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void addProductReply(boolean successful, String name)
  {
    if(successful)
      support.firePropertyChange("ProductAdded", null, name);
    else
      support.firePropertyChange("ProductExists", null, name);
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
