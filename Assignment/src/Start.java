//import java.util.ArrayList;

public class Start {
    public static void main(String[] args) {
        System.out.println("Welcome to NTU CAMS System!");
        int reset = Reset.PromptForReset(); // reset or retain data
        FileRead.read(reset);
        AccountVerification.Login();

        User currentUser = AccountVerification.getCurrentUser();

        // change password for current user in the system
        ChangeAccountPassword.changePassword();
        System.out.println(currentUser.getName() + " logged out");

        // another user will be in the system
        AccountVerification.Login();
        currentUser = AccountVerification.getCurrentUser();

        /*
         * commented out to test the linking to the different pages
         * // change password for current user in the system
         * ChangeAccountPassword.changePassword();
         * System.out.println("end of program");
         */

        String userRole = UserRepository.getUserRole(currentUser.getUserID());
        // the following 3 codes below are just for testing purposes
        System.out.println("Current user: " + currentUser.getName());
        System.out.println("user id:" + currentUser.getUserID());
        System.out.println("user role: " + userRole);

        if (userRole == "Student") {
            System.out.println("Redirecting to student menu");
            StudentMainPage studentPage = new StudentMainPage(currentUser, userRole);
            studentPage.run();
        } else if (userRole == "Staff") {
            System.out.println("Redirecting to staff menu");
        }
    }
}
