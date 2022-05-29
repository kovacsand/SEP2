package server.model;

import shared.transferobjects.User;

/**
 * Interface used for logging in users and adding new accounts
 * @author S2G2
 * @version 1.1
 */
public interface AccountModel
{
  /**
   * Client logging into their account
   * @param username client's username
   * @param password client's password
   */
  User login(String username, String password);

  /**
   * Client adding a new account to the system
   * @param user to be added
   * @param password of the new user
   * @return the newly added user if it was successful, otherwise null
   */
  User addAccount(User user, String password);
}