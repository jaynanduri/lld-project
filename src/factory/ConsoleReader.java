package factory;

public class ConsoleReader implements Reader {

  @Override
  public void read() {
    System.out.println("Enter command: Reading from console");
  }

  @Override
  public String toString() {
    return "ConsoleReader";
  }
}
