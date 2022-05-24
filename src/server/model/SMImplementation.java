package server.model;

import server.model.mediator.ProductDAOImplementation;
import server.model.mediator.SaleDAOImplementation;
import shared.transferobjects.Product;
import shared.transferobjects.Receipt;
import shared.transferobjects.Basket;
import shared.transferobjects.Salesperson;

import java.sql.SQLException;

public class SMImplementation implements SaleModel
{
  public SMImplementation()
  {

  }

  @Override public Receipt finaliseSale(Basket basket, Salesperson salesperson)
  {
    Receipt newReceipt = null;
    try
    {
      newReceipt = SaleDAOImplementation.getInstance().addSale(basket, salesperson);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return newReceipt;
  }

  @Override public Product addProductToBasket(Product product, int quantity)
  {
    Product changedProduct = null;
    try
    {
      changedProduct = ProductDAOImplementation.getInstance().changeStock(product.getId(), -quantity);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return changedProduct;
  }

  @Override public Product removeProductFromBasket(Product product)
  {
    Product changedProduct = null;
    try
    {
      changedProduct = ProductDAOImplementation.getInstance().changeStock(product.getId(), product.getQuantity());
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return changedProduct;
  }
}
