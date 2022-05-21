package client.view;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.scene.control.Alert;

/**
 * Interface for ViewController to make sure each ViewController implements this method.
 * @author S2G2
 * @version 1.0
 */
public interface ViewController
{
  /**
   * Initializing the view with its default values and binding the values with the properties from the ViewModel.
   * @param vh The ViewHandler handling transition between the different views.
   * @param vmf The ViewModelFactory creating the ViewModels for each view.
   */
  void init(ViewHandler vh, ViewModelFactory vmf);

  default void showErrorWindow(String headerText, String contentText)
  {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(headerText);
    alert.setContentText(contentText);
    alert.showAndWait();
  }
}
