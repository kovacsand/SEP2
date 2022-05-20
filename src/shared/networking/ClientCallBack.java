package shared.networking;

import java.io.Serializable;
import java.rmi.Remote;

/**
 *Interface for networking part of the application. It is used get messages from the client to the client-side model.
 * @author S2G2
 * @version 1.0
 */
public interface ClientCallBack extends Remote, Serializable
{

}
