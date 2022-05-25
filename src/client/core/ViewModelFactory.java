package client.core;

import client.view.addaccount.AddAccountViewModel;
import client.view.addproduct.AddProductViewModel;
import client.view.login.LoginViewModel;
import client.view.sale.SaleViewModel;
import client.view.stock.StockViewModel;

/**
 * A class factory for creating view models
 * @author S2G2
 * @version 1.1
 */
public class ViewModelFactory
{
  private final LoginViewModel loginVM;
  private final AddAccountViewModel addAccountVM;
  private final AddProductViewModel addProductVM;
  private final StockViewModel stockVM;
  private final SaleViewModel saleVM;

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
    saleVM=new SaleViewModel(modelFactory.getSaleModel(),
        modelFactory.addProductModel());
  }

  /**
   * Gets the LoginViewModel object
   * @return LoginViewModel object initialized in the constructor
   */
  public LoginViewModel getLoginViewModel()
  {
    return loginVM;
  }

  /**
   * Gets the AddAccountViewModel object
   * @return AddAccountViewModel object initialized in the constructor
   */
  public AddAccountViewModel getAddAccountViewModel()
  {
    return addAccountVM;
  }

  /**
   * Gets the AddProductViewModel object
   * @return AddProductViewModel object initialized in the constructor
   */
  public AddProductViewModel getAddProductViewModel()
  {
     return addProductVM;
  }

  /**
   * Gets the StockViewModel object
   * @return StockViewModel object initialized in the constructor
   */
  public StockViewModel getStockViewModel()
  {
    return stockVM;
  }

  /**
   * Gets the SaleViewModel object
   * @return SaleViewModel object initialized in the constructor
   */

  public SaleViewModel getSaleViewModel()
  {
    return saleVM;
  }
}
