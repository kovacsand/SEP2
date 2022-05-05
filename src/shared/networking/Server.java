package shared.networking;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote
{
  void registerClient(ClientCallBack client) throws RemoteException;
  void unregisterClient(ClientCallBack client) throws RemoteException;
  AccountServer getAccountServer() throws RemoteException;
  WarehouseServer getWarehouseServer() throws RemoteException;
}
