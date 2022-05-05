package server.model;

import shared.transferobjects.User;
import shared.utils.Subject;

public interface AccountModel extends Subject
{
  void login(String username, String password);
  void addAccount(User user);
  void loginReply(boolean successful, User user);
  void addAccountReply(boolean successful, String username);
}
