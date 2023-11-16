package UI;

import java.util.InputMismatchException;

import Controller.Account.LoginManager;
import Controller.Suggestion.StaffSuggestionManager;
import Controller.Users.StaffManager;
import Entity.Staff;

public class StaffSuggestionMainPage {
    public static void run(){
        boolean continueMenu = true;
        Staff currentUser = (Staff)LoginManager.getCurrentUser();
        while (continueMenu){
            StaffSuggestionMainMenu.displayStaffSuggestionMenu();
            try{
                int option = InputScanner.promptForInt("Input your choice of action (1-3): ");
                switch(option){
                    case 1:
                        //View Suggestion
                        System.out.println("Viewing Suggestions...");
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
                }
            }
            catch(InputMismatchException e){
                System.out.println("Invalid option. Please enter a valid option");
                InputScanner.clear();
            }
        }
    }
}
