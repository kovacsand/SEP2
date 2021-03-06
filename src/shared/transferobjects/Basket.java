package shared.transferobjects;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that is responsible for Basket object
 * @author S2G2
 * @version 1.0
 */
public class Basket implements Serializable
{
  private final Map<Product, Integer> products;
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
   * Get method for getting ID of the Sale
   * @return id of the Sale
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
      products.put(product, products.get(product) + quantity);
    else
      products.put(product, quantity);
    totalPrice += quantity * product.getPrice();
  }

  /**
   * Removing the Product object completely (including quantity) of the Product HashMap of the sale
   * @param product object that needs to be removed
   */
  public void removeProduct(Product product)
  {
    products.remove(product);
    totalPrice -= product.getQuantity() * product.getPrice();
  }
}