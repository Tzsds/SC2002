package UI;

import java.util.List;

import Controller.Enquiry.EnquiryManager;
import Entity.Enquiry;
import Entity.User;
import Repository.EnquiryRepository;

public class EnquiryMainPage {
    private MainMenu mainMenu;
    private User currentUser;

    public EnquiryMainPage(User currentUser) {
        this.currentUser = currentUser;
        mainMenu = new EnquiryMainMenu();
    }

    public void run() {
        boolean continueMenu = true;
        while (continueMenu) {
            mainMenu.displayMainMenu();
            int option = InputScanner.promtForInt("Input your choice of action (1-4):");
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
                    break;
                case 4:

                    System.out.println("Delete your enquiry...");

                    break;
                case 5:
                    System.out.println("Returning to main menu");
                    continueMenu = false;
                    return;
                default:
                    System.out.println("Invalid option. Please enter a valid option.");
                    break;
            }
        }

    }
}
