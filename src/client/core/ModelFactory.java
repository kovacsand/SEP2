package client.core;

import client.model.*;

/**
 * A class factory for creating the client-side models
 * @author S2G2
 * @version 1.0
 */
public class ModelFactory
{
  public SaleModel saleModel;
  private AccountModel accountModel;
  private ProductModel addProductModel;
  private ReceiptModel receiptModel;

  /**
   * One-argument constructor initializing the models
   * @param clientFactory necessary to create the models
   */
  public ModelFactory(ClientFactory clientFactory)
  {
    accountModel = new AMImplementation(clientFactory.getClient());
    addProductModel = new PMImplementation(clientFactory.getClient());
    saleModel=new SMImplementation(clientFactory.getClient());
  }

  /**
   * Gets the account model
   * @return the AccountModel object
   */
  public AccountModel getAccountModel()
  {
    return accountModel;
  }

  /**
   * Gets the product model
   * @return the ProductModel object
   */
  public ProductModel addProductModel()
  {
    return addProductModel;
  }

  /**
   * Gets the sale model
   * @return the SaleModel object
   */
  public SaleModel getSaleModel()
  {
    return saleModel;
  }
  public ReceiptModel getReceiptModel()
  {
    return receiptModel;
  }
}
