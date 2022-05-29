package server.model.mediator;

import shared.transferobjects.Accountant;
import shared.transferobjects.Manager;
import shared.transferobjects.Salesperson;
import shared.transferobjects.User;

import java.sql.*;

/**
 * Implementation of Data Access Object interface handling products.
 * It is created following the Singleton Design Pattern
 * @author S2G2
 * @version 1.0
 */
public class AccountDAOImplementation implements AccountDAO
{
  private static AccountDAOImplementation instance;

  /**
   * Private constructor following the Singleton Pattern, registering the SQL driver
   * @throws SQLException if something is wrong with the database
   */
  private AccountDAOImplementation() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  /**
   * Getting the single existing instance
   * @return the instance
   * @throws SQLException if something is wrong with the database
   */
  public static synchronized AccountDAOImplementation getInstance() throws SQLException
  {
    if (instance == null)
      instance = new AccountDAOImplementation();
    return instance;
  }

  @Override public User login(String username, String password) throws SQLException
  {
    User loggedInUser = null;
    ResultSet resultSet;
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM Employees WHERE username = ? AND password = ?;");
      statement.setString(1, username);
      statement.setString(2, password);
      resultSet = statement.executeQuery();
    }
    if(resultSet.next())
    {
      String dataBaseName = resultSet.getString("username");
      int dataBaseRole = resultSet.getInt("role_id");
      if(dataBaseRole == 1)
        loggedInUser = new Manager(dataBaseName);
      if(dataBaseRole == 2)
        loggedInUser = new Salesperson(dataBaseName);
      if(dataBaseRole == 3)
        loggedInUser = new Accountant(dataBaseName);
    }
    return loggedInUser;
  }

  @Override public User addAccount(User user, String password) throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT username FROM Employees WHERE username = ?;");
      statement.setString(1, user.getUsername());
      ResultSet resultSet = statement.executeQuery();
      if(resultSet.next())
      {
        return null;
      }
    }
    String sqlStatement = "INSERT INTO Employees (username, password, role_id) VALUES (?, ?, ?);";
    int databaseRole = -1;
    if (user instanceof Manager)
      databaseRole = 1;
    if (user instanceof Salesperson)
      databaseRole = 2;
    if (user instanceof Accountant)
      databaseRole = 3;

    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(sqlStatement);
      statement.setString(1, user.getUsername());
      statement.setString(2, password);
      statement.setInt(3, databaseRole);
      statement.executeUpdate();
    }
    return user;
  }
}