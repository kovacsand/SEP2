package client.model;

import shared.transferobjects.User;

public interface AccountModel
{
  void login(String username, String password);

  void addAccount(String username, String password, String role);

  void loginReply(boolean successful, User user);

  void addAccountReply(boolean successful, String username);
}
