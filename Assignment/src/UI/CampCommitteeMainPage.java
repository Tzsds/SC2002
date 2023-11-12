package UI;

import Controller.Account.ChangeAccountPassword;
import Controller.Account.LoginManager;
import Controller.Camp.CampManager;
import Controller.File.User.WriteUser;
import Entity.CampCommittee;

public class CampCommitteeMainPage {

    public static void run() {
        boolean continueMenu = true;
        while (continueMenu) {
            CampCommittee User = (CampCommittee)LoginManager.getCurrentUser();
            System.out.println("Welcome " + User.getName());
            CampCommitteeMainMenu.displayMainMenu();
            int option = InputScanner.promptForInt("Input your choice of action (1-9): ");
            switch (option) {
                case 1:
                    // Display list of camps available
                    System.out.println("Retrieving list of available camps...");
                    CampManager.viewAvailableCamps();
                    break;
                case 2:
                    System.out.println("Navigating to camp registration page");
                    break;
                case 3:
                    // Navigate to Enquiry Page
                    System.out.println("Navigating to Enquiry Page...");
                    EnquiryMainPage enquiryPage = new EnquiryMainPage(LoginManager.getCurrentUser());
                    enquiryPage.run();
                    break;
                case 4:
                    // Navigate to Suggestion Page
                    System.out.println("Navigating to Suggestion Page");
                    SuggestionMainPage.run();
                    break;
                case 5:
                    //View Registered Camps
                    User.viewRegisteredCamps();
                    return;
                case 6:
                    //View and Reply to Enquiries
                    System.out.println("Viewing Enquiries...");
                    continueMenu = false;
                    break;
                case 7:
                    //Generate Report
                    break;
                case 8:
                    //Change Password
                    System.out.println("Initiating password change process...");
                    ChangeAccountPassword.changePassword();
                    WriteUser.FileWriteCampCommittee();
                    System.out.println("Logging out...");
                    continueMenu = false;
                    break;
                case 9:
                    //Log out
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
