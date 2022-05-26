package client.view.receipt;

import client.model.ReceiptModel;
import shared.transferobjects.Receipt;

import java.util.ArrayList;

public class ReceiptViewModel
{
  private final ReceiptModel model;
  public ReceiptViewModel (ReceiptModel model)
  {
    this.model=model;
  }
  public ArrayList<Receipt> getAllReceipts()
   {
     return model.getAllReceipts();
   }
}
