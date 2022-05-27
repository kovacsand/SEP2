package client.view.salesreport;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

/**
 * ViewController class of the GUI for SalesReportView
 * @author S2G2
 * @version 1.0
 */
public class SalesReportViewController implements ViewController
{
  private SalesReportViewModel viewModel;
  private ViewHandler vh;

  @FXML private Label startDate;
  @FXML private Label endDate;
  @FXML private Label totalIncome;

  @FXML private DatePicker pickStartDate;
  @FXML private DatePicker pickEndDate;

  @FXML public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    this.viewModel = vmf.getSalesReportViewModel();

    startDate.setText(null);
    endDate.setText(null);
    totalIncome.setText(null);

    pickStartDate.valueProperty().bindBidirectional(viewModel.startDateProperty());
    pickEndDate.valueProperty().bindBidirectional(viewModel.endDateProperty());
    viewModel.totalIncomeProperty().bindBidirectional(totalIncome.textProperty());
  }


  @FXML private void onCreateReportButton()
  {
    if (viewModel.startDateProperty().getValue() == null ||
        viewModel.endDateProperty().getValue() == null)
      showErrorWindow("Please select both dates", "One or more fields are empty");
    else
    {
      viewModel.generateIncome();
    }
  }

  @FXML private void onBackButton()
  {
    vh.openView("Main");
  }

}
