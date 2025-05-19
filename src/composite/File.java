package composite;

public class File extends FileSystemComponent {
  public File(String name) {
    super(name);
  }
  @Override
  public void display(String indent) {
    System.out.println(indent + "+ " + this.fileName);
  }
}
