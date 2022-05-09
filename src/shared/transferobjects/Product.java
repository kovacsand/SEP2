package shared.transferobjects;

import java.io.Serializable;

public class Product implements Serializable
{
  private String name;
  private String description;
  private double price;

  public Product(String name, String description, double price)
  {
    this.name = name;
    this.description = description;
    this.price = price;
  }
}
