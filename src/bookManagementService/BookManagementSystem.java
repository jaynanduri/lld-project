package bookManagementService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class BookManagementSystem implements BookManagement {
  private final BooksCatalog catalog;
  private final AtomicReference<User> user = new AtomicReference<>(null);
  BookManagementSystem(BooksCatalog catalog) {
    this.catalog = catalog;
  }

  @Override
  public List<Book> getBooks(SearchStrategy s) {
    List<Book> res = new ArrayList<>();
    for (Book book : catalog.getBooks()) {
      if (book.search(s)) res.add(book);
    }
    return res;
  }

  @Override
  public boolean issuedBook(Book book, User user) {
    return false;
  }

  @Override
  public boolean returnedBook(User user) {
    return false;
  }
}
