package server.model;

import shared.transferobjects.Product;
import shared.utils.Subject;

public interface AddProductModel extends Subject
{
  Product addProduct(Product product);
}
