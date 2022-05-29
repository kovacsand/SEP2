package server.model.mediator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Interface created just for connecting to the database
 * @author S2G2
 * @version 1.0
 */
public interface DAOInterface
{
  /**
   * Default method to connect to the database, the classes implementing the interface shouldn't change it
   * @return A Connection object that is used to send data to the database
   * @throws SQLException if something is wrong with the database
   */
  default Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(PostgreSQLAccess.DATABASE_PROTOCOL + "://" + PostgreSQLAccess.DATABASE_HOST + ":" + PostgreSQLAccess.DATABASE_PORT + "/" + PostgreSQLAccess.DATABASE_PATH + "?" + PostgreSQLAccess.DATABASE_QUERY + "=" + PostgreSQLAccess.DATABASE_SCHEMA_NAME, PostgreSQLAccess.DATABASE_USER_NAME, PostgreSQLAccess.DATABASE_USER_PASSWORD);
  }
}