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
  private int selectedReceiptId;
  @FXML private TableView<Receipt> receiptTable;
  @FXML private TableColumn<Receipt, String> idColumn;
  @FXML private TableColumn<Receipt, String> salespersonColumn;
  @FXML private TableColumn<Receipt, String> dateColumn;
  @FXML private TableColumn<Receipt, String> timeColumn;
  @FXML private TableColumn<Receipt, String> totalPriceColumn;


  public void init (ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh=vh;
    viewModel=vmf.getReceiptViewModel();
    idColumn.setCellFactory(new PropertyValueFactory("id"));
    salespersonColumn.setCellFactory(new PropertyValueFactory("salesperson"));
    dateColumn.setCellFactory(new PropertyValueFactory("date"));
    timeColumn.setCellFactory(new PropertyValueFactory("time"));
    totalPriceColumn.setCellFactory(new PropertyValueFactory("totalPrice"));
    receipts=new ArrayList<Receipt>();

    //fillReceiptTable();
  }
  private void fillReceiptTable()
  {
    receiptTable.getItems().clear();
    receipts=viewModel.getAllReceipts();
    populateReceiptTable(receipts);
    receiptTable.getSortOrder().add(idColumn);
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
    selectedReceiptId=receiptTable.getSelectionModel().getSelectedItem().getId();
    vh.openView("ReceiptDetails");
  }
}
