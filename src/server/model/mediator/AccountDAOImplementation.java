package server.model.mediator;

import shared.transferobjects.Accountant;
import shared.transferobjects.Manager;
import shared.transferobjects.Salesperson;
import shared.transferobjects.User;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class AccountDAOImplementation implements AccountDAO
{
  private static final String DATABASE_SCHEMA_NAME = "warehousemanagementsystem";
  private static final String DATABASE_USER_NAME = "postgres";
  private static final String DATABASE_USER_PASSWORD = "1234";
  private static AccountDAOImplementation instance;

  private AccountDAOImplementation() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized AccountDAOImplementation getInstance() throws SQLException
  {
    if (instance == null)
      instance = new AccountDAOImplementation();
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=" + DATABASE_SCHEMA_NAME, DATABASE_USER_NAME, DATABASE_USER_PASSWORD);
  }

  @Override public Map<String, User> getAllUsers() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement;
      ResultSet resultSet;
      HashMap<String, User> result = new HashMap<>();

      //Getting all the managers
      statement = connection.prepareStatement("SELECT * FROM ManagerAccount");
      resultSet = statement.executeQuery();
      while (resultSet.next())
      {
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        Manager manager = new Manager(username, password);
        result.put(password, manager);
      }

      //Getting all the salespeople
      statement = connection.prepareStatement("SELECT * FROM SalespersonAccount");
      resultSet = statement.executeQuery();
      while (resultSet.next())
      {
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        Salesperson salesperson = new Salesperson(username, password);
        result.put(password, salesperson);
      }

      //Getting all the accountants
      statement = connection.prepareStatement("SELECT * FROM AccountantAccount");
      resultSet = statement.executeQuery();
      while (resultSet.next())
      {
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        Accountant accountant = new Accountant(username, password);
        result.put(password, accountant);
      }
      return result;
    }
  }
}
