package server.model.mediator;

import shared.transferobjects.User;

import java.sql.SQLException;
import java.util.Map;

/**
 * Interface for Database Access Object accessing Accounts
 * @author S2G2
 * @version 1.0
 */
public interface AccountDAO
{
  /**
   * Connects to the database and tries to log in with the passed credentials.
   * @return The logged-in User object, if the login was successful, null, if it failed
   * @throws SQLException
   */
  User getLoggedInUser(String username, String password) throws SQLException;

  User addAccount(User user, String password) throws SQLException;
}
