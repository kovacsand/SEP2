package shared.networking;

import shared.transferobjects.Product;
import shared.utils.Subject;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *Interface for networking part of the application. It is used get messages from the client to the client-side model.
 * @author S2G2
 * @version 1.0
 */
public interface ClientCallBack extends Remote, Serializable
{
  /**
   * Notifying the listeners if adding the product was successful
   * @param successful the boolean telling if the data update had been successful or not
   * @param name of the Account added
   * @throws RemoteException all methods of a class implementing Remote should throw this exception
   */
  void addProductReply(boolean successful, String name) throws RemoteException;

  /**
   * Notifying the listener by sending the product list
   * @param productList list of the Product objects
   * @throws RemoteException all methods of a class implementing Remote should throw this exception
   */
  void getAllProductsReply(ArrayList<Product> productList) throws RemoteException;

  /**
   * Notifying in the listener that the stocks had been increased.
   * @param successful the boolean telling if the data update had been successful or not
   * @throws RemoteException all methods of a class implementing Remote should throw this exception
   */
  void increaseStockReply(boolean successful) throws RemoteException;
}
