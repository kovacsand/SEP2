package server.model;

import shared.transferobjects.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AMImplementation implements AccountModel
{
  private PropertyChangeSupport support;

  public AMImplementation()
  {
    support = new PropertyChangeSupport(this);
  }

  @Override public void login(String username, String password)
  {

  }

  @Override public void addAccount(User user)
  {

  }

  @Override public void loginReply(boolean successful, User user)
  {
    if(successful)
      support.firePropertyChange("LoginSuccessful", null, user);
    else
      support.firePropertyChange("LoginFailed", null, null);
  }

  @Override public void addAccountReply(boolean successful, String username)
  {
    if(successful)
    support.firePropertyChange("AccountAdded", null, username);
    else
      support.firePropertyChange("AccountExists", null, username);
  }

  @Override public void addListener(String propertyName, PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(propertyName, listener);
  }

  @Override public void removeListener(String propertyName, PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(propertyName, listener);
  }
}
