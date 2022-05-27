package client.model;

import shared.transferobjects.Receipt;
import shared.utils.Subject;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Model of the Receipt class
 * @Author S2G2
 * @Version 1.0
 */
public interface ReceiptModel extends Subject
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
