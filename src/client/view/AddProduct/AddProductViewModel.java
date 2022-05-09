package client.view.AddProduct;

import client.model.AddProductModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * A class that determines the behaviour of the GUI while creating a new product.
 * @author S2G2
 * @version 1.0
 */
public class AddProductViewModel implements PropertyChangeListener
{
  private AddProductModel model;
  private StringProperty name;
  private StringProperty price;
  private StringProperty description;

  public AddProductViewModel (AddProductModel addProductModel)
  {
    /**
     * An argument constructor that initializes all fields.
     */
    model=addProductModel;
    name=new SimpleStringProperty();
    price=new SimpleStringProperty();
    description=new SimpleStringProperty();
  }
  public void addProduct ()
  {
    /**
     * A method that determines the behaviour of the GUI after pressing the confirm button while adding a new product. It prints out the values given,
     * calls a model method to create the product, and empties all the fields.
     * @param name a field that is printed then emptied
     * @param description a field that is printed then emptied
     * @param price a field that is printed then emptied
     */
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
    /**
     * A method that returns name
     * @param name the name that is returned
     */
    return name;
  }
  public StringProperty priceProperty()
  {
    /**
     * A method that returns a price
     * @param price the price that is returned
     */
    return price;
  }
  public StringProperty descriptionProperty()
  {
    /**
     * A method that returns a description
     * @param description the description that is returned
     */
    return description;
  }
  @Override public void propertyChange(PropertyChangeEvent evt)
  {

  }

}
