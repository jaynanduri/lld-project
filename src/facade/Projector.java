package facade;

// 1. Subsystem Class: Projector
public class Projector {
  String description;
  DvdPlayer dvd;

  public Projector(String description, DvdPlayer dvd) {
    this.description = description;
    this.dvd = dvd;
  }

  public void on() {
    System.out.println(description + " on");
  }

  public void off() {
    System.out.println(description + " off");
  }

  public void wideScreenMode() {
    System.out.println(description + " in widescreen mode (16:9 aspect ratio)");
  }

  public void tvMode() {
    System.out.println(description + " in tv mode (4:3 aspect ratio)");
  }

  @Override
  public String toString() {
    return description;
  }
}
