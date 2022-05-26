package client.view.receipt;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.transferobjects.Product;
import shared.transferobjects.Receipt;

import java.util.ArrayList;
import java.util.HashMap;

public class DetailsViewController implements ViewController
{
  private ViewHandler vh;
  private Receipt receipt;
  private int productListSize;
  private DetailsViewModel viewModel;
  private ArrayList<Product> productList;
  private ArrayList<Integer> productsQuantityList;
  @FXML private Label idLabel;
  @FXML private Label salespersonLabel;
  @FXML private Label dateLabel;
  @FXML private Label timeLabel;
  @FXML private Label salePriceLabel;
  @FXML private TableView<Product> receiptTable;
  @FXML private TableColumn<Product, String> productIdColumn;
  @FXML private TableColumn<Product, String> productNameColumn;
  @FXML private TableColumn<Integer, String> quantityColumn;
  @FXML private TableColumn<Product, String> unitPriceColumn;
  @FXML private TableColumn<Integer, String> totalPriceColumn;

  @Override public void init (ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh=vh;
    //viewModel=vmf.getDetailsViewModel();
    productIdColumn.setCellFactory(new PropertyValueFactory("id"));
    productNameColumn.setCellFactory(new PropertyValueFactory("name"));
    quantityColumn.setCellFactory(new PropertyValueFactory("quantity"));
    unitPriceColumn.setCellFactory(new PropertyValueFactory("unitPrice"));
    totalPriceColumn.setCellFactory(new PropertyValueFactory("totalPrice"));

    fillLabels();
    fillTable();
  }
  private void fillLabels()
  {
    idLabel.setText(""+receipt.getId());
    salespersonLabel.setText(receipt.getSalesperson().getUsername());
    dateLabel.setText(""+receipt.getDateTime().getYear()+"/"+receipt.getDateTime().getMonth()+"/"+receipt.getDateTime().getDayOfMonth());
    timeLabel.setText(""+receipt.getDateTime().getHour()+":"+receipt.getDateTime().getMinute()+":"+receipt.getDateTime().getSecond());
    salePriceLabel.setText(""+receipt.getSale().getTotalPrice());
  }
  private void fillTable()
  {
    receiptTable.getItems().clear();
    receipt=viewModel.getReceipt();
    populateReceiptTable(receipt.getSale().getProducts());
    receiptTable.getSortOrder().add(productIdColumn);
  }
  private void populateReceiptTable(HashMap<Product, Integer> products)
  {
    productListSize=0;
    for (Product i:products.keySet())
    {
      productList.add(i);
      productsQuantityList.add(products.get(i));
      productListSize++;
    }
    for (int i=0;i<=productListSize;i++)
    {
      receiptTable.getItems().add(productList.get(i));

    }
  }
  @FXML private void onBackButton()
  {
    vh.openView("ReceiptTable");
  }
}
