package client.view.AddProduct;

import client.model.AddProductModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AddProductViewModel implements PropertyChangeListener
{
  private AddProductModel model;
  private StringProperty name;
  private StringProperty price;
  private StringProperty description;

  public AddProductViewModel (AddProductModel addProductModel)
  {
    model=addProductModel;
    name=new SimpleStringProperty();
    price=new SimpleStringProperty();
    description=new SimpleStringProperty();
  }
  public void addProduct ()
  {
    System.out.println("You are creating a product named "+name.getValue()+" described as "+description.getValue()+" priced at "+price.getValue());
    model.addProduct(name.getValue(), description.getValue(), Double.parseDouble(price.getValue()));
    Platform.runLater(()->
    {
      name.setValue("");
      description.setValue("");
      price.setValue("");
    }
    );
  }
  public void cancel()
  {
    //do stuff here
  }
  public StringProperty nameProperty ()
  {
    return name;
  }
  public StringProperty priceProperty()
  {
    return price;
  }
  public StringProperty descriptionProperty()
  {
    return description;
  }
  @Override public void propertyChange(PropertyChangeEvent evt)
  {

  }

}
