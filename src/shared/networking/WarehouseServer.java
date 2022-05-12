package shared.networking;

import shared.transferobjects.Product;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for server-side to handle everything with the warehouse.
 * @author S2G2
 * @version 1.0
 */
public interface WarehouseServer extends Remote
{

  /**
   * Adding product in through the model. The server passes the argument to the model.
   * @param product to be added
   * @return If adding is successful, return their User Object, if not, return null.
   * @throws RemoteException
   */
  Product addProduct(Product product) throws RemoteException;
}
