package decorator;
import java.util.*;

// Need to perform something similar to select * from table where age > 25 and physics <= 75
public class Result {
  private final Map<ResultField, String> resultVals;
  public Result(Map<ResultField, String> resultVals) {
    this.resultVals = resultVals;
  }
  public String get(ResultField field) {
    return resultVals.get(field);
  }
}

// Expectation : Given a list of results apply some filters on top of it and return a subset of
// results.

