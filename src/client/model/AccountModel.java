package client.model;

import shared.transferobjects.User;
import shared.utils.Subject;

/**
 * Interface used for logging in users and adding new accounts, extends Subject interface
 * @author S2G2
 * @version 1.0
 */
public interface AccountModel extends Subject
{
  /**
   * Client logging into their account
   * @param username client's username
   * @param password client's password
   */
  void login(String username, String password);

  /**
   * Adding a new account
   * @param username username of the account
   * @param password password of the account
   * @param role role of the account
   */
  void addAccount(String username, String password, String role);

  /**
   * Sending information about the result of logging into an existing account
   * @param successful parameter that needs to be met for the user to log in successfully
   * @param user user that is logging in
   */
  void loginReply(boolean successful, User user);

  /**
   * Sending information about the result of the addition of a new account
   * @param successful parameter that needs to be met for the account to be added successfully
   * @param username  username of the new account
   */
  void addAccountReply(boolean successful, String username);
}
