package UI;

import Controller.Account.ChangeAccountPassword;
import Controller.Account.LoginManager;
import Controller.Enquiry.EnquiryManager;
import Controller.File.User.WriteUser;
import Controller.Users.StaffManager;
import Entity.Staff;

public class StaffMainPage {
    
    public static void run() {
        boolean continueMenu = true;
        while (continueMenu) {
            Staff currentStaff = (Staff)LoginManager.getCurrentUser();
            System.out.println("Welcome " + currentStaff.getName());
            StaffMainMenu.displayMainMenu();
            int option = InputScanner.promptForInt("Input your choice of action (1-12): ");
            System.out.println();
            switch (option) {

                case 1:
                    // Show all Camps
                    System.out.println("Showing all camps created...");
                    StaffManager.viewAllCamps();
                    break;
                case 2:
                    // Create new camp
                    System.out.println("Navigating to create camp page...");
                    currentStaff.createNewCamp();
                    break;
                case 3:
                    // View camps created by the staff
                    System.out.println("Showing camps created by you...");
                    StaffManager.viewCampsCreated(currentStaff.getListOfCampsCreated());
                    break;
                case 4: 
                    //Edit camp
                    StaffManager.editCamp(currentStaff.getListOfCampsCreated());
                    break;
                case 5: 
                    // Delete camps created by staff
                    currentStaff.deleteCamp();
                    break;
                case 6:
                    // Show Enqueries asked by students
                    System.out.println("Showing enqueries by students...");
                    EnquiryManager.viewAllEnquiries();
                    break;
                case 7:
                    // View suggestions given by camp committee members
                    System.out.println("Showing suggestions from camp committee members...");
                    StaffManager.viewSuggestions();
                    break;
                case 8:
                    // Generate camp report
                    System.out.println("Generating camp report...");
                    // generateCampReport();
                    break;
                case 9:
                    // Generate performance report
                    System.out.println("Generating performance report of camp committee members...");
                    // generatePerformanceReport();
                    break;
                case 10:
                    //Change Password
                    SystemMessage.changePassword();
                    ChangeAccountPassword.changePassword();
                    WriteUser.FileWriteStaff();
                    SystemMessage.LogOut();
                    continueMenu = false;
                    break;
                case 11:
                    //Log Out
                    SystemMessage.LogOut();
                    continueMenu = false;
                    break;
                case 12:
                    //Quit Program
                    SystemMessage.ExitMessage();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please enter a valid option.");
                    break;
            }
        }
    }
}