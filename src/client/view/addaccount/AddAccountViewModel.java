package client.view.addaccount;

import client.model.AccountModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * ViewModel class for adding an account
 * @author S2G2
 * @version 1.0
 */
public class AddAccountViewModel implements PropertyChangeListener
{
  private final AccountModel model;
  private StringProperty username;
  private StringProperty password;
  private StringProperty role;

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
   * Clears the values afterwards for the next addition
   */
  public User addAccount()
  {
    User addedUser = model.addAccount(username.getValue(), password.getValue(), role.getValue());
    Platform.runLater(() ->
    {
      username.setValue(null);
      password.setValue(null);
      role.setValue(null);
    });
    return addedUser;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    System.out.println(evt.getNewValue() != null ? "New account added " + evt.getNewValue() : "Account already exists");
  }
}
