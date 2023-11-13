package UI;

import Controller.Account.ChangeAccountPassword;
import Controller.Account.LoginManager;
import Controller.File.User.WriteUser;
import Controller.Users.StudentManager;
import Entity.Student;

public class StudentMainPage {

    public static void run() {
        boolean continueMenu = true;
        while (continueMenu) {
            System.out.println("Welcome " + LoginManager.getCurrentUser().getName());
            Student currentStudent = (Student)LoginManager.getCurrentUser();
            StudentMainMenu.displayMainMenu();
            int option = InputScanner.promptForInt("Input your choice of action (1-7): ");
            switch (option) {

                case 1:
                    // Display list of camps available
                    System.out.println("Retrieving list of available camps...");
                    StudentManager.viewAvailableCamps();
                    break;

                case 2:
                    //Register for camp under Student Manager
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
                    currentStudent.viewRegisteredCamps();
                    break;
                case 5:
                    // Initiate password change process
                    SystemMessage.changePassword();
                    ChangeAccountPassword.changePassword();
                    WriteUser.FileWriteStudent();
                    SystemMessage.LogOut();
                    continueMenu = false;
                    break;
                case 6:
                    //Log Out
                    SystemMessage.LogOut();
                    continueMenu = false;
                    break;
                case 7:
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
