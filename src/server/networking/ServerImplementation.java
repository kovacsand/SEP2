package server.networking;

import server.model.AMImplementation;
import server.model.APMImplementation;
import shared.networking.AccountServer;
import shared.networking.ClientCallBack;
import shared.networking.Server;
import shared.networking.WarehouseServer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ServerImplementation implements Server
{
  private AccountServer accountServer;
  private WarehouseServer warehouseServer;
  private Map<ClientCallBack, PropertyChangeListener> clients;

  public ServerImplementation() throws RemoteException
  {
    UnicastRemoteObject.exportObject(this, 0);
    clients = new HashMap<>();
  }

  public void startServer() throws RemoteException, AlreadyBoundException
  {
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind("Server", this);
  }

  @Override public void registerClient(ClientCallBack client) throws RemoteException
  {
    getAccountServer().registerClient(client);
    PropertyChangeListener listener = new PropertyChangeListener()
    {
      @Override public void propertyChange(PropertyChangeEvent evt)
      {
        try
        {
          //TODO respond to client
        } catch (Exception e)
        {
          e.printStackTrace();
          clients.remove(client);
        }
      }
    };
    clients.put(client, listener);

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
