package client.model;

public interface AddProductModel
{
  void addProduct(String name, String desc, double price);

  void addProductReply(boolean successful, String name);
}
