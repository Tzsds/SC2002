package UI;
import Controller.Account.ChangeAccountPassword;
import Entity.Staff;
import Entity.User;

public class StaffMainPage {
    private MainMenu mainMenu;
    private InputScanner inputScanner;
    private User currentUser;

    public StaffMainPage(User currentUser) {
        this.currentUser = currentUser;
        mainMenu = new StaffMainMenu();
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
                    //Show all Camps
                    System.out.println("Showing all camps created...");
                    //Staff.viewAllCamps();
                    break;
                case 3:
                    //Create new camp
                    System.out.println("Navigating to create camp page...");
                    Staff.createNewCamp();
                    break;
                case 4:
                    //View camps created by the staff
                    System.out.println("Showing camps created by you...");
                    //viewCampCreatedList();
                    break;                
                case 5:
                    //Show Enqueries asked by students
                    System.out.println("Showing enqueries by students...");
                    //viewEnqueries();
                    break;
                case 6:
                    //View suggestions given by camp committee members
                    System.out.println("Showing suggestions from camp committee members...");
                    //viewSuggestions();
                    break;
                case 7:
                    //Generate camp report
                    System.out.println("Generating camp report...");
                    //generateCampReport();
                    break;
                case 8:
                    //Generate performance report
                    System.out.println("Generating performance report of camp committee members...");
                    //generatePerformanceReport();
                    break;
                case 9:
                    // Initiate password change process
                    System.out.println("Initiating password change process...");
                    ChangeAccountPassword.changePassword();
                    System.out.println("Returning back to main page...");
                    break;
                case 10:
                    System.out.println("Logging out...");
                    continueMenu = false;
                    return;
                default:
                    System.out.println("Invalid option. Please enter a valid option.");
                    break;
            }
        }
    }
}