package server.model;

import server.model.mediator.ProductDAOImplementation;
import shared.transferobjects.Product;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class that implements the ProductModel interface on server side.
 * @author S2G2
 * @version 1.0
 */
public class PMImplementation implements ProductModel
{

  /**
   * Zero-argument constructor initializing the AccountModel implementation class
   */
  public PMImplementation()
  {
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

  @Override public Product removeProduct(Product product)
  {
    Product productToBeDeleted = null;
    try
    {
      productToBeDeleted = ProductDAOImplementation.getInstance().removeProduct(product);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return  productToBeDeleted;
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
}