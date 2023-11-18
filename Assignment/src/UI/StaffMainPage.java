package UI;

import Controller.Account.ChangeAccountPassword;
import Controller.Account.LoginManager;
import Controller.File.User.WriteUser;
import Controller.Users.StaffManager;
import Entity.Staff;

public class StaffMainPage {

    public static void run() {
        boolean continueMenu = true;
        Staff currentStaff = (Staff) LoginManager.getCurrentUser();
        System.out.println("Welcome " + currentStaff.getName());
        StaffMainMenu.displayMainMenu();
        while (continueMenu) {
            int option = InputScanner.promptForInt("Input your choice of action (1-12): ");
            switch (option) {

                case 1:
                    // Show all Camps
                    System.out.println("Showing all camps created...");
                    StaffManager.viewAllCamps();
                    System.out.println("Welcome " + currentStaff.getName());
                    StaffMainMenu.displayMainMenu();
                    break;
                case 2:
                    // Create new camp
                    System.out.println("Navigating to create camp page...");
                    currentStaff.createNewCamp();
                    StaffMainMenu.displayMainMenu();
                    break;
                case 3:
                    // View camps created by the staff
                    System.out.println("Showing camps created by you...");
                    StaffManager.viewCampsCreated(currentStaff.getListOfCampsCreated());
                    StaffMainMenu.displayMainMenu();
                    break;
                case 4:
                    // Edit camp
                    StaffManager.editCamp(currentStaff.getListOfCampsCreated());
                    StaffMainMenu.displayMainMenu();
                    break;
                case 5:
                    // Delete camps created by staff
                    currentStaff.deleteCamp();
                    StaffMainMenu.displayMainMenu();
                    break;
                case 6:
                    // Show Enqueries asked by students
                    StaffEnquiryPage enquiryPage = new StaffEnquiryPage();
                    enquiryPage.run();
                    StaffMainMenu.displayMainMenu();
                    break;
                case 7:
                    // View suggestions given by camp committee members
                    System.out.println("Navigating to suggestion page...");
                    StaffSuggestionMainPage.run();
                    StaffMainMenu.displayMainMenu();
                    break;
                case 8:
                    // Generate camp report
                    System.out.println("Listing camps created by you...");
                    currentStaff.generateCampReport();
                    StaffMainMenu.displayMainMenu();
                    break;
                case 9:
                    // Generate performance report
                    System.out.println("Generating performance report of camp committee members...");
                    currentStaff.generatePerformanceReport();
                    StaffMainMenu.displayMainMenu();
                    break;
                case 10:
                    // Change Password
                    Display.changePassword();
                    ChangeAccountPassword.changePassword();
                    WriteUser.FileWriteStaff();
                    Display.LogOut();
                    continueMenu = false;
                    break;
                case 11:
                    // Log Out
                    Display.LogOut();
                    continueMenu = false;
                    break;
                case 12:
                    // Quit Program
                    Display.ExitMessage();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please enter a valid option.");
                    break;
            }
            System.out.println();
        }
    }
}