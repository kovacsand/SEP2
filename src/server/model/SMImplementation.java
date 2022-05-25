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

  @Override public Product addProductToBasket(Product product, int quantity, boolean alreadyInBasket)
  {
    Product changedProductBasket = null;
    if (!alreadyInBasket)
    {
      try
      {
        changedProductBasket = ProductDAOImplementation.getInstance().increaseInBaskets(product);
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }

    Product changedProductQuantity = null;
    try
    {
      changedProductQuantity = ProductDAOImplementation.getInstance().changeStock(product.getId(), -quantity);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return ( (changedProductBasket != null && changedProductQuantity != null) ? changedProductQuantity : null);
  }

  @Override public Product removeProductFromBasket(Product product)
  {
    Product changedProductBasket = null;
    try
    {
      changedProductBasket = ProductDAOImplementation.getInstance().decreaseInBaskets(product);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

    Product changedProductQuantity = null;
    try
    {
      System.out.println(product.getQuantity());
      changedProductQuantity = ProductDAOImplementation.getInstance().changeStock(product.getId(), product.getQuantity());
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

    return ( (changedProductBasket != null && changedProductQuantity != null) ? changedProductQuantity : null);
  }
}
