package server.networking;

import server.model.AccountModel;
import shared.networking.AccountServer;
import shared.transferobjects.User;

import java.rmi.RemoteException;

public class AccountServerImplementation implements AccountServer
{
  private final AccountModel accountModel;
  public AccountServerImplementation(AccountModel accountModel)
  {
    this.accountModel = accountModel;
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
