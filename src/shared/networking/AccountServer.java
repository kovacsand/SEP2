package shared.networking;

import shared.transferobjects.User;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AccountServer extends Remote
{
  void registerClient(ClientCallBack client) throws RemoteException;
  void login(String username, String password) throws RemoteException;
  void addAccount(User user) throws RemoteException;
}
