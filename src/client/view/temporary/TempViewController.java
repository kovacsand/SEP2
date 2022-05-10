package client.view.temp;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TempViewController implements ViewController
{
  private ViewHandler vh;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh=vh;
  }
  @FXML public void onAddProductButton ()
  {
    vh.openView("AddProduct");
  }
  @FXML public void onCreateAccountButton ()
  {
   vh.openView("AddAccount");
  }
}
