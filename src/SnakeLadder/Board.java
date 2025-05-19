package SnakeLadder;

import java.util.HashMap;
import java.util.Map;

public class Board {
  private static Board board; // for singleton object
  private int size;
  private Map<Integer, Integer> snakes;
  private Map<Integer, Integer> ladders;

  private Board(int size) {
    this.size = size;
    snakes = new HashMap<>();
    ladders = new HashMap<>();
  }
  // singleton method initiate game board
  public static Board getBoard(int size) {
    if (board == null) {
      board = new Board(size);
    }
    return board;
  }


  public Map<Integer, Integer> getSnakes() {
    return snakes;
  }

  public Map<Integer, Integer> getLadders() {
    return ladders;
  }

  public int getSize() {
    return size;
  }

  public void addSnake(int x, int y) {
    snakes.put(x, y);
  }

  public void addLadder(int x, int y) {
    ladders.put(x, y);
  }
}
