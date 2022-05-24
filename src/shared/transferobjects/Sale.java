package shared.transferobjects;

import java.util.HashMap;
import java.util.Map;

public class Sale
{
  private Map<Product, Integer> products;
  private int id;
  private double totalPrice;

  public Sale()
  {
    products = new HashMap<>();
    totalPrice = 0;
  }

  public Sale(int id)
  {
    this.id = id;
    products = new HashMap<>();
    totalPrice = 0;
  }

  public int getId()
  {
    return id;
  }

  public HashMap<Product, Integer> getProducts()
  {
    return (HashMap<Product, Integer>) products;
  }

  public double getTotalPrice()
  {
    return totalPrice;
  }

  public void addProduct(Product product, int quantity)
  {
    if(products.containsKey(product))
    {
      products.put(product, products.get(product) + quantity);
    }
    else
    {
      products.put(product, quantity);
    }
    totalPrice += product.getPrice() * quantity;
  }

  public void removeProduct(Product product)
  {
    products.remove(product);
  }
}
