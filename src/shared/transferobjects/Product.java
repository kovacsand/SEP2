package shared.transferobjects;

import java.io.Serializable;

public class Product implements Serializable
{
  private int id;
  private String name;
  private String description;
  private double price;

  public Product(int id, String name, String description, double price)
  {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
  }
}
