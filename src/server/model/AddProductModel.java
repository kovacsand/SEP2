package server.model;

import shared.transferobjects.Product;
import shared.utils.Subject;

/**
 * Interface used for adding products to the database and
 * @author S2G2
 * @version 1.0
 */
public interface AddProductModel extends Subject
{
  /**
   * Adding new product to the database
   * @param product to be added
   * @return the newly added Product object
   */
  Product addProduct(Product product);
}
