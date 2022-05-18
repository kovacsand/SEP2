package server.model;

import shared.transferobjects.Product;
import shared.utils.Subject;

import java.util.ArrayList;

/**
 * Interface used for adding products to the database and
 * @author S2G2
 * @version 1.1
 */
public interface ProductModel extends Subject
{

  /**
   * Adding new product to the database
   * @param product to be added
   * @return the newly added Product object
   */
  Product addProduct(Product product);
  /**
   * Getting all products from the database depending on the role
   * @param role determines which SQL statement database has to make
   * @return an ArrayList<Product> of all the products the was fetched through the DAO
   */
  ArrayList<Product> getAllProducts(char role);

  /**
   * Increasing the stock of the product
   * @param id of the product
   * @param quantity by how much the stock must be increased
   */
  void increaseStock(int id, int quantity);

}
