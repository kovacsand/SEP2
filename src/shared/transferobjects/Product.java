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
   * Five-argument constructor initializing the Product object
   * @param id of the product
   * @param name of the product
   * @param description of the product
   * @param price of the product
   * @param quantity of the product
   */
  public Product(int id, String name, String description, double price, int quantity)
  {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.quantity = quantity;
  }

  /**
   * Get the id of the product
   * @return id of the product
   */
  public int getId()
  {
    return id;
  }

  /**
   * Get the name of the product
   * @return name of the product
   */
  public String getName()
  {
    return name;
  }

  /**
   * Get the description of the product
   * @return description of the product
   */
  public String getDescription()
  {
    return description;
  }

  /**
   * Get the price of the product
   * @return price of the product
   */
  public double getPrice()
  {
    return price;
  }

  /**
   * Get the quantity of the product
   * @return quantity of the product
   */
  public int getQuantity()
  {
    return quantity;
  }

  /**
   * Equals method to check if the Products are the same
   * @param obj that needs to be checked
   * @return true or false, depending on if the objects are the same
   */
  public boolean equals(Object obj)
  {
    if(!(obj instanceof Product))
      return false;

    Product other = (Product) obj;
    return id == other.id && name.equals(other.name) && description.equals(other.description) && price == other.price && quantity == other.quantity;
  }
}
