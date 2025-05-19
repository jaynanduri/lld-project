package facade;

// 1. Subsystem Class: DvdPlayer
public class DvdPlayer {
  String description;
  int currentTrack;
  String movie;
  Amplifier amplifier;

  public DvdPlayer(String description, Amplifier amplifier) {
    this.description = description;
    this.amplifier = amplifier;
  }

  public void on() {
    System.out.println(description + " on");
  }

  public void off() {
    System.out.println(description + " off");
  }

  public void play(String movie) {
    this.movie = movie;
    System.out.println(description + " playing \"" + movie + "\"");
  }

  public void stop() {
    currentTrack = 0;
    System.out.println(description + " stopped \"" + movie + "\"");
  }

  public void pause() {
    System.out.println(description + " paused \"" + movie + "\"");
  }

  public void setTwoChannelAudio() {
    System.out.println(description + " setting two channel audio");
  }

  public void setSurroundSound() {
    System.out.println(description + " setting surround audio");
  }

  @Override
  public String toString() {
    return description;
  }
}