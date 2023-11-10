package Entity;

public class User {
    private String userID;
    private static String name;
    private String faculty;
    private String password;

    public User(String userID, String name, String faculty, String password) {
        this.userID = userID;
        User.name = name;
        this.faculty = faculty;
        this.password = password;
    }

    public static String getName() {
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
