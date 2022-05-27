package server.model;

import shared.transferobjects.Receipt;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Server-side model for receipts, between DAO and networking
 * @author S2G2
 * @version 1.0
 */
public interface ReceiptModel
{
  /**
   * Calls the method in the ReceiptDAO to get all receipts as an ArrayList
   * Uses a try/catch for SQLExceptions
   * @return an ArrayList<Receipt> of all receipts if successful, null if unsuccessful
   */
  ArrayList<Receipt> getAllReceipts();


  /**
   * Calls generateIncome method in the ReceiptDAO to get the income in the given timeframe
   * @param startDate starting date of the given timeframe
   * @param endDate end date of the given timeframe
   * @return total income
   */
  double generateIncome(LocalDateTime startDate, LocalDateTime endDate);
}
