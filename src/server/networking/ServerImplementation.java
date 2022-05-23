package server.networking;

import com.sun.javafx.property.adapter.PropertyDescriptor;
import server.model.AMImplementation;
import server.model.PMImplementation;
import shared.networking.ClientCallBack;
import shared.networking.Server;
import shared.transferobjects.Product;
import shared.transferobjects.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.http.WebSocket;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the Server interface
 * @author S2G2
 * @version 1.0
 */
public class ServerImplementation implements Server
{
  private final AccountServer accountServer;
  private final WarehouseServer warehouseServer;
  private Map<ClientCallBack, PropertyChangeListener> clients;
  private PropertyChangeSupport support;

  /**
   * Zero-argument constructor initializing the Server and the Sub-Servers
   * @throws RemoteException all methods of a class implementing Remote should throw this exception
   */
  public ServerImplementation() throws RemoteException
  {
    UnicastRemoteObject.exportObject(this, 0);
    clients = new HashMap<>();
    accountServer = new AccountServerImplementation(new AMImplementation());
    warehouseServer = new WarehouseServerImplementation(new PMImplementation());
    support = new PropertyChangeSupport(this);
  }

  /**
   * Intended to be run before running the client. Creates registry and binds the server to it.
   * @throws RemoteException all methods of a class implementing Remote should throw this exception
   * @throws AlreadyBoundException the server can only be bound to one registry
   */
  public void startServer() throws RemoteException, AlreadyBoundException
  {
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind("Server", this);
    System.out.println("The server is running");
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

    Product newProduct= warehouseServer.addProduct(product);
    if(newProduct!=null)
      onProductChange();
    return newProduct;
  }

  @Override public ArrayList<Product> getAllProducts(char role) throws RemoteException
  {
    return warehouseServer.getAllProducts(role);
  }

  @Override public Product changeStock(ClientCallBack client, int id, int quantity) throws RemoteException
  {
    onProductChange();
    return warehouseServer.changeStock(id, quantity);
  }

  private void onProductChange()
  {
    for (Map.Entry<ClientCallBack, PropertyChangeListener> set: clients.entrySet())
    {
      try
      {
        set.getKey().onProductDataChange();
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }
    }
  }

  @Override public void registerStockViewer(ClientCallBack client)
  {
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
    System.out.println("Client Added: " + client.toString());
    clients.put(client,listener);
  }

  @Override public void deregisterStockViewer(ClientCallBack client)
  {
    System.out.println("Client Removed: " + client.toString() );
    clients.remove(client);
  }

}
