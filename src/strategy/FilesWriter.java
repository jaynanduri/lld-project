package strategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FilesWriter implements Writer {
  private final BufferedWriter writer;
  public FilesWriter(String filename) throws IOException {
    try{
      this.writer = new BufferedWriter(new FileWriter(filename));
    } catch(IOException e){
      throw new IOException(e);
    }
  }
  @Override
  public void write(String text) throws IOException {
    this.writer.write(text);
  }
}
