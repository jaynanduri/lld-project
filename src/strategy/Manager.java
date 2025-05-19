package strategy;

import java.io.IOException;

// Typical use case of Strategy pattern, where the concrete implementations are abstracted and makes
// the system extremely extensible.
public class Manager {
  private final Reader reader;
  private final Convertor convertor;
  private final Writer writer;

  public Manager(Reader reader, Convertor convertor, Writer writer) {
    this.reader = reader;
    this.convertor = convertor;
    this.writer = writer;
  }
  // Read from anywhere, write to anywhere and convert to any form.
  public void start() throws IOException {
    String text = reader.read();
    String alteredText = convertor.convert(text);
    writer.write(alteredText);
  }
}
