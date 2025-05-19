package facade;

// 1. Subsystem Class: Amplifier
public class Amplifier {
  String description;
  DvdPlayer dvd;
  // Other components like Tuner, CDPlayer could be here

  public Amplifier(String description) {
    this.description = description;
  }

  public void on() {
    System.out.println(description + " on");
  }

  public void off() {
    System.out.println(description + " off");
  }

  public void setStereoSound() {
    System.out.println(description + " stereo mode on");
  }

  public void setSurroundSound() {
    System.out.println(description + " surround sound on (5.1 channel)");
  }

  public void setVolume(int level) {
    System.out.println(description + " setting volume to " + level);
  }

  public void setDvd(DvdPlayer dvd) {
    System.out.println(description + " setting DVD player to " + dvd);
    this.dvd = dvd;
  }
}