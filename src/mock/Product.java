package mock;

public class Product {
  // product id needed for quicker lookup
  private String name;
  private double price;
  private int count;
  Product(String name, double price, int count) {
    this.name = name;
    this.price = price;
    this.count = count;
  }
  public double getPrice(){
    return this.price;
  }
  public int getCount() {return this.count;}

  public String getName() {return this.name;}

}
