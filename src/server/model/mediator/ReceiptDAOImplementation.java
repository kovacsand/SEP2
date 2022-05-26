package server.model.mediator;

import javafx.util.converter.LocalDateTimeStringConverter;
import shared.transferobjects.*;

import java.beans.PropertyChangeListener;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

/**
 * Implementation of Data Access Object interface handling receipts. It is created following the Singleton Pattern
 */
public class ReceiptDAOImplementation implements ReceiptDAO
{
  private static ReceiptDAOImplementation instance;

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
          "SELECT * FROM Receipts");
      resultSet = statement.executeQuery();

      while (resultSet.next())
      {

        int id = resultSet.getInt("id");

        //Finding the Salesperson
        Salesperson salesperson = new Salesperson(resultSet.getString("salesperson"));


        //Filling the basket
        Basket basket = new Basket();
        PreparedStatement basketStatement = connection.prepareStatement(
    "SELECT p.id, p.name, p.description, p.price, sp.quantity FROM Products p, SoldProducts sp WHERE p.id IN (SELECT sp.product_id WHERE receipt_id = ?);"
        );
        basketStatement.setInt(1,id);
        ResultSet basketSet = basketStatement.executeQuery();
        while(basketSet.next())
        {
          int productId = basketSet.getInt("id");
          String name = basketSet.getString("name");
          String descript = basketSet.getString("description");
          double price = basketSet.getDouble("price");
          int quantity = basketSet.getInt("quantity");
          basket.addProduct(new Product(productId,name,descript,price,quantity),quantity);
        }

        LocalDateTime date = resultSet.getTimestamp("date_time").toLocalDateTime();
        allReceipts.add(new Receipt(id,salesperson,null,date));
      }

    }
    return allReceipts;
  }

  @Override public Receipt getReceiptDetails(int receiptID) throws SQLException
  {
    ResultSet resultSet = null;
    Receipt receiptDetails = null;

    try (Connection connection = getConnection())
    {
      return null;
    }
  }


}
