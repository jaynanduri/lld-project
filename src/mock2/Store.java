package mock2;
import java.util.*;
/*
 * Store(Collection of games)
 *  fields - Map<GameId, Integer>
 *  - Store getStore();
 *  - addGame(Game g)
 *  - printStats(int game_id)
 *  - List<Game> listGames();
 *  -----
 *  - User checkouts a game, user needs to add the game to his library.
 *  
 */
public class Store {
    private final List<Game> games;
    private static Store store;
    private Store() {
        this.games = new ArrayList<>();
    }
    public static synchronized Store getStore(){
        if(store == null) return new Store();
        return store;
    }
    public void addGame(Game g){
        this.games.add(g);
    }
    public String printStats(Game g){
        return g.toString();
    }
    public List<Game> getGames() {
        return games;
    }
}
