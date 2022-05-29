package server.model.mediator;

import shared.transferobjects.Product;

import java.sql.*;
import java.util.ArrayList;

/**
 * Implementation of Data Access Object interface handling accounts.
 * It is created following the Singleton Design Pattern
 * @author S2G2
 * @version 1.1
 */
public class ProductDAOImplementation implements ProductDAO
{
  private static ProductDAOImplementation instance;

  /**
   * Private constructor following the Singleton Pattern, registering the SQL driver
   * @throws SQLException if something is wrong with the database
   */
  private ProductDAOImplementation() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  /**
   * Getting the single existing instance
   * @return the instance
   * @throws SQLException if something is wrong with the database
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
    double newProductPrice = product.getPrice();

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

  @Override public Product removeProduct(Product product) throws SQLException
  {
    int rowsDeleted;
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("UPDATE Products SET discontinued = TRUE WHERE id = ? AND inBaskets = 0;");
      statement.setInt(1, product.getId());
      rowsDeleted = statement.executeUpdate();
    }
    if (rowsDeleted == 1)
      return product;
    return null;
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
            "SELECT * FROM Products WHERE NOT discontinued;");
        resultSet = statement.executeQuery();
      }
    }
    if (role == 'S' || role == 's')
    {
      try (Connection connection = getConnection())
      {
        PreparedStatement statement = connection.prepareStatement(
            "SELECT * FROM Products WHERE NOT discontinued AND quantity > 0;");
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

  @Override public Product changeStock(int id, int quantity) throws SQLException
  {
    Product product = null;
    ResultSet resultSet;
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE Products SET quantity = quantity + ? WHERE id = ?;");
      statement.setInt(1, quantity);
      statement.setInt(2, id);
      statement.executeUpdate();
    }

    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM Products WHERE id = ?;");
      statement.setInt(1, id);
      resultSet = statement.executeQuery();
    }
    while(resultSet != null && resultSet.next())
    {
      int productId = resultSet.getInt("id");
      String name = resultSet.getString("name");
      String description = resultSet.getString("description");
      double price = resultSet.getDouble("price");
      int productQuantity = resultSet.getInt("quantity");
      product = new Product(productId, name, description, price, productQuantity);
    }
    return product;
  }

  @Override public Product increaseInBaskets(Product product) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("UPDATE Products SET inBaskets = inBaskets + 1 WHERE id = ?;");
      statement.setInt(1, product.getId());
      if (statement.executeUpdate() == 1)
        return product;
    }
    return null;
  }

  @Override public Product decreaseInBaskets(Product product) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("UPDATE Products SET inBaskets = inBaskets - 1 WHERE id = ?;");
      statement.setInt(1, product.getId());
      if (statement.executeUpdate() == 1)
        return product;
    }
    return null;
  }
}