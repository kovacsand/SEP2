package client.model;

import shared.transferobjects.Product;
import shared.utils.Subject;

import java.util.ArrayList;

/**
 * Interface used for adding new products, extends the Subject interface
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
   * @param productList the list of products as an ArrayList
   */
  void getAllProductsReply(ArrayList<Product> productList);
}
