package UI;

import Controller.Account.ChangeAccountPassword;
import Controller.Account.LoginManager;
import Controller.File.User.WriteUser;
import Controller.Suggestion.SuggestionManager;
import Entity.CampCommittee;
import Entity.Student;

public class CampCommitteeMainPage {

    public static void run() {
        boolean continueMenu = true;
        while (continueMenu) {
            System.out.println("Welcome " + LoginManager.getCurrentUser().getName());
            CampCommitteeMainMenu.displayMainMenu();
            int option = InputScanner.promtForInt("Input your choice of action (1-9):");
            switch (option) {
                case 1:
                    // Display list of camps available
                    System.out.println("Retrieving list of available camps...");
                    Student.viewAvailableCamps();
                    break;
                case 2:
                    // Navigate to Enquiry Page
                    System.out.println("Navigating to Enquiry Page...");
                    break;
                case 3:
                    // Display registered camps
                    System.out.println("Retrieving registered camps...");
                    
                    //Print ArrayList<Camp> registeredCamps

                    break;
                case 4:
                    //Submit suggestion
                    System.out.println("Submitting Suggestions...");
                    SuggestionManager.addSuggestion();
                    break;
                case 5:
                    System.out.println("Viewing Enquiries...");
                    continueMenu = false;
                    return;
                case 6:
                    System.out.println("Viewing Suggestions...");
                    CampCommittee temp = (CampCommittee)LoginManager.getCurrentUser();
                    SuggestionManager.printSuggestions(temp.getSuggestions());
                    break;
                case 7:
                    System.out.println("Generating Report...");
                    continueMenu = false;
                    break;
                case 8:
                    System.out.println("Initiating password change process...");
                    ChangeAccountPassword.changePassword();
                    WriteUser.FileWriteCampCommittee();
                    System.out.println("Logging out...");
                    continueMenu = false;
                    break;
                case 9:
                    System.out.println("Logging out...");
                    continueMenu = false;
                    break;
                default:
                    System.out.println("Invalid option. Please enter a valid option.");
                    break;
            }
        }

    }
}
