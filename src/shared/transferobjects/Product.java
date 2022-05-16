package shared.transferobjects;

import java.io.Serializable;

/**
 * Class containing all information about the product
 * @author S2G2
 * @version 1.0
 */
public class Product implements Serializable
{

  private String name;
  private String description;
  private double price;

  /**
   * Three-argument constructor initializing the Product object
   * @param name name of the product
   * @param description description of the product
   * @param price price of the product
   */
  public Product(String name, String description, double price)
  {
    this.name = name;
    this.description = description;
    this.price = price;
  }

  /**
   * Get the name of the product
   * @return
   */
  public String getName()
  {
    return name;
  }

  /**
   * Get the description of the product
   * @return
   */
  public String getDescription()
  {
    return description;
  }

  /**
   * Get the price of the product
   * @return
   */
  public double getPrice()
  {
    return price;
  }
}
