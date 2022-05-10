package server.model.mediator;

import shared.transferobjects.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDAOImplementation implements ProductDAO
{
  private static ProductDAOImplementation instance;

  private ProductDAOImplementation() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized ProductDAOImplementation getInstance() throws SQLException
  {
    if (instance == null)
      instance = new ProductDAOImplementation();
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=" + PostgreSQLAccess.DATABASE_SCHEMA_NAME, PostgreSQLAccess.DATABASE_USER_NAME, PostgreSQLAccess.DATABASE_USER_PASSWORD);
  }

  @Override public void addProduct(Product product) throws SQLException
  {
    String newProductName = product.getName();
    String newProductDescription = product.getDescription();
    Double newProductPrice = product.getPrice();

    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO Product VALUES (DEFAULT, ?, ?, ?);");
      statement.setString(1, newProductName);
      statement.setString(2, newProductDescription);
      statement.setDouble(3, newProductPrice);
      statement.executeUpdate();
    }
  }
}
