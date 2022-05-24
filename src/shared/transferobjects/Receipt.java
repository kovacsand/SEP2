package shared.transferobjects;

import java.time.LocalDateTime;

public class Receipt
{
  private int id;
  private Sale sale;
  private LocalDateTime dateTime;
  private Salesperson salesperson;

  public Receipt(Salesperson salesperson, Sale sale)
  {
    this.sale = sale;
    this.salesperson = salesperson;
    dateTime = LocalDateTime.now();
  }

  public Receipt(int id, Salesperson salesperson, Sale sale, LocalDateTime dateTime)
  {
    this.id = id;
    this.salesperson = salesperson;
    this.sale = sale;
    this.dateTime = dateTime;
  }

  public int getId()
  {
    return id;
  }

  public Sale getSale()
  {
    return sale;
  }

  public LocalDateTime getDateTime()
  {
    return dateTime;
  }

  public Salesperson getSalesperson()
  {
    return salesperson;
  }
}
