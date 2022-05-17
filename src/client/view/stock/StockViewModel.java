package client.view.stock;

import client.model.ProductModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.Product;
import shared.transferobjects.User;
import shared.utils.Subject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class StockViewModel implements PropertyChangeListener, Subject
{
  private ProductModel model;
  private PropertyChangeSupport support;

  public StockViewModel (ProductModel productModel)
  {
    this.model = productModel;
    support = new PropertyChangeSupport(this);
    model.addListener("GetProducts", this);
  }

  public void getAllProducts(char role)
  {
    model.getAllProducts(role);
  }

  public void increaseStock(int id,int quantity)
  {
    model.increaseStock(id,quantity);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    System.out.println("List of products");
    System.out.println(((ArrayList<Product>)evt.getNewValue()).get(0).getName());
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
