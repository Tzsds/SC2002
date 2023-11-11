package Entity;

public class User {
    private String userID;
    private String name;
    private String faculty;
    private String password;

    public User(String userID, String name, String faculty, String password) {
        this.userID = userID;
        this.name = name;
        this.faculty = faculty;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
