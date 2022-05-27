package client.view.salesreport;

import client.model.ReceiptModel;
import javafx.application.Platform;
import javafx.beans.property.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SalesReportViewModel
{
  private final ReceiptModel model;

  private StringProperty totalIncome;
  private Property<LocalDate> startDate;
  private Property<LocalDate> endDate;


  public SalesReportViewModel (ReceiptModel receiptModel)
  {
    this.model = receiptModel;
    totalIncome = new SimpleStringProperty();
    startDate = new SimpleObjectProperty();
    endDate = new SimpleObjectProperty();
  }

  /**
   * Displays total income
   * @return total income
   */
  public StringProperty totalIncomeProperty()
  {
    return totalIncome;
  }

  /**
   * Getting LocalDate property of the startDate
   * @return start date
   */
  public Property<LocalDate> startDateProperty()
  {
    return startDate;
  }

  /**
   * Getting LocalDate property of the endDate
   * @return end date
   */
  public Property<LocalDate> endDateProperty()
  {
    return endDate;
  }

  /**
   * Calling generateIncome method on the model and setting totalIncome label
   */
  public void generateIncome()
  {
    LocalDateTime startTime = LocalDateTime.of(startDate.getValue(), LocalTime.of(0,0));
    LocalDateTime endTime = LocalDateTime.of(endDate.getValue(),LocalTime.of(0,0));
    Platform.runLater(()->
    totalIncome.setValue("DKK: " + model.generateIncome(startTime,endTime)));

  }
}
