package factory;

public class ReaderFactory {
  // centralized object creation
  public static Reader getReader(String type){
    Reader reader = null;
    if (type.equalsIgnoreCase("console")){
      reader = new ConsoleReader();
    } else if (type.equalsIgnoreCase("drive")){
      reader = new DriveReader();
    }
    return reader;
  }
}
