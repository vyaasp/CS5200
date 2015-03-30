import java.util.Date;

/**
 * Created by vyaas on 3/29/15.
 */
public class Comment {

    String comment;
    Date date = new Date();
    String userName;
    int movieId;
    int id;
    public Comment(int id, String userName, String comment, Date date, int movieId) {
        this.id = id;
        this.comment = comment;
        this.date = date;
        this.userName = userName;
        this.movieId = movieId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int id) {
        this.movieId = id;
    }
}