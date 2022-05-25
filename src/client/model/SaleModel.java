package client.model;

import shared.transferobjects.Product;
import shared.transferobjects.Receipt;
import shared.transferobjects.Basket;
import shared.transferobjects.Salesperson;

public interface SaleModel
{
  /**
   * Finalising a sale, when a salesperson is making a sale which will call Client's method of finalising sale to contact the server and later database
   * @param basket object that is created while doing the sale in GUI
   * @param salesperson object of the person who is making the sale
   * @return receipt of the sale that is auto-generated when sale is successfully created
   */
  Receipt finaliseSale(Basket basket, Salesperson salesperson);

  /**
   * Adding a product to the basket to 'reserve it', so others can not sell it
   * @param product object that is being added to the basket
   * @param quantity amount of the product that is being added to the basket
   * @param alreadyInBasket boolean to determine if the product had already been in the local basket
   * @return the product object that has been added
   */
  Product addProductToBasket(Product product, int quantity, boolean alreadyInBasket);
  /**
   * Removing a reserved product from the basket
   * @param product object that is being removed from the basket
   * @return product object that has been removed
   */
  Product removeProductFromBasket(Product product);
}
