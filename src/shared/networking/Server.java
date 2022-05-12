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

  User login(String username, String password) throws RemoteException;

  User addAccount(User user, String password) throws RemoteException;

  Product addProduct(Product product) throws RemoteException;

}
