package strategy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FilesReader implements Reader {
  private final BufferedReader fileReader;

  public FilesReader(String path) throws FileNotFoundException {
    try{
      fileReader = new BufferedReader(new FileReader(path));
    }
    catch (Exception e){
      throw new FileNotFoundException();
    }
  }
  @Override
  public String read() {
    StringBuilder buffer = new StringBuilder();
    try{
      String line;
      while ((line = fileReader.readLine()) != null){
        buffer.append(line);
      }

    } catch (Exception e){
      throw new RuntimeException(e);
    }
    return buffer.toString();
  }
}
