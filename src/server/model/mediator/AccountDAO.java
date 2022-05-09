package server.model.mediator;

import shared.transferobjects.User;

import java.sql.SQLException;
import java.util.Map;

public interface AccountDAO
{
  Map<String, User> getAllUsers() throws SQLException;
}
