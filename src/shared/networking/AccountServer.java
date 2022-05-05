package shared.networking;

import shared.transferobjects.User;

public interface AccountServer
{
  void login(String username, String password);
  void addAccount(User user);
}
