package server.networking;

import shared.transferobjects.Product;
import shared.transferobjects.Receipt;
import shared.transferobjects.Sale;
import shared.transferobjects.Salesperson;

public interface SaleServer
{
  Receipt finaliseSale(Sale sale, Salesperson salesperson);
  Product addProductToBasket(Product product, int quantity);
  Product removeProductFromBasket(Product product);
}
