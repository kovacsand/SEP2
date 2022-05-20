package client.view.login;

import client.model.AccountModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.User;

import java.beans.PropertyChangeSupport;

/**
 * ViewModel class for logging in
 * @author S2G2
 * @version 1.0
 */
public class LoginViewModel
{
  private final AccountModel model;
  private StringProperty username;
  private StringProperty password;
  private StringProperty errorMessage;
  private final PropertyChangeSupport support;
  private User user;

  /**
   * One-argument constructor initializing accountModel object and SimpleStringProperties. Also adding the ViewModel as a listener to the AccountModel.
   * @param accountModel the model used by the ViewModel
   */
  public LoginViewModel(AccountModel accountModel)
  {
    this.model = accountModel;
    username = new SimpleStringProperty();
    password = new SimpleStringProperty();
    errorMessage = new SimpleStringProperty();
    support = new PropertyChangeSupport(this);
  }

  /**
   * Passing the values from the GUI to the model to log in
   * Clears the values in the GUI so that the user can enter new arguments
   */
  public User login()
  {
    user = model.login(username.getValue(), password.getValue());
    Platform.runLater(() ->
    {
      username.setValue(null);
      password.setValue(null);
      if (user == null)
        errorMessage.setValue("Incorrect credentials!");
      else
        errorMessage.setValue("Hello, " + user.getUsername());
    });
    return user;
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
}