package client.view.login;

import client.model.AccountModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import shared.transferobjects.Accountant;
import shared.transferobjects.Manager;
import shared.transferobjects.Salesperson;
import shared.transferobjects.User;
import shared.utils.Subject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel class for logging in
 * @author S2G2
 * @version 1.0
 */
public class LoginViewModel implements PropertyChangeListener, Subject
{
  private AccountModel model;
  private StringProperty username;
  private StringProperty password;
  private StringProperty errorMessage;
  private PropertyChangeSupport support;
  private String role;

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
    support = new PropertyChangeSupport(this);
    model.addListener("LoginSuccessful", this);
    model.addListener("LoginFailed", this);
  }

  /**
   * Passing the values from the GUI to the model to log in
   * Clears the values in the GUI so that the user can enter new arguments
   */
  public void login()
  {
    model.login(username.getValue(), password.getValue());
    Platform.runLater(() ->
    {
      username.setValue(null);
      password.setValue(null);
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
      if (evt.getNewValue() instanceof Manager)
        role = "Manager";
      if (evt.getNewValue() instanceof Salesperson)
        role = "Salesperson";
      if (evt.getNewValue() instanceof Accountant)
        role = "Accountant";
      support.firePropertyChange("LoginSuccess",null,role);
    }
    else if (evt.getPropertyName().equals("LoginFailed"))
      Platform.runLater(() -> errorMessage.setValue("Incorrect credentials!"));
  }


  @Override public void addListener(String propertyName,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(propertyName,listener);
  }

  @Override public void removeListener(String propertyName,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(propertyName,listener);
  }
}