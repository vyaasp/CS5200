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
public class ActorManager {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/EMP";

    //  Database credentials
    static final String USER = "username";
    static final String PASS = "password";
    Connection conn = null;
    Statement stmt = null;
    public ActorManager() {
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
    void createActor(Actor newActor){
        String sql;
        try {
            sql = "INSERT INTO Actor (id, firstName, lastName, dateOfBirth) Values ("
                    + newActor.getId() + ",\"" + newActor.getFirstName() + "\",\"" + newActor.getLastName() + "\",\"" +
                    newActor.getDateOfBirth()+"\")";
            ResultSet rs = stmt.executeQuery(sql);
            rs.close();
        }catch(Exception e){
            System.out.println("Couldn't execute SQL:"+e);
        }
    }
    List<Actor> readAllActors(){
        List<Actor> allActors = new LinkedList<Actor>();
        String sql;
        int counter =0;
        sql = "SELECT id, firstName, lastName, dateOfBirth FROM Movie";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int id  = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String dateOfBirth = rs.getString("dateOfBirth");
                allActors.add(counter, new Actor(id, firstName, lastName, dateOfBirth));
                counter++;
            }
        }catch(Exception e){
            System.out.println("Couldn't execute SQL:"+e);
        }
        return allActors;
    }
    Actor readActor(String actorId){
        Actor actor=null;
        String sql;
        sql = "SELECT id, firstName, lastName, dateOfBirth FROM Actor where id="+actorId;
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int id  = rs.getInt("id");
                String firstName = rs.getString("Password");
                String lastName = rs.getString("firstName");
                String dateOfBirth = rs.getString("dateOfBirth");
                actor = new Actor(id, firstName, lastName, dateOfBirth);
                break;
            }
        }catch(Exception e){
            System.out.println("Couldn't execute SQL:"+e);
        }
        return actor;
    }
    void updateActor(String actorId, Actor actor){
        String sql;
        sql = "UPDATE Actor SET id="+actorId+" where id="+actor.id;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        }catch(Exception e){
            System.out.println("Couldn't execute SQL:"+e);
        }
    }
    void deleteActor(String actorId){
        String sql;
        sql = "DELETE FROM Actor where id=\""+actorId+"\"";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        }catch(Exception e){
            System.out.println("Couldn't execute SQL:"+e);
        }
    }

}
