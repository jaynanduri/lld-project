package notifyMeFeature;

public interface ObservableStock {
  void add(User user);
  void remove(User user);
  void update();
  void increaseCount(int count);
  void decreaseCount(int count);
  int getCount();
}
