import java.util.Date;

/**
 * Created by vyaas on 3/29/15.
 */
public class User {

    String userName;
    String Password;
    String firstName;
    String LastName;
    String email;
    Date dateOfBirth = new Date();

    public User(String userName, String password, String firstName, String lastName, String email, Date dateOfBirth) {
        this.userName = userName;
        Password = password;
        this.firstName = firstName;
        LastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
