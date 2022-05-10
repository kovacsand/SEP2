package client.core;

import client.view.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{
  private Stage stage;
  private ViewModelFactory vmf;

  public ViewHandler(Stage stage, ViewModelFactory vmf)
  {
    this.stage = stage;
    this.vmf = vmf;
  }

  public void openView(String view)
  {
    FXMLLoader loader = new FXMLLoader();
    if (view.equals("Login"))
      loader.setLocation(getClass().getResource("../view/login/LoginView.fxml"));
    if(view.equals("AddAccount"))
      loader.setLocation(getClass().getResource("../view/addaccount/AddAccountView.fxml"));
    if(view.equals("AddProduct"))
    {
      loader.setLocation(getClass().getResource("../view/AddProduct/AddProductView.fxml"));
    }
    if(view.equals("Temp"))
    {
      loader.setLocation(getClass().getResource("../view/temp/temporaryGUI.fxml"));
    }
    Parent root = null;
      try
    {
      root = loader.load();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    ViewController viewController = loader.getController();
    stage.setTitle(view);
    viewController.init(this, vmf);
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }
}