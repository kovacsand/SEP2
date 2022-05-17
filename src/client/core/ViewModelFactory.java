package client.core;

import client.view.addaccount.AddAccountViewModel;
import client.view.addproduct.AddProductViewModel;
import client.view.login.LoginViewModel;
import client.view.stock.StockViewModel;

/**
 * A class factory for creating view models
 * @author S2G2
 * @version 1.0
 */
public class ViewModelFactory
{
  private LoginViewModel loginVM;
  private AddAccountViewModel addAccountVM;
  private AddProductViewModel addProductVM;
  private StockViewModel stockVM;
  /**
   * One-argument constructor initializing the ViewModelFactory class
   * @param modelFactory  ModelFactory object that has the necessary get methods for each model
   *                      and initializes them
   */
  public ViewModelFactory(ModelFactory modelFactory)
  {
    loginVM = new LoginViewModel(modelFactory.getAccountModel());
    addAccountVM = new AddAccountViewModel(modelFactory.getAccountModel());
    addProductVM = new AddProductViewModel(modelFactory.addProductModel());
    stockVM = new StockViewModel(modelFactory.addProductModel());
  }

  /**
   * Gets the LoginViewModel object
   * @return new LoginViewModel object
   */
  public LoginViewModel getLoginViewModel()
  {
    return loginVM;
  }

  /**
   * Gets the AddAccountViewModel object
   * @return new AddAccountViewModel object
   */
  public AddAccountViewModel getAddAccountViewModel()
  {
    return addAccountVM;
  }

  /**
   * Gets the AddProductViewModel object
   * @return new AddProductViewModel object
   */
  public AddProductViewModel getAddProductViewModel()
  {
     return addProductVM;
  }
  
  public StockViewModel getStockViewModel()
  {
    return stockVM;
  }
}
