package factory;

public class DriveReader implements Reader {
  @Override
  public void read() {
    System.out.println("Enter command: Reading from driver");
  }

  @Override
  public String toString() {
    return "DriveReader";
  }
}
