//import java.util.Scanner;

public class StudentMainPage {
    private MainMenu mainMenu;
    private InputScanner inputScanner;
    private User currentUser;
    private String userRole;

    public StudentMainPage(User currentUser, String userRole) {
        this.currentUser = currentUser;
        this.userRole = userRole;
        mainMenu = new StudentMainMenu();
        inputScanner = new InputScanner();

    }

    public void run() {
        boolean continueMenu = true;
        while (continueMenu) {
            System.out.println("Welcome " + currentUser.getName());
            mainMenu.displayMainMenu();
            int option = inputScanner.promtForInt("Input your choice of action (1-7):");
            switch (option) {

                case 1:
                    // Navigate to Faculty Information page
                    System.out.println("Navigating to Faculty Information page...");
                    break;
                case 2:
                    // Display status as committee
                    System.out.println("Checking your status as committee...");
                    break;
                case 3:
                    // Display list of camps available
                    System.out.println("Retrieving list of available camps...");
                    break;
                case 4:
                    // Navigate to Enquiry Page
                    System.out.println("Navigating to Enquiry Page...");
                    break;
                case 5:
                    // Display registered camps
                    System.out.println("Retrieving registered camps...");

                    break;
                case 6:
                    // Initiate password change process
                    System.out.println("Initiating password change process...");
                    ChangeAccountPassword.changePassword();
                    System.out.println("Returning back to main page...");
                    break;
                case 7:
                    System.out.println("Logging out...");
                    continueMenu = false;
                    return;
                default:
                    System.out.println("Invalid option. Please enter a valid option.");
                    break;
            }
        }

    }

    /*
     * public static void main(String[] args) {
     * StudentMainPage studentMainPage = new StudentMainPage(currentUser,
     * "Student");
     * studentMainPage.run();
     * 
     * /*
     * // Display main menu
     * System.out.println("----------------------------------------");
     * System.out.println("Welcome to the Main Page");
     * System.out.println("----------------------------------------");
     * System.out.println("1. View Faculty Information");
     * System.out.println("2. Status As Committee");
     * System.out.println("3. View List of Camps Available");
     * System.out.println("4. Enquiry Page");
     * System.out.println("5. View Registered Camps");
     * System.out.println("6. Change Password");
     * System.out.println("7. Logout");
     * System.out.println("----------------------------------------");
     * 
     * // Handle user input using switch statement
     * Scanner input = new Scanner(System.in);
     * System.out.print("Selection: ");
     * int option = input.nextInt();
     * 
     * switch (option) {
     * case 1:
     * // Navigate to Faculty Information page
     * System.out.println("Navigating to Faculty Information page...");
     * break;
     * case 2:
     * // Display status as committee
     * System.out.println("Checking your status as committee...");
     * break;
     * case 3:
     * // Display list of camps available
     * System.out.println("Retrieving list of available camps...");
     * break;
     * case 4:
     * // Navigate to Enquiry Page
     * System.out.println("Navigating to Enquiry Page...");
     * break;
     * case 5:
     * // Display registered camps
     * System.out.println("Retrieving registered camps...");
     * 
     * break;
     * case 6:
     * // Initiate password change process
     * System.out.println("Initiating password change process...");
     * //ChangeAccountPassword changeAccountPassword = new ChangeAccountPassword();
     * break;
     * case 7:
     * System.out.println("Logging out...");
     * break;
     * default:
     * System.out.println("Invalid option. Please enter a valid option.");
     * break;
     * }
     * 
     * input.close();
     * }
     */
    // } */
}
