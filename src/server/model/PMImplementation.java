package server.model;

import server.model.mediator.ProductDAOImplementation;
import shared.transferobjects.Product;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class that implements the AddProductModel interface on server side.
 * @author S2G2
 * @version 1.0
 */
public class PMImplementation implements ProductModel
{
  private final PropertyChangeSupport support;

  /**
   * Zero-argument constructor initializing the AccountModel implementation class
   */
  public PMImplementation()
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

  @Override public ArrayList<Product> getAllProducts(char role)
  {
    ArrayList<Product> allProducts = new ArrayList<>();
    try
    {
      allProducts = ProductDAOImplementation.getInstance().getAllProducts(role);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return allProducts;
  }

  @Override public Product changeStock(int id, int quantity)
  {
    Product product = null;
    try
    {
      product = ProductDAOImplementation.getInstance().changeStock(id, quantity);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return product;
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
