package server.model;

import server.model.mediator.ReceiptDAOImplementation;
import shared.transferobjects.Receipt;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Class that implements the AccountModel interface on server side.
 * @author S2G2
 * @version 1.0
 */
public class RMImplementation implements ReceiptModel
{
  @Override public ArrayList<Receipt> getAllReceipts()
  {
    ArrayList<Receipt> allReceipts = null;
    try
    {
      allReceipts = ReceiptDAOImplementation.getInstance().getAllReceipts();
    }
    catch(SQLException e)
    {
      e.printStackTrace();
    }
    return allReceipts;
  }

  @Override public double generateIncome(LocalDateTime startDate,
      LocalDateTime endDate)
  {
    double income = 0;
    try
    {
      income = ReceiptDAOImplementation.getInstance().generateIncome(startDate, endDate);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return income;
  }
}