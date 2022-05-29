package client.model;

import shared.transferobjects.Receipt;
import shared.utils.Subject;

import java.beans.PropertyChangeEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Interface used for getting Receipts and calculating income
 * @author S2G2
 * @version 1.0
 */
public interface ReceiptModel extends Subject
{
  /**
   * Calls the method to get all receipts and returns them as an arraylist of receipt objects
   * @return ArrayList<Receipt>
   */
  ArrayList<Receipt> getAllReceipts();

  /**
   * Fires a change when Product Data changes
   * @param evt ProductData is changing
   */
  void onReceiptDataChange(PropertyChangeEvent evt);

  /**
   * Call the method on the server, passing itself as a ClientCallBack object to
   * be added as looking at the receipt view
   */
  void registerReceiptViewer();

  /**
   * Calls the method on the server,passing itself as a ClientCallBack object to
   * be removed as looking at the receipt view
   */
  void deregisterReceiptViewer();

  /**
   * Calls generateIncome method in the client to get the income in the given timeframe
   * @param startDate starting date of the given timeframe
   * @param endDate end date of the given timeframe
   * @return total income
   */
  double generateIncome(LocalDateTime startDate, LocalDateTime endDate);
}
