package decorator;

public class Cheese extends Pizza{
  private final Pizza pizza;
  public Cheese(Pizza pizza) {
    super("cheese-topping", 9);
    this.pizza = pizza;
  }

  @Override
  public String getName() {
    return super.getName() + " " + pizza.getName();
  }

  @Override
  public double getCost() {
    return super.getCost() + pizza.getCost();
  }
}
