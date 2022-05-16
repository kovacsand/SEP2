package client.view.stock;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;

public class StockViewController implements ViewController
{
  private ViewHandler vh;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh=vh;

  }

  public void onBackButtonPress ()
  {

  }
}
