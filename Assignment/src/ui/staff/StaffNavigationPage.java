package ui.staff;

import controller.account.ChangeAccountPassword;
import controller.account.LoginManager;
import controller.file.user.WriteUser;
import controller.report.ReportManager;
import controller.user.StaffManager;
import controller.utils.InputScanner;
import entity.Staff;
import ui.Display;
import ui.NavigationPage;

/**
 * Represents a navigation page for Staff users
 * This class extends NavigationPage class and provide menu options
 * for Staff to interact with the system
 * 
 * @author SCSZ Group 4
 * @version 1.0 
 * @since 25/11/2023
 */
public class StaffNavigationPage extends NavigationPage {
    /**
     * Constructs a new instance of StaffNavigationPage
     * Initializes the main menu for Staff
     */
    public StaffNavigationPage(){
        menu = new StaffMainMenu();
    /**
     * Runs the Staff Navigation Page displaying the menu
     * and handling user input for various actions related to camp management
     * The user can view, create and edit camps, view camps created, manage enquiries,
     * manage suggestions, manage report, change password, logging out and quitting the page
     */
    }
    public void run() {
        boolean continueMenu = true;
        Staff currentStaff = (Staff) LoginManager.getCurrentUser();
        boolean error = false;
        while (continueMenu) {
            if (!error){
                System.out.println("Welcome " + currentStaff.getName());
                menu.displayMenu();
            }
            error = false;
            int option = InputScanner.promptForInt("Input your choice of action (1-14): ");
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
                    // Edit camp
                    StaffManager.editCamp(currentStaff.getListOfCampsCreated());
                    break;
                case 5:
                    // Delete camps created by staff
                    currentStaff.deleteCamp();
                    break;
                case 6:
                    // Show Enqueries asked by students
                    StaffEnquiryNavigationPage enquiryPage = new StaffEnquiryNavigationPage();
                    enquiryPage.run();
                    break;
                case 7:
                    // View suggestions given by camp committee members
                    System.out.println("Navigating to suggestion page...");
                    StaffSuggestionNavigationPage page = new StaffSuggestionNavigationPage();
                    page.run();
                    break;
                case 8:
                    // View list of camps
                    System.out.println("Listing camps created by you...");
                    ReportManager.viewCampReportForStaff();
                    break;
                case 9:
                    // Generate camp report
                    System.out.println("Listing camps created by you...");
                    ReportManager.generateCampReportForStaff();
                    break;
                case 10:
                    // Generate enquiry list report
                    System.out.println("Generating enquiry report of camp...");
                    ReportManager.generateEnquiryReportForStaff();
                    break;    
                case 11:
                    // Generate performance report
                    System.out.println("Generating performance report of camp committee members...");
                    ReportManager.generatePerformanceReportForStaff();
                    break;
                case 12:
                    // Change Password
                    Display.changePassword();
                    ChangeAccountPassword.changePassword();
                    WriteUser.FileWriteStaff();
                    Display.logOut();
                    continueMenu = false;
                    break;
                case 13:
                    // Log Out
                    Display.clearScreen();
                    Display.logOut();
                    System.out.println(currentStaff.getName() + " logged out");
                    continueMenu = false;
                    break;
                case 14:
                    // Quit Program
                    Display.exitMessage();
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