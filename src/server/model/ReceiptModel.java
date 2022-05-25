package server.model;

import shared.transferobjects.Receipt;

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
   * Calls the method in the ReceiptDAO to get details about a single receipt
   * Uses a try/catch for SQLExceptions
   * @param receiptID the id of the requested receipt
   * @return the requested receipt as a receipt object if successful, null if unsuccessful
   */
  Receipt getReceiptDetails(int receiptID);

}
