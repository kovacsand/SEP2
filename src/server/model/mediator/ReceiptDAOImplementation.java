package server.model.mediator;

import shared.transferobjects.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Implementation of Data Access Object interface handling receipts.
 * It is created following the Singleton Design Pattern
 * @author S2G2
 * @version 1.1
 */
public class ReceiptDAOImplementation implements ReceiptDAO
{
  private static ReceiptDAOImplementation instance;

  /**
   * Private constructor following the Singleton Pattern, registering the SQL driver
   * @throws SQLException if something is wrong with the database
   */
  private ReceiptDAOImplementation() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  /**
   * Getting the single instance
   * @return the instance
   * @throws SQLException if something wrong with database
   */
  public static synchronized ReceiptDAOImplementation getInstance() throws SQLException
  {
    if (instance == null)
      instance = new ReceiptDAOImplementation();
    return instance;
  }

  @Override public ArrayList<Receipt> getAllReceipts() throws SQLException
  {
    ResultSet resultSet;
    ArrayList<Receipt> allReceipts = new ArrayList<>();

    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM Receipts;");
      resultSet = statement.executeQuery();

      while (resultSet.next())
      {

        int id = resultSet.getInt("id");

        //Finding the Salesperson
        Salesperson salesperson = new Salesperson(resultSet.getString("salesperson"));

        //Filling the basket
        Basket basket = new Basket();
        PreparedStatement basketStatement = connection.prepareStatement(
            "SELECT p.id, p.name, p.description, p.price, sp.quantity FROM Products p, SoldProducts sp WHERE p.id IN (SELECT sp.product_id WHERE receipt_id = ?);");
        basketStatement.setInt(1, id);
        ResultSet basketSet = basketStatement.executeQuery();
        while (basketSet.next())
        {
          int productId = basketSet.getInt("id");
          String name = basketSet.getString("name");
          String description = basketSet.getString("description");
          double price = basketSet.getDouble("price");
          int quantity = basketSet.getInt("quantity");
          basket.addProduct(new Product(productId,name,description,price,quantity),quantity);
        }

        LocalDateTime date = resultSet.getTimestamp("date_time").toLocalDateTime();
        allReceipts.add(new Receipt(id,salesperson,basket,date));
      }
    }
    return allReceipts;
  }

  @Override public double generateIncome(LocalDateTime startDate, LocalDateTime endDate) throws SQLException
  {
    String start =  startDate.getYear() + "-" + startDate.getMonthValue() + "-" + startDate.getDayOfMonth() + " 00:00:00.000000";
    String end = endDate.getYear() + "-" + endDate.getMonthValue() + "-" + endDate.getDayOfMonth() + " 23:59:59.999999";
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT SUM(total_price) FROM Receipts WHERE date_time BETWEEN ? AND ?;");
      statement.setTimestamp(1, Timestamp.valueOf(start));
      statement.setTimestamp(2, Timestamp.valueOf(end));
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
        return resultSet.getDouble(1);
    }
    return 0;
  }
}