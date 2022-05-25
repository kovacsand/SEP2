package server.model;

import server.model.mediator.ReceiptDAOImplementation;
import shared.transferobjects.Receipt;

import java.sql.SQLException;
import java.util.ArrayList;

public class RMImplementation implements ReceiptModel
{
  @Override public ArrayList<Receipt> getAllReceipts()
  {
    ArrayList<Receipt> allReceipts = null;
    try{
      allReceipts = ReceiptDAOImplementation.getInstance().getAllReceipts();
    }
    catch(SQLException e)
    {
      e.printStackTrace();
    }
    return allReceipts;
  }

  @Override public Receipt getReceiptDetails(int receiptID)
  {
    Receipt receiptDetails = null;
    try{
      receiptDetails = ReceiptDAOImplementation.getInstance()
          .getReceiptDetails(receiptID);
    }
    catch(SQLException e)
    {
      e.printStackTrace();
    }
    return receiptDetails;
  }
}
