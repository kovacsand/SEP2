package shared.transferobjects;

import java.io.Serializable;

public abstract class User implements Serializable
{
  private String username;
  private String password;

  public User(String username, String password)
  {
    this.username = username;
    this.password = password;
  }

  public String getUsername()
  {
    return username;
  }
}
