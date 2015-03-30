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
public class CastManager {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/EMP";

    //  Database credentials
    static final String USER = "username";
    static final String PASS = "password";
    Connection conn = null;
    Statement stmt = null;
    public CastManager() {
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

    void createCast(Cast newCast){
        String sql;
        try {
            sql = "INSERT INTO User (characterName, actorId, movieId, castId) Values (\"" +newCast.getCharacterName()+ "\",\""
                    +newCast.getActorId()+ "\",\""
                    + newCast.getMovieId() + "\")";
            ResultSet rs = stmt.executeQuery(sql);
            rs.close();
        }catch(Exception e){
            System.out.println("Couldn't execute SQL:"+e);
        }
    }
    List<Cast> readAllCast(){
        List<Cast> allCast = new LinkedList<Cast>();
        String sql;
        int counter =0;
        sql = "SELECT characterName, actorId, movieId, actorId FROM Cast";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String characterName = rs.getString("characterName");
                int actorId  = rs.getInt("actorId");
                int movieId  = rs.getInt("movieId");
                int castId  = rs.getInt("castId");
                allCast.add(counter, new Cast(characterName, actorId, movieId, castId));
                counter++;
            }
        }catch(Exception e){
            System.out.println("Couldn't execute SQL:"+e);
        }
        return allCast;
    }
    List<Cast> readAllCastForActor(String actorId){
        List<Cast> allCast = new LinkedList<Cast>();
        String sql;
        int counter =0;
        sql = "SELECT characterName, actorId, movieId, castId FROM Cast where actorId=\""+actorId+"\"";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String characterName = rs.getString("characterName");
                int actorIdDB  = rs.getInt("actorId");
                int movieId  = rs.getInt("movieId");
                int castId  = rs.getInt("castId");
                allCast.add(counter, new Cast(characterName, actorIdDB, movieId, castId));
                counter++;
            }
        }catch(Exception e){
            System.out.println("Couldn't execute SQL:"+e);
        }
        return allCast;
    }
    List<Cast> readAllCastForMovie(String movieId){
        List<Cast> allCast = new LinkedList<Cast>();
        String sql;
        int counter =0;
        sql = "SELECT characterName, actorId, movieId, castId FROM Cast where movieId=\""+movieId+"\"";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String characterName = rs.getString("characterName");
                int actorIdDB  = rs.getInt("actorId");
                int movieIdDB  = rs.getInt("movieId");
                int castId  = rs.getInt("castId");
                allCast.add(counter, new Cast(characterName, actorIdDB, movieIdDB, castId));
                counter++;
            }
        }catch(Exception e){
            System.out.println("Couldn't execute SQL:"+e);
        }
        return allCast;
    }
    Cast readCastForId(String castId){
        Cast cast = null;
        String sql;
        sql = "SELECT characterName, actorId, movieId, castId FROM Cast where castId=\""+castId+"\"";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String characterName = rs.getString("characterName");
                int actorIdDB  = rs.getInt("actorId");
                int movieIdDB  = rs.getInt("movieId");
                int castIdDB  = rs.getInt("castId");
                cast =  new Cast(characterName, actorIdDB, movieIdDB, castIdDB);
                break;
            }
        }catch(Exception e){
            System.out.println("Couldn't execute SQL:"+e);
        }
        return cast;
    }
    void updateCast(String castId, Cast newCast){
        String sql;
        sql = "UPDATE Cast SET castId=\"" +castId+ "\"where id=\""+newCast.getCastId()+"\"";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        }catch(Exception e){
            System.out.println("Couldn't execute SQL:"+e);
        }
    }
    void deleteCast(String castId){
        String sql;
        sql = "DELETE FROM Cast where castId=\""+castId+"\"";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        }catch(Exception e){
            System.out.println("Couldn't execute SQL:"+e);
        }
    }

}
