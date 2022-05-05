package shared.networking;

import shared.transferobjects.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AccountServer extends Remote
{
  void login(String username, String password) throws RemoteException;
  void addAccount(User user) throws RemoteException;
}
