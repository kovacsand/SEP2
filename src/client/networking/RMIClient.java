package client.networking;

import shared.networking.ClientCallBack;
import shared.networking.Server;
import shared.transferobjects.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A class for RMIClient to establish connection to a server
 * @author S2G2
 * @version 1.3
 */
public class RMIClient implements Client, ClientCallBack
{
  private final PropertyChangeSupport support;
  private Server server;

  /**
   * No-argument constructor initializing the RMIClient and PropertyChangeSupport object
   */
  public RMIClient()
  {
    support = new PropertyChangeSupport(this);
    startClient();
  }

  @Override public void startClient()
  {
    try
    {
      UnicastRemoteObject.exportObject(this, 0);
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);
      server = (Server) registry.lookup("Server");
      System.out.println("The client is running");
    }
    catch (RemoteException | NotBoundException e)
    {
      e.printStackTrace();
    }
  }

  @Override public User login(String username, String password)
  {
    User loggedInUser = null;
    try
    {
      loggedInUser = server.login(username, password);
      if (loggedInUser != null)
        System.out.println("User logged in as: " + loggedInUser.getUsername());
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return loggedInUser;
  }

  @Override public User addAccount(User user, String password)
  {
    User newlyAddedUser = null;
    try
    {
      newlyAddedUser = server.addAccount(user, password);
      if (newlyAddedUser != null)
        System.out.println("New Account added: " + newlyAddedUser.getUsername());
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return newlyAddedUser;
  }

  @Override public Product addProduct(Product product)
  {
    Product newlyAddedProduct = null;
    try
    {
      newlyAddedProduct = server.addProduct(product);
      if (newlyAddedProduct != null)
        System.out.println("New Product added: " + newlyAddedProduct.getName());
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return newlyAddedProduct;
  }

  @Override public ArrayList<Product> getAllProducts(char role)
  {
    ArrayList<Product> products = new ArrayList<>();
    try
    {
      products = server.getAllProducts(role);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return products;
  }

  @Override public Product changeStock(int id, int quantity)
  {
    Product product = null;
    try
    {
      product = server.changeStock(this, id, quantity);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return product;
  }

  @Override public void registerStockViewer()
  {
    try
    {
      server.registerStockViewer(this);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void deregisterStockViewer()
  {
    try
    {
      server.deregisterStockViewer(this);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public Receipt finaliseSale(Basket basket, Salesperson salesperson)
  {

    Receipt receipt = null;

    try
    {
      receipt = server.finaliseSale(basket, salesperson);
      if (receipt != null)
        System.out.println("New Receipt generated");
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return receipt;
  }

  @Override public Product addProductToBasket(Product product, int quantity, boolean alreadyInBasket)
  {
    Product soldProduct = null;
    try
    {
      soldProduct = server.addProductToBasket(product, quantity, alreadyInBasket);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return soldProduct;
  }

  @Override public Product removeProductFromBasket(Product product)
  {

    Product returnedProduct = null;
    try
    {
      returnedProduct = server.removeProductFromBasket(product);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return returnedProduct;
  }

  @Override public Product removeProduct(Product product)
  {
    Product removedProduct = null;
    try
    {
      removedProduct = server.removeProduct(product);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return removedProduct;
  }

  @Override public ArrayList<Receipt> getAllReceipts()
  {
    ArrayList<Receipt> allReceipts = null;
    try
    {
      allReceipts = server.getAllReceipts();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return allReceipts;
  }


  @Override public double generateIncome(LocalDateTime startDate,
      LocalDateTime endDate)
  {
    double income = 0;
    try
    {
      income = server.generateIncome(startDate, endDate);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return income;
  }

  @Override public void onProductDataChange()
  {
    support.firePropertyChange("ProductDataChanged", 0, 1);
  }

  @Override public void addListener(String propertyName,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(propertyName, listener);
  }

  @Override public void removeListener(String propertyName,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(propertyName, listener);
  }
}