package server.model.mediator;

import shared.transferobjects.Accountant;
import shared.transferobjects.Manager;
import shared.transferobjects.Salesperson;
import shared.transferobjects.User;
import java.sql.*;

/**
 * Implementation of Data Access Object interface handling accounts. It is created following the Singleton Pattern
 * @author S2G2
 * @version 1.0
 */
public class AccountDAOImplementation implements AccountDAO
{
  private static final String DATABASE_SCHEMA_NAME = "warehousemanagementsystem";
  private static final String DATABASE_USER_NAME = "postgres";
  private static final String DATABASE_USER_PASSWORD = "1234";
  private static AccountDAOImplementation instance;

  /**
   * Private constructor following the Singleton Pattern, registering the SQL driver
   * @throws SQLException
   */
  private AccountDAOImplementation() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  /**
   * Getting the single existing instance
   * @return
   * @throws SQLException
   */
  public static synchronized AccountDAOImplementation getInstance() throws SQLException
  {
    if (instance == null)
      instance = new AccountDAOImplementation();
    return instance;
  }

  /**
   * Establishing the connection to the database
   * @return
   * @throws SQLException
   */
  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=" + DATABASE_SCHEMA_NAME, DATABASE_USER_NAME, DATABASE_USER_PASSWORD);
  }

  @Override public User getLoggedInUser(String username, String password) throws SQLException
  {
    String dataBaseName = null;
    String dataBasePassword = null;
    User loggedInUser = null;

    //If user is manager
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM ManagerAccount WHERE username = ? AND password = ?");
      statement.setString(1, username);
      statement.setString(2, password);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        dataBaseName = resultSet.getString("username");
        dataBasePassword = resultSet.getString("password");
        loggedInUser = new Manager(dataBaseName, dataBasePassword);
      }
      System.out.println(dataBaseName + dataBasePassword);
    }
    if (loggedInUser != null)
      return loggedInUser;

    //If user is salesperson
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM SalespersonAccount WHERE username = ? AND password = ?");
      statement.setString(1, username);
      statement.setString(2, password);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        dataBaseName = resultSet.getString("username");
        dataBasePassword = resultSet.getString("password");
        loggedInUser = new Salesperson(dataBaseName, dataBasePassword);
      }
      System.out.println(dataBaseName + dataBasePassword);
    }
    if (loggedInUser != null)
      return loggedInUser;

    //If user is accountant
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * FROM AccountantAccount WHERE username = ? AND password = ?");
      statement.setString(1, username);
      statement.setString(2, password);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        dataBaseName = resultSet.getString("username");
        dataBasePassword = resultSet.getString("password");
        loggedInUser = new Accountant(dataBaseName, dataBasePassword);
      }
      System.out.println(dataBaseName + dataBasePassword);
    }
    return loggedInUser;
  }
}