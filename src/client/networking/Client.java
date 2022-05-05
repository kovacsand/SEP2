package client.networking;

import shared.transferobjects.Product;
import shared.transferobjects.User;
import shared.utils.Subject;

public interface Client extends Subject
{
  void login(String username, String password);

  void addProduct(Product product);

  void addAccount(User user);

  void registerClient();

  void unregisterClient();
}
