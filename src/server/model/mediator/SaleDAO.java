package server.model.mediator;

import shared.transferobjects.Receipt;
import shared.transferobjects.Sale;
import shared.transferobjects.Salesperson;

import java.sql.SQLException;

public interface SaleDAO extends DAOInterface
{
  Receipt addSale(Sale sale, Salesperson salesperson) throws SQLException;
}
