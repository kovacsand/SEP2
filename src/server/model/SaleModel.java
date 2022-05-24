package server.model;

import shared.transferobjects.Product;
import shared.transferobjects.Receipt;
import shared.transferobjects.Sale;
import shared.transferobjects.Salesperson;

import java.rmi.RemoteException;

public interface SaleModel
{
  //TODO whoever takes model write javadocs
  Receipt finaliseSale(Sale sale, Salesperson salesperson) throws RemoteException;
  Product addProductToBasket(Product product, int quantity) throws RemoteException;
  Product removeProductFromBasket(Product product) throws RemoteException;
}
