package UI;

import Controller.Account.LoginManager;
import Controller.Enquiry.CampCommitteeEnquiryManager;
import Controller.Utilities.InputScanner;
import Entity.CampCommittee;

public class CampCommitteeEnquiryPage {
    private MainMenu mainMenu;

    public CampCommitteeEnquiryPage() {
        mainMenu = new MainMenu();
    }

    public void run() {
        boolean continueMenu = true;
        boolean error = false;
        CampCommittee currentUser = (CampCommittee)LoginManager.getCurrentUser();
        while (continueMenu) {
            if(!error){
                mainMenu.displayMainMenu();
                System.out.println("1. View Enquiries");
                System.out.println("2. Create Your Enquiries");
                System.out.println("3. Reply to Camp's Enquiry");
                System.out.println("4. Back to main menu");
                System.out.println("----------------------------------------");
            }
            error = false;
            int option = InputScanner.promptForInt("Input your choice of action (1-4):");
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
                    CampCommitteeEnquiryManager.replyEnquiry(currentUser);
                    break;
                case 4:
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
