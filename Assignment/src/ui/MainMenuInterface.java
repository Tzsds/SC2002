package ui;

/**
 * The MainMenuInterface provides a standard interface for displaying main menus
 * in the NTU CAMS System
 * Classes implementing this interface are expected to provide a method to
 * display the main menu
 * 
 * @author SCSZ Group 4
 * @version 1.0 
 * @since 25/11/2023
 */
public interface MainMenuInterface {
    /**
     * Displays the main menu for user interaction
     * The content of the menu may vary depending on the specific implementation
     */
    public void displayMenu();
}
