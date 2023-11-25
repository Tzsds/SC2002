package ui.campcommittee;

import controller.account.LoginManager;
import controller.suggestion.SuggestionManager;
import controller.utils.InputScanner;
import entity.CampCommittee;
import ui.NavigationPage;
/**
 * Represents a navigation page for Camp Committee to manage suggestions
 * This class extend NavigationPage class and is sepcific to the Camp Committee user role
 * 
 * @author SCSZ Group 4
 * @version 1.0 
 * @since 25/11/2023
 */
public class CampCommitteeSuggestionNavigationPage extends NavigationPage {
    /**
     * Constructs a new instance of CampCommitteeSuggestionNavigationPage
     * Initializes the main menu for Camp Committee suggestion
     */
    public CampCommitteeSuggestionNavigationPage(){
        menu = new CampCommitteeSuggestionMainMenu();
    }
    /**
     * Runs the Camp Committee Suggestion Navigation Page displaying the menu
     * and handling user input for various actions related to suggestions
     * The user can view suggestions, give suggestions, edit suggestions
     * delete suggestions and view processed suggestions
     */
    public void run(){
        boolean continueMenu = true;
        boolean error = false;
        CampCommittee currentUser = (CampCommittee)LoginManager.getCurrentUser();
        while (continueMenu){
            if (!error){
                menu.displayMenu();
            }
            error = false;
            int option = InputScanner.promptForInt("Input your choice of action (1-6): ");
            System.out.println();
            switch(option){
                case 1:
                    //View Suggestion
                    System.out.println("Viewing pending Suggestions...");
                    SuggestionManager.printSuggestions(SuggestionManager.getSuggestions(currentUser.getSuggestions(), true));
                    break;

                case 2:
                    //Give Suggestion
                    SuggestionManager.addSuggestion();
                    break;

                case 3:
                    //Edit Suggestion
                    SuggestionManager.editSuggestion(currentUser.getSuggestions());
                    break;

                case 4:
                    //Delete Suggestion
                    SuggestionManager.deleteSuggestion(currentUser.getSuggestions());
                    break;
                case 5:
                    //View processed suggestions
                    System.out.println("Viewing processed Suggestions...");
                    SuggestionManager.printSuggestions(SuggestionManager.getSuggestions(currentUser.getSuggestions(), false));
                    break;
                case 6:
                    //Back to MM
                    System.out.println("Returning to main menu");
                    continueMenu = false;
                    break;

                default:
                    System.out.println("Invalid option. Please enter a valid option");
                    error = true;
            }
            if (!error){
                System.out.println();
            }
        }
    }
}