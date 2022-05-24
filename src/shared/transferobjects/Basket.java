package shared.transferobjects;

import java.util.HashMap;
import java.util.Map;

public class Basket
{
  /**
   * Class that is responsible for Sale object
   *
   * @author S2G2
   * @version 1.0
   */
  private Map<Product, Integer> products;
  private int id;
  private double totalPrice;

  /**
   * No-argument constructor initializing the Sale object, initializes products HashMap, and sets totalPrice to 0
   */
  public Basket()
  {
    products = new HashMap<>();
    totalPrice = 0;
  }

  /**
   * One-argument constructor initializing the Sale object, initializing products HashMap, and sets totalPrice to 0
   * @param id of the sale
   */
  public Basket(int id)
  {
    this.id = id;
    products = new HashMap<>();
    totalPrice = 0;
  }

  /**
   * Get method for getting ID of the Sale
   * @return id of the Sale
   */
  public int getId()
  {
    return id;
  }

  /**
   * Get method for getting the HashMap of the Products that are in the sale
   * @return hashmap of the Products that are in the sale
   */
  public HashMap<Product, Integer> getProducts()
  {
    return (HashMap<Product, Integer>) products;
  }

  /**
   * Get method for getting the total price of the sale
   * @return total price of the sale
   */
  public double getTotalPrice()
  {
    return totalPrice;
  }

  /**
   * Adding a Product object, and it's quantity to the Product HashMap of the Sale
   * @param product object that needs to be added to the Sale
   * @param quantity amount of the product that is being added to the Sale
   */
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

  /**
   * Removing the Product object completely (including quantity) of the Product HashMap of the sale
   * @param product object that needs to be removed
   */
  public void removeProduct(Product product)
  {
    products.remove(product);
  }
}
