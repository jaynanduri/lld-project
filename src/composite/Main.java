package composite;

public class Main {
  public static void main(String[] args) {
    // Create the root directory
    FileSystemComponent root = new Directory("root");

    // Create subdirectories
    FileSystemComponent home = new Directory("home");
    FileSystemComponent users = new Directory("users");
    FileSystemComponent documents = new Directory("documents");
    FileSystemComponent downloads = new Directory("downloads");
    FileSystemComponent pictures = new Directory("pictures");

    // Create files
    FileSystemComponent readme = new File("README.txt");
    FileSystemComponent profile = new File("profile.jpg");
    FileSystemComponent report = new File("report.docx");
    FileSystemComponent vacation = new File("vacation.jpg");
    FileSystemComponent setup = new File("setup.exe");

    // Build the file system structure
    root.add(home);
    root.add(users);

    users.add(documents);
    users.add(downloads);
    users.add(pictures);

    home.add(readme); // Example: file in home directory

    documents.add(report);
    documents.add(profile); // Example: file in documents

    pictures.add(vacation);

    downloads.add(setup);

    // Demonstrate treating components uniformly
    System.out.println("--- Displaying File System Structure ---");
    root.display(""); // Start displaying from the root with no initial indent

    System.out.println("\n--- Adding a new file to documents ---");
    File letter = new File("letter.pdf");
    documents.add(letter);
    root.display(""); // Display again with the new file

    System.out.println("\n--- Removing setup.exe from downloads ---");
    downloads.remove(setup);
    root.display("");
    setup.add(report);
  }
}
