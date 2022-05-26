package client.view.sale;

import client.model.ProductModel;
import client.model.SaleModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

  private double totalPrice;
  private StringProperty totalPriceProperty;

  public SaleViewModel(SaleModel saleModel, ProductModel productModel)
  {
    this.saleModel=saleModel;
    this.productModel=productModel;
    support=new PropertyChangeSupport(this);

    totalPrice = 0;
    totalPriceProperty = new SimpleStringProperty();
  }

  public Receipt finaliseSale(Basket basket, Salesperson salesperson)
  {
    return saleModel.finaliseSale(basket,salesperson);
  }

  public Product addProductToBasket(Product product,int quantity, boolean alreadyInBasket)
  {
    totalPrice += product.getPrice() * quantity;
    setTotalPriceProperty(totalPrice);
    return saleModel.addProductToBasket(product,quantity, alreadyInBasket);
  }

  public Product removeProductFromBasket(Product product)
  {
    totalPrice -= product.getPrice() * product.getQuantity();
    setTotalPriceProperty(totalPrice);
    return saleModel.removeProductFromBasket(product);
  }


  private void setTotalPriceProperty(double totalPrice)
  {
    totalPriceProperty.setValue("Total price: " + totalPrice);
  }

  public ArrayList<Product> getAllProducts(char role)
  {
    return productModel.getAllProducts(role);
  }

  public Product changeStock(int id,int quantity)
  {
    return productModel.changeStock(id,quantity);
  }

  public StringProperty totalPriceProperty()
  {
    return totalPriceProperty;
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
