package server.networking;

import server.model.AddProductModel;
import shared.networking.WarehouseServer;
import shared.transferobjects.Product;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class WarehouseServerImplementation implements WarehouseServer
{
  private final AddProductModel addProductModel;

  public WarehouseServerImplementation(AddProductModel addProductModel)
      throws RemoteException
  {
    UnicastRemoteObject.exportObject(this, 0);
    this.addProductModel = addProductModel;
  }

  @Override public void addProduct(Product product) throws RemoteException
  {
    addProductModel.addProduct(product);
  }
}
