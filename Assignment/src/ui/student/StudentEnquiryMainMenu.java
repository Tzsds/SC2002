package ui.student;

import ui.MainMenuInterface;

/**
 * Represents the main menu for Student Enquiries
 * This class implements the MainMenuInterface for standardized menu structures
 * and provides options for students to view, create, edit and delete enquiries
 * 
 * @author SCSZ Group 4
 * @version 1.0 
 * @since 25/11/2023
 */
public class StudentEnquiryMainMenu implements MainMenuInterface {

    /**
     * Displays the Student Enquiry Main Page Menu on the console
     * This menu includes options for Student to view , create, edit and delete
     * enquiries
     */
    public void displayMenu() {
        System.out.println("----------------------------------------");
        System.out.println("Welcome to the Student Enquiry Main Page");
        System.out.println("----------------------------------------");
        System.out.println("1. View Enquiries Created");
        System.out.println("2. Create Enquiry");
        System.out.println("3. Edit Enquiry");
        System.out.println("4. Delete Enquiry");
        System.out.println("5. Back to main menu");
        System.out.println("----------------------------------------");

    }

}
