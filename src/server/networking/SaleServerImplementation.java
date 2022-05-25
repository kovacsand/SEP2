package server.networking;

import server.model.SaleModel;
import shared.transferobjects.Product;
import shared.transferobjects.Receipt;
import shared.transferobjects.Basket;
import shared.transferobjects.Salesperson;

public class SaleServerImplementation implements SaleServer
{
  private final SaleModel saleModel;

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
