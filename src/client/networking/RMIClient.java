package client.networking;

import shared.networking.ClientCallBack;
import shared.networking.Server;
import shared.transferobjects.Product;
import shared.transferobjects.User;

import java.beans.PropertyChangeSupport;

public class RMIClient implements Client, ClientCallBack
{
  private PropertyChangeSupport support;
  private Server server;

  public RMIClient()
  {
    support = new PropertyChangeSupport(this);
  }

  @Override public void login(String username, String password)
  {

  }

  @Override public void addProduct(Product product)
  {

  }

  @Override public void addAccount(User user)
  {

  }

  @Override public void registerClient()
  {

  }

  @Override public void unregisterClient()
  {

  }

  @Override public void loginReply(boolean successful, User user)
  {

  }

  @Override public void addAccountReply(boolean successful, String username)
  {

  }

  @Override public void addProductReply(boolean successful, String name)
  {

  }
}
