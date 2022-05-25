package server.networking;

import shared.transferobjects.Receipt;

import java.util.ArrayList;

/**
 * Server-side class between the receipt model and the RMI client
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
   * calls the method in the ReceiptModel to request details of one receipt
   * @param receiptID id of the requested receipt
   * @return requested receipt as an Receipt object
   */
  Receipt getReceiptDetails(int receiptID);


}
