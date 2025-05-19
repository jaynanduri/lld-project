package SnakeLadder;

import java.util.ArrayList;
import java.util.List;

public class Game {
  private List<Player> players;
  private int currentPlayerIndex;

  public Game() {
    players = new ArrayList<Player>();
    currentPlayerIndex = 0;
  }

  public void addPlayer(Player player) {
    players.add(player);
  }
  public void notifyAllPlayers(String message) {
    for (Player player : players) {
      player.update(message);
    }
  }

  public Player getCurrentPlayer() {
    return players.get(currentPlayerIndex);
  }

  public void nextTurn(){
    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
  }
}
