package client.model;

import client.networking.Client;
import shared.transferobjects.Accountant;
import shared.transferobjects.Manager;
import shared.transferobjects.Salesperson;
import shared.transferobjects.User;

/**
 * Class that implements the AccountModel interface on client side.
 * @author S2G2
 * @version 1.0
 */
public class AMImplementation implements AccountModel
{
  private final Client client;

  /**
   * One-argument constructor initializing the AccountModel implementation class
   * @param client Client object that will pass the necessary information
   */
  public AMImplementation(Client client)
  {
    this.client = client;
  }

  @Override public User login(String username, String password)
  {
    return client.login(username, password);
  }

  @Override public User addAccount(String username, String password, String role)
  {
    User addedUser = null;
    switch (role)
    {
      case "Accountant":
        addedUser = client.addAccount(new Accountant(username), password);
        break;
      case "Manager":
        addedUser = client.addAccount(new Manager(username), password);
        break;
      case "Salesperson":
        addedUser = client.addAccount(new Salesperson(username), password);
        break;
    }
    return addedUser;
  }
}