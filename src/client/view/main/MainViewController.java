package client.view.main;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * ViewController class of the GUI for the main window
 * @author S2G2
 * @version 1.0
 */
public class MainViewController implements ViewController
{
  private ViewHandler vh;

  /**
   * Initializing ViewHandler
   * @param vh to change views accordingly on the button press
   * @param vmf to get viewModel, so that view does not directly talk to the model, but everything goes through view-model (MVVM)
   */
  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh=vh;
  }
  /**
   * On Add Product button press
   */
  @FXML private void onAddProductButton ()
  {
    vh.openView("AddProduct");
  }

  /**
   * On Create Account button press
   */
  @FXML private void onCreateAccountButton ()
  {
   vh.openView("AddAccount");
  }

  /**
   * On Log out button press
   */
  @FXML private void onLogOutButton()
  {
    vh.openView("Login");
  }
}
