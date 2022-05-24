package server.networking;

import server.model.SaleModel;
import shared.transferobjects.Product;
import shared.transferobjects.Receipt;
import shared.transferobjects.Sale;
import shared.transferobjects.Salesperson;

public class SaleServerImplementation implements SaleServer
{
  private final SaleModel saleModel;

  public SaleServerImplementation(SaleModel saleModel)
  {
    this.saleModel = saleModel;
  }

  @Override public Receipt finaliseSale(Sale sale, Salesperson salesperson)
  {
    return saleModel.finaliseSale(sale, salesperson);
  }

  @Override public Product addProductToBasket(Product product, int quantity)
  {
    return saleModel.addProductToBasket(product, quantity);
  }

  @Override public Product removeProductFromBasket(Product product)
  {
    return saleModel.removeProductFromBasket(product);
  }
}
