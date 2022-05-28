package client.view.addaccount;

import client.model.AccountModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.User;

/**
 * ViewModel class for adding an account
 * @author S2G2
 * @version 1.0
 */
public class AddAccountViewModel
{
  private final AccountModel model;
  private final StringProperty username;
  private final StringProperty password;
  private final StringProperty role;

  /**
   * One-argument constructor initializing accountModel object and SimpleStringProperties
   * @param accountModel the model used by the viewmodel
   */
  public AddAccountViewModel(AccountModel accountModel)
  {
    model = accountModel;
    username = new SimpleStringProperty(null);
    password = new SimpleStringProperty(null);
    role = new SimpleStringProperty();
  }

  /**
   * Getting StringProperty of the username
   * @return username
   */
  public StringProperty getUsername()
  {
    return username;
  }

  /**
   * Getting StringProperty of the password
   * @return password
   */
  public StringProperty getPassword()
  {
    return password;
  }

  /**
   * Getting StringProperty of the role
   * @return role
   */
  public StringProperty getRole()
  {
    return role;
  }

  /**
   * Passing the values of the GUI to the model, to add the account to the database later
   */
  public User addAccount()
  {
    return model.addAccount(username.getValue(), password.getValue(), role.getValue());
  }
}
