package ui.staff;

import ui.MainMenuInterface;

/**
 * Represents the main menu for Staff Enquiries
 * This class implements the MainMenuInterface for standardized menu structures
 * and provides options for staff members to view and reply to enquiries
 * directed to them on their respective camps
 * @author SCSZ Group 4
 * @version 1.0 
 * @since 25/11/2023
 */

public class StaffEnquiryMainMenu implements MainMenuInterface {

    /**
     * Displays the Staff Enquiry Main Page Menu on the console
     * This menu includes options for Staff members to view and reply to the
     * enquiries directed to them on their respective camps
     */
    public void displayMenu() {
        System.out.println("----------------------------------------");
        System.out.println("Welcome to the Staff Enquiry Main Page");
        System.out.println("----------------------------------------");
        System.out.println("1. View Enquiries");
        System.out.println("2. Reply to Enquiry");
        System.out.println("3. Back to main menu");
        System.out.println("----------------------------------------");
    }
}
