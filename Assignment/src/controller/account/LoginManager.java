package controller.account;

import controller.file.FileWriting;
import controller.utils.InputScanner;
import entity.CampCommittee;
import entity.Staff;
import entity.Student;
import entity.User;
import repository.userrepository.CampCommitteeRepository;
import repository.userrepository.StaffRepository;
import repository.userrepository.StudentRepository;
import ui.Display;

/**
 * Manages login process for users in the system.
 * This class handles user authentication, password checks, and first-time login
 * procedures.
 */
public class LoginManager {

    private static User currentUser; // Current user which is in the system
    private static String role; // Role of current user in the system

    /**
     * Initiates the login process by checking user ID and password.
     * If its the first time in the system, prompts the user to change their
     * password
     */
    public static void Login() {
        LoginManager.checkID();
        LoginManager.checkPassword(currentUser);
        if (currentUser.getPassword().equals("password")) {
            System.out.println("First time in the system, please change your password.");
            ChangeAccountPassword.changePassword();
            Display.firstTimeLogin();
            FileWriting.FileWriteUser();
            Login();
        }
    }

    /**
     * Checks the user ID entered by the user and sets the current user and role
     * accordingly.
     */
    private static void checkID() {
        User temp;
        while (true) {
            String id = InputScanner.promptForString("Enter your User ID: ");

            for (CampCommittee s : CampCommitteeRepository.getListOfCampCommittee()) {
                temp = s;
                if (temp.getUserID().equals(id)) {
                    currentUser = s;
                    role = "CampCommittee";
                    return;
                }
            }

            for (Student s : StudentRepository.getListOfStudents()) {
                temp = s;
                if (temp.getUserID().equals(id)) {
                    currentUser = s;
                    role = "Student";
                    return;
                }
            }
            for (Staff s : StaffRepository.getListOfStaff()) {
                temp = s;
                if (temp.getUserID().equals(id)) {
                    currentUser = s;
                    role = "Staff";
                    return;
                }
            }
            System.out.println("Invalid User ID!");
        }
    }

    /**
     * Checks the password entered by the user against the user's actual password
     *
     * @param person - the logged in User whose password needs to be check
     */
    private static void checkPassword(User person) {
        while (true) {
            String pw = InputScanner.promptForString("Enter your password: ");
            if (pw.equals(person.getPassword())) {
                return;
            } else {
                System.out.println("Wrong password entered!");
            }
        }
    }

    /**
     * Sets the current user.
     * 
     * @param newUser - The new User to be set as the current user
     */
    public static void setUser(User newUser) {
        currentUser = newUser;
    }

    /**
     * Sets the current user role.
     * 
     * @param newRole - The new role to be set as the current role
     */
    public static void setCurrentRole(String newRole) {
        role = newRole;
    }

    /**
     * Gets the current user
     * 
     * @return - The current user
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * Gets the role of the current user
     * 
     * @return - The role of the current user
     */
    public static String getUserRole() {
        return role;
    }
}
