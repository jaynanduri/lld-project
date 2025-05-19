package SnakeLadder;

public class Player implements Observer {
  private String name;
  private int pos;

  public Player(String name) {
    this.name = name;
    this.pos = 0;
  }

  @Override
  public void update(String message) {
    System.out.println(name + ": " + message);
  }

  public String getName() {
    return name;
  }

  public int getPos() {
    return pos;
  }

  public void setPos(int pos) {
    this.pos = pos;
  }
}
