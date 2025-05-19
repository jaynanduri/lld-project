package decorator;

public class Main {
  public static void main(String[] args) {
    // wheat base cheese, mushroom, cheese pizza. Change the behavior of object at runtime
    // For frontend development, we would add components on top of each other. Thus changing the
    // behavior at runtime. Also, use decorator to prevent exponential class explosion.

    // classical setup: If you wrap an object with the concrete implementation, but the final object
    // remains to be the object of the super class.
    Pizza pizza = new Cheese(
            new Mushroom(
                    new Cheese(
                            new WheatBase()
                    )
            )
    );
    System.out.println(pizza.getCost());
    System.out.println(pizza.getName());
  }
}
