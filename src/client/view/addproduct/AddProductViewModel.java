package client.view.addproduct;

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

  /**
   * An argument constructor that initializes all fields.
   */
  public AddProductViewModel (AddProductModel addProductModel)
  {
    model=addProductModel;
    name=new SimpleStringProperty();
    price=new SimpleStringProperty();
    description=new SimpleStringProperty();
  }

  /**
   * A method that determines the behaviour of the GUI after pressing the add button while adding a new product. It prints out the values given,
   * calls a model method to create the product, and empties all the fields.
   */
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

  /**
   * A method that returns name
   * @return name the name that is returned
   */
  public StringProperty nameProperty ()
  {
    return name;
  }

  /**
   * A method that returns a price
   * @return price the price that is returned
   */
  public StringProperty priceProperty()
  {
    return price;
  }

  /**
   * A method that returns a description
   * @return description the description that is returned
   */
  public StringProperty descriptionProperty()
  {
    return description;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {

  }
}