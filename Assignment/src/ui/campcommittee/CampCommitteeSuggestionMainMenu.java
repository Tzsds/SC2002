package ui.campcommittee;

import ui.MainMenuInterface;

/**
 * Represents the main menu for Camp Committee Suggestion
 * Implements the MainMenuInterface for standardized menu structures
 */
public class CampCommitteeSuggestionMainMenu implements MainMenuInterface {

    /**
     * Display the Camp Committee Suggestion Main Page Menu
     * Menu includes options for Camp Committee members to view, create, edit and
     * delete their suggestions
     */
    public void displayMenu() {
        System.out.println("----------------------------------------");
        System.out.println("Welcome to Suggestion Main Page");
        System.out.println("----------------------------------------");
        System.out.println("1. View your unprocessed suggestions");
        System.out.println("2. Give suggestions");
        System.out.println("3. Edit your suggestions");
        System.out.println("4. Delete your suggestions");
        System.out.println("5. View your processed suggestions");
        System.out.println("6. Back to main menu");
        System.out.println("----------------------------------------");
    }
}
