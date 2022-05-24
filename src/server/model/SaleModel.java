package server.model;

import shared.transferobjects.Product;
import shared.transferobjects.Receipt;
import shared.transferobjects.Sale;
import shared.transferobjects.Salesperson;

public interface SaleModel
{
  //TODO whoever takes model write javadocs
  Receipt finaliseSale(Sale sale, Salesperson salesperson);
  Product addProductToBasket(Product product, int quantity);
  Product removeProductFromBasket(Product product);
}
