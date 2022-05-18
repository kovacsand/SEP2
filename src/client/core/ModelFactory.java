package client.core;

import client.model.AMImplementation;
import client.model.PMImplementation;
import client.model.AccountModel;
import client.model.ProductModel;

/**
 * A class factory for creating the client-side models
 * @author S2G2
 * @version 1.0
 */
public class ModelFactory
{
  private AccountModel accountModel;
  private ProductModel addProductModel;

  /**
   * One-argument constructor initializing the models
   * @param clientFactory necessary to create the models
   */
  public ModelFactory(ClientFactory clientFactory)
  {
    accountModel = new AMImplementation(clientFactory.getClient());
    addProductModel = new PMImplementation(clientFactory.getClient());
  }

  /**
   * Gets the account model
   * @return the AccountModel
   */
  public AccountModel getAccountModel()
  {
    return accountModel;
  }

  /**
   * Gets the product model
   * @return the ProductModel
   */
  public ProductModel addProductModel()
  {
    return addProductModel;
  }
}
