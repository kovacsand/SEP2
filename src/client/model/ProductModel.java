package client.model;

import shared.transferobjects.Product;
import shared.utils.Subject;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

/**
 * Interface used for adding new products, adding stock, and getting
 * the list of products, extends the Subject interface
 * @author S2G2
 * @version 1.0
 */
public interface ProductModel extends Subject
{
  /**
   * Fires a change when Product Data changes
   * @param evt ProductData is changing
   */
  void onProductDataChange(PropertyChangeEvent evt);


  /**
   * Adding new product
   * @param name name of the new product
   * @param desc description of the new product
   * @param price price of the new product
   */
  Product addProduct(String name, String desc, double price);

  /**
   * Requesting all products in the database to be displayed
   * @param role char that determines what products will be returned based on user role
   */
  ArrayList<Product> getAllProducts(char role);

  /**
   * Sends a request to increase the quantity of a product
   * @param id the id of the product
   * @param quantity the amount to increase the product by
   */
  Product changeStock(int id, int quantity);

  /**
   * Sends a request to the client to remove the product
   * @param product selected product from the view
   * @return successfully removed product, null if not successful
   */
  Product removeProduct(Product product);


  void registerStockViewer();

  void deregisterStockViewer();
}
