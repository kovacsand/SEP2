package server.networking;

import server.model.AMImplementation;
import server.model.APMImplementation;
import shared.networking.AccountServer;
import shared.networking.ClientCallBack;
import shared.networking.Server;
import shared.networking.WarehouseServer;
import shared.transferobjects.Product;
import shared.transferobjects.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the Server interface
 * @author S2G2
 * @version 1.0
 */
public class ServerImplementation implements Server
{
  private AccountServer accountServer;
  private WarehouseServer warehouseServer;
  private Map<ClientCallBack, PropertyChangeListener> clients;

  /**
   * Zero-argument constructor initializing the Server
   * @throws RemoteException
   */
  public ServerImplementation() throws RemoteException
  {
    UnicastRemoteObject.exportObject(this, 0);
    clients = new HashMap<>();
    accountServer = new AccountServerImplementation(new AMImplementation());
    warehouseServer = new WarehouseServerImplementation(new APMImplementation());
  }

  /**
   * Intended to be run before running the client. Creates registry and binds the server to it.
   * @throws RemoteException
   * @throws AlreadyBoundException
   */
  public void startServer() throws RemoteException, AlreadyBoundException
  {
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind("Server", this);
  }

  @Override public void registerClient(ClientCallBack client) throws RemoteException
  {
    accountServer.registerClient(client);
    PropertyChangeListener listener = new PropertyChangeListener()
    {
      @Override public void propertyChange(PropertyChangeEvent evt)
      {
        try
        {
          //TODO respond to client
        } catch (Exception e)
        {
          e.printStackTrace();
          clients.remove(client);
        }
      }
    };
    clients.put(client, listener);

  }

  @Override public void unregisterClient(ClientCallBack client) throws RemoteException
  {
    //!!TODO
  }

  @Override public User login(String username, String password) throws RemoteException
  {
    return accountServer.login(username, password);
  }

  @Override public User addAccount(User user, String password) throws RemoteException
  {
    return accountServer.addAccount(user, password);
  }

  @Override public Product addProduct(Product product) throws RemoteException
  {
    return warehouseServer.addProduct(product);
  }

}
