package decorator;

public class Mushroom extends Pizza{
  private final Pizza pizza;
  public Mushroom(Pizza pizza) {
    super("mushroom-topping", 1);
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
