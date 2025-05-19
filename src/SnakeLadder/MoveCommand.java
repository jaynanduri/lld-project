package SnakeLadder;

public class MoveCommand implements Command {
  private Player player;
  private int steps;
  private Board board;

  public MoveCommand(Player player, int steps, Board board) {
    this.player = player;
    this.steps = steps;
    this.board = board;
  }

  @Override
  public void execute() {
    int newPos = player.getPos() + steps;
    if (newPos > board.getSize()) newPos = board.getSize();
    if (board.getSnakes().containsKey(newPos)) newPos = board.getSnakes().get(newPos);
    else if (board.getLadders().containsKey(newPos)) board.getLadders().get(newPos);
    player.setPos(newPos);
  }
}
