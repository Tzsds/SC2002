package ui.staff;

import ui.MainMenuInterface;

/**
 * Represents the main menu for Staff Suggestion
 * This class implements the MainMenuInterface for standardized menu structures
 * and provides options for staff members to view and reply to suggestions
 * directed to them on their respective camps.
 */

public class StaffSuggestionMainMenu implements MainMenuInterface {

    /**
     * Displays the Staff Suggestion Main Page Menu on the console.
     * This menu includes options for Staff members to view and reply to the
     * suggestions directed to them on their respective camps.
     */
    public void displayMenu() {
        System.out.println("----------------------------------------");
        System.out.println("Welcome to Staff Suggestion Main Page");
        System.out.println("----------------------------------------");
        System.out.println("1. View suggestions");
        System.out.println("2. Respond to suggestions");
        System.out.println("3. Back to main menu");
        System.out.println("----------------------------------------");
    }
}
