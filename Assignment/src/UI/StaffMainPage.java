package UI;

import Controller.Account.ChangeAccountPassword;
import Controller.Account.LoginManager;
import Entity.Staff;

public class StaffMainPage {
    
    public static void run() {
        boolean continueMenu = true;
        while (continueMenu) {
            System.out.println("Welcome " + LoginManager.getCurrentUser().getName());
            StaffMainMenu.displayMainMenu();
            int option = InputScanner.promtForInt("Input your choice of action (1-9):");
            switch (option) {

                case 1:
                    // Show all Camps
                    System.out.println("Showing all camps created...");
                    // Staff.viewAllCamps();
                    break;
                case 2:
                    // Create new camp
                    System.out.println("Navigating to create camp page...");
                    Staff.createNewCamp();
                    break;
                case 3:
                    // View camps created by the staff
                    System.out.println("Showing camps created by you...");
                    // viewCampCreatedList();
                    break;
                case 4:
                    // Show Enqueries asked by students
                    System.out.println("Showing enqueries by students...");
                    // viewEnqueries();
                    break;
                case 5:
                    // View suggestions given by camp committee members
                    System.out.println("Showing suggestions from camp committee members...");
                    // viewSuggestions();
                    break;
                case 6:
                    // Generate camp report
                    System.out.println("Generating camp report...");
                    // generateCampReport();
                    break;
                case 7:
                    // Generate performance report
                    System.out.println("Generating performance report of camp committee members...");
                    // generatePerformanceReport();
                    break;
                case 8:
                    // Initiate password change process
                    System.out.println("Initiating password change process...");
                    ChangeAccountPassword.changePassword();
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