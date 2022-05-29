package client.view.receipt;

import client.model.ReceiptModel;
import shared.transferobjects.Receipt;

import java.util.ArrayList;

/**
 * A class that determines the behaviour of the GUI while viewing receipts
 * @author S2G2
 * @version 1.0
 */
public class ReceiptViewModel
{
  private final ReceiptModel model;

  /**
   * A one-argument constructor that initializes all fields
   * @param model the model to be used by the viewmodel
   */
  public ReceiptViewModel (ReceiptModel model)
  {
    this.model=model;
  }

  /**
   * Getting all the receipts from the model depending on the user's role
   * @return an ArrayList of all the receipts
   */
  public ArrayList<Receipt> getAllReceipts()
   {
     return model.getAllReceipts();
   }
}