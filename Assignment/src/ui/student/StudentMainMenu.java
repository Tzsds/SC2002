package ui.student;

import ui.MainMenuInterface;

/**
 * Represents the main menu for Student
 * This class provides options to view list of available camps, register for
 * camps, navigate to enquiry page, view registered camps
 * Additionally, includes options to withdraw from a camp, change the password,
 * log out, and quit the program
 * 
 * This class implements the MainMenuInterface to ensure consistency in menu
 * display across different user roles
 * 
 * @author SCSZ Group 4
 * @version 1.0 
 * @since 25/11/2023
 */

public class StudentMainMenu implements MainMenuInterface {

    /**
     * Displays the Student Main Menu on the console
     * This menu includes options for Student to view list of available camps,
     * register for camps, navigate to enquiry page, view registered camps
     * Additionally, includes options to withdraw from a camp, change the password,
     * log out, and quit the program
     */

    public void displayMenu() {
        System.out.println("----------------------------------------");
        System.out.println("Welcome to the Student Main Page");
        System.out.println("----------------------------------------");
        System.out.println("1. View List of Camps Available");
        System.out.println("2. Register for Camp");
        System.out.println("3. Enquiry Page");
        System.out.println("4. View Registered Camps");
        System.out.println("5. Withdraw From Camps");
        System.out.println("6. Change Password");
        System.out.println("7. Logout");
        System.out.println("8. Quit Program");
        System.out.println("----------------------------------------");
    }
}
