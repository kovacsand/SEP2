package server.model;

import server.model.mediator.AccountDAOImplementation;
import shared.transferobjects.Manager;
import shared.transferobjects.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.HashMap;

public class AMImplementation implements AccountModel
{
  private PropertyChangeSupport support;
  private HashMap<String, User> users; //Key: password, Value: User

  public AMImplementation()
  {
    support = new PropertyChangeSupport(this);
    users = new HashMap<>();

    try
    {
      users = (HashMap<String, User>) AccountDAOImplementation.getInstance().getAllUsers();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void login(String username, String password)
  {
    //admin access
    if (username.equals("admin") && password.equals("password"))
    {
      loginReply(true, new Manager("admin", "password"));
      return;
    }

    //Password not in database
    if (!users.containsKey(password))
    {
      loginReply(false, null);
      return;
    }

    //username connected to password is not matching with provided username
    if (!users.get(password).getUsername().equals(username))
    {
      loginReply(false, null);
      return;
    }

    if (users.get(password).getUsername().equals(username))
    {
      loginReply(true, new Manager(username, password));
      return;
    }
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
