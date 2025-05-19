package SnakeLadder;

import java.util.Random;

public class LoadedDice implements DiceStrategy{
  private Random random;
  public LoadedDice() {
    random = new Random();
  }
  @Override
  public int rollDice() {
    return random.nextInt(3) + 1;
  }
}
