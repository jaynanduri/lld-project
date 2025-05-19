package bookManagementService;

public class BookFactory {

  public static Book createBook(String title, String author, String isbn, String publisher,
                                String bookType) {
    if (bookType.equals("Comic")) {
      return new ComicBook(title, author, isbn, publisher);
    }
    return new OtherBook(title, author, isbn, publisher);
  }
}
