package client.model;

import client.networking.Client;
import shared.transferobjects.Receipt;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Class that implements the ReceiptModel interface
 * @author S2G2
 * @version 1.0
 */
public class RMImplementation implements ReceiptModel
{
  private final PropertyChangeSupport support;
  private final Client client;

  /**
   * One-argument constructor initializing the SaleModelImplementation class
   * @param client object that will pass the necessary information
   */
  public RMImplementation(Client client)
  {
    this.support = new PropertyChangeSupport(this);
    this.client = client;
    client.addListener("ReceiptDataChanged",this::onReceiptDataChange);
  }

  @Override public void onReceiptDataChange(PropertyChangeEvent evt)
  {
    support.firePropertyChange(evt);
  }

  @Override public void registerReceiptViewer()
  {
    client.registerReceiptViewer();
  }

  @Override public void deregisterReceiptViewer()
  {
    client.deregisterReceiptViewer();
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
    support.addPropertyChangeListener(propertyName, listener);
  }

  @Override public void removeListener(String propertyName,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(propertyName, listener);
  }
}