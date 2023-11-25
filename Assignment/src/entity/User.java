package entity;

/**
 * This class represents a generic user in the system with basic attributes
 * 
 * @author SCSZ Group 4
 * @version 1.0
 * @since 25/11/23
 */

public class User {
    /** The unique user ID of the user. */
    private String userID;
    /** The name of the user. */
    private String name;
    /** The faculty to which the user belongs. */
    private String faculty;
     /** The password associated with the user's account. */
    private String password;

    /**
     * Constructs a new User object with the specified user ID, name, faculty, and password
     *
     * @param userID - the unique user ID of the user
     * @param name - the name of the user
     * @param faculty - the faculty to which the user belongs
     * @param password - the password associated with the user's account
     */
    public User(String userID, String name, String faculty, String password) {
        this.userID = userID;
        this.name = name;
        this.faculty = faculty;
        this.password = password;
    }

     /**
     * Retrieves the name of the user
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the faculty of the user
     * @return the faculty of the user
     */
    public String getFaculty() {
        return faculty;
    }

    /**
     * Retrieves the user id of the user
     * @return the user id of the user
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Retrieves the password of the user
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets or updates the password associated with the user's account
     * @param password - the new password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
