package server.networking;

import shared.transferobjects.Product;
import shared.transferobjects.Receipt;
import shared.transferobjects.Sale;
import shared.transferobjects.Salesperson;

/**
 * Interface for server-side to handle everything with the warehouse.
 * @author S2G2
 * @version 1.0
 */
public interface SaleServer
{
  /**
   * Finalising a sale of one or several products. The sub-server calls the method to execute the process.
   * @param sale the sale
   * @param salesperson the person making the sale
   * @return the newly generated Receipt
   */
  Receipt finaliseSale(Sale sale, Salesperson salesperson);

  /**
   * Adding a product to a basket, which changes the stock of the product. The sub-server calls the method to execute the process.
   * @param product to be changed
   * @param quantity to be added to the basket
   * @return the Product with the new quantity
   */
  Product addProductToBasket(Product product, int quantity);

  /**
   * Adding a product to a basket, which changes the stock of the product. The sub-server calls the method to execute the process.
   * @param product to be changed
   * @return the Product with the new quantity
   */
  Product removeProductFromBasket(Product product);
}
