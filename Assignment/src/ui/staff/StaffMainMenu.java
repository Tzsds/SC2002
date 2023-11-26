package ui.staff;

import ui.MainMenuInterface;

/**
 * Represents the main menu for Staff members
 * This class provides options to view, create, edit, delete camps, access
 * enquiry and suggestion pages, view registered camps, attendee lists, and
 * generate reports
 * Additionally, includes options to withdraw from a camp, change the password,
 * log out, and quit the program
 * 
 * This class implements the MainMenuInterface to ensure consistency in menu
 * display across different user roles
 * 
 * @author SCSZ Group 4
 * @version 1.0 
 * @since 25/11/2023
 */

public class StaffMainMenu implements MainMenuInterface {

    /**
     * Displays the Staff Main Page menu on the console
     * The menu includes options to perform various actions related to camps
     */
    public void displayMenu() {
        System.out.println("----------------------------------------");
        System.out.println("Welcome to the Staff Main Page");
        System.out.println("----------------------------------------");
        System.out.println("1. View All Camps\n"
                + "2. Create New Camp\n"
                + "3. View Camps Created\n"
                + "4. Edit Camp\n"
                + "5. Delete Camp\n"
                + "6. Enquiry Page\n"
                + "7. Suggestion Page\n"
                + "8. View Attendee List\n"
                + "9. Generate Camp Attendee List Report\n"
                + "10. Generate Enquiry List Report\n"
                + "11. Generate Performance Report\n"
                + "12. Change Password\n"
                + "13. Log Out\n"
                + "14. Quit Program\n"
                + "----------------------------------------");
    }
}
