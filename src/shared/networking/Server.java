package shared.networking;

import shared.transferobjects.Product;
import shared.transferobjects.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for the RMI server. It ensures, that the connection is established between the client(s) and the server.
 * @author S2G2
 * @version 1.0
 */
public interface Server extends Remote
{
  /**
   * Establishes connections between the server and the client.
   * @param client
   * @throws RemoteException
   */
  void registerClient(ClientCallBack client) throws RemoteException;

  /**
   * Disconnects the client from the server.
   * @param client
   * @throws RemoteException
   */
  void unregisterClient(ClientCallBack client) throws RemoteException;
  /**
   * Logging in through the model. The server passes the arguments to the model.
   * @param username of the user
   * @param password of the user
   * @return If the login is successful, return their User Object, if not, return null.
   * @throws RemoteException
   */
  User login(String username, String password) throws RemoteException;

  /**
   * Adding an account through the model
   * @param user of the new account
   * @param password of the new account
   * @return the newly added account, if couldn't add, return null
   * @throws RemoteException
   */
  User addAccount(User user, String password) throws RemoteException;

  /**
   * Adding product in through the model. The server passes the argument to the model.
   * @param product to be added
   * @return If adding is successful, return their User Object, if not, return null.
   * @throws RemoteException
   */
  Product addProduct(Product product) throws RemoteException;

}
