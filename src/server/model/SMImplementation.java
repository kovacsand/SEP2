package server.model;

import server.model.mediator.ProductDAOImplementation;
import server.model.mediator.SaleDAOImplementation;
import shared.transferobjects.Product;
import shared.transferobjects.Receipt;
import shared.transferobjects.Basket;
import shared.transferobjects.Salesperson;

import java.sql.SQLException;

/**
 * Class that implements the SaleModel interface on server side.
 * @author S2G2
 * @version 1.0
 */
public class SMImplementation implements SaleModel
{
  @Override public Receipt finaliseSale(Basket basket, Salesperson salesperson)
  {
    basket.getProducts().forEach((product, quantity) -> {
      try
      {
        ProductDAOImplementation.getInstance().decreaseInBaskets(product);
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    });

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
      changedProductQuantity = ProductDAOImplementation.getInstance().changeStock(product.getId(), product.getQuantity());
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

    return ( (changedProductBasket != null && changedProductQuantity != null) ? changedProductQuantity : null);
  }
}