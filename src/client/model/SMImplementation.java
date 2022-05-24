package client.model;

import client.networking.Client;
import shared.transferobjects.Product;
import shared.transferobjects.Receipt;
import shared.transferobjects.Basket;
import shared.transferobjects.Salesperson;

public class SMImplementation implements SaleModel
{
  /**
   * Class that implements the SaleModel interface
   *
   * @author S2G2
   * @version 1.0
   */
  private Client client;

  /**
   * One-argument constructor initializing the SaleModelImplementation class
   * @param client object that will pass the necessary information
   */
  public SMImplementation(Client client)
  {
    this.client = client;
    client.startClient();
  }
  @Override public Receipt finaliseSale(Basket basket, Salesperson salesperson)
  {
    return client.finaliseSale(basket, salesperson);
  }

  @Override public Product addProductToBasket(Product product, int quantity)
  {
    return client.addProductToBasket(product, quantity);
  }

  @Override public Product removeProductFromBasket(Product product)
  {
    return client.removeProductFromBasket(product);
  }
}
