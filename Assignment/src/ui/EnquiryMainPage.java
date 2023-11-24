package ui;

import Controller.enquiries.EnquiryManager;
import Controller.utils.InputScanner;
import entity.User;

public class EnquiryMainPage implements MainPage {
    private MainMenu mainMenu;
    private User currentUser;

    public EnquiryMainPage(User currentUser) {
        this.currentUser = currentUser;
        mainMenu = new EnquiryMainMenu();
    }

    public void run() {
        boolean continueMenu = true;
        boolean error = false;
        while (continueMenu) {
            if(!error){
                mainMenu.displayMainMenu();
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
