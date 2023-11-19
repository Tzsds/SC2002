package UI;

import Controller.Account.ChangeAccountPassword;
import Controller.Account.LoginManager;
import Controller.File.User.WriteUser;
import Controller.Users.StudentManager;
import Entity.CampCommittee;

public class CampCommitteeMainPage {

    public static void run() {
        boolean continueMenu = true;
        boolean error = false;
        CampCommittee User = (CampCommittee) LoginManager.getCurrentUser();
        while (continueMenu) {
            if (!error){
                System.out.println("Welcome " + User.getName());
                CampCommitteeMainMenu.displayMainMenu();
            }
            error = false;
            int option = InputScanner.promptForInt("Input your choice of action (1-10): ");
            System.out.println();
            switch (option) {
                case 1:
                    // Display list of camps available
                    System.out.println("Retrieving list of available camps...");
                    StudentManager.viewAvailableCamps();
                    break;
                case 2:
                    // Register for camp under Student Manager
                    System.out.println("Navigating to camp registration page");
                    StudentManager.registerForCamps();
                    break;
                case 3:
                    // Navigate to Enquiry Page
                    System.out.println("Navigating to Enquiry Page...");
                    CampCommitteeEnquiryPage enquiryPage = new CampCommitteeEnquiryPage();
                    enquiryPage.run();
                    break;
                case 4:
                    // Navigate to Suggestion Page
                    System.out.println("Navigating to Suggestion Page");
                    SuggestionMainPage.run();
                    break;
                case 5:
                    // View Registered Camps
                    User.viewRegisteredCamps();
                    break;
                case 6:
                    // Generate Camp Attendee Report
                    System.out.println("Navigating to generate camp attendee list report...");
                    User.generateCampReport();
                    break;
                case 7:
                    // Generate Enquiry Report
                    System.out.println("Navigating to generate camp enquiry report...");
                    User.generateEnquiryReport();
                    break;
                case 8:
                    // Generate Report
                    System.out.println("Withdrawing From Camps...");
                    StudentManager.withdrawFromCamp();
                    break;
                case 9:
                    // Change Password
                    Display.changePassword();
                    ChangeAccountPassword.changePassword();
                    WriteUser.FileWriteCampCommittee();
                    Display.LogOut();
                    continueMenu = false;
                    break;
                case 10:
                    // Log out
                    Display.LogOut();
                    continueMenu = false;
                    System.out.println(User.getName() + " logged out");
                    break;
                case 11:
                    // Quit Program
                    Display.ExitMessage();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please enter a valid option.");
                    error = true;
                    break;
            }
            if (!error){
                System.out.println();
            }
        }

    }
}
