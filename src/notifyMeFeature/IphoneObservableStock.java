package notifyMeFeature;

import java.util.ArrayList;
import java.util.List;

public class IphoneObservableStock implements ObservableStock {
  private String productName;
  private int productCount;
  private final List<User> obs;

  public IphoneObservableStock(String productName, int productCount) {
    this.productName = productName;
    this.productCount = productCount;
    this.obs = new ArrayList<>();
  }

  @Override
  public void add(User user) {
    this.obs.add(user);
  }

  @Override
  public void remove(User user) {
    this.obs.remove(user);
  }

  @Override
  public void update() {
    for(User user : obs) {
      user.update();
    }
  }

  @Override
  public void increaseCount(int count) {
    this.productCount += count;
    this.update();
  }

  @Override
  public void decreaseCount(int count) {
    this.productCount -= count;
    this.update();
  }

  @Override
  public int getCount() {
    return this.productCount;
  }
}
