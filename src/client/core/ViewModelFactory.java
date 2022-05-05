package client.core;

import client.view.login.LoginViewModel;

public class ViewModelFactory
{
  private LoginViewModel loginVM;

  public ViewModelFactory(ModelFactory modelFactory)
  {
    loginVM = new LoginViewModel(modelFactory.getAccountModel());
  }

  public LoginViewModel getLoginViewModel()
  {
    return loginVM;
  }
}
