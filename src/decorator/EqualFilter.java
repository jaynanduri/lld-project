package decorator;

import java.util.ArrayList;
import java.util.List;

public class EqualFilter implements Filter {
  private final ResultField field;
  private final String value;

  public EqualFilter(ResultField field, String value) {
    this.field = field;
    this.value = value;
  }
  @Override
  public List<Result> filter(List<Result> results) {
    List<Result> filteredResults = new ArrayList<>();
    for (Result result : results) {
      if (result.get(field).equals(value)) filteredResults.add(result);
    }
    return filteredResults;
  }
}
