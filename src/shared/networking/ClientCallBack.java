package shared.networking;

import shared.transferobjects.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallBack extends Remote
{
  void loginReply(boolean successful, User user) throws RemoteException;
  void addAccountReply(boolean successful, String username) throws RemoteException;
  void addProductReply(boolean successful, String name) throws RemoteException;
}
