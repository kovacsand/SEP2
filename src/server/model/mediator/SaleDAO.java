package server.model.mediator;

import shared.transferobjects.Basket;
import shared.transferobjects.Receipt;
import shared.transferobjects.Salesperson;

import java.sql.SQLException;

/**
 * Interface for Database Access Object for creating Receipts, SoldProducts
 * @author S2G2
 * @version 1.0
 */
public interface SaleDAO extends DAOInterface
{
  /**
   * Method creating new Receipt and new SoldProducts entries connected to it.
   * @param basket the products of the sale
   * @param salesperson the employee making the sale
   * @return the newly created Receipt, null if there was a problem
   * @throws SQLException if something is wrong with the database
   */
  Receipt addSale(Basket basket, Salesperson salesperson) throws SQLException;
}
