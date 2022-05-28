package client.view.login;

import client.model.AccountModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.User;

/**
 * ViewModel class for logging in
 * @author S2G2
 * @version 1.1
 */
public class LoginViewModel
{
  private final AccountModel model;
  private final StringProperty username;
  private final StringProperty password;

  /**
   * One-argument constructor initializing accountModel object and SimpleStringProperties
   * @param accountModel the model used by the ViewModel
   */
  public LoginViewModel(AccountModel accountModel)
  {
    this.model = accountModel;
    username = new SimpleStringProperty();
    password = new SimpleStringProperty();
  }

  /**
   * Passing the values from the GUI to the model to log in
   */
  public User login()
  {
    return model.login(username.getValue(), password.getValue());
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
}