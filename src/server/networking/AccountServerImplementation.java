package server.networking;

import server.model.AccountModel;
import shared.networking.AccountServer;
import shared.networking.ClientCallBack;
import shared.transferobjects.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the AccountServer interface
 * @author S2G2
 * @version 1.0
 */
public class AccountServerImplementation implements AccountServer
{
  private final AccountModel accountModel;
  private Map<ClientCallBack, PropertyChangeListener> clients;

  /**
   * One-argument constructor initializing the AccountServer implementation class.
   * @param accountModel the model that will be used by the server.
   */
  public AccountServerImplementation(AccountModel accountModel)
  {
    try
    {
      UnicastRemoteObject.exportObject(this, 0);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    this.accountModel = accountModel;
    clients = new HashMap<>();
  }

  @Override public void registerClient(ClientCallBack client)
      throws RemoteException
  {
    PropertyChangeListener listener = new PropertyChangeListener()
    {
      @Override public void propertyChange(PropertyChangeEvent evt)
      {
        try
        {
          //If we want to have Observer, or broadcasting
        } catch (Exception e)
        {
          e.printStackTrace();
          clients.remove(client);
        }
      }
    };
    clients.put(client, listener);
  }

  @Override public User login(String username, String password) throws RemoteException
  {
    return accountModel.login(username, password);
  }

  @Override public User addAccount(User user, String password) throws RemoteException
  {
    return accountModel.addAccount(user, password);
  }
}
