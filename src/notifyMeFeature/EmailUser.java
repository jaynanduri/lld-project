package notifyMeFeature;

public class EmailUser implements User {
  private final ObservableStock observableStock;

  public EmailUser(ObservableStock observableStock) {
    this.observableStock = observableStock;
  }
  @Override
  public void update() {
    System.out.println("EmailUser.update: " + observableStock.getCount());
  }
}
