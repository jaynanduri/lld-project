package decorator;

import java.util.*;

public class LessThanFilter implements Filter {

  private final ResultField field;
  private final String val;

  public LessThanFilter(ResultField field, String val) {
    this.field = field;
    this.val = val;
  }

  @Override
  public List<Result> filter(List<Result> results) {
    List<Result> filteredResults = new ArrayList<>();
    for (Result res: results) {
      int fieldVal = Integer.parseInt(res.get(field));
      if (fieldVal < Integer.parseInt(val)) filteredResults.add(res);

    }
    return filteredResults;
  }
}
