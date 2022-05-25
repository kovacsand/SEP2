package server.model.mediator;

import shared.transferobjects.Receipt;
import shared.utils.Subject;

import java.sql.SQLException;
import java.util.ArrayList;

/**
  *
  * @author S2G2
  * @version 1.0
  * **/
public interface ReceiptDAO extends DAOInterface
{

 /**
  * Getting all receipts from the database
  * @return All receipts as an ArrayList of type Receipts
  */
 ArrayList<Receipt> getAllReceipts() throws SQLException;

 /**
  * Getting details of one receipt from database
  * @param receiptID the id of the requested receipt
  * @return Details of the receipt
  * @throws SQLException
  */
 Receipt getReceiptDetails(int receiptID) throws SQLException;
}
