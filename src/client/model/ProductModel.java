package client.model;

import shared.utils.Subject;

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
}
