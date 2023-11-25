package ui;

/**
 * This class provides methods for printing messages and performing
 * console-related actions
 * These methods are used for user interaction and system feedback in the NTU
 * CAMS System
 * @author SCSZ Group 4
 * @version 1.0 
 * @since 25/11/2023
 */

public class Display {

    /**
     * Displays the welcome message for the NTU CAMS System
     */
    public static void welcomeMessage() {
        System.out.println("Welcome to NTU CAMS System!");
    }

    /**
     * Displays the exit message when the user exits the NTU CAMS System
     */
    public static void exitMessage() {
        System.out.println("Thanks for using NTU CAMS System.");
        System.out.println("Exiting Program ...");
    }

    /**
     * Displays a message indicating that the user is logging out
     */
    public static void logOut() {
        System.out.println("Logging out ...");
    }

    /**
     * Displays a message indicating the initiation of the password change process
     */
    public static void changePassword() {
        System.out.println("Initiating password change process...");
    }

    /**
     * Displays a message indicating that the user needs to log in again during the
     * first-time login
     */
    public static void firstTimeLogin() {
        System.out.println("Please log in again");
    }

    /**
     * Clears the console screen
     * Note: This method uses ANSI escape codes and may not work in all
     * environments
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
