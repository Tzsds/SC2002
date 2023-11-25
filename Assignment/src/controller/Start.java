package controller;
import controller.account.LoginManager;
import controller.file.FileRead;
import ui.Display;
import ui.NavigationPage;
import ui.campcommittee.CampCommitteeNavigationPage;
import ui.staff.StaffNavigationPage;
import ui.student.StudentNavigationPage;

/** 
 * The main class responsible for starting the program
 * It initializes necessary components, handles user login and redirects users based
 * on their roles to the appropriate navigation pages
 * 
 * @author SCSZ Group 4
 * @version 1.0 
 * @since 25/11/2023
 */
public class Start {

    /**
     * The main method that starts the program
     * 
     * @param args the command-line arguments (not used in this program)
     */
    public static void main(String[] args) {
        // Display welcome message and prompt for data reset
        Display.welcomeMessage();
        int reset = Reset.PromptForReset(); // reset or retain data
        FileRead.read(reset);

        while (true) {
            // Perform user login
            LoginManager.Login();
            String userRole = LoginManager.getUserRole();
            Display.clearScreen();
            NavigationPage page;
            // entering main page

            // Determine the user's role and redirect to the appropriate navigation page
            if (userRole == "Student") {
                System.out.println("Redirecting to student menu");
                page = new StudentNavigationPage();
            }
            else if (userRole == "Staff") {
                System.out.println("Redirecting to staff menu");
                page = new StaffNavigationPage();
            }
            else{
                System.out.println("Redirecting to Camp Committee menu");
                page = new CampCommitteeNavigationPage();
            }
            // Run the selected navigation page
            page.run();
            Display.welcomeMessage();
        }
    }
}