package client.networking;

import shared.transferobjects.*;
import shared.utils.Subject;

import java.beans.PropertyChangeEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Interface for Client using RMI and Observer Pattern
 *
 * @author S2G2
 * @version 1.1
 */
public interface Client extends Subject
{
  /**
   * Starting the client and connecting to the server
   */
  void startClient();

  /**
   * Unregistering the client from the server when disconnecting
   */
 // void unregisterClient();

  /**
   * User logging into the account
   *
   * @param username of the user
   * @param password of the user
   * @return the logged-in User object, null, if logging in was unsuccessful
   */
  User login(String username, String password);

  /**
   * Adding an account to the database
   *
   * @param user object that needs to be added
   */
  User addAccount(User user, String password);

  /**
   * Adding a product to the database
   * @param product object that needs to be added
   */
  Product addProduct(Product product);

  /**
   * Requests a list of all products from the Database
   *
   * @param role gets the products based on role: M = manager, S = salesperson
   */
  ArrayList<Product> getAllProducts(char role);

  /**
   * Calls the method on the server to increase the stock of a product
   *
   * @param id       the id of the product that is to be increased
   * @param quantity the number that we want the stock to be increased by
   */
  Product changeStock(int id, int quantity);

  //TODO javadocs for these five stud muffins
  void registerStockViewer();
  void deregisterStockViewer();

  Product addProductToBasket(Product product, int quantity);
  Product removeProductFromBasket(Product product);

  /**
   * Calls the method on the server to insert the sale and the receipt
   * @param sale the Sale transferObject with products, quantities,
   * @param salesperson the Salesperson who is making this sale
   * @return Receipt object containing the sale
   */
  Receipt finaliseSale(Sale sale, Salesperson salesperson);

}
