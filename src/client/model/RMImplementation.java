package client.model;

import client.networking.Client;
import shared.transferobjects.Receipt;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class RMImplementation implements ReceiptModel
{
  private final PropertyChangeSupport support;
  private final Client client;

  public RMImplementation(Client client)
  {
    support = new PropertyChangeSupport(this);
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

  @Override public void addListener(String propertyName,
      PropertyChangeListener listener)
  {

  }

  @Override public void removeListener(String propertyName,
      PropertyChangeListener listener)
  {

  }
}
