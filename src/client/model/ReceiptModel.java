package client.model;

import shared.transferobjects.Receipt;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Interface used for getting Receipts and calculating income
 * @author S2G2
 * @version 1.0
 */
public interface ReceiptModel
{
  /**
   * Calls the method to get all receipts and returns them as an arraylist of receipt objects
   * @return ArrayList<Receipt>
   */
  ArrayList<Receipt> getAllReceipts();

  /**
   * Calls generateIncome method in the client to get the income in the given timeframe
   * @param startDate starting date of the given timeframe
   * @param endDate end date of the given timeframe
   * @return total income
   */
  double generateIncome(LocalDateTime startDate, LocalDateTime endDate);
}
