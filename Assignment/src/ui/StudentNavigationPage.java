package ui;

import controller.account.ChangeAccountPassword;
import controller.account.LoginManager;
import controller.file.user.WriteUser;
import controller.user.StudentManager;
import controller.utils.InputScanner;
import entity.Student;

public class StudentNavigationPage extends NavigationPage {

    public StudentNavigationPage(){
        menu = new StudentMainMenu();
    }
    public void run() {
        boolean continueMenu = true;
        Student currentStudent = (Student)LoginManager.getCurrentUser();
        boolean error = false;
        while (continueMenu) {
            if (!error){
                System.out.println("Welcome " + LoginManager.getCurrentUser().getName());
                menu.displayMenu();
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
                        CampCommitteeNavigationPage page = new CampCommitteeNavigationPage();
                        page.run();
                    }
                    break;
                case 3:
                    // Navigate to Enquiry Page
                    System.out.println("Navigating to Enquiry Page...");
                    EnquiryMainPage enquiryPage = new EnquiryMainPage();
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
