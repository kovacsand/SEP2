package client.core;

import client.model.AddProductModel;
import client.view.login.LoginViewModel;
import client.view.AddProduct.AddProductViewModel;

public class ViewModelFactory
{
  private LoginViewModel loginVM;
  private AddProductViewModel addProductVM;

  public ViewModelFactory(ModelFactory modelFactory)
  {
    loginVM = new LoginViewModel(modelFactory.getAccountModel());
  }

  public LoginViewModel getLoginViewModel()
  {
    return loginVM;
  }
  public AddProductViewModel getAddProductViewModel() {return addProductVM;}
}
