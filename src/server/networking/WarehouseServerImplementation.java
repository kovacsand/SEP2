package server.networking;

import server.model.ProductModel;
import shared.networking.WarehouseServer;
import shared.transferobjects.Product;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Implementation of the WarehouseServer interface
 * @author S2G2
 * @version 1.0
 */
public class WarehouseServerImplementation implements WarehouseServer
{
  private final ProductModel productModel;

  /**
   * One-argument constructor initializing the WarehouseServer implementation class.
   * @param productModel the model that will be used by the server.
   */
  public WarehouseServerImplementation(ProductModel productModel)
      throws RemoteException
  {
    UnicastRemoteObject.exportObject(this, 0);
    this.productModel = productModel;
  }

  @Override public Product addProduct(Product product) throws RemoteException
  {
    return productModel.addProduct(product);
  }

  @Override public ArrayList<Product> getAllProducts(char role) throws RemoteException
  {
    return productModel.getAllProducts(role);
  }

  @Override public void increaseStock(int id, int quantity) throws RemoteException
  {
    productModel.increaseStock(id, quantity);
  }
}
