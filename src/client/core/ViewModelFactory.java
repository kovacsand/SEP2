package client.core;

import client.view.addaccount.AddAccountViewModel;
import client.view.login.LoginViewModel;

public class ViewModelFactory
{
  private LoginViewModel loginVM;
  private AddAccountViewModel addAccountVM;

  public ViewModelFactory(ModelFactory modelFactory)
  {
    loginVM = new LoginViewModel(modelFactory.getAccountModel());
    addAccountVM = new AddAccountViewModel(modelFactory.getAccountModel());
  }

  public LoginViewModel getLoginViewModel()
  {
    return loginVM;
  }

  public AddAccountViewModel getAddAccountVM()
  {
    return addAccountVM;
  }
}
