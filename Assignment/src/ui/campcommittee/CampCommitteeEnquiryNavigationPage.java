package ui.campcommittee;

import controller.account.LoginManager;
import controller.enquiry.CampCommitteeEnquiryManager;
import controller.utils.InputScanner;
import entity.CampCommittee;
import ui.NavigationPage;
/**
 * Represents a navigation page for Camp Committee to manage enquiries
 * This class extend NavigationPage class and is sepcific to the Camp Committee user role
 * 
 * @author SCSZ Group 4
 * @version 1.0 
 * @since 25/11/2023
 */
public class CampCommitteeEnquiryNavigationPage extends NavigationPage{
    /**
     * Constructs a new instance of CampCommitteeEnquiryNavigationPage
     * Initializes the main menu for Camp Committee Enquiry
     */
    public CampCommitteeEnquiryNavigationPage() {
        menu = new CampCommitteeEnquiryMainMenu();
    }
    /**
     * Runs the Camp Committee Enquiry Navigation Page displaying the menu
     * and handling user input for various actions related to enquiries
     * The user can view, add, edit, delete and reply to enquiries
     */
    public void run() {
        boolean continueMenu = true;
        boolean error = false;
        CampCommittee currentUser = (CampCommittee) LoginManager.getCurrentUser();
        while (continueMenu) {
            if (!error) {
                menu.displayMenu();
            }
            error = false;
            int option = InputScanner.promptForInt("Input your choice of action (1-6): ");
            switch (option) {

                case 1:
                    // Navigate to Enquiry Page
                    System.out.println("Displaying enquiries:\n");
                    CampCommitteeEnquiryManager.viewAllEnquiriesCampCommittee(currentUser);
                    break;
                case 2:
                    CampCommitteeEnquiryManager.addEnquiry();
                    break;
                case 3:
                    CampCommitteeEnquiryManager.editEnquiry(currentUser);
                    break;
                case 4:
                    CampCommitteeEnquiryManager.deleteEnquiry(currentUser);
                    break;
                case 5:
                    CampCommitteeEnquiryManager.replyEnquiry(currentUser);
                    break;
                case 6:
                    System.out.println("Returning to main menu");
                    continueMenu = false;
                    return;
                default:
                    System.out.println("Invalid option. Please enter a valid option.");
                    error = true;
                    break;
            }
            if (!error) {
                System.out.println();
            }
        }
    }
}
