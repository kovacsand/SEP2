package server.model.mediator;

import shared.transferobjects.Product;

import java.sql.SQLException;

public interface ProductDAO
{
  Product addProduct(Product product) throws SQLException;
}
