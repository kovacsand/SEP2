package server.networking;

import server.model.AddProductModel;
import shared.networking.WarehouseServer;
import shared.transferobjects.Product;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Implementation of the WarehouseServer interface
 * @author S2G2
 * @version 1.0
 */
public class WarehouseServerImplementation implements WarehouseServer
{
  private final AddProductModel addProductModel;

  /**
   * One-argument constructor initializing the WarehouseServer implementation class.
   * @param addProductModel the model that will be used by the server.
   */
  public WarehouseServerImplementation(AddProductModel addProductModel)
      throws RemoteException
  {
    UnicastRemoteObject.exportObject(this, 0);
    this.addProductModel = addProductModel;
  }

  @Override public Product addProduct(Product product) throws RemoteException
  {
    return addProductModel.addProduct(product);
  }
}
