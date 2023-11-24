package ui;

import Controller.Account.ChangeAccountPassword;
import Controller.Account.LoginManager;
import Controller.File.User.WriteUser;
import Controller.Users.StudentManager;
import Controller.Utilities.InputScanner;
import entities.Student;

public class StudentMainPage implements MainPage {

    public void run() {
        boolean continueMenu = true;
        Student currentStudent = (Student)LoginManager.getCurrentUser();
        boolean error = false;
        while (continueMenu) {
            if (!error){
                System.out.println("Welcome " + LoginManager.getCurrentUser().getName());
                StudentMainMenu.displayMainMenu();
            }
            int option = InputScanner.promptForInt("Input your choice of action (1-8): ");
            switch (option) {
                case 1:
                    // Display list of camps available
                    System.out.println("Retrieving list of available camps...");
                    StudentManager.viewAvailableCamps();
                    break;

                case 2:
                    //Register for camp under Student Manager
                    System.out.println("Navigating to Camp Registration Page");
                    StudentManager.registerForCamps();
                    if (LoginManager.getUserRole() == "CampCommittee"){
                        continueMenu = false;
                        Display.clearScreen();
                        CampCommitteeMainPage page = new CampCommitteeMainPage();
                        page.run();
                    }
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
                    // Display registered camps
                    System.out.println("Retrieving registered camps...");
                    StudentManager.withdrawFromCamp();
                    break;
                case 6:
                    // Initiate password change process
                    Display.changePassword();
                    ChangeAccountPassword.changePassword();
                    WriteUser.FileWriteStudent();
                    Display.logOut();
                    continueMenu = false;
                    break;
                case 7:
                    //Log Out
                    Display.logOut();
                    System.out.println(currentStudent.getName() + " logged out");
                    continueMenu = false;
                    break;
                case 8:
                    //Quit Program
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
