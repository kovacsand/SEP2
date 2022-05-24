package server.model.mediator;

import shared.transferobjects.Product;
import shared.transferobjects.Receipt;
import shared.transferobjects.Sale;
import shared.transferobjects.Salesperson;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class SaleDAOImplementation implements SaleDAO
{
  private static SaleDAOImplementation instance;
  private SaleDAOImplementation() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }
  public static synchronized SaleDAOImplementation getInstance()
      throws SQLException
  {
    if(instance == null)
      instance = new SaleDAOImplementation();
    return instance;
  }
  @Override public Receipt addSale(Sale sale, Salesperson salesperson)
      throws SQLException
  {
    Receipt receipt = null;
    Sale newSale = null;
    ResultSet resultSet = null;
    int id = 0;
    HashMap<Product, Integer> products = sale.getProducts();
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO Sales VALUES (DEFAULT, ?, ?, ?);");
      statement.setString(1, salesperson.getUsername());
      statement.setString(2, String.valueOf(LocalDateTime.now()));
      statement.setDouble(3, sale.getTotalPrice());
      statement.executeUpdate();
      resultSet = statement.getGeneratedKeys();
      if(resultSet.next())
        id = resultSet.getInt("id");
      statement = connection.prepareStatement("INSERT INTO SoldProducts VALUES(?, ?, ?);");
      for (Map.Entry<Product, Integer> productsInHash : products.entrySet())
      {
        statement.setInt(1, id);
        statement.setInt(2, productsInHash.getKey().getId());
        statement.setInt(3, productsInHash.getValue());
        statement.executeUpdate();
      }
      statement = connection.prepareStatement(";");
      receipt = new Receipt(id, salesperson, );



    }
    return receipt;
  }

}
