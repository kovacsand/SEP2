package shared.transferobjects;

import java.io.Serializable;

/**
 * Class containing all the information about the users, superclass of: Accountant, Manager and Salesperson classes
 * @author S2G2
 * @version 1.0
 */
public abstract class User implements Serializable
{
  private String username;

  /**
   * Two-argument constructor initializing the User object
   * @param username username of the user
   */
  public User(String username)
  {
    this.username = username;
  }

  /**
   * Getting the username of the user
   * @return username of the user
   */
  public String getUsername()
  {
    return username;
  }
}
