package shared.networking;

import shared.transferobjects.Product;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface WarehouseServer extends Remote
{
  Product addProduct(Product product) throws RemoteException;
}
