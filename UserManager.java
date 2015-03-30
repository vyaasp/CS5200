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

public class UserManager {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/EMP";

    //  Database credentials
    static final String USER = "username";
    static final String PASS = "password";
    Connection conn = null;
    Statement stmt = null;
    public UserManager() {
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
    void createUser(User newUser){
        String sql;
        try {
            sql = "INSERT INTO User (userName, Password, firstName, LastName, email, dateOfBirth) Values (\""
                    + newUser.getUserName() + "\",\"" + newUser.getPassword() + "\",\"" + newUser.getFirstName() + "\",\"" +
                    newUser.getLastName() + "\",\"" + newUser.getEmail() + "\",\"" + newUser.getDateOfBirth() + "\")";
            ResultSet rs = stmt.executeQuery(sql);
            rs.close();
        }catch(Exception e){
            System.out.println("Couldn't execute SQL:"+e);
        }
    }
    List<User> readAllUsers(){
        List<User> allUsers = new LinkedList<User>();
        String sql;
        int counter =0;
        sql = "SELECT userName, Password, firstName, LastName, email, dateOfBirth FROM Users";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                //Retrieve by column name
                String userName  = rs.getString("userName");
                String Password = rs.getString("Password");
                String firstName = rs.getString("firstName");
                String LastName = rs.getString("LastName");
                String email = rs.getString("email");
                Date dateOfBirth = rs.getTimestamp("dateOfBirth");
                allUsers.add(counter, new User(userName, Password, firstName, LastName, email, dateOfBirth));
                counter++;
            }
        }catch(Exception e){
            System.out.println("Couldn't execute SQL:"+e);
        }
        return allUsers;
    }
    User readUser(String username){
        User user=null;
        String sql;
        sql = "SELECT userName, Password, firstName, LastName, email, dateOfBirth FROM Users where userName=\""
                +username+"\"";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String userName  = rs.getString("userName");
                String Password = rs.getString("Password");
                String firstName = rs.getString("firstName");
                String LastName = rs.getString("LastName");
                String email = rs.getString("email");
                Date dateOfBirth = rs.getTimestamp("dateOfBirth");
                user = new User(userName, Password, firstName, LastName, email, dateOfBirth);
                break;
            }
        }catch(Exception e){
            System.out.println("Couldn't execute SQL:"+e);
        }
        return user;
    }
    void updateUser(String username, User user){
        String sql;
        sql = "UPDATE User SET username=\""+username+"\" where userName=\""+user.getUserName()+"\"";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        }catch(Exception e){
            System.out.println("Couldn't execute SQL:"+e);
        }
    }
    void deleteUser(String username){
        String sql;
        sql = "DELETE FROM User where username=\""+username+"\"";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        }catch(Exception e){
            System.out.println("Couldn't execute SQL:"+e);
        }
    }

}
