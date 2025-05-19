/*
Required features
Board init(predefined size) - Singleton pattern
Players: Multiple players can play the game
Dice Roll: A player can roll a dice and move
Game logic: Movement of acc to dice roll and handling snake and laddder
 */

/*
 * Patterns involved -
 * 1. Singleton - For the creation of game board to ensure all players interact with the same board
 * 2. Factory Pattern - Creating snake and ladder objects
 * 3. Strategy pattern - For rolling the dice, can be changed dynamically
 * 4. Command Pattern - Encapsulate a request as a object to parameterize clients with queues, reqs
 * and operations
 * 5. Observe pattern - To notify all the players about their turns.
 */

// Class definitions
/*
1. Board
  - attributes (size, Map for snake pos, Map for ladder pos)
  - functions
    a. static Board getBoard(int size) - returns a gameBoard instance
    b. all getters for size, snakes and ladders
    c. void addSnake(int start, int end)
    d. void addLadder(int start, int end)

2. ObjectFactory for Snakes and Ladders
   a. static void createSnakes(Board b, List<int[]> snakes)
   b. static void createLadders(Board b, List<int[]> ladders)

3. Observe Pattern for Player Turns
    - create observe interface and make Player implement it. This has update method which is called
    in game
4. Game
  - attributes int currentPlayerIndex, List<Player> players
  - apis
    void addPlayer(Player p)
    void notifyPlayers()
    public Player getCurrentPlayer()
    public void nextTurn() - switch the currentPlayerIdx in a circular manner
5. Strategy design pattern for dice, can be further extended to create new dices
   - Interface DiceStrategy: int rollDice()
   - NormalDice implements DiceStrategy
      Attributes: Random random
      apis: int rollDice() - return a random value from the face of the dice
6. Command pattern to execute moves in the game.
  - Interface Command: void execute()
  - MoveCommand implements Command
    Attributes: Player p, int step, Board b (snakes and ladders)
    apis:
      void execute() - check if the player's position + step doesn't exceed boardSize,  then check
      for snakes and ladders in the board and then update the next position of player accordingly.

 */

package SnakeLadder;

import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    Board board = Board.getBoard(100);
    ObjectFactory.createSnakes(board, Arrays.asList(new int[][]{{16, 6}, {48, 26}, {49, 11},
            {56, 53}, {62, 19}, {64, 60}, {87, 24}, {93, 73}, {95, 75}, {98, 78}}));
    ObjectFactory.createLadders(board, Arrays.asList(new int[][]{{1, 38}, {4, 14}, {9, 31},
            {21, 42}, {28, 84}, {36, 44}, {51, 67}, {71, 91}, {80, 100}}));

    Game game = new Game();
    Player p1 = new Player("Jay");
    Player p2 = new Player("Bob");

    game.addPlayer(p1);
    game.addPlayer(p2);
    DiceStrategy dice = new NormalDice();

    while(true) {
      Player currentPlayer = game.getCurrentPlayer();
      int steps = dice.rollDice();
      Command c = new MoveCommand(currentPlayer, steps, board);
      c.execute();

      game.notifyAllPlayers(currentPlayer.getName() + " rolled a " + steps + " and moved to "
                            + currentPlayer.getPos());
      game.nextTurn();
      if (currentPlayer.getPos() == board.getSize()) {
        game.notifyAllPlayers(currentPlayer.getName() + " wins!");
        break;
      }

    }


  }
}

