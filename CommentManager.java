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
public class CommentManager {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/EMP";

    //  Database credentials
    static final String USER = "username";
    static final String PASS = "password";
    Connection conn = null;
    Statement stmt = null;
    public CommentManager() {
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

    void createComment(Comment newComment){
        String sql;
        try {
            sql = "INSERT INTO User (id, username, comment, date, movieID) Values (" +newComment.getId()+ ",\""
                    +newComment.getUserName()+ "\",\""
                    + newComment.getComment() + "\",\"" + newComment.getDate() + "\",\"" + newComment.getMovieId()+ "\")";
            ResultSet rs = stmt.executeQuery(sql);
            rs.close();
        }catch(Exception e){
            System.out.println("Couldn't execute SQL:"+e);
        }
    }
    List<Comment> readAllComments(){
        List<Comment> allComments = new LinkedList<Comment>();
        String sql;
        int counter =0;
        sql = "SELECT id, userName, comment, date, movieId FROM Users";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                String userName  = rs.getString("userName");
                String comment  = rs.getString("comment");
                Date date = rs.getTimestamp("dateOfBirth");
                int movieId  = rs.getInt("movieId");
                allComments.add(counter, new Comment(id, userName, comment, date, movieId));
                counter++;
            }
        }catch(Exception e){
            System.out.println("Couldn't execute SQL:"+e);
        }
        return allComments;
    }
    List<Comment> readAllCommentsForUsername(String username){
        List<Comment> allComments = new LinkedList<Comment>();
        String sql;
        int counter =0;
        sql = "SELECT id, userName, comment, date, movieId FROM Comment where userName=\""+username+"\"";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int id  = rs.getInt("id");
                String userName  = rs.getString("userName");
                String comment = rs.getString("comment");
                Date date = rs.getTimestamp("date");
                int movieId  = rs.getInt("movieId");
                allComments.add(counter, new Comment(id, userName, comment, date, movieId));
                counter++;
            }
        }catch(Exception e){
            System.out.println("Couldn't execute SQL:"+e);
        }
        return allComments;
    }
    List<Comment> readAllCommentsForMovie(String movieId){
        List<Comment> allComments = new LinkedList<Comment>();
        String sql;
        int counter =0;
        sql = "SELECT id, userName, comment, date, movieId FROM Comment where movieId=\""+movieId+"\"";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int id  = rs.getInt("id");
                String userName  = rs.getString("userName");
                String comment = rs.getString("comment");
                Date date = rs.getTimestamp("date");
                int movieIdDB  = rs.getInt("movieId");
                allComments.add(counter, new Comment(id, userName, comment, date, movieIdDB));
                counter++;
            }
        }catch(Exception e){
            System.out.println("Couldn't execute SQL:"+e);
        }
        return allComments;
    }
    Comment readCommentForId(String commentId){
        Comment comment = null;
        String sql;
        sql = "SELECT id, userName, comment, date, movieId FROM Comment where id=\""+commentId+"\"";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int id  = rs.getInt("id");
                String userName  = rs.getString("userName");
                String commentDB = rs.getString("comment");
                Date date = rs.getTimestamp("date");
                int movieIdDB  = rs.getInt("movieId");
                comment =  new Comment(id, userName, commentDB, date, movieIdDB);
                break;
            }
        }catch(Exception e){
            System.out.println("Couldn't execute SQL:"+e);
        }
        return comment;
    }

    void updateComment(String commentId, String newComment){
        String sql;
        sql = "UPDATE Comment SET comment=\""+newComment+"\" where id=\""+newComment+"\"";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        }catch(Exception e){
            System.out.println("Couldn't execute SQL:"+e);
        }
    }

    void deleteComment(String commentId){
        String sql;
        sql = "DELETE FROM Comment where id=\""+commentId+"\"";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        }catch(Exception e){
            System.out.println("Couldn't execute SQL:"+e);
        }
    }
}
