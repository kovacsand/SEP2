package server.model.mediator;

import shared.transferobjects.Product;

import java.sql.SQLException;

/**
 * Interface for Database Access Object accessing Products
 * @author S2G2
 * @version 1.0
 */
public interface ProductDAO extends DAOInterface
{
  /**
   * Connects to the database and tries to add a new product
   * @param product The p
   * @return
   * @throws SQLException
   */
  Product addProduct(Product product) throws SQLException;
}
