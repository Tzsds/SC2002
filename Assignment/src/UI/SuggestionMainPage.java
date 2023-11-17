package UI;

import java.util.InputMismatchException;

import Controller.Account.LoginManager;
import Controller.Suggestion.SuggestionManager;
import Entity.CampCommittee;

public class SuggestionMainPage {
    public static void run(){
        boolean continueMenu = true;
        CampCommittee currentUser = (CampCommittee)LoginManager.getCurrentUser();
        while (continueMenu){
            SuggestionMainMenu.displaySuggestionMenu();
            try{
                int option = InputScanner.promptForInt("Input your choice of action (1-4): ");
                switch(option){
                    case 1:
                        //View Suggestion
                        System.out.println("Viewing Suggestions...");
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
                        SuggestionManager.printSuggestions(SuggestionManager.getSuggestions(currentUser.getSuggestions(), false));
                        break;
                    case 6:
                        //Back to MM
                        System.out.println("Returning to main menu");
                        continueMenu = false;
                        break;

                    default:
                        System.out.println("Invalid option. Please enter a valid option");
                }
                System.out.println();
            }
            catch(InputMismatchException e){
                System.out.println("Invalid option. Please enter a valid option");
                InputScanner.clear();
            }
        }
    }
}