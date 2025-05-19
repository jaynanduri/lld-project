package composite;

import java.util.List;

public abstract class FileSystemComponent {
  protected String fileName;
  public FileSystemComponent(String fileName) {
    this.fileName = fileName;
  }
  public abstract void display(String indent);

  public void add(FileSystemComponent fileSystemComponent) {
    throw new UnsupportedOperationException("Cannot add components to a file");
  }
  public void remove(FileSystemComponent fileSystemComponent) {
    throw new UnsupportedOperationException("a File has not components");
  }
  public List<FileSystemComponent> getChildren() {
    throw new UnsupportedOperationException("Cannot get children of a file");
  }
}
