package server.model;

import shared.transferobjects.Product;
import shared.utils.Subject;

public interface AddProductModel extends Subject
{
  void addProduct(Product product);
  void addProductReply(boolean successful, String name);
}
