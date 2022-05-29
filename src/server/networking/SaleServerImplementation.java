package server.networking;

import server.model.SaleModel;
import shared.transferobjects.Product;
import shared.transferobjects.Receipt;
import shared.transferobjects.Basket;
import shared.transferobjects.Salesperson;

/**
 * Implementation of the SaleServer interface
 * @author S2G2
 * @version 1.0
 */
public class SaleServerImplementation implements SaleServer
{
  private final SaleModel saleModel;

  /**
   * One-argument constructor initializing the WarehouseServer implementation class.
   * @param saleModel the model that will be used by the server.
   */
  public SaleServerImplementation(SaleModel saleModel)
  {
    this.saleModel = saleModel;
  }

  @Override public Receipt finaliseSale(Basket basket, Salesperson salesperson)
  {
    return saleModel.finaliseSale(basket, salesperson);
  }

  @Override public Product addProductToBasket(Product product, int quantity, boolean alreadyInBasket)
  {
    return saleModel.addProductToBasket(product, quantity, alreadyInBasket);
  }

  @Override public Product removeProductFromBasket(Product product)
  {
    return saleModel.removeProductFromBasket(product);
  }
}