package ui.campcommittee;

/**
 * Represents the main menu for the Camp Committee members
 * Provides options to view camps, register for a camp, access enquiry and
 * suggestion pages,
 * view registered camps, attendee lists, and generate reports
 * additionally, includes options to withdraw from a camp, change the password,
 * logout and quit the program
 * @author SCSZ Group 4
 * @version 1.0 
 * @since 25/11/2023
 */
public class CampCommitteeMainMenu {

    /**
     * Displays the Camp Committee Navigation Page menu
     * The menu includes options to perform various actions related to camps and
     * committee responsibilities
     */
    public static void displayMainMenu() {
        System.out.println("----------------------------------------");
        System.out.println("Welcome to the Camp Committee Main Page");
        System.out.println("----------------------------------------");
        System.out.println("1. View List of Camps Available");
        System.out.println("2. Register For Camp");
        System.out.println("3. Enquiry Page");
        System.out.println("4. Suggestion Page");
        System.out.println("5. View Registered Camps");
        System.out.println("6. View Attendee List as Camp Committee");
        System.out.println("7. Generate Report For Camp Attendee List");
        System.out.println("8. Generate Report For Camp Enquiry List");
        System.out.println("9. Withdraw from Camp");
        System.out.println("10. Change Password");
        System.out.println("11. Logout");
        System.out.println("12. Quit Program");
        System.out.println("----------------------------------------");
    }
}
