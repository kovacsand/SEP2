package server.model.mediator;

import shared.transferobjects.Basket;
import shared.transferobjects.Receipt;
import shared.transferobjects.Salesperson;

import java.sql.SQLException;

public interface SaleDAO extends DAOInterface
{
  Receipt addSale(Basket basket, Salesperson salesperson) throws SQLException;
}
