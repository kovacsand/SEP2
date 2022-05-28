package client.core;

import client.view.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import shared.transferobjects.Receipt;
import shared.transferobjects.User;

import java.io.IOException;

/**
 * A class to handle creating and changing views.
 * @author S2G2
 * @version 1.2
 */
public class ViewHandler
{
  private final Stage stage;
  private final ViewModelFactory vmf;
  private User user;
  private Receipt detailedReceipt;

  /**
   * Two-argument constructor that takes a stage and viewmodel factory
   * and instantiates these.
   * @param stage the JavaFX container for the GUI
   * @param vmf the ViewModelFactory used to create ViewModels
   */
  public ViewHandler(Stage stage, ViewModelFactory vmf)
  {
    this.stage = stage;
    this.vmf = vmf;
  }

  /**
   * Method to find and open the views.
   * The 'view' string names the view, and points to the fxml file.
   * @param view the name of the view that we want to open
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
    Scene scene = null;
    if (root != null)
      scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    //Center window
    Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
    stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
    stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
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
   * Setting a Receipt field in the class, it is used to access the selected
   * receipt across several views
   * @param detailedReceipt the chosen receipt
   */
  public void setDetailedReceipt(Receipt detailedReceipt)
  {
    this.detailedReceipt = detailedReceipt;
  }

  /**
   * Getting the user of the system
   * @return the user info
   */
  public User getUser()
  {
    return user;
  }

  /**
   * Getting the selected receipt
   * @return the Receipt object
   */
  public Receipt getDetailedReceipt()
  {
    return detailedReceipt;
  }
}