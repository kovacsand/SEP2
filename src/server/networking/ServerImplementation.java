package server.networking;

import server.model.AMImplementation;
import server.model.APMImplementation;
import shared.networking.AccountServer;
import shared.networking.ClientCallBack;
import shared.networking.Server;
import shared.networking.WarehouseServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerImplementation implements Server
{
  private AccountServer accountServer;
  private WarehouseServer warehouseServer;

  public ServerImplementation() throws RemoteException
  {
    UnicastRemoteObject.exportObject(this, 0);
  }

  public void startServer() throws RemoteException, AlreadyBoundException
  {
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind("Server", this);
  }

  @Override public void registerClient(ClientCallBack client) throws RemoteException
  {
    //!!TODO
  }

  @Override public void unregisterClient(ClientCallBack client) throws RemoteException
  {
    //!!TODO
  }

  @Override public AccountServer getAccountServer() throws RemoteException
  {
    if(accountServer == null)
      accountServer = new AccountServerImplementation(new AMImplementation());
    return accountServer;
  }

  @Override public WarehouseServer getWarehouseServer() throws RemoteException
  {
    if(warehouseServer == null)
      warehouseServer = new WarehouseServerImplementation(new APMImplementation());
    return warehouseServer;
  }
}
