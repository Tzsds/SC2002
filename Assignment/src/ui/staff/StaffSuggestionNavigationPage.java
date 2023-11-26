package ui.staff;

import controller.account.LoginManager;
import controller.suggestion.StaffSuggestionManager;
import controller.user.StaffManager;
import controller.utils.InputScanner;
import entity.Staff;
import ui.NavigationPage;
/**
 * Represents a navigation page for Staff to manage suggestions
 * This class extend NavigationPage class and is sepcific to Staff user role
 * 
 * @author SCSZ Group 4
 * @version 1.0 
 * @since 25/11/2023
 */
public class StaffSuggestionNavigationPage extends NavigationPage{

    /**
     * Constructs a new instance of StaffSuggestionNavigationPage
     * Initializes the main menu for Staff suggestion
     */
    public StaffSuggestionNavigationPage(){
        menu = new StaffSuggestionMainMenu();
    }
    /**
     * Runs the Staff Suggestion Navigation Page displaying the menu
     * and handling user input for various actions related to suggestions
     * The user can view and process suggestions
     */
    public void run(){
        boolean continueMenu = true;
        Staff currentUser = (Staff)LoginManager.getCurrentUser();
        boolean error = false;
        while (continueMenu){
            if (!error){
                menu.displayMenu();
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
                    System.out.println("Returning to main menu...");
                    return;

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
