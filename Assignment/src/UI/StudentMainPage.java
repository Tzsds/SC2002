package UI;

import Controller.Account.ChangeAccountPassword;
import Controller.Account.LoginManager;
import Controller.File.User.WriteUser;
import Entity.Student;

public class StudentMainPage {
    
    public static void run() {
        boolean continueMenu = true;
        while (continueMenu) {
            System.out.println("Welcome " + LoginManager.getCurrentUser().getName());
            StudentMainMenu.displayMainMenu();
            int option = InputScanner.promtForInt("Input your choice of action (1-5):");
            switch (option) {

                case 1:
                    // Display list of camps available
                    System.out.println("Retrieving list of available camps...");
                    Student.viewAvailableCamps();
                    break;
                case 2:
                    // Navigate to Enquiry Page
                    System.out.println("Navigating to Enquiry Page...");
                    EnquiryMainPage enquiryPage = new EnquiryMainPage(LoginManager.getCurrentUser());
                    enquiryPage.run();
                    break;
                case 3:
                    // Display registered camps
                    System.out.println("Retrieving registered camps...");

                    break;
                case 4:
                    // Initiate password change process
                    System.out.println("Initiating password change process...");
                    ChangeAccountPassword.changePassword();
                    WriteUser.FileWriteStudent();
                    System.out.println("Logging out...");
                    continueMenu = false;
                    break;
                case 5:
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
