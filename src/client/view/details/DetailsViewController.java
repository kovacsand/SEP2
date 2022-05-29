package client.view.details;

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

/**
 * A class that controls the GUI for viewing details about a receipt
 * @author S2G2
 * @version 1.0
 */
public class DetailsViewController implements ViewController
{
  private ViewHandler vh;
  private Receipt receipt;

  @FXML private Label idLabel;
  @FXML private Label salespersonLabel;
  @FXML private Label dateLabel;
  @FXML private Label timeLabel;
  @FXML private Label salePriceLabel;
  @FXML private TableView<Product> basketTable;
  @FXML private TableColumn<Product, String> idColumn;
  @FXML private TableColumn<Product, String> nameColumn;
  @FXML private TableColumn<Product, String> quantityColumn;
  @FXML private TableColumn<Product, String> unitPriceColumn;
  @FXML private TableColumn<Product, String> totalPriceColumn;

  @Override public void init (ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    receipt = vh.getDetailedReceipt();
    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    unitPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

    fillLabels();
    fillTable();
  }

  /**
   * Changes the labels to show details about the Receipt
   */
  private void fillLabels()
  {
    idLabel.setText(""+receipt.getId());
    salespersonLabel.setText(receipt.getSalesperson().getUsername());
    dateLabel.setText(""+receipt.getDateTime().getYear()+"/"+receipt.getDateTime().getMonth()+"/"+receipt.getDateTime().getDayOfMonth());
    timeLabel.setText(""+receipt.getDateTime().getHour()+":"+receipt.getDateTime().getMinute()+":"+receipt.getDateTime().getSecond());
    salePriceLabel.setText(""+receipt.getBasket().getTotalPrice());
  }

  /**
   * Populates the table to show details about the Receipt's Basket
   */
  private void fillTable()
  {
    basketTable.getItems().clear();
    ArrayList<Product> products = new ArrayList<>();

    receipt.getBasket().getProducts().forEach((product, quantity) -> products.add(product));
    for (Product product : products)
      basketTable.getItems().add(product);

    basketTable.getSortOrder().add(idColumn);
  }

  /**
   * On Back button press opens the Main window
   */
  @FXML private void onBackButton()
  {
    vh.openView("Receipt");
  }
}
