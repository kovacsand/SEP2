package client.model;

import shared.transferobjects.Product;
import shared.utils.Subject;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

/**
 * Interface used for adding new products, adding stock, and getting
 * the list of products, extends the Subject interface
 * @author S2G2
 * @version 1.2
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
   * @return the new Product object, null if the process was unsuccessful
   */
  Product addProduct(String name, String desc, double price);

  /**
   * Requesting all products in the database to be displayed
   * @param role char that determines what products will be returned based on user role
   * @return ArrayList of all the products, null if the process was unsuccessful
   */
  ArrayList<Product> getAllProducts(char role);

  /**
   * Sends a request to increase the quantity of a product
   * @param id the id of the product
   * @param quantity the amount to increase the product by
   * @return the updated Product, null if the process was unsuccessful
   */
  Product changeStock(int id, int quantity);

  /**
   * Sends a request to the client to remove the product
   * @param product selected product from the view
   * @return successfully removed product, null if not successful
   */
  Product removeProduct(Product product);

  /**
   * Call the method on the client, to be added as looking at the stock view
   */
  void registerStockViewer();

  /**
   * Calls the method on the client, to be removed as looking at the stock view
   */
  void deregisterStockViewer();
}