package bookManagementService;

public class User extends Person {
  private Book currentIssuedBook;
  public User(String personId) {
    super(personId);
    this.personId = personId;
  }
  public Book getCurrentIssuedBook() {
    return currentIssuedBook;
  }
  public void setCurrentIssuedBook(Book book) {
    this.currentIssuedBook = book;
  }
}
