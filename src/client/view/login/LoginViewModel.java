package client.view.login;

import client.model.AccountModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginViewModel implements PropertyChangeListener
{
  private AccountModel model;
  private StringProperty username;
  private StringProperty password;
  private StringProperty errorMessage;

  public LoginViewModel(AccountModel accountModel)
  {
    this.model = accountModel;
    username = new SimpleStringProperty();
    password = new SimpleStringProperty();
    errorMessage = new SimpleStringProperty();
    model.addListener("LoginSuccessful", this);
    model.addListener("LoginFailed", this);
  }

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

  public StringProperty usernameProperty()
  {
    return username;
  }

  public StringProperty passwordProperty()
  {
    return password;
  }

  public StringProperty errorMessageProperty()
  {
    return errorMessage;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    if (evt.getPropertyName().equals("LoginSuccessful"))
    {
      Platform.runLater(() ->
      {
        errorMessage.setValue("Welcome, " + ((User) evt.getNewValue()).getUsername());
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