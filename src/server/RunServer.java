package server;

import server.networking.ServerImplementation;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class RunServer
{
  public static void main(String[] args) throws RemoteException, AlreadyBoundException
  {
    ServerImplementation server = new ServerImplementation();
    server.startServer();
  }
}
