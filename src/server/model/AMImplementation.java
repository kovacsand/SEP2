package server.model;

import server.model.mediator.AccountDAOImplementation;
import shared.transferobjects.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;

/**
 * Class that implements the AccountModel interface on server side.
 * @author S2G2
 * @version 1.0
 */
public class AMImplementation implements AccountModel
{
  private PropertyChangeSupport support;

  /**
   * Zero-argument constructor initializing the AccountModel implementation class
   */
  public AMImplementation()
  {
    support = new PropertyChangeSupport(this);
  }

  @Override public User login(String username, String password)
  {
    User user = null;
    try
    {
      user = AccountDAOImplementation.getInstance().getLoggedInUser(username, password);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return user;
  }

  @Override public User addAccount(User user, String password)
  {
    User addedUser = null;
    try
    {
      addedUser = AccountDAOImplementation.getInstance().addAccount(user, password);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return addedUser;
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
