package server.model.mediator;

import shared.transferobjects.Product;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Interface for Database Access Object accessing Products
 * @author S2G2
 * @version 1.1
 */
public interface ProductDAO extends DAOInterface
{

  /**
   * Connects to the database and tries to add a new product
   * @param product The product that the user wants to add
   * @return the newly added product if it was successful, otherwise null
   * @throws SQLException if something is wrong with the database
   */
  Product addProduct(Product product) throws SQLException;

  Product removeProduct(Product product) throws SQLException;

  /**
   * Fetching all products from the database, depending on the role of the user.
   * @param role of the user
   * @return an ArrayList<Product> of all the products in the database
   * @throws SQLException if something is wrong with the database
   */
  ArrayList<Product> getAllProducts(char role) throws SQLException;
  /**
   * Increasing the stock of the product (registering new order)
   * @param id of the product
   * @param quantity by how much the product stock must be increased
   * @throws SQLException if something is wrong with the database
   */
  Product changeStock(int id, int quantity) throws SQLException;

  /**
   * Increasing the inBaskets attribute of the Product in the database
   * @param product
   * @return the changed product if the process was successful, otherwise null
   * @throws SQLException if something is wrong with the database
   */
  Product increaseInBaskets(Product product) throws SQLException;

  /**
   * Decreasing the inBaskets attribute of the Product in the database
   * @param product
   * @return the changed product if the process was successful, otherwise null
   * @throws SQLException if something is wrong with the database
   */
  Product decreaseInBaskets(Product product) throws SQLException;
}
