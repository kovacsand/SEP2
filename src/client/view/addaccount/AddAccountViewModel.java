package client.view.addaccount;

import client.model.AccountModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * ViewModel class for adding an account
 * @author S2G2
 * @version 1.0
 */
public class AddAccountViewModel
{
  private AccountModel model;
  private StringProperty username;
  private StringProperty password;
  private StringProperty role;

  /**
   * One-argument constructor initializing accountModel object and SimpleStringProperties
   * @param accountModel
   */
  public AddAccountViewModel(AccountModel accountModel)
  {
    model = accountModel;
    username = new SimpleStringProperty();
    password = new SimpleStringProperty();
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
   * Clears the values afterwards for the next addition
   */
  public void addAccount()
  {
    model.addAccount(username.getValue(), password.getValue(), role.getValue());
    Platform.runLater(() ->
    {
      username.setValue("");
      password.setValue("");
      role.setValue(null);
    });
  }
}
