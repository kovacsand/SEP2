package shared.networking;

import shared.transferobjects.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Interface for the RMI server. It ensures, that the connection is established between the client(s) and the server.
 * @author S2G2
 * @version 1.0
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
   * @param sale the sale
   * @param salesperson the person making the sale
   * @return the newly generated Receipt
   * @throws RemoteException all methods of a class implementing Remote should throw this exception
   */
  Receipt finaliseSale(Sale sale, Salesperson salesperson) throws RemoteException;

  /**
   * Adding a product to a basket, which changes the stock of the product
   * @param product to be changed
   * @param quantity to be added to the basket
   * @return the Product with the new quantity
   * @throws RemoteException all methods of a class implementing Remote should throw this exception
   */
  Product addProductToBasket(Product product, int quantity) throws RemoteException;

  /**
   * Adding a product to a basket, which changes the stock of the product
   * @param product to be changed
   * @return the Product with the new quantity
   * @throws RemoteException all methods of a class implementing Remote should throw this exception
   */
  Product removeProductFromBasket(Product product) throws RemoteException;

  //TODO javadocs for these two cutie bunnies
  void registerStockViewer(ClientCallBack client) throws RemoteException;
  void deregisterStockViewer(ClientCallBack client) throws RemoteException;
}
