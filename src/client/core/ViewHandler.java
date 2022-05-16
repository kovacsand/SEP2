package client.core;

import client.view.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

/**
 * A class to handle creating and changing views.
 * @author S2G2
 * @version 1.0
 */
public class ViewHandler
{
  private Stage stage;
  private ViewModelFactory vmf;
  private String role;


  /**
   * Two-argument constructor that takes a stage and viewmodel factory
   * and instantiates these.
   * @param stage
   * @param vmf
   */
  public ViewHandler(Stage stage, ViewModelFactory vmf)
  {
    this.stage = stage;
    this.vmf = vmf;
  }

  /**
   * Method to find open the fx views.
   * The 'view' string names the view, and points to the fxml file.
   * @param view
   */
  public void openView(String view)
  {
    FXMLLoader loader = new FXMLLoader();
    if (view.equals("Login"))
      role = null;
    String fxmlPath = "../view/" + view.toLowerCase() + "/" + view + ".fxml" ;
    loader.setLocation(getClass().getResource(fxmlPath));
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

  /**
   * Setting the role of the employee who uses the system
   * @param role of the employee
   */
  public void setRole(String role)
  {
    this.role = role;
  }

  /**
   * Getting the role of the employee who uses the system
   * @return the role of the employee
   */
  public String getRole()
  {
    return role;
  }
}