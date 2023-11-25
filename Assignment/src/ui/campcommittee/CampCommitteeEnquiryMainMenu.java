package ui.campcommittee;

import ui.MainMenuInterface;

/**
 * Represents the main menu for Camp Committee Enquiries
 * Implements the MainMenuInterface for standardized menu structures
 */
public class CampCommitteeEnquiryMainMenu implements MainMenuInterface {

    /**
     * Display the Camp Committee Enquiry Main Page Menu
     * Menu includes options for Camp Committee members to view, create, edit and
     * delete their own enquiries directed to other camps
     * As well as to reply to enquiries for the camp that they are in charged of.
     */
    public void displayMenu() {
        System.out.println("----------------------------------------");
        System.out.println("Welcome to the Camp Committee Enquiry Main Page");
        System.out.println("----------------------------------------");
        System.out.println("1. View Enquiries");
        System.out.println("2. Create Your Enquiries");
        System.out.println("3. Edit Your Enquiries");
        System.out.println("4. Delete Your Enquiries");
        System.out.println("5. Reply to Camp's Enquiry");
        System.out.println("6. Back to main menu");
        System.out.println("----------------------------------------");
    }
}
