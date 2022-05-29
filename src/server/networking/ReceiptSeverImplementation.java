package server.networking;

import server.model.ReceiptModel;
import shared.transferobjects.Receipt;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Implementation of the ReceiptServer interface
 * @author S2G2
 * @version 1.0
 */
public class ReceiptSeverImplementation implements ReceiptServer
{
  private final ReceiptModel receiptModel;

  /**
   * One-argument constructor initializing the WarehouseServer implementation class.
   * @param receiptModel the model that will be used by the server.
   */
  public ReceiptSeverImplementation(ReceiptModel receiptModel)
  {
    this.receiptModel = receiptModel;
  }

  @Override public ArrayList<Receipt> getAllReceipts()
  {
    return receiptModel.getAllReceipts();
  }

  @Override public double generateIncome(LocalDateTime startDate,
      LocalDateTime endDate)
  {
    return receiptModel.generateIncome(startDate, endDate);
  }
}