package server.networking;

import shared.transferobjects.Receipt;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Interface for server-side to handle everything with the receipts.
 * @author S2G2
 * @version 1.0
 */
public interface ReceiptServer
{
  /**
   * calls the method in the ReceiptModel to request all receipts
   * @return all receipts as an ArrayList<Receipt>
   */
  ArrayList<Receipt> getAllReceipts();

  /**
   * Calls generateIncome method in the ReceiptModel to get the income in the given timeframe
   * @param startDate starting date of the given timeframe
   * @param endDate end date of the given timeframe
   * @return total income
   */
  double generateIncome(LocalDateTime startDate, LocalDateTime endDate);
}