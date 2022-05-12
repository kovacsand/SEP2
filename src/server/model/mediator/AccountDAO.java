package server.model.mediator;

import shared.transferobjects.User;

import java.sql.SQLException;
import java.util.Map;

/**
 * Interface for Database Access Object accessing Accounts
 * @author S2G2
 * @version 1.0
 */
public interface AccountDAO extends DAOInterface
{
  /**
   * Connects to the database and tries to log in with the passed credentials.
   * @param username The username provided by the user
   * @param password The password provided by the password
   * @return The logged-in User object, if the login was successful, null, if it failed
   * @throws SQLException
   */
  User getLoggedInUser(String username, String password) throws SQLException;

  /**
   * Connects to the database and tries to add a new product.
   * @param user The user to be added
   * @param password The password of the user to be added
   * @return The newly added Usser object
   * @throws SQLException
   */
  User addAccount(User user, String password) throws SQLException;
}
