package client.networking;

import shared.networking.ClientCallBack;
import shared.networking.Server;
import shared.transferobjects.Product;
import shared.transferobjects.User;

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

  @Override public void login(String username, String password)
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

    if (loggedInUser == null)
      loginReply(false, null);
    else
      loginReply(true, loggedInUser);
  }

  @Override public void addProduct(Product product)
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

    if (newlyAddedProduct == null)
      addProductReply(false, null);
    else
      addProductReply(true, newlyAddedProduct.getName());
  }

  @Override public void addAccount(User user, String password)
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

    if (newlyAddedUser == null)
      addAccountReply(false, null);
    else
      addAccountReply(true, newlyAddedUser.getUsername());
  }

  @Override public void registerClient()
  {
    try
    {
      UnicastRemoteObject.exportObject(this, 0);
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);
      server = (Server) registry.lookup("Server");
      server.registerClient(this);
    }
    catch (RemoteException | NotBoundException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void unregisterClient()
  {
    try
    {
      server.unregisterClient(this);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void getAllProducts(char role)
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
    onGetAllProductsReply(products != null, products);
  }

  private void onGetAllProductsReply(boolean successful, ArrayList<Product> products)
  {
    support.firePropertyChange("GetProducts", null, products);
  }

  @Override public void increaseStock(int id, int quantity)
  {
    try
    {
      server.increaseStock(this, id, quantity);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void loginReply(boolean successful, User user)
  {
    if (successful)
      support.firePropertyChange("LoginSuccessful", null, user);
    else
      support.firePropertyChange("LoginFailed", null, null);
  }

  @Override public void addAccountReply(boolean successful, String username)
  {
    if (successful)
      support.firePropertyChange("AccountAdded", null, username);
    else
      support.firePropertyChange("AccountExists", null, username);
  }

  @Override public void addProductReply(boolean successful, String name)
  {
    if (successful)
      support.firePropertyChange("ProductAdded", null, name);
    else
      support.firePropertyChange("ProductExists", null, name);
  }

  @Override public void getAllProductsReply(ArrayList<Product> productList) throws RemoteException
  {
    if (!(productList.isEmpty()))
      support.firePropertyChange("GetProducts",null,productList);
    else
      support.firePropertyChange("GetProducts",null,new Product("No Products","No Descriptions",0));
  }

  @Override public void increaseStockReply(boolean successful)
  {
    System.out.println(successful ? "stock increased" : "problem has arisen");
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
