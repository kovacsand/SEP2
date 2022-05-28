package client.view.main;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import shared.transferobjects.Manager;
import shared.transferobjects.Salesperson;
import shared.transferobjects.User;

/**
 * ViewController class of the GUI for the main window
 * @author S2G2
 * @version 1.2
 */
public class MainViewController implements ViewController
{
  private ViewHandler vh;
  @FXML private Button addAccountButton;
  @FXML private Button addProductButton;
  @FXML private Button salesButton;
  @FXML private Button stockButton;
  @FXML private Button receiptButton;
  @FXML private Button salesReportButton;

  @FXML private Label welcomeMessageLabel;

  /**
   * Initializing ViewHandler
   * @param vh to change views accordingly on the button press
   * @param vmf to get viewModel, so that view does not directly talk to the model, but everything goes through view-model (MVVM)
   */
  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh=vh;

    //Setting all the buttons disabled
    addAccountButton.setDisable(true);
    addProductButton.setDisable(true);
    salesButton.setDisable(true);
    stockButton.setDisable(true);
    receiptButton.setDisable(true);
    salesReportButton.setDisable(true);

    //Setting the welcome message
    User loggedInUser = vh.getUser();
    String name = loggedInUser.getUsername();
    String role = (loggedInUser instanceof Manager ? "Manager" :
        loggedInUser instanceof Salesperson ? "Salesperson" : "Accountant");
    welcomeMessageLabel.setText(String.format("Welcome, %s (%s)", name, role));

    //Enabling buttons based on role
    switch (role)
    {
      case "Manager":
        addAccountButton.setDisable(false);
        addProductButton.setDisable(false);
        stockButton.setDisable(false);
        break;
      case "Salesperson":
        stockButton.setDisable(false);
        salesButton.setDisable(false);
        break;
      case "Accountant":
        receiptButton.setDisable(false);
        salesReportButton.setDisable(false);
        break;
    }
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

  /**
   * On View Stock button press
   */
  @FXML private void onViewStockButton()
  {
    vh.openView("Stock");
  }

  /**
   * On Make a sale button press
   */
  @FXML private void onSalesButton()
  {
    vh.openView("Sale");
  }

  /**
   * On View Receipts button press
   */
  @FXML private void onViewReceiptsButton()
  {
    vh.openView("Receipt");
  }

  /**
   * On Sales Reports button press
   */
  @FXML private void onSalesReportButton() {vh.openView("SalesReport");}
}
