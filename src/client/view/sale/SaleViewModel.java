package client.view.sale;

import client.model.ProductModel;
import client.model.SaleModel;
import shared.transferobjects.Basket;
import shared.transferobjects.Product;
import shared.transferobjects.Receipt;
import shared.transferobjects.Salesperson;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class SaleViewModel
{
  private final SaleModel saleModel;
  private final ProductModel productModel;
  private final PropertyChangeSupport support;
  public SaleViewModel(SaleModel saleModel, ProductModel productModel)
  {
    this.saleModel=saleModel;
    this.productModel=productModel;
    support=new PropertyChangeSupport(this);
  }

  public Receipt finaliseSale(Basket basket, Salesperson salesperson)
  {
    return saleModel.finaliseSale(basket,salesperson);
  }

  public Product addProductToBasket(Product product,int quantity, boolean alreadyInBasket)
  {
    return saleModel.addProductToBasket(product,quantity, alreadyInBasket);
  }

  public Product removeProductFromBasket(Product product)
  {
    return saleModel.removeProductFromBasket(product);
  }

  public ArrayList<Product> getAllProducts(char role)
  {
    return productModel.getAllProducts(role);
  }

  public Product changeStock(int id,int quantity)
  {
    return productModel.changeStock(id,quantity);
  }

  public void registerStockViewer()
  {
    productModel.registerStockViewer();
  }

  public void deregisterStockViewer()
  {
    productModel.deregisterStockViewer();
  }

}
