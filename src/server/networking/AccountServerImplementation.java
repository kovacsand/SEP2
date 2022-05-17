package server.networking;

import server.model.AccountModel;
import shared.transferobjects.User;

/**
 * Implementation of the AccountServer interface
 * @author S2G2
 * @version 1.0
 */
public class AccountServerImplementation implements AccountServer
{
  private final AccountModel accountModel;

  /**
   * One-argument constructor initializing the AccountServer implementation class.
   * @param accountModel the model that will be used by the server.
   */
  public AccountServerImplementation(AccountModel accountModel)
  {
    this.accountModel = accountModel;
  }

  @Override public User login(String username, String password)
  {
    return accountModel.login(username, password);
  }

  @Override public User addAccount(User user, String password)
  {
    return accountModel.addAccount(user, password);
  }
}
