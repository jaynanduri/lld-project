package composite;

import java.util.ArrayList;
import java.util.List;

public class Directory extends FileSystemComponent{
  private final List<FileSystemComponent> components = new ArrayList<>();
  public Directory(String name) {
    super(name);
  }

  @Override
  public void display(String indent) {
    System.out.println(indent + "+ " + this.fileName + "/");
    for (FileSystemComponent component : components) {
      component.display(indent + " ");
    }
  }

  @Override
  public void add(FileSystemComponent fileSystemComponent) {
    this.components.add(fileSystemComponent);
  }
  public List<FileSystemComponent> getComponents() {
    return components;
  }

  @Override
  public void remove(FileSystemComponent fileSystemComponent) {
    this.components.remove(fileSystemComponent);
  }
}
