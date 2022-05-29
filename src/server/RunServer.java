package server;

import server.networking.ServerImplementation;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

/**
 * Class for launching the client-side application
 * @author S2G2
 * @version 1.0
 */
public class RunServer
{
  public static void main(String[] args) throws RemoteException, AlreadyBoundException
  {
    ServerImplementation server = new ServerImplementation();
    server.startServer();
  }
}