package shared.networking;

public interface Server
{
  void registerClient(ClientCallBack client);
  void unregisterClient(ClientCallBack client);
  AccountServer getAccountServer();
  WarehouseServer getWarehouseServer();
}
