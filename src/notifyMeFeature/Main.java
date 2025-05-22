package notifyMeFeature;
/*

Design and implement the "notify me" feature in an e-commerce website. User should be notified
when the product he is looking for has enough count.

ObservableStock(interface) -> IphoneProduct, BananaProduct, etc
  attr: List<Observer> obs, int count, string name
  - apis
  void add(Observer o)
  void remove(Observer o)
  void updateProductCount()
  int getProductCount()
  Communication(interface) -> Email, SMS
    attr: Product p
    -apis:
      void notify()

*/

public class Main {
  public static void main(String[] args) {
    ObservableStock stock = new IphoneObservableStock("Iphone", 20);
    User user = new EmailUser(stock);
    User user2 = new SMSUser(stock);
    stock.add(user);
    stock.add(user2);
    stock.increaseCount(1);
    stock.decreaseCount(1);
  }
}
