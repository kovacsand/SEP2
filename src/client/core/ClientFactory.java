package client.core;

import client.networking.Client;
import client.networking.RMIClient;

/**
 * A class factory for creating new RMIClient object
 * @author S2G2
 * @version 1.0
 */
public class ClientFactory
{
  private Client client;

  /**
   * Gets the RMIClient object if it exists, uses lazy instantiation to create one in case it's null
   * @return new RMIClient object
   */
  public Client getClient()
  {
    if (client == null)
      client = new RMIClient();
    return client;
  }
}
