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

public class AccountServerImplementation implements AccountServer
{
  private final AccountModel accountModel;
  private Map<ClientCallBack, PropertyChangeListener> clients;

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

  private void onLoginReply(PropertyChangeEvent propertyChangeEvent)
  {

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
          if (evt.getPropertyName().equals("LoginSuccessful"))
            System.out.println("Successful Login");
        } catch (Exception e)
        {
          e.printStackTrace();
          accountModel.removeListener("LoginSuccessful", this);
          clients.remove(client);
        }
      }
    };
    clients.put(client, listener);
    accountModel.addListener("LoginSuccessful", listener);
  }

  @Override public void login(String username, String password) throws RemoteException
  {
    accountModel.login(username, password);
  }

  @Override public void addAccount(User user) throws RemoteException
  {
    accountModel.addAccount(user);
  }
}
