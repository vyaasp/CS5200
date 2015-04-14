/**
 * Created by vyaas on 3/29/15.
 */
public class Cast {
    String characterName;
    int actorId;
    int movieId;
    int castId;

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int id) {
        this.actorId = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int id) {
        this.movieId = id;
    }

    public int getCastId() {
        return castId;
    }

    public void setCastId(int id) {
        this.castId = id;
    }

    public Cast(String characterName, int actionId, int movieID, int castId) {
        this.characterName = characterName;
        this.actorId = actorId;
        this.movieId = movieID;
        this.castId = castId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

}
