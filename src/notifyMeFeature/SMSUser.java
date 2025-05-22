package notifyMeFeature;

public class SMSUser implements User {
  private final ObservableStock stock;
  public SMSUser(ObservableStock stock) {
    this.stock = stock;
  }
  @Override
  public void update() {
    System.out.println("SMSUser.update: " + stock.getCount());
  }
}
