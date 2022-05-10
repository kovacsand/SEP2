package client.core;

import client.view.addaccount.AddAccountViewModel;
import client.view.login.LoginViewModel;
import client.view.addproduct.AddProductViewModel;

public class ViewModelFactory
{
  private LoginViewModel loginVM;
  private AddAccountViewModel addAccountVM;
  private AddProductViewModel addProductVM;


  public ViewModelFactory(ModelFactory modelFactory)
  {
    loginVM = new LoginViewModel(modelFactory.getAccountModel());
    addAccountVM = new AddAccountViewModel(modelFactory.getAccountModel());
    addProductVM=new AddProductViewModel(modelFactory.addProductModel());
  }

  public LoginViewModel getLoginViewModel()
  {
    return loginVM;
  }
  
  public AddAccountViewModel getAddAccountVM()
  {
    return addAccountVM;
  }
  
  public AddProductViewModel getAddProductViewModel() 
  {
     return addProductVM;
  }
}
