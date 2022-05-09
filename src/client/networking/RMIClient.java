package client.networking;

import shared.networking.AccountServer;
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

public class RMIClient implements Client, ClientCallBack
{
  private PropertyChangeSupport support;
  private Server server;
  private AccountServer accountServer;

  public RMIClient()
  {
    support = new PropertyChangeSupport(this);
  }

  @Override public void login(String username, String password)
  {
    try
    {
      server.getAccountServer().login(username, password);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void addProduct(Product product)
  {
    try
    {
      server.getWarehouseServer().addProduct(product);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void addAccount(User user)
  {
    try
    {
      accountServer.addAccount(user);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void registerClient()
  {
    try
    {
      UnicastRemoteObject.exportObject(this, 0);
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);
      server = (Server) registry.lookup("Server");
      server.registerClient(this);
      accountServer = server.getAccountServer();
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

  @Override public void loginReply(boolean successful, User user)
  {
    if(successful)
      support.firePropertyChange("LoginSuccessful", null, user);
    else
      support.firePropertyChange("LoginFailed", null, null);
  }

  @Override public void addAccountReply(boolean successful, String username)
  {
    if(successful)
      support.firePropertyChange("AccountAdded", null, username);
    else
      support.firePropertyChange("AccountExists", null, username);
  }

  @Override public void addProductReply(boolean successful, String name)
  {
    if(successful)
      support.firePropertyChange("ProductAdded", null, name);
    else
      support.firePropertyChange("ProductExists", null, name);
  }

  @Override public void addListener(String propertyName, PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(propertyName, listener);
  }

  @Override public void removeListener(String propertyName, PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(propertyName, listener);
  }
}
