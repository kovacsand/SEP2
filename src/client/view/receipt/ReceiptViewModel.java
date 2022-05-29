package client.view.receipt;

import client.model.ReceiptModel;
import shared.transferobjects.Receipt;
import shared.utils.Subject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 * A class that determines the behaviour of the GUI while viewing receipts
 * @author S2G2
 * @version 1.0
 */
public class ReceiptViewModel implements PropertyChangeListener, Subject
{
  private final PropertyChangeSupport support;
  private final ReceiptModel model;

  /**
   * A one-argument constructor that initializes all fields
   * @param model the model to be used by the viewmodel
   */
  public ReceiptViewModel (ReceiptModel model)
  {
    this.support = new PropertyChangeSupport(this);
    this.model=model;
    model.addListener("ReceiptDataChanged", this);
  }

  /**
   * Getting all the receipts from the model depending on the user's role
   * @return an ArrayList of all the receipts
   */
  public ArrayList<Receipt> getAllReceipts()
   {
     return model.getAllReceipts();
   }

  /**
   * Registering the client for the live update feature
   */
  public void registerStockViewer()
  {
    model.registerReceiptViewer();
  }

  /**
   * Deregistering the client from the live update feature
   */
  public void deregisterStockViewer()
  {
    model.deregisterReceiptViewer();
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    support.firePropertyChange(evt);
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