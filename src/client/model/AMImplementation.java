package client.model;

import client.networking.Client;
import shared.networking.Server;
import shared.transferobjects.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class AMImplementation implements AccountModel
{
  private PropertyChangeSupport support;
  private Client client;
  private Server server;



  public AMImplementation(Client client)
  {
    support=new PropertyChangeSupport(this);
    this.client=client;
    client.addListener("LoginFailed",this::onLoginReply);
    client.addListener("LoginSuccessful", this::onLoginReply);
  }

  public void onLoginReply(PropertyChangeEvent evt)
  {
    if (evt.getPropertyName().equals("LoginFailed"))
      loginReply(false, null);
    else
      loginReply(true, (User) evt.getNewValue());
  }

  @Override public void login(String username, String password)
  {
    client.login(username, password);
  }

  @Override public void addAccount(String username, String password,
      String role)
  {

  }

  @Override public void loginReply(boolean successful, User user)
  {
    if(successful)
      support.firePropertyChange("LoginSuccessful",null,user);
    else
      support.firePropertyChange("LoginFailed",null,null);
  }

  @Override public void addAccountReply(boolean successful, String username)
  {

  }

  @Override public void addListener(String propertyName,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(propertyName,listener);
  }

  @Override public void removeListener(String propertyName,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(propertyName, listener);
  }
}
