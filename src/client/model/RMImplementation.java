package client.model;

import client.networking.Client;
import shared.transferobjects.Receipt;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Class that implements the ReceiptModel interface
 * @author S2G2
 * @version 1.0
 */
public class RMImplementation implements ReceiptModel
{
  private final Client client;

  /**
   * One-argument constructor initializing the SaleModelImplementation class
   * @param client object that will pass the necessary information
   */
  public RMImplementation(Client client)
  {
    this.client = client;
  }

  @Override public ArrayList<Receipt> getAllReceipts()
  {
    return client.getAllReceipts();
  }

  @Override public double generateIncome(LocalDateTime startDate,
      LocalDateTime endDate)
  {
    return client.generateIncome(startDate, endDate);
  }
}