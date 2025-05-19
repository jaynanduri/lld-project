package singleton;

public class Main {
  public static void main(String[] args) {

    Board board = Board.getInstance();
    board.printBoard();
    board.makeMove(0, 0);
    board.printBoard();
    board = Board.getInstance();
    board.printBoard();
  }
}
