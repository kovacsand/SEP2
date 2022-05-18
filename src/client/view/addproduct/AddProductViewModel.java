package client.view.addproduct;

import client.model.ProductModel;
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
  private final ProductModel model;
  private StringProperty name;
  private StringProperty price;
  private StringProperty description;

  /**
   * An argument constructor that initializes all fields.
   * @param addProductModel the model to be used by the viewmodel
   */
  public AddProductViewModel (ProductModel addProductModel)
  {
    model=addProductModel;
    name=new SimpleStringProperty();
    price=new SimpleStringProperty();
    description=new SimpleStringProperty();
    model.addListener("ProductAdded", this);
    model.addListener("ProductExists", this);
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
    model.increaseStock(1, 1);
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
    System.out.println(evt.getNewValue() != null ? "New product added " + evt.getNewValue() : "ERROR - run, product not added");
  }
}