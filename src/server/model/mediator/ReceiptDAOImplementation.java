package server.model.mediator;

import shared.transferobjects.Receipt;

import java.beans.PropertyChangeListener;
import java.sql.*;
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
    ResultSet resultSet = null;
    ArrayList<Receipt> allReceipts = new ArrayList<>();

    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM Receipts");
      resultSet = statement.executeQuery();
    }
    return allReceipts;
  }

  @Override public Receipt getReceiptDetails(int receiptID) throws SQLException
  {
    return null;
  }

}
