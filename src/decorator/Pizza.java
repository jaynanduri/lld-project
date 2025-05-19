package decorator;

public abstract class Pizza {
  private final String name;
  private final double cost;

  public Pizza(String name, double cost) {
    this.name = name;
    this.cost = cost;
  }
  public String getName() {
    return name;
  }
  public double getCost() {
    return cost;
  }
}
