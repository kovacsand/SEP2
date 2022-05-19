package shared.transferobjects;

/**
 * Class containing information about the users assigned the role Accountant, extends User class
 * @author S2G2
 * @version 1.0
 */
public class Accountant extends User
{
  /**
   * Two-argument constructor initializing the Accountant object, extends User class
   * @param username username of the accountant (inherited from User class)
   */
  public Accountant(String username)
  {
    super(username);
  }
}
