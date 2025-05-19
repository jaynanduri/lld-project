///*
//package mock3;
//import java.util.*;
//
//import javax.naming.directory.DirContext;
//public class Req {
//
//}
//// Design a snake and ladder game
//*/
///*
// * Player //
// * Gameboard - 2 people
// * (Snakes, Ladder, OneMove) - Move
// * Dice
// *  - roll()
// */
//
//
//class Player{
//    private final Map<Integer, Integer> pos;
//    private final Queue<Integers> prevPos;
//    Player(int x, int y){
//        this.pos = new HashMap<>();
//        this.pos.put(x, y);
//    }
//
//    public Player movePlayer(int x, int y){
//        return new Player(x, y);
//    }
//
//
//
//
//}
//
//public interface Move{
//    public Player makeMove(Player p);
//}
//
//class SnakeMove implements Move{
//    private final int x;
//    private final int y;
//    SnakeMove(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }
//
//    public Player makeMove(Player p){
//        return p.movePlayer(x, y);
//    }
//
//    // player.notify()
//}
//
//// classes for LadderMove and SingleMove
//
//class GameBoard {
//    private final List<Move, Move> board;
//    // private final Map<String, Move> board;
//    private final int width;
//    private final int height;
//    private final Map<Integer, Player> players;
//    private int currPlayer;
//    private final Dice d;
//    GameBoard(Map<String, Move> board, int x, int y, Map<Integer, Player> players, Dice d){
//        //String x pos and y pos delimited by ",". String.split
//        // traverse the keyset and fill the gameboard accordingly
//    }
//
//    public boolean playerTurn(int playerId){
//        if (!players.containsKey(playerId) || playerId == currPlayer) throw new IllegalArgumentException("Player doesn't exist or same player can't play the move twice");
//
//        moves = this.dice.roll();
//        // player update his current pos, new player object that needs store in my hashmap
//        if player.currPos == (n, n) return true;
//
////        return false;
//    }
//
//    public void pushGameUpdates(){
//
//    }
//
//    public void undoMove(Player p) {
//        x, y = p.prevPos.poll();
//        p.makeMove(x, y);
//    }
//
//
//
//
//
//}
//
//class Dice {
//    private final int faces;
//    Dice(int f){
//        faces = f;
//    }
//    public int roll(){
//        return Math.random(faces);
//    }
//}
//
//class DiceExtended extends Dice{
//    public int cheatRoll(Player p){
//        return Math.random([1,2,3]);
//    }
//}
