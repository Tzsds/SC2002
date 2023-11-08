package UI;
//import java.util.Scanner;

import Controller.Account.ChangeAccountPassword;
import Entity.User;

public class CampCommitteeMainPage {
    private MainMenu mainMenu;
    private InputScanner inputScanner;
    private User currentUser;
    private String userRole;

    public CampCommitteeMainPage(User currentUser, String userRole) {
        this.currentUser = currentUser;
        this.userRole = userRole;
        mainMenu = new StudentMainMenu();
        inputScanner = new InputScanner();

    }

    public void run() {
        boolean continueMenu = true;
        while (continueMenu) {
            System.out.println("Welcome " + currentUser.getName());
            mainMenu.displayMainMenu();
            int option = inputScanner.promtForInt("Input your choice of action (1-9):");
            switch (option) {

                case 1:
                    // Display list of camps available
                    System.out.println("Retrieving list of available camps...");
                    break;
                case 2:
                    // Navigate to Enquiry Page
                    System.out.println("Navigating to Enquiry Page...");
                    break;
                case 3:
                    // Display registered camps
                    System.out.println("Retrieving registered camps...");

                    break;
                case 4:
                    // Initiate password change process
                    System.out.println("Submitting Suggestions...");
                    continueMenu = false;
                    return;
                case 5:
                    System.out.println("Viewing Enquiries...");
                    continueMenu = false;
                    return;
                case 6:
                    System.out.println("Viewing Suggestions...");
                    continueMenu = false;
                    return;
                case 7:
                    System.out.println("Generating Report...");
                    continueMenu = false;
                    return;
                case 8:
                    System.out.println("Initiating password change process...");
                    ChangeAccountPassword.changePassword();
                    System.out.println("Returning back to main page...");
                    break;
                case 9:
                    System.out.println("Logging out...");
                    continueMenu = false;
                    return;
                default:
                    System.out.println("Invalid option. Please enter a valid option.");
                    break;
            }
        }

    }
}
