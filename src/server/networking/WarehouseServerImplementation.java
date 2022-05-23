package server.networking;

import server.model.ProductModel;
import shared.transferobjects.Product;
import shared.utils.Subject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 * Implementation of the WarehouseServer interface
 * @author S2G2
 * @version 1.0
 */
public class WarehouseServerImplementation implements WarehouseServer
{
  private final ProductModel productModel;
  private PropertyChangeSupport support;

  /**
   * One-argument constructor initializing the WarehouseServer implementation class.
   *
   * @param productModel the model that will be used by the server.
   */
  public WarehouseServerImplementation(ProductModel productModel)
  {
    this.productModel = productModel;
    support=new PropertyChangeSupport(this);
  }

  @Override public Product addProduct(Product product)
  {
    return productModel.addProduct(product);
  }

  @Override public ArrayList<Product> getAllProducts(char role)
  {
    return productModel.getAllProducts(role);
  }

  @Override public Product changeStock(int id, int quantity)
  {
    return productModel.changeStock(id, quantity);
  }

}
