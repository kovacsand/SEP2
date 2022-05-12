package server.model.mediator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DAOInterface
{
  default Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(PostgreSQLAccess.DATABASE_PROTOCOL + "://" + PostgreSQLAccess.DATABASE_HOST + ":" + PostgreSQLAccess.DATABASE_PORT + "/" + PostgreSQLAccess.DATABASE_PATH + "?" + PostgreSQLAccess.DATABASE_QUERY + "=" + PostgreSQLAccess.DATABASE_SCHEMA_NAME, PostgreSQLAccess.DATABASE_USER_NAME, PostgreSQLAccess.DATABASE_USER_PASSWORD);
  }
}
