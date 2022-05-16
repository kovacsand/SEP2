package client.view.stock;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class StockViewController implements ViewController
{
  private ViewHandler vh;
  private ObservableList<StockViewModel> stockViewModels= FXCollections.observableArrayList(
      new StockViewModel("1", "apple", "green", "3.00", "7"),
      new StockViewModel("2", "beanz", "brown", "4.00", "6")
  );

  @FXML private TableView<StockViewModel> stockData;
  @FXML public TableColumn<StockViewModel, String> id;
  @FXML public TableColumn<StockViewModel, String> name;
  @FXML public TableColumn<StockViewModel, String> description;
  @FXML public TableColumn<StockViewModel, String> price;
  @FXML public TableColumn<StockViewModel, String> stock;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh=vh;
    stockData=new TableView<>();
    id=new TableColumn<>();
    name=new TableColumn<>();
    description=new TableColumn<>();
    price=new TableColumn<>();
    stock=new TableColumn<>();
    id.setCellFactory(new PropertyValueFactory("Id"));
    name.setCellFactory(new PropertyValueFactory("Name"));
    description.setCellFactory(new PropertyValueFactory("Description"));
    price.setCellFactory(new PropertyValueFactory("Price"));
    stock.setCellFactory(new PropertyValueFactory("Stock"));
    stockData.getItems().add(new StockViewModel("3","banana", "blue", "7.00","12"));
  }

  @FXML private void onBackButtonPress ()
  {
    vh.openView("Main");
  }

}
