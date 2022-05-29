package client.view.addproduct;

import client.model.ProductModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.Product;


/**
 * A class that determines the behaviour of the GUI while creating a new product.
 * @author S2G2
 * @version 1.0
 */
public class AddProductViewModel
{
  private final ProductModel model;
  private final StringProperty name;
  private final StringProperty price;
  private final StringProperty description;

  /**
   * A one-argument constructor that initializes all fields.
   * @param addProductModel the model to be used by the viewmodel
   */
  public AddProductViewModel (ProductModel addProductModel)
  {
    model = addProductModel;
    name = new SimpleStringProperty(null);
    price = new SimpleStringProperty(null);
    description = new SimpleStringProperty(null);
  }

  /**
   * A method that determines the behaviour of the GUI after pressing the add button while adding a new product. It prints out the values given,
   * calls a model method to create the product, and empties all the fields.
   */
  public Product addProduct ()
  {
    return model.addProduct(name.getValue(), description.getValue(), Double.parseDouble(price.getValue()));
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
}