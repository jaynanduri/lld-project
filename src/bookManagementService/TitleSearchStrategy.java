package bookManagementService;

import java.util.List;

public class TitleSearchStrategy implements SearchStrategy {

  @Override
  public String searchBook(String keyword) {
    return keyword;
  }
}
