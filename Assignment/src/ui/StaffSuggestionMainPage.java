package ui;

import Controller.Account.LoginManager;
import Controller.Suggestion.StaffSuggestionManager;
import Controller.Users.StaffManager;
import Controller.Utilities.InputScanner;
import entity.Staff;

public class StaffSuggestionMainPage implements MainPage{
    public void run(){
        boolean continueMenu = true;
        Staff currentUser = (Staff)LoginManager.getCurrentUser();
        boolean error = false;
        while (continueMenu){
            if (!error){
                StaffSuggestionMainMenu.displayStaffSuggestionMenu();
            }
            error = false;
            int option = InputScanner.promptForInt("Input your choice of action (1-3): ");
            switch(option){
                case 1:
                    //View Suggestion
                    System.out.println("Showing list of suggestions...");
                    StaffManager.viewSuggestions();
                    break;

                case 2:
                    //Process Suggestion
                    System.out.println("Process suggestions...");
                    StaffSuggestionManager.processSuggestions(currentUser.getListOfCampsCreated());
                    break;

                case 3:
                    //Back to MM
                    System.out.println("Returning to main menu");
                    continueMenu = false;
                    break;

                default:
                    System.out.println("Invalid option. Please enter a valid option");
                    error = true;
                    break;
            }
            if (!error){
                System.out.println();
            }
        }
    }
}
