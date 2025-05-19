package mock2;
import java.util.*;

//Game g(genre, rating, singlePlayer, multiPlayer, executables, downloads)
/*
 * - run()
 *  - String getGenre()
 *  - int totalDownloads(int gameId)
 */
class Genre{
    private final String genre;
    private final String subGenre;
    public Genre(String s, String g){
        this.genre = s;
        this.subGenre = g;
    }
    // getters for genre and subgenre
}
enum GameRating {
    ONE, 
    TWO,
    THREE
}

enum GameType{
    SINGLE_PLAYER,
    MULTI_PLAYER,
}
class Exec{
    Exec(){

    }
    public void run(){

    }
}
public class Game {
    private final List<Genre> genres;
    public List<Genre> getGenres() {
        return genres;
    }

    private GameRating rating;
    public GameRating getRating() {
        return rating;
    }

    private final GameType gt;
    public GameType getGt() {
        return gt;
    }

    private final Exec exec;
    public Exec getExec() {
        return exec;
    }

    private int downloadCount;

    public int getDownloadCount() {
        return downloadCount;
    }

    public Game(List<Genre> genres, GameRating rt, GameType gt, Exec c){
        this.genres = genres;
        this.rating = rt;
        this.gt = gt;
        this.exec = c;
        this.downloadCount = 0;
    }

    public void run(){
        this.exec.run();
    }

    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Genre -> ");
        for (Genre g: genres){
            res.append(g);
        }

        res.append(", Ratings: " + rating);
        res.append(", GameType: " + gt);
        res.append(", DownloadCount: " + downloadCount);
        return res.toString();
    }

}
