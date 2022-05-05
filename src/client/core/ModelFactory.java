package client.core;

import client.model.AMImplementation;
import client.model.APMImplementation;
import client.model.AccountModel;
import client.model.AddProductModel;

public class ModelFactory
{
  private AccountModel accountModel;
  private AddProductModel addProductModel;

  public ModelFactory(ClientFactory clientFactory)
  {
    accountModel = new AMImplementation(clientFactory.getClient());
    addProductModel = new APMImplementation(clientFactory.getClient());
  }

  public AccountModel getAccountModel()
  {
    return accountModel;
  }

  public AddProductModel addProductModel()
  {
    return addProductModel;
  }
}
