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
   */
  User login(String username, String password);

  /**
   * Adding a new account
   * @param username username of the account
   * @param password password of the account
   * @param role role of the account
   */
  User addAccount(String username, String password, String role);
}
