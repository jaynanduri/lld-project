package decorator;
import java.util.List;

public interface Filter {
  List<Result> filter(List<Result> results);
}
