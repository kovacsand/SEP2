package client.model;

import client.networking.Client;
import shared.transferobjects.Accountant;
import shared.transferobjects.Manager;
import shared.transferobjects.Salesperson;
import shared.transferobjects.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Class that implements the AccountModel interface on client side.
 * @author S2G2
 * @version 1.0
 */
public class AMImplementation implements AccountModel
{
  private final PropertyChangeSupport support;
  private final Client client;

  /**
   * One-argument constructor initializing the AccountModel implementation class,
   * also initializes the PropertyChangeSupport object and adds listeners for onLoginReply() method
   * @param client Client object that will pass the necessary information
   */
  public AMImplementation(Client client)
  {
    support = new PropertyChangeSupport(this);
    this.client = client;
    client.registerClient();
    client.addListener("LoginFailed", this::onLoginReply);
    client.addListener("LoginSuccessful", this::onLoginReply);
    client.addListener("AccountAdded", this::onAddAccountReply);
    client.addListener("AccountExists", this::onAddAccountReply);
  }

  /**
   * Listens to the result of login()
   * @param evt event that's being listened to
   */
  public void onLoginReply(PropertyChangeEvent evt)
  {
    loginReply(evt.getNewValue() != null, (User) evt.getNewValue());
  }

  /**
   * Listens to the result of addAccount()
   * @param evt event that's being listened to
   */
  public void onAddAccountReply(PropertyChangeEvent evt)
  {
    addAccountReply(evt.getNewValue() != null, (String) evt.getNewValue());
  }

  @Override public void login(String username, String password)
  {
    client.login(username, password);
  }

  @Override public void addAccount(String username, String password, String role)
  {
    switch (role)
    {
      case "Accountant":
        client.addAccount(new Accountant(username, password), password);
        break;
      case "Manager":
        client.addAccount(new Manager(username, password), password);
        break;
      case "Salesperson":
        client.addAccount(new Salesperson(username, password), password);
        break;
    }
  }

  @Override public void loginReply(boolean successful, User user)
  {
    if (successful)
      support.firePropertyChange("LoginSuccessful", null, user);
    else
      support.firePropertyChange("LoginFailed", null, null);
  }

  @Override public void addAccountReply(boolean successful, String username)
  {
    if (successful)
      support.firePropertyChange("AccountAdded", null, username);
    else
      support.firePropertyChange("AccountExists", null, null);
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
