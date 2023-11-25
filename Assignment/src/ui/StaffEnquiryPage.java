package ui;

import controller.user.StaffManager;
import controller.utils.InputScanner;

public class StaffEnquiryPage extends MainPage {
    public StaffEnquiryPage(){
        menu = new StaffEnquiryMainMenu();
    }

    public void run() {
        boolean continueMenu = true;
        boolean error = false;
        while (continueMenu) {
            if (!error){
                System.out.println("----------------------------------------");
                System.out.println("Welcome to the Staff Enquiry Main Page");
                System.out.println("----------------------------------------");
                System.out.println("1. View Enquiries");
                System.out.println("2. Reply to Enquiry");
                System.out.println("3. Back to main menu");
                System.out.println("================================");
            }
            error = false;
            int option = InputScanner.promptForInt("Input your choice of action (1-3): ");
            switch (option) {

                case 1:
                    // Navigate to Enquiry Page
                    System.out.println("Displaying enquiries:\n");
                    StaffManager.viewEnquiries();
                    break;
                case 2:
                    StaffManager.replyEnquiry();
                    break;
                case 3:
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
