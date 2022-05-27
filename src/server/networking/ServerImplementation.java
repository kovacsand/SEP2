package server.networking;

import server.model.AMImplementation;
import server.model.PMImplementation;
import server.model.RMImplementation;
import server.model.SMImplementation;
import shared.networking.ClientCallBack;
import shared.networking.Server;
import shared.transferobjects.*;

import java.beans.PropertyChangeSupport;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Implementation of the Server interface
 * @author S2G2
 * @version 1.4
 */
public class ServerImplementation implements Server
{
  private final AccountServer accountServer;
  private final WarehouseServer warehouseServer;
  private final SaleServer saleServer;
  private final ReceiptServer receiptServer;
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
    receiptServer = new ReceiptSeverImplementation(new RMImplementation());
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

  public void stopServer() throws RemoteException, NotBoundException
  {
    LocateRegistry.getRegistry(1099).unbind("Server");
    System.out.println("The server stopped");
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
    if(newProduct != null)
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
    Receipt newReceipt = saleServer.finaliseSale(basket, salesperson);
    if (newReceipt != null)
      onProductChange();
    return newReceipt;
  }

  @Override public Product addProductToBasket(Product product, int quantity, boolean alreadyInBasket) throws RemoteException
  {
    Product changedProduct = saleServer.addProductToBasket(product, quantity, alreadyInBasket);
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

  @Override public Product removeProduct(Product product) throws RemoteException
  {
    Product removedProduct = warehouseServer.removeProduct(product);
    if (removedProduct != null)
      onProductChange();
    return removedProduct;
  }

  @Override public ArrayList<Receipt> getAllReceipts() throws RemoteException
  {
    return receiptServer.getAllReceipts();
  }


  @Override public double generateIncome(LocalDateTime startDate,
      LocalDateTime endDate)
  {
    return receiptServer.generateIncome(startDate, endDate);
  }
}
