package server.model.mediator;

import server.networking.SaleServerImplementation;
import shared.transferobjects.Product;
import shared.transferobjects.Receipt;
import shared.transferobjects.Basket;
import shared.transferobjects.Salesperson;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SaleDAOImplementation implements SaleDAO
{
  private static SaleDAOImplementation instance;

  /**
   * Private constructor following the Singleton Pattern, registering the SQL driver
   * @throws SQLException if something is wrong with the database
   */
  private SaleDAOImplementation() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  /**
   * Getting the single existing instance
   * @return the instance
   * @throws SQLException if something is wrong with the database
   */
  public static synchronized SaleDAOImplementation getInstance()
      throws SQLException
  {
    if(instance == null)
      instance = new SaleDAOImplementation();
    return instance;
  }

  @Override public Receipt addSale(Basket basket, Salesperson salesperson)
      throws SQLException
  {
    Receipt receipt = null;
    Basket newBasket = new Basket();
    ResultSet resultSet = null;
    int id = 0;
    HashMap<Product, Integer> products = basket.getProducts();
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO Receipts VALUES ( DEFAULT, ?, NOW(), ?);", PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, salesperson.getUsername());
      statement.setDouble(2, basket.getTotalPrice());
      statement.executeUpdate();
      resultSet = statement.getGeneratedKeys();
      if(resultSet.next())
        id = (int) resultSet.getObject("id");
      statement = connection.prepareStatement("INSERT INTO SoldProducts VALUES(?, ?, ?);");
      for (Map.Entry<Product, Integer> productsInHash : products.entrySet())
      {
        statement.setInt(1, id);
        statement.setInt(2, productsInHash.getKey().getId());
        statement.setInt(3, productsInHash.getValue());
        statement.executeUpdate();
      }
      statement = connection.prepareStatement("SELECT p.id, p.name, p.description, p.price, sp.quantity FROM Products p, SoldProducts sp WHERE p.id IN (SELECT sp.product_id WHERE receipt_id = ?);");
      statement.setInt(1, id);
      resultSet = statement.executeQuery();
      while(resultSet.next())
      {
        int productId = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        double price = resultSet.getDouble("price");
        int quantity = resultSet.getInt("quantity");
        newBasket.addProduct(new Product(productId, name, description, price, quantity), quantity);
      }
      receipt = new Receipt(id, salesperson, newBasket, LocalDateTime.now());
    }
    return receipt;
  }
}
