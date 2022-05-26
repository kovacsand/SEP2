package shared.networking;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *Interface for networking part of the application. It is used get messages from the client to the client-side model.
 * @author S2G2
 * @version 1.1
 */
public interface ClientCallBack extends Remote, Serializable
{
  /**
   * Method to tell the client that the Product table in the Database had been updated
   * @throws RemoteException all methods of a class implementing Remote should throw this exception
   */
  void onProductDataChange() throws RemoteException;
}
