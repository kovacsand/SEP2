package shared.networking;

import shared.transferobjects.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for server-side to handle everything with the accounts.
 * @author S2G2
 * @version 1.0
 */
public interface AccountServer extends Remote
{
  void registerClient(ClientCallBack client) throws RemoteException;

  /**
   * Logging in through the model. The server passes the arguments to the model.
   * @param username of the user
   * @param password of the user
   * @return If the login is successful, return their User Object, if not, return null.
   * @throws RemoteException
   */
  User login(String username, String password) throws RemoteException;
  void addAccount(User user, String password) throws RemoteException;
}
