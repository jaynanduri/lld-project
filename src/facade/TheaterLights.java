package facade;

// 1. Subsystem Class: TheaterLights
public class TheaterLights {
  String description;
  int level;

  public TheaterLights(String description) {
    this.description = description;
  }

  public void on() {
    System.out.println(description + " on");
  }

  public void off() {
    System.out.println(description + " off");
  }

  public void dim(int level) {
    this.level = level;
    System.out.println(description + " dimming to " + level + "%");
  }

  @Override
  public String toString() {
    return description;
  }
}