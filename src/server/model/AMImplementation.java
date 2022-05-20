package server.model;

import server.model.mediator.AccountDAOImplementation;
import shared.transferobjects.User;

import java.sql.SQLException;

/**
 * Class that implements the AccountModel interface on server side.
 * @author S2G2
 * @version 1.0
 */
public class AMImplementation implements AccountModel
{
  /**
   * Zero-argument constructor initializing the AccountModel implementation class
   */
  public AMImplementation()
  {
  }

  @Override public User login(String username, String password)
  {
    User user = null;
    try
    {
      user = AccountDAOImplementation.getInstance().login(username, password);
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
}

