package client.view.login;

import client.model.AccountModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginViewModel implements PropertyChangeListener
{
  private AccountModel model;

  public LoginViewModel(AccountModel accountModel)
  {
    this.model = accountModel;
  }

  public void login()
  {
    System.out.println("You're logging in");
    model.login("Finn", "5678");
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {

  }
}