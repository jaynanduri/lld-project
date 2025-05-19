package mock2;

public class Req {
    
}
/**
 * GamePublisher(List<Game> games, store) - upload a game to store
 *  - uploadGame()
 * User(int gameId) - can download a game and play // client code
 * Game g(genre, rating, singlePlayer, multiPlayer, executables, downloads)
 *  - run()
 *  - String getGenre()
 *  - int totalDownloads(int gameId)
 * Store(Collection of games, downloads of a particular game)
 *  fields - Map<GameId, Integer>
 *  - Store getStore();
 *  - List<Game> listGames();
 */