package decorator;

import java.util.List;

public class OrFilter implements Filter {
  private final Filter f1;
  private final Filter f2;
  public OrFilter(Filter f1, Filter f2) {
    this.f1 = f1;
    this.f2 = f2;
  }
  @Override
  public List<Result> filter(List<Result> results) {
    List<Result> list = f1.filter(results);
    list.addAll(f2.filter(results));
    return list;
  }
}
