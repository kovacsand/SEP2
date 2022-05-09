package server.model;

import shared.transferobjects.User;
import shared.utils.Subject;

import java.io.Serializable;

public interface AccountModel extends Subject, Serializable
{
  void login(String username, String password);
  void addAccount(User user);
  void loginReply(boolean successful, User user);
  void addAccountReply(boolean successful, String username);
}
