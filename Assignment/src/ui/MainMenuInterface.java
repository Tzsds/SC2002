package ui;

/**
 * The MainMenuInterface provides a standard interface for displaying main menus
 * in the NTU CAMS System.
 * Classes implementing this interface are expected to provide a method to
 * display the main menu.
 */
public interface MainMenuInterface {
    /**
     * Displays the main menu for user interaction.
     * The content of the menu may vary depending on the specific implementation.
     */
    public void displayMenu();
}
