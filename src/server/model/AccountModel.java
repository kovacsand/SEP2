package server.model;

import shared.transferobjects.User;
import shared.utils.Subject;

import java.io.Serializable;

/**
 * Interface used for logging in users and adding new accounts, extends Subject interface
 * @author S2G2
 * @version 1.0
 */
public interface AccountModel extends Subject, Serializable
{
  /**
   * Client logging into their account
   * @param username client's username
   * @param password client's password
   */
  User login(String username, String password);
  void addAccount(User user, String password);
  void addAccountReply(boolean successful, String username);
}
