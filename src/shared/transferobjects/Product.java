package shared.transferobjects;

import java.io.Serializable;

/**
 * Class containing all information about the product
 * @author S2G2
 * @version 1.0
 */
public class Product implements Serializable
{
  private int id;
  private String name;
  private String description;
  private double price;
  private int quantity;

  /**
   * Three-argument constructor initializing the Product object
   * @param name of the product
   * @param description of the product
   * @param price of the product
   */
  public Product(String name, String description, double price)
  {
    this.name = name;
    this.description = description;
    this.price = price;
    quantity = 0;
  }

  /**
   * Four-argument constructor initializing the Product object
   * @param id of the product
   * @param name of the product
   * @param description of the product
   * @param price of the product
   */
  public Product(int id, String name, String description, double price)
  {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    quantity = 0;
  }

  /**
   * Get the id of the product
   * @return
   */
  public int getId()
  {
    return id;
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

  /**
   * Get the quantity of the product
   * @return
   */
  public int getQuantity()
  {
    return quantity;
  }
}
