package client.model;

import client.networking.Client;
import shared.transferobjects.User;

public class AMImplementation implements AccountModel
{
  public AMImplementation(Client client)
  {

  }

  @Override public void login(String username, String password)
  {

  }

  @Override public void addAccount(String username, String password,
      String role)
  {

  }

  @Override public void loginReply(boolean successful, User user)
  {

  }

  @Override public void addAccountReply(boolean successful, String username)
  {

  }
}
