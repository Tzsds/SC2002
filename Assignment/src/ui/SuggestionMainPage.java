package ui;

import Controller.Account.LoginManager;
import Controller.Suggestion.SuggestionManager;
import Controller.Utilities.InputScanner;
import entity.CampCommittee;

public class SuggestionMainPage implements MainPage {
    public void run(){
        boolean continueMenu = true;
        boolean error = false;
        CampCommittee currentUser = (CampCommittee)LoginManager.getCurrentUser();
        while (continueMenu){
            if (!error){
                SuggestionMainMenu.displaySuggestionMenu();
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