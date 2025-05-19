package factory;

public class Main {
  public static void main(String[] args) {
    /* antipattern for object creation, which needs to bo duplicated at all the places where we are
    creating the objects
    String s;
    if (s.equals("console")) new ConsoleReader();
    else if (s.equals("drive")) new DriveReader();
     */
    Reader reader = ReaderFactory.getReader("drive");
    System.out.println(reader.toString());
  }
}
