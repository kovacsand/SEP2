package client.networking;

import shared.networking.ClientCallBack;
import shared.networking.Server;
import shared.transferobjects.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * A class for RMIClient to establish connection to a server
 *
 * @author S2G2
 * @version 1.0
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

  /*@Override public void unregisterClient()
  {
    try
    {
      //server.unregisterClient(this);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }*/

  @Override public User login(String username, String password)
  {
    User loggedInUser = null;
    try
    {
      loggedInUser = server.login(username, password);
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
    catch(RemoteException e)
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

  @Override public Receipt finaliseSale(Sale sale, Salesperson salesperson)
  {
    return null;
  }

  @Override public Product addProductToBasket(Product product, int quantity)
  {
    return null;
  }

  @Override public Product removeProductFromBasket(Product product)
  {
    return null;
  }

  @Override public void onProductDataChange()
  {
    support.firePropertyChange("ProductDataChanged", 0,1);
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
