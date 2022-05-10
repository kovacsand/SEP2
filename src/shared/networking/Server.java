package shared.networking;

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
   * Handles the sub-server handling accounts using lazy instantiation.
   * @return the Account sub-server
   * @throws RemoteException
   */
  AccountServer getAccountServer() throws RemoteException;

  /**
   * Handles the sub-server handling the products and everything connected to the warehouse using lazy instantiation.
   * @return the Warehouse sub-server
   * @throws RemoteException
   */
  WarehouseServer getWarehouseServer() throws RemoteException;
}
