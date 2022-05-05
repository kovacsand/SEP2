package shared.networking;

import shared.transferobjects.User;

public interface ClientCallBack
{
  void loginReply(boolean successful, User user);
  void addAccountReply(boolean successful, String username);
  void addProductReply(boolean successful, String name);
}
