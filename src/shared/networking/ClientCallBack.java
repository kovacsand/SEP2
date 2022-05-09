package shared.networking;

import shared.transferobjects.User;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallBack extends Remote, Serializable
{
  /**
 * Notifying the listeners if the login reply is successful
 * @param successful if the login is successful
 * @param user object that is logging in
   * @throws RemoteException
 */
  void loginReply(boolean successful, User user) throws RemoteException;

  /**
   * Notifying the listeners if adding the account was successful
   * @param successful if the adding is successful
   * @param username of the account that is being added
   * @throws RemoteException
   */
  void addAccountReply(boolean successful, String username) throws RemoteException;

  /**
   * Notifying the listeners if adding the product was successful
   * @param successful
   * @param name
   * @throws RemoteException
   */
  void addProductReply(boolean successful, String name) throws RemoteException;
}
