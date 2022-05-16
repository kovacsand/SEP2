package server.model.mediator;

import shared.transferobjects.Product;

import java.sql.*;
import java.util.ArrayList;

/**
 * Implementation of Data Access Object interface handling accounts. It is created following the Singleton Pattern
 *
 * @author S2G2
 * @version 1.0
 */
public class ProductDAOImplementation implements ProductDAO
{
  private static ProductDAOImplementation instance;

  private ProductDAOImplementation() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  /**
   * Getting the single existing instance
   *
   * @return the instance
   * @throws SQLException
   */
  public static synchronized ProductDAOImplementation getInstance()
      throws SQLException
  {
    if (instance == null)
      instance = new ProductDAOImplementation();
    return instance;
  }

  @Override public Product addProduct(Product product) throws SQLException
  {
    String newProductName = product.getName();
    String newProductDescription = product.getDescription();
    Double newProductPrice = product.getPrice();

    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO Products VALUES (DEFAULT, ?, ?, ?, ?);");
      statement.setString(1, newProductName);
      statement.setString(2, newProductDescription);
      statement.setDouble(3, newProductPrice);
      statement.setInt(4, 0);
      statement.executeUpdate();
    }
    return product;
  }

  @Override public ArrayList<Product> getAllProducts(char role)
      throws SQLException
  {
    ResultSet resultSet = null;
    ArrayList<Product> allProducts = new ArrayList<>();
    if (role == 'M' || role == 'm')
    {
      try (Connection connection = getConnection())
      {
        PreparedStatement statement = connection.prepareStatement(
            "SELECT * FROM Products;");
        resultSet = statement.executeQuery();
      }
    }
    if (role == 'S' || role == 's')
    {
      try (Connection connection = getConnection())
      {
        PreparedStatement statement = connection.prepareStatement(
            "SELECT * FROM Products WHERE quantity > 0;");
        resultSet = statement.executeQuery();
      }
    }
    while (resultSet != null && resultSet.next())
    {
      int id = resultSet.getInt("id");
      String name = resultSet.getString("name");
      String description = resultSet.getString("description");
      double price = resultSet.getDouble("price");
      int quantity = resultSet.getInt("quantity");
      Product product = new Product(id, name, description, price, quantity);
      allProducts.add(product);
    }
    return allProducts;
  }

  @Override public void increaseStock(int id, int quantity) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE Products SET quantity = quantity + ? WHERE id = ?;");
      statement.setInt(1, quantity);
      statement.setInt(2, id);
      statement.executeUpdate();
    }
  }
}
