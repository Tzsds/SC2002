public class User {
    private String userID;
    private String name;
    private String faculty;
    private String password;
    
    public User(String userID, String name, String faculty){
        this.userID = userID;
        this.name = name;
        this.faculty = faculty;
        this.password = "password";
    }

    public void setPassword(String password){
        this.password = password;
    }
}
