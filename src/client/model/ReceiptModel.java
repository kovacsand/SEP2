package client.model;

import shared.transferobjects.Receipt;
import shared.utils.Subject;

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
   * Calls the getReceiptDetails method in the client to get the details of the receipt
   * @param receiptID id of the requested receipt
   * @return the requested receipt object
   */
  Receipt getReceiptDetails(int receiptID);
}
