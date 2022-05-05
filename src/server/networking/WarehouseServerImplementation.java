package server.networking;

import server.model.AddProductModel;
import shared.networking.WarehouseServer;
import shared.transferobjects.Product;

import java.rmi.RemoteException;

public class WarehouseServerImplementation implements WarehouseServer
{
  private final AddProductModel addProductModel;

  public WarehouseServerImplementation(AddProductModel addProductModel)
  {
    this.addProductModel = addProductModel;
  }
  @Override public void addProduct(Product product) throws RemoteException
  {
    addProductModel.addProduct(product);
  }
}
