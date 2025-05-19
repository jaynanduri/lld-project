package decorator;

import java.util.List;
import java.util.stream.Collectors;

public class AndFilter implements Filter {
  private final Filter f1;
  private final Filter f2;
  public AndFilter(Filter f1, Filter f2) {
    this.f1 = f1;
    this.f2 = f2;
  }

  @Override
  public List<Result> filter(List<Result> results) {
    List<Result> l1 = f1.filter(results);
    List<Result> l2 = f2.filter(results);
    return l1.stream().filter(l2::contains).collect(Collectors.toList());
  }
}
