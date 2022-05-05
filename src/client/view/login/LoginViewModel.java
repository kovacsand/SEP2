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

  @Override public void propertyChange(PropertyChangeEvent evt)
  {

  }
}
