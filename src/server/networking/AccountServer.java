package server.networking;

import shared.transferobjects.User;

/**
 * Interface for server-side to handle everything with the accounts.
 * @author S2G2
 * @version 1.1
 */
public interface AccountServer
{
  /**
   * Logging in through the model. The server passes the arguments to the model.
   * @param username of the user
   * @param password of the user
   * @return If the login is successful, return their User Object, if not, return null.
   */
  User login(String username, String password);

  /**
   * Adding an account through the model
   * @param user of the new account
   * @param password of the new account
   * @return the newly added account, if it couldn't be added, return null
   */
  User addAccount(User user, String password);
}