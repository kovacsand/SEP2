package client.model;

import shared.transferobjects.Product;
import shared.utils.Subject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
   * Adding new product
   * @param name name of the new product
   * @param desc description of the new product
   * @param price price of the new product
   */

  void addProduct(String name, String desc, double price);

  /**
   * Sending information about the result of adding new product
   * @param successful parameter that needs to be met for the product to be added successfully
   * @param name  name of the new product
   */
  void addProductReply(boolean successful, String name);

  /**
   * Requesting all products in the database to be displayed
   * @param role char that determines what products will be returned based on user role
   */
  void getAllProducts(char role);

  /**
   * Sending the arraylist of products to be displayed
   * @param evt the list of products as an ArrayList
   */
  void getAllProductsReply(PropertyChangeEvent evt);

  /**
   * Sends a request to increase the quantity of a product
   * @param id the id of the product
   * @param quantity the amount to increase the product by
   */
  void increaseStock(int id, int quantity);
}
