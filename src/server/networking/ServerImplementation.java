package server.networking;

import server.model.AMImplementation;
import server.model.PMImplementation;
import server.model.SMImplementation;
import shared.networking.ClientCallBack;
import shared.networking.Server;
import shared.transferobjects.*;

import java.beans.PropertyChangeSupport;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
 * Implementation of the Server interface
 * @author S2G2
 * @version 1.2
 */
public class ServerImplementation implements Server
{
  private final AccountServer accountServer;
  private final WarehouseServer warehouseServer;
  private final SaleServer saleServer;
  private Set<ClientCallBack> clients;
  private PropertyChangeSupport support;

  /**
   * Zero-argument constructor initializing the Server and the Sub-Servers
   * @throws RemoteException all methods of a class implementing Remote should throw this exception
   */
  public ServerImplementation() throws RemoteException
  {
    UnicastRemoteObject.exportObject(this, 0);
    clients = new HashSet<>();
    accountServer = new AccountServerImplementation(new AMImplementation());
    warehouseServer = new WarehouseServerImplementation(new PMImplementation());
    saleServer = new SaleServerImplementation(new SMImplementation());
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
    Product changedProduct = warehouseServer.changeStock(id, quantity);
    if (changedProduct != null)
      onProductChange();
    return changedProduct;
  }

  @Override public Receipt finaliseSale(Basket basket, Salesperson salesperson) throws RemoteException
  {
    return saleServer.finaliseSale(basket, salesperson);
  }

  @Override public Product addProductToBasket(Product product, int quantity) throws RemoteException
  {
    Product changedProduct = saleServer.addProductToBasket(product, quantity);
    if (changedProduct != null)
      onProductChange();
    return changedProduct;
  }

  @Override public Product removeProductFromBasket(Product product) throws RemoteException
  {
    Product changedProduct = saleServer.removeProductFromBasket(product);
    if (changedProduct != null)
      onProductChange();
    return changedProduct;
  }

  /**
   * Method that calls on all the clients who are looking at the stock view (clients)
   * and calls their onProductDataChange methods.
   */
  private void onProductChange()
  {
    for (ClientCallBack client: clients)
    {
      try
      {
        client.onProductDataChange();
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }
    }
  }

  @Override public void registerStockViewer(ClientCallBack client)
  {
    clients.add(client);
  }

  @Override public void deregisterStockViewer(ClientCallBack client)
  {
    clients.remove(client);
  }
}
