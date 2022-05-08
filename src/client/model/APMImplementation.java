package client.model;

import client.networking.Client;
import shared.networking.Server;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class APMImplementation implements AddProductModel
{
  private PropertyChangeSupport support;
  private Client client;
  private Server server;
  public APMImplementation(Client client)
  {
    support=new PropertyChangeSupport(this);
    this.client=client;

  }

  @Override public void addProduct(String name, String desc, double price)
  {

  }

  @Override public void addProductReply(boolean successful, String name)
  {

  }

  @Override public void addListener(String propertyName,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(propertyName, listener);
  }

  @Override public void removeListener(String propertyName,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(propertyName,listener);
  }
}
