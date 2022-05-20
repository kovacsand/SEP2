package client.view.main;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import shared.transferobjects.Accountant;
import shared.transferobjects.Manager;
import shared.transferobjects.Salesperson;

/**
 * ViewController class of the GUI for the main window
 * @author S2G2
 * @version 1.0
 */
public class MainViewController implements ViewController
{
  private ViewHandler vh;
  @FXML Button addProduct;
  @FXML Button addAccount;
  @FXML Label username;

  /**
   * Initializing ViewHandler
   * @param vh to change views accordingly on the button press
   * @param vmf to get viewModel, so that view does not directly talk to the model, but everything goes through view-model (MVVM)
   */
  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh=vh;
    if(vh.getUser() instanceof Manager)
    {
      //SHOW MANAGER THINGS
      //they're able to do anything
    }
    if (vh.getUser() instanceof Salesperson)
    {
      //SHOW SALESPERSON THINGS
      addAccount.setDisable(true);
      addProduct.setDisable(true);
    }
    if (vh.getUser() instanceof Accountant)
    {
      //SHOW ACCOUNTANT THINGS
      addAccount.setDisable(true);
      addProduct.setDisable(true);
    }
    username.setText(vh.getUser().getUsername());
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
    addProduct.setDisable(false);
    addAccount.setDisable(false);
    vh.openView("Login");
  }
  @FXML private void onViewStockButton()
  {
    vh.openView("Stock");
  }
}
