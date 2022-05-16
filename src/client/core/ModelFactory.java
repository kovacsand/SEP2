package client.core;

import client.model.AMImplementation;
import client.model.PMImplementation;
import client.model.AccountModel;
import client.model.ProductModel;

public class ModelFactory
{
  private AccountModel accountModel;
  private ProductModel addProductModel;

  public ModelFactory(ClientFactory clientFactory)
  {
    accountModel = new AMImplementation(clientFactory.getClient());
    addProductModel = new PMImplementation(clientFactory.getClient());
  }

  public AccountModel getAccountModel()
  {
    return accountModel;
  }

  public ProductModel addProductModel()
  {
    return addProductModel;
  }
}
