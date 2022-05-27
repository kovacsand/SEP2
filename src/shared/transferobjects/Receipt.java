package shared.transferobjects;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Receipt class responsible for generating receipt when the sale is made
 * @author S2G2
 * @version 1.0
 */
public class Receipt implements Serializable
{
  private int id;
  private Basket basket;
  private LocalDateTime dateTime;
  private Salesperson salesperson;

  /**
   * Two-argument constructor initializing the Receipt object, also sets dateTime to LocalDateTime.now()
   * @param salesperson User that made the sale
   * @param basket object that Receipt corresponds to
   */
  public Receipt(Salesperson salesperson, Basket basket)
  {
    this.basket = basket;
    this.salesperson = salesperson;
    dateTime = LocalDateTime.now();
  }

  /**
   * Four-argument constructor initializing the Receipt object
   * @param id of the Receipt
   * @param salesperson User that made the sale
   * @param basket object that Receipt corresponds to
   * @param dateTime date and time of the moment when the Sale was made
   */
  public Receipt(int id, Salesperson salesperson, Basket basket, LocalDateTime dateTime)
  {
    this.id = id;
    this.salesperson = salesperson;
    this.basket = basket;
    this.dateTime = dateTime;
  }

  /**
   * Get method to get the ID of the Receipt
   * @return ID of the Receipt
   */
  public int getId()
  {
    return id;
  }

  /**
   * Get method to get the Sale object of the Receipt
   * @return Sale object of the Receipt
   */
  public Basket getBasket()
  {
    return basket;
  }

  /**
   * Get method to get the date and the time of when the Receipt was created
   * @return the date and the time of when Receipt was createad
   */
  public LocalDateTime getDateTime()
  {
    return dateTime;
  }

  public String getDate()
  {
    return dateTime.getYear() + "/" + dateTime.getMonth() + "/" + dateTime.getDayOfMonth();
  }

  public String getTime()
  {
    return dateTime.getHour() + ":" + dateTime.getMinute() + ":" + dateTime.getSecond();
  }

  /**
   * Get method to get the User who made the sale
   * @return salesperson object that made the sale
   */
  public Salesperson getSalesperson()
  {
    return salesperson;
  }

  public String getSalespersonName()
  {
    return salesperson.getUsername();
  }

  public String getTotalPrice()
  {
    return basket.getTotalPrice() + "";
  }
}
