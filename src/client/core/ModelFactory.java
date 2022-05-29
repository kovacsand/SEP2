package client.core;

import client.model.*;

/**
 * A class factory for creating the client-side models
 * @author S2G2
 * @version 1.2
 */
public class ModelFactory
{
  private final AccountModel accountModel;
  private final ProductModel productModel;
  private final SaleModel saleModel;
  private final ReceiptModel receiptModel;

  /**
   * One-argument constructor initializing the models
   * @param clientFactory necessary to create the models
   */
  public ModelFactory(ClientFactory clientFactory)
  {
    accountModel = new AMImplementation(clientFactory.getClient());
    productModel = new PMImplementation(clientFactory.getClient());
    saleModel=new SMImplementation(clientFactory.getClient());
    receiptModel=new RMImplementation(clientFactory.getClient());
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
  public ProductModel getProductModel()
  {
    return productModel;
  }

  /**
   * Gets the sale model
   * @return the SaleModel object
   */
  public SaleModel getSaleModel()
  {
    return saleModel;
  }

  /**
   * Gets the receipt model
   * @return the ReceiptModel object
   */
  public ReceiptModel getReceiptModel()
  {
    return receiptModel;
  }
}