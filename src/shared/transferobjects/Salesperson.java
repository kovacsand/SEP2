package shared.transferobjects;

/**
 * Class containing information about the users assigned the role Salesperson, extends User class
 * @author S2G2
 * @version 1.0
 */
public class Salesperson extends User
{
  /**
   * Two-argument constructor initializing the Salesperson object
   * @param username username of the salesperson (inherited from User class)
   */
  public Salesperson(String username)
  {
    super(username);
  }
}
