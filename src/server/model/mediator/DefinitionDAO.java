package server.model.mediator;

import java.sql.SQLException;

/**
 * Interface for Database Access Object defining the database
 * and filling it with synthetic data
 * @author S2G2
 * @version 1.0
 */
public interface DefinitionDAO extends DAOInterface
{
  /**
   * Defines the Database using DDL
   * @throws SQLException if something is wrong with the database
   */
  void defineDatabase() throws SQLException;
}