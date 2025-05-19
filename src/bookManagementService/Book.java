package bookManagementService;

abstract public class Book {
  private String bookId;
  private String bookName;
  private String author;
  private String publisher;
  private Book_Status status;

  public Book(String bookId, String bookName, String author, String publisher) {
    this.bookId = bookId;
    this.bookName = bookName;
    this.author = author;
    this.publisher = publisher;
    this.status = Book_Status.AVAILABLE;
  }
  public String getBookId() {
    return bookId;
  }
  public String getBookName() {
    return bookName;
  }
  public String getAuthor() {
    return author;
  }
  public String getPublisher() {
    return publisher;
  }
  public Book_Status getStatus() {
    return status;
  }
  public void setStatus(Book_Status status) {
    this.status = status;
  }
  public boolean search(SearchStrategy s) {
    return true;
  }
}
