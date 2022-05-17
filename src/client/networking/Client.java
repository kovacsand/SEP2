package client.networking;

import shared.transferobjects.Product;
import shared.transferobjects.User;
import shared.utils.Subject;

/**
 * Interface for Client using RMI and Observer Pattern
 *
 * @author S2G2
 * @version 1.0
 */
public interface Client extends Subject
{
  /**
   * User logging into the account
   *
   * @param username of the user
   * @param password of the user
   */
  void login(String username, String password);

  /**
   * Adding a product to the database
   *
   * @param product object that needs to be added
   */
  void addProduct(Product product);

  /**
   * Adding account to the database
   *
   * @param user object that needs to be added
   */
  void addAccount(User user, String password);

  /**
   * Registering the client to the server while connecting
   */
  void registerClient();

  /**
   * Unregistering the client from the server when disconnecting
   */
  void unregisterClient();

  /**
   * Requests a list of all products from the Database
   *
   * @param role gets the products based on role: M = manager, S = salesperson
   */
  void getAllProducts(char role);

  /**
   * Calls the method on the server to increase the stock of a product
   * @param id the id of the product that is to be increased
   * @param quantity the number that we want the stock to be increased by
   */
  void increaseStock(int id, int quantity);
}
