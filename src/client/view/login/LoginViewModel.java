package client.view.login;

import client.model.AccountModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.Accountant;
import shared.transferobjects.Manager;
import shared.transferobjects.Salesperson;
import shared.transferobjects.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * ViewModel class for logging in
 * @author S2G2
 * @version 1.0
 */
public class LoginViewModel implements PropertyChangeListener
{
  private AccountModel model;
  private StringProperty username;
  private StringProperty password;
  private StringProperty errorMessage;

  /**
   * One-argument constructor initializing accountModel object and SimpleStringProperties. Also adding the ViewModel as a listener to the AccountModel.
   * @param accountModel
   */
  public LoginViewModel(AccountModel accountModel)
  {
    this.model = accountModel;
    username = new SimpleStringProperty();
    password = new SimpleStringProperty();
    errorMessage = new SimpleStringProperty();
    model.addListener("LoginSuccessful", this);
    model.addListener("LoginFailed", this);
  }

  /**
   * Passing the values from the GUI to the model to log in
   * Clears the values in the GUI so that the user can enter new arguments
   */
  public void login()
  {
    System.out.println("You're logging in with this:" + username.getValue() + ", " + password.getValue());
    model.login(username.getValue(), password.getValue());
    Platform.runLater(() ->
    {
      username.setValue("");
      password.setValue("");
    });
  }

  /**
   * Getting StringProperty of the username
   * @return username
   */
  public StringProperty usernameProperty()
  {
    return username;
  }

  /**
   * Getting StringProperty of the password
   * @return password
   */
  public StringProperty passwordProperty()
  {
    return password;
  }

  /**
   * Getting StringProperty of the error message
   * @return errorMessage
   */
  public StringProperty errorMessageProperty()
  {
    return errorMessage;
  }

  /**
   * The ViewModel is observing the AccountModel, so it needs to implement this method
   * @param evt The PropertyChangeEvent fired by the AccountModel
   */
  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    if (evt.getPropertyName().equals("LoginSuccessful"))
    {
      String result = "Welcome, " + ((User) evt.getNewValue()).getUsername();
      if (evt.getNewValue() instanceof Manager)
        result += ", M";
      if (evt.getNewValue() instanceof Salesperson)
        result += ", S";
      if (evt.getNewValue() instanceof Accountant)
        result += ", A";
      String finalResult = result;
      Platform.runLater(() ->
      {
        errorMessage.setValue(finalResult);
      });
    }
    else if (evt.getPropertyName().equals("LoginFailed"))
    {
      Platform.runLater(() ->
      {
        errorMessage.setValue("Incorrect credentials!");
      });
    }
  }
}