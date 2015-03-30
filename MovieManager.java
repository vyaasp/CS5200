import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by vyaas on 3/29/15.
 */
public class MovieManager {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/EMP";

    //  Database credentials
    static final String USER = "username";
    static final String PASS = "password";
    Connection conn = null;
    Statement stmt = null;
    public MovieManager() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println("Couldn't connect to DB:"+e);
        }
    }

    void createMovie(Movie newMovie){
        String sql;
        try {
            sql = "INSERT INTO Movie (id, title, posterImage, releaseDate) Values ("
                    + newMovie.getId() + ",\"" + newMovie.getTitle() + "\",\"" + newMovie.getPosterImage() + "\",\"" +
                    newMovie.getReleaseDate()+"\")";
            ResultSet rs = stmt.executeQuery(sql);
            rs.close();
        }catch(Exception e){
            System.out.println("Couldn't execute SQL:"+e);
        }
    }
    List<Movie> readAllMovies(){
        List<Movie> allMovies = new LinkedList<Movie>();
        String sql;
        int counter =0;
        sql = "SELECT id, title, posterImage, releaseDate FROM Movie";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int id  = rs.getInt("id");
                String title = rs.getString("Password");
                String posterImage = rs.getString("firstName");
                Date releaseDate = rs.getTimestamp("dateOfBirth");
                allMovies.add(counter, new Movie(id, title, posterImage, releaseDate));
                counter++;
            }
        }catch(Exception e){
            System.out.println("Couldn't execute SQL:"+e);
        }
        return allMovies;
    }
    Movie readMovie(String movieId){
        Movie movie=null;
        String sql;
        sql = "SELECT id, title, posterImage, releaseDate FROM Movie where id=\""+movieId+"\"";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int id  = rs.getInt("id");
                String title = rs.getString("Password");
                String posterImage = rs.getString("firstName");
                Date releaseDate = rs.getTimestamp("dateOfBirth");
                movie = new Movie(id, title, posterImage, releaseDate);
                break;
            }
        }catch(Exception e){
            System.out.println("Couldn't execute SQL:"+e);
        }
        return movie;
    }
    void updateMovie(String movieId, Movie movie){
        String sql;
        sql = "UPDATE Movie SET id=\""+movieId+"\" where id=\""+movie.id+"\"";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        }catch(Exception e){
            System.out.println("Couldn't execute SQL:"+e);
        }
    }
    void deleteMovie(String movieId){
        String sql;
        sql = "DELETE FROM Movie where id=\""+movieId+"\"";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        }catch(Exception e){
            System.out.println("Couldn't execute SQL:"+e);
        }
    }

}
