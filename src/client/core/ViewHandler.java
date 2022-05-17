package client.core;

import client.view.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import shared.transferobjects.User;

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
  private User user;


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
      user = null;
    String fxmlPath = "../view/" + view.toLowerCase() + "/" + view + "View.fxml" ;
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
   * Storing the user info of the employee who uses the system
   * This is used to get their role and username across all views
   * @param user of the employee
   */
  public void setUser(User user)
  {
    this.user = user;
  }

  /**
   * Getting the user of the system
   * @return the user info
   */
  public User getUser()
  {
    return user;
  }
}