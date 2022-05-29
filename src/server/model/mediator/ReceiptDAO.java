package server.model.mediator;

import shared.transferobjects.Receipt;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
  * Interface for Database Access Object accessing Receipts
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
  * Getting the income of the given timeframe
  * @param startDate starting date of the given timeframe
  * @param endDate end date of the given timeframe
  * @return total income
  */
  double generateIncome(LocalDateTime startDate, LocalDateTime endDate) throws SQLException;
}