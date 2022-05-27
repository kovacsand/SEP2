package shared.networking;

import shared.transferobjects.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Interface for the RMI server. It ensures, that the connection is established between the client(s) and the server.
 * @author S2G2
 * @version 1.4
 */
public interface Server extends Remote
{
  /**
   * Logging in through the model. The server passes the arguments to the model.
   * @param username of the user
   * @param password of the user
   * @return If the login is successful, return their User Object, if not, return null.
   * @throws RemoteException all methods of a class implementing Remote should throw this exception
   */
  User login(String username, String password) throws RemoteException;

  /**
   * Adding an account through the model
   * @param user of the new account
   * @param password of the new account
   * @return the newly added account, if the server couldn't add it, return null
   * @throws RemoteException all methods of a class implementing Remote should throw this exception
   */
  User addAccount(User user, String password) throws RemoteException;

  /**
   * Adding product in through the model. The server passes the argument to the model.
   * @param product to be added
   * @return If adding is successful, return their User Object, if not, return null.
   * @throws RemoteException all methods of a class implementing Remote should throw this exception
   */
  Product addProduct(Product product) throws RemoteException;
  /**
   * Getting all products through the model. The server passes the argument to the model
   * @param role of the user, to know which products list to return depending on the role
   * @return an ArrayList<Product> of all the products on the database
   * @throws RemoteException all methods of a class implementing Remote should throw this exception
   */
  ArrayList<Product> getAllProducts(char role) throws  RemoteException;

  /**
   * Increasing the stock of the product through the model. The server passes the argument to the model
   * @param id of the product
   * @param quantity amount which needs to be increased by
   * @throws RemoteException all methods of a class implementing Remote should throw this exception
   */
  Product changeStock(ClientCallBack client, int id, int quantity) throws RemoteException;

  /**
   * Finalising a sale of one or several products. The server has the sale as the argument and the salesperson making the sale.
   * @param basket the sale
   * @param salesperson the person making the sale
   * @return the newly generated Receipt
   * @throws RemoteException all methods of a class implementing Remote should throw this exception
   */
  Receipt finaliseSale(Basket basket, Salesperson salesperson) throws RemoteException;

  /**
   * Adding a product to a basket, which changes the stock of the product
   * @param product to be changed
   * @param quantity to be added to the basket
   * @param alreadyInBasket boolean to determine if the product had already been in the local basket
   * @return the Product with the new quantity
   * @throws RemoteException all methods of a class implementing Remote should throw this exception
   */
  Product addProductToBasket(Product product, int quantity, boolean alreadyInBasket) throws RemoteException;

  /**
   * Adding a product to a basket, which changes the stock of the product
   * @param product to be changed
   * @return the Product with the new quantity
   * @throws RemoteException all methods of a class implementing Remote should throw this exception
   */
  Product removeProductFromBasket(Product product) throws RemoteException;

  /**
   * Call the method on the SaleServer, passing itself as a ClientCallBack object to
   * be added as looking at the stock view
   * @throws RemoteException all methods of a class implementing remote should throw this exception
   */
  void registerStockViewer(ClientCallBack client) throws RemoteException;

  /**
   * Calls the method on the SaleServer,passing itself as a ClientCallBack object to
   * be removed as looking at the stock view
   * @param client the user as a client object
   * @throws RemoteException all methods of a class implementing remote should throw this exception
   */
  void deregisterStockViewer(ClientCallBack client) throws RemoteException;

  /**
   * Calls the method in Warehouse Server to remove a product from the database
   * @param product the product object that should be removed
   * @returns the removed product, null if unsuccessful
   * @throws RemoteException all methods of a class implementing remote should throw this exception
   * */
  Product removeProduct(Product product) throws RemoteException;

  /**
   * Calls the method in the ReceiptServer to return all the receipts as an ArrayList of Receipt objects
   * @return ArrayList<Receipt>
   * @throws RemoteException all methods of a class implementing remote should throw this exception
   */
  ArrayList<Receipt> getAllReceipts() throws RemoteException;

  /**
   * Calls generateIncome method in the ReceiptServer to get the income in the given timeframe
   * @param startDate starting date of the given timeframe
   * @param endDate end date of the given timeframe
   * @return total income
   */
  double generateIncome(LocalDateTime startDate, LocalDateTime endDate) throws RemoteException;
}
