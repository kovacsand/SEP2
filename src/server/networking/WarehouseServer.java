package server.networking;

import shared.transferobjects.Product;

import java.util.ArrayList;

/**
 * Interface for server-side to handle everything with the warehouse.
 * @author S2G2
 * @version 1.0
 */
public interface WarehouseServer
{
  /**
 * Adding product in through the model. The server passes the argument to the model.
 * @param product to be added
 * @return If adding is successful, return their User Object, if not, return null.
 */
  Product addProduct(Product product);

  /**
   * Getting all products through the model. The server passes the argument to the model
   * @param role of the user, to know which products list to return depending on the role
   * @return the newly added Product object, if the addition failed, null
   */
  ArrayList<Product> getAllProducts(char role);

  /**
   * Increasing the stock of the product through the model. The server passes the argument to the model
   * @param id of the product
   * @param quantity amount which needs to be increased by
   */
  Product changeStock(int id, int quantity);

  /**
   *
   * Removing the product from the database. This server passes to the model
   * @param product the product to be removed
   * @return the removed product object if successful, null if unsuccessful
   */
  Product removeProduct(Product product);
}
