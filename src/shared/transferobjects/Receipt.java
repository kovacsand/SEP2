package shared.transferobjects;

import java.time.LocalDateTime;

public class Receipt
{
  /**
   * Receipt class responsible for generating receipt when the Sale is made
   *
   * @author S2G2
   * @version 1.0
   */
  private int id;
  private Sale sale;
  private LocalDateTime dateTime;
  private Salesperson salesperson;

  /**
   * Two-argument constructor initializing the Receipt object, also sets dateTime to LocalDateTime.now()
   * @param salesperson User that made the sale
   * @param sale object that Receipt corresponds to
   */
  public Receipt(Salesperson salesperson, Sale sale)
  {
    this.sale = sale;
    this.salesperson = salesperson;
    dateTime = LocalDateTime.now();
  }

  /**
   * Four-argument constructor initializing the Receipt object
   * @param id of the Receipt
   * @param salesperson User that made the sale
   * @param sale object that Receipt corresponds to
   * @param dateTime date and time of the moment when the Sale was made
   */
  public Receipt(int id, Salesperson salesperson, Sale sale, LocalDateTime dateTime)
  {
    this.id = id;
    this.salesperson = salesperson;
    this.sale = sale;
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
  public Sale getSale()
  {
    return sale;
  }

  /**
   * Get method to get the date and the time of when the Receipt was created
   * @return the date and the time of when Receipt was createad
   */
  public LocalDateTime getDateTime()
  {
    return dateTime;
  }

  /**
   * Get method to get the User who made the sale
   * @return salesperson object that made the sale
   */
  public Salesperson getSalesperson()
  {
    return salesperson;
  }
}
