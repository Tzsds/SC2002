package UserInterface;

import Controller.Account.ChangeAccountPassword;
import Controller.Account.LoginManager;
import Controller.File.User.WriteUser;
import Controller.Report.ReportManager;
import Controller.Users.StaffManager;
import Controller.Utilities.InputScanner;
import Entity.Staff;

public class StaffMainPage implements MainPage {

    public void run() {
        boolean continueMenu = true;
        Staff currentStaff = (Staff) LoginManager.getCurrentUser();
        boolean error = false;
        while (continueMenu) {
            if (!error){
                System.out.println("Welcome " + currentStaff.getName());
                StaffMainMenu.displayMainMenu();
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
                    StaffEnquiryPage enquiryPage = new StaffEnquiryPage();
                    enquiryPage.run();
                    break;
                case 7:
                    // View suggestions given by camp committee members
                    System.out.println("Navigating to suggestion page...");
                    StaffSuggestionMainPage page = new StaffSuggestionMainPage();
                    page.run();
                    break;
                case 8:
                    // View suggestions given by camp committee members
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