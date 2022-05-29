package client.model;

import shared.transferobjects.User;

/**
 * Interface used for logging in users and adding new accounts, extends Subject interface
 * @author S2G2
 * @version 1.0
 */
public interface AccountModel
{
  /**
   * Client logging into their account
   * @param username client's username
   * @param password client's password
   * @return the logged-in User object, null, if logging in was unsuccessful
   */
  User login(String username, String password);

  /**
   * Adding a new account
   * @param username username of the account
   * @param password password of the account
   * @param role role of the account
   * @return the new Account object, null if the process was unsuccessful
   */
  User addAccount(String username, String password, String role);
}
