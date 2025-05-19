package decorator;

import java.util.*;

public class GreaterThanFilter implements Filter {
  private final ResultField field;
  private final String val;

  public GreaterThanFilter(ResultField field, String value) {
    this.field = field;
    this.val = value;
  }

  @Override
  public List<Result> filter(List<Result> results) {
    List<Result> filteredResults = new ArrayList<>();
    for (Result res: results){
      int fieldVal = Integer.parseInt(res.get(field));
      if (fieldVal > Integer.parseInt(val)) filteredResults.add(res);
    }
    return filteredResults;
  }
}
