package ui.staff;

import ui.MainMenuInterface;

public class StaffSuggestionMainMenu implements MainMenuInterface{
    public void displayMenu(){
        System.out.println("----------------------------------------");
        System.out.println("Welcome to Staff Suggestion Main Page");
        System.out.println("----------------------------------------");
        System.out.println("1. View suggestions");
        System.out.println("2. Respond to suggestions");
        System.out.println("3. Back to main menu");
        System.out.println("----------------------------------------");
    }
}
