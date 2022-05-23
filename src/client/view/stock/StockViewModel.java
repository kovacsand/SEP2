package client.view.stock;

import client.model.ProductModel;
import shared.transferobjects.Product;
import shared.utils.Subject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class StockViewModel implements PropertyChangeListener, Subject
{
  private final ProductModel model;
  private final PropertyChangeSupport support;

  public StockViewModel (ProductModel productModel)
  {
    this.model = productModel;
    support = new PropertyChangeSupport(this);
    model.addListener("ProductDataChanged",this);

  }

  public ArrayList<Product> getAllProducts(char role)
  {
    return model.getAllProducts(role);
  }

  public Product changeStock(int id,int quantity)
  {
   return model.changeStock(id,quantity);
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

  public void registerStockViewer()
  {
    model.registerStockViewer();
  }

  public void deregisterStockViewer()
  {
    model.deregisterStockViewer();
  }
}
