package client.networking;

import shared.transferobjects.*;
import shared.utils.Subject;

import java.util.ArrayList;

/**
 * Interface for Client using RMI and Observer Pattern
 *
 * @author S2G2
 * @version 1.2
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

  /**
   * Call the method on the server, passing itself as a ClientCallBack object to
   * be added as looking at the stock view
   */
  void registerStockViewer();

  /**
   * Calls the method on the server,passing itself as a ClientCallBack object to
   * be removed as looking at the stock view
   */
  void deregisterStockViewer();

  /**
   * Calls the method on the server to insert the sale and the receipt
   * @param basket the Sale transferObject with products, quantities,
   * @param salesperson the Salesperson who is making this sale
   * @return Receipt object containing the sale
   */
  Receipt finaliseSale(Basket basket, Salesperson salesperson);

  /**
   * Calls the method in the server to reduce the available stock of a product
   * and add it to a sale
   * @param product the selected product to edit
   * @param quantity the amount to reduce the quantity by
   * @param alreadyInBasket boolean to determine if the product had already been in the local basket
   * @return the product with the quantity to be added to the sale
   */
  Product addProductToBasket(Product product, int quantity, boolean alreadyInBasket);

  /**
   * Calls the method in the server to remove the product from the basket
   * @param product the product to be removed from the basket
   * @return the product that has been removed from the basket and returned to
   * the stock
   */
  Product removeProductFromBasket(Product product);

  /**
   * Calls the method in the server to remove a product from the database
   * @param product the product to be removed
   * @return the removed product if successful, null if unsuccessful
   */
  Product removeProduct(Product product);

  /**
   * Calls the method to get an arraylist of all the receipt as receipt objects
   * @return all receipts as an ArrayList<Receipt>
   */
  ArrayList<Receipt> getAllReceipts();

  /**
   * Calls the method on the server to get the details of one receipt
   * @param receiptID the id of the request receipt
   * @return the requested receipt object, null if unsuccessful
   */
  Receipt getReceiptDetails(int receiptID);
}
