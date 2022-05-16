package client.view.stock;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StockViewModel
{
  private StringProperty id;
  private StringProperty name;
  private StringProperty description;
  private StringProperty price;
  private StringProperty stock;


  public StockViewModel (String id, String name, String description, String price, String stock)
  {
    setId(id);
    setName(name);
    setDescription(description);
    setPrice(price);
    setId(id);
  }

  public void setId(String id)
  {
    this.id=new SimpleStringProperty(id);
  }

  public void setName(String name)
  {
    this.name=new SimpleStringProperty(name);
  }

  public void setDescription(String description)
  {
    this.description=new SimpleStringProperty(description);
  }

  public void setPrice(String price)
  {
    this.price=new SimpleStringProperty(price);
  }
   public void setStock(String stock)
   {
     this.stock=new SimpleStringProperty(stock);
   }

  public String getStock()
  {
    return stock.get();
  }

  public String getDescription()
  {
    return description.get();
  }

  public String getId()
  {
    return id.get();
  }

  public String getName()
  {
    return name.get();
  }

  public String getPrice()
  {
    return price.get();
  }
}
