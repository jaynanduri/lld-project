package bookManagementService;

import java.util.List;

public interface BookManagement {
  boolean issuedBook(Book book, User user);
  boolean returnedBook(User user);
  List<Book> getBooks(SearchStrategy searchStrategy);
}
