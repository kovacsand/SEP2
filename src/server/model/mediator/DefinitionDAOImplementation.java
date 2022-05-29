package server.model.mediator;

import shared.transferobjects.Manager;

import java.sql.*;

/**
 * Implementation of Data Access Object interface defining the database.
 * It is created following the Singleton Design Pattern
 * @author S2G2
 * @version 1.0
 */
public class DefinitionDAOImplementation implements DefinitionDAO
{
  private static DefinitionDAOImplementation instance;

  private DefinitionDAOImplementation() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized DefinitionDAOImplementation getInstance() throws SQLException
  {
    if (instance == null)
      instance = new DefinitionDAOImplementation();
    return instance;
  }

  @Override public void defineDatabase() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement schema = connection.prepareStatement(
        "DROP SCHEMA IF EXISTS WMS CASCADE;"
          + "CREATE SCHEMA IF NOT EXISTS WMS;"
          + "SET SCHEMA 'wms'"
      );
      schema.executeUpdate();
      PreparedStatement domains = connection.prepareStatement(
        "CREATE DOMAIN role     AS VARCHAR(20)   CHECK (VALUE IN ('Manager', 'Salesperson', 'Accountant'));\n"
          + "CREATE DOMAIN username AS VARCHAR(60);"
          + "CREATE DOMAIN password AS VARCHAR(80);"
      );
      domains.executeUpdate();
      PreparedStatement tables = connection.prepareStatement(
      "CREATE TABLE Roles("
              + "    id          SERIAL      PRIMARY KEY"
              + ",   role        role        NOT NULL);"
        + "CREATE TABLE Employees("
              + "    username    username    PRIMARY KEY"
              + ",   password    password    NOT NULL"
              + ",   role_id     INTEGER     REFERENCES Roles(id));"
        + "CREATE TABLE Products("
              + "    id              SERIAL          PRIMARY KEY"
              + ",   name            VARCHAR(100)    NOT NULL"
              + ",   description     VARCHAR(280)    NOT NULL"
              + ",   price           DECIMAL(9, 2)   NOT NULL"
              + ",   quantity        INTEGER         NOT NULL"
              + ",   inBaskets       INTEGER         DEFAULT 0"
              + ",   discontinued    BOOLEAN         DEFAULT FALSE);"
        + "CREATE TABLE Receipts("
              + "    id          SERIAL          PRIMARY KEY"
              + ",   salesperson username        REFERENCES Employees(username)"
              + ",   date_time   TIMESTAMP       NOT NULL"
              + ",   total_price DECIMAL(12, 2)  NOT NULL);"
        + "CREATE TABLE IF NOT EXISTS SoldProducts("
              + "    receipt_id      INTEGER         REFERENCES Receipts(id)"
              + ",   product_id      INTEGER         REFERENCES Products(id)"
              + ",   quantity        INTEGER         NOT NULL CHECK (quantity > 0)"
              + ",   PRIMARY KEY (receipt_id, product_id)" + ");"
      );
      tables.executeUpdate();
      PreparedStatement roles = connection.prepareStatement(
      "INSERT INTO Roles VALUES"
              + "    (DEFAULT, 'Manager')"
              + ",   (DEFAULT, 'Salesperson')"
              + ",   (DEFAULT, 'Accountant');"
      );
      roles.executeUpdate();

      //Default manager
      AccountDAOImplementation.getInstance().addAccount(new Manager("admin"), "admin");
    }
  }
}