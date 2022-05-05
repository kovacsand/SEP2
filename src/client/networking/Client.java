package client.networking;

import shared.transferobjects.Product;
import shared.transferobjects.User;

public interface Client
{
  void login(String username, String password);

  void addProduct(Product product);

  void addAccount(User user);

  void registerClient();

  void unregisterClient();
}
