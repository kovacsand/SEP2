package client;

import client.core.ClientFactory;
import client.core.ModelFactory;
import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Class extending the java.fx.Application initialising factories
 * and opening the Login window
 * @author S2G2
 * @version 1.0
 */
public class WMSApp extends Application
{
  @Override public void start(Stage stage)
  {
    ClientFactory clientFactory = new ClientFactory();
    ModelFactory modelFactory = new ModelFactory(clientFactory);
    ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);
    ViewHandler viewHandler = new ViewHandler(stage, viewModelFactory);
    viewHandler.openView("Login");
  }
}