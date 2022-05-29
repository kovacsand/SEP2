package client.view.receipt;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.transferobjects.Receipt;

import java.util.ArrayList;

//TODO live update for Receipts
/**
 * A class that controls the GUI for viewing Receipt
 * @author S2G2
 * @version 1.0
 */
public class ReceiptViewController implements ViewController
{
  @FXML private TableView<Receipt> receiptTable;
  @FXML private TableColumn<Receipt, String> idColumn;
  @FXML private TableColumn<Receipt, String> salespersonColumn;
  @FXML private TableColumn<Receipt, String> dateColumn;
  @FXML private TableColumn<Receipt, String> timeColumn;
  @FXML private TableColumn<Receipt, String> totalPriceColumn;

  private ViewHandler vh;
  private ReceiptViewModel viewModel;
  private ArrayList<Receipt> receipts;

  public void init (ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh=vh;
    receipts = new ArrayList<>();
    viewModel = vmf.getReceiptViewModel();

    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    salespersonColumn.setCellValueFactory(new PropertyValueFactory<>("salespersonName"));
    dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
    totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

    fillReceiptTable();
  }

  /**
   * Populates the receipt table with data from the database
   */
  private void fillReceiptTable()
  {
    receiptTable.getItems().clear();
    receipts = viewModel.getAllReceipts();
    for (Receipt receipt:receipts)
      receiptTable.getItems().add(receipt);
    receiptTable.getSortOrder().add(idColumn);
  }

  /**
   * On Back button press opens the Main window
   */
  @FXML private void onBackButton()
  {
    vh.openView("Main");
  }

  /**
   * On View button press opens details about the selected Receipt
   */
  @FXML private void onViewButton ()
  {
    Receipt selectedReceipt = receiptTable.getSelectionModel().getSelectedItem();
    if (selectedReceipt == null)
    {
      showErrorWindow("Please select the Receipt for which you want to see its details.");
      return;
    }
    vh.setDetailedReceipt(selectedReceipt);
    vh.openView("Details");
  }
}