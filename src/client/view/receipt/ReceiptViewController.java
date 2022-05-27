package client.view.receipt;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.model.ReceiptModel;
import client.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.transferobjects.Receipt;

import java.util.ArrayList;

public class ReceiptViewController implements ViewController
{
  private ViewHandler vh;
  private ReceiptModel model;
  private ReceiptViewModel viewModel;
  private ArrayList<Receipt> receipts;
  @FXML private TableView<Receipt> receiptTable;
  @FXML private TableColumn<Receipt, String> idColumn;
  @FXML private TableColumn<Receipt, String> salespersonColumn;
  @FXML private TableColumn<Receipt, String> dateColumn;
  @FXML private TableColumn<Receipt, String> timeColumn;
  @FXML private TableColumn<Receipt, String> totalPriceColumn;


  public void init (ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh=vh;
    receipts = new ArrayList<>();
    viewModel = vmf.getReceiptViewModel();

    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    salespersonColumn.setCellValueFactory(new PropertyValueFactory<>("salespersonName"));
    dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    timeColumn.setCellValueFactory(new PropertyValueFactory("time"));
    totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

    receiptTable.getSortOrder().add(idColumn);
    fillReceiptTable();
  }
  private void fillReceiptTable()
  {
    receiptTable.getItems().clear();
    receipts = viewModel.getAllReceipts();
    populateReceiptTable(receipts);
  }
  private void populateReceiptTable(ArrayList<Receipt> receipts)
  {
    for (Receipt receipt:receipts)
      receiptTable.getItems().add(receipt);
  }
  @FXML private void onBackButton()
  {
    vh.openView("Main");
  }
  @FXML private void onViewButton ()
  {
    Receipt selectedReceipt = receiptTable.getSelectionModel().getSelectedItem();
    if (selectedReceipt == null)
      showErrorWindow("No Receipt selected!", "Please select the Receipt for which you want to see its details.");
    else
    {
      vh.setDetailedReceipt(selectedReceipt);
      vh.openView("Details");
    }
  }
}
