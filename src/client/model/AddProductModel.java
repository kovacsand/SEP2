package client.model;

import shared.utils.Subject;

public interface AddProductModel extends Subject
{
  void addProduct(String name, String desc, double price);

  void addProductReply(boolean successful, String name);
}
