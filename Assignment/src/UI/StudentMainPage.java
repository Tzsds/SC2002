package UI;

import Controller.Account.ChangeAccountPassword;
import Controller.Account.LoginManager;
import Controller.File.User.WriteUser;
import Entity.Camp;
import Entity.Staff;
import Entity.Student;

public class StudentMainPage {

    public static void run() {
        boolean continueMenu = true;
        while (continueMenu) {
            System.out.println("Welcome " + LoginManager.getCurrentUser().getName());
            Student currentStudent = (Student)LoginManager.getCurrentUser();
            StudentMainMenu.displayMainMenu();
            int option = InputScanner.promptForInt("Input your choice of action (1-6):");
            switch (option) {

                case 1:
                    // Display list of camps available
                    System.out.println("Retrieving list of available camps...");
                    Student.viewAvailableCamps();
                    break;

                case 2:
                    System.out.println("Navigating to Camp Registration Page");

                    //Camp.addParticipants(currentStudent);
                    //Camp.addCampCommittee(currentStudent);
                    break;
                case 3:
                    // Navigate to Enquiry Page
                    System.out.println("Navigating to Enquiry Page...");
                    EnquiryMainPage enquiryPage = new EnquiryMainPage(LoginManager.getCurrentUser());
                    enquiryPage.run();
                    break;
                case 4:
                    // Display registered camps
                    System.out.println("Retrieving registered camps...");

                    break;
                case 5:
                    // Initiate password change process
                    System.out.println("Initiating password change process...");
                    ChangeAccountPassword.changePassword();
                    WriteUser.FileWriteStudent();
                    System.out.println("Logging out...");
                    continueMenu = false;
                    break;
                case 6:
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
