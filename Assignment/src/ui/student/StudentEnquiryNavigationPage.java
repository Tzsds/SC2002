package ui.student;

import controller.account.LoginManager;
import controller.enquiry.EnquiryManager;
import controller.utils.InputScanner;
import entity.User;
import ui.NavigationPage;
/**
 * Represents a navigation page for Student to manage enquiries
 * This class extend NavigationPage class and is sepcific to the Student user role
 * @author SCSZ Group 4
 * @version 1.0 
 * @since 25/11/2023
 */
public class StudentEnquiryNavigationPage extends NavigationPage {
    /**
     * Constructs a new instance of StudentEnquiryNavigationPage
     * Initializes the main menu for Student Enquiry
     */ 
    public StudentEnquiryNavigationPage() {
        menu = new StudentEnquiryMainMenu();
    }
    /**
     * Runs the Student Enquiry Navigation Page displaying the menu
     * and handling user input for various actions related to enquiries
     * The user can view , create, edit and delete enquiries
     */
    public void run() {
        boolean continueMenu = true;
        boolean error = false;
        User currentUser = LoginManager.getCurrentUser();
        while (continueMenu) {
            if(!error){
                menu.displayMenu();
            }
            error = false;
            int option = InputScanner.promptForInt("Input your choice of action (1-5): ");
            switch (option) {

                case 1:
                    // Navigate to Enquiry Page
                    System.out.println("Displaying enquiries:\n");
                    EnquiryManager.viewStudentEnquiries(currentUser);
                    break;
                case 2:
                    System.out.println("Creating a new enquiry");
                    EnquiryManager.addEnquiry();
                    break;
                case 3:

                    System.out.println("Edit your enquiry...");
                    EnquiryManager.editEnquiry(currentUser);
                    break;
                case 4:
                    EnquiryManager.deleteEnquiry(currentUser);
                    break;
                case 5:
                    System.out.println("Returning to main menu");
                    continueMenu = false;
                    return;
                default:
                    System.out.println("Invalid option. Please enter a valid option.");
                    error = true;
                    break;
            }
            if(!error){
                System.out.println();
            }
        }
    }
}
