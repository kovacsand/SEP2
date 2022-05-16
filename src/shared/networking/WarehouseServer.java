package shared.networking;

import shared.transferobjects.Product;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Interface for server-side to handle everything with the warehouse.
 * @author S2G2
 * @version 1.0
 */
public interface WarehouseServer extends Remote
{
  /**
 * Adding product in through the model. The server passes the argument to the model.
 * @param product to be added
 * @return If adding is successful, return their User Object, if not, return null.
 * @throws RemoteException
 */
  Product addProduct(Product product) throws RemoteException;

  /**
   * Getting all products through the model. The server passes the argument to the model
   * @param role of the user, to know which products list to return depending on the role
   * @return
   * @throws RemoteException
   */
  ArrayList<Product> getAllProducts(char role) throws  RemoteException;

  /**
   * Increasing the stock of the product through the model. The server passes the argument to the model
   * @param id of the product
   * @param quantity amount which needs to be increased by
   * @throws RemoteException
   */
  void increaseStock(int id, int quantity) throws RemoteException;
}
