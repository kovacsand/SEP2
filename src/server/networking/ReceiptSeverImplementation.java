package server.networking;

import server.model.AccountModel;
import server.model.RMImplementation;
import server.model.ReceiptModel;
import shared.transferobjects.Receipt;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReceiptSeverImplementation implements ReceiptServer
{
  private final ReceiptModel receiptModel;
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
