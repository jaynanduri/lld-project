package bookManagementService;

import java.util.ArrayList;
import java.util.List;

public class BooksCatalog {
  private final List<Book> books;
  public BooksCatalog() {
    this.books = new ArrayList<>();
  }
  public void addBook(Book book) {
    this.books.add(book);
  }
  public List<Book> getBooks() {
    return this.books;
  }
}
