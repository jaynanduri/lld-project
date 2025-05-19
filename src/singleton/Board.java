package singleton;

public class Board {
  private static Board board; // make sure there is a single instance of an object at any time

  // tic-tac-toe game variables
  private Character[][] boardArray;
  private char currentPlayer;
  private boolean gameOver;

  private Board() {
    boardArray = new Character[3][3];
    for (int i = 0; i < boardArray.length; i++) {
      for (int j = 0; j < boardArray[0].length; j++) {
        boardArray[i][j] = null;
      }
    }
    currentPlayer = 'X';
    gameOver = false;
  }
  // static method to create the singleton instance (lazy initialization)
  public static synchronized Board getInstance() {
    if (board == null) {
      board = new Board();
    }
    return board;
  }

  // reset the game board
  public void resetBoard() {
    for (int i = 0; i < boardArray.length; i++) {
      for (int j = 0; j < boardArray[0].length; j++) {
        boardArray[i][j] = null;
      }
    }
    currentPlayer = 'X';
    gameOver = false;
  }

  // make a move on the board
  public void makeMove(int row, int col) {
    if (row < 0 || row >= boardArray.length || col < 0 || col >= boardArray[0].length) {
      System.out.println("Invalid move: row or col out of bounds, must be within 0 and 2");
    }
    if (boardArray[row][col] == null && !gameOver) {
      boardArray[row][col] = currentPlayer;
      if (checkWinner()) {
        gameOver = true;
        System.out.println("Player: " + currentPlayer + " wins!");
      }
      if (isBoardFull()) {
        gameOver = true;
        System.out.println("GameOver! ended in a draw");
      }
      currentPlayer = (currentPlayer == 'X' ? 'O': 'X');
      System.out.println("Move Made. Next Player: " + currentPlayer);

    }
    else System.out.println("Invalid move: Position occupied or game over");
  }

  private boolean checkWinner() {
    // checking rows
    for (Character[] characters : boardArray) {
      if (characters[0] != null && characters[0].equals(characters[1])
          && characters[1].equals(characters[2])) {
        return true;
      }
    }
    // checking cols
    for (int j = 0; j < boardArray[0].length; j++) {
      if (boardArray[0][j] != null && boardArray[0][j].equals(boardArray[1][j])
      && boardArray[1][j].equals(boardArray[2][j])) {
        return true;
      }
    }

    // checking diagonals
    if (boardArray[0][0] != null &&
        boardArray[0][0].equals(boardArray[1][1]) &&
        boardArray[1][1].equals(boardArray[2][2])) {
      return true;
    }

    return boardArray[0][2] != null &&
           boardArray[0][2].equals(boardArray[1][1]) &&
           boardArray[1][1].equals(boardArray[2][0]);
  }

  private boolean isBoardFull() {
    for (Character[] characters : boardArray) {
      for (int j = 0; j < boardArray[0].length; j++) {
        if (characters[j] == null) return false;
      }
    }
    return true;
  }
  // Print the current board state
  public void printBoard() {
    System.out.println("Current Board State:");
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (boardArray[i][j] == null) {
          System.out.print(" - ");
        } else {
          System.out.print(" " + boardArray[i][j] + " ");
        }
      }
      System.out.println();
    }
    System.out.println();
  }
}
