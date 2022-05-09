package shared.transferobjects;

/**
 * Class containing information about the users assigned the role Manager, extends User class
 * @author S2G2
 * @version 1.0
 */
public class Manager extends User
{
  /**
   * Two-argument constructor initializing the Manager object
   * @param username username of the manager (inherited from User class)
   * @param password password of the manager (inherited from User class)
   */
  public Manager(String username, String password)
  {
    super(username, password);
  }

  public void addUser(String username, String password, String role)
  {

  }
}
