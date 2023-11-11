package Controller;
import Controller.Account.LoginManager;
import Controller.File.FileRead;
import Entity.User;
import UI.CampCommitteeMainPage;
import UI.StaffMainPage;
import UI.StudentMainPage;

public class Start {
    public static void main(String[] args) {
        System.out.println("Welcome to NTU CAMS System!");
        int reset = Reset.PromptForReset(); // reset or retain data
        FileRead.read(reset);

        while (true) {
            LoginManager.Login();
            User currentUser = LoginManager.getCurrentUser();
            String userRole = LoginManager.getUserRole();
            // the following 3 codes below are just for testing purposes
            System.out.println("Current user: " + currentUser.getName());
            System.out.println("user id:" + currentUser.getUserID());
            System.out.println("user role: " + userRole);
            System.out.println();

            // entering main pages
            if (userRole == "Student") {
                System.out.println("Redirecting to student menu");
                StudentMainPage.run();
            }
            else if (userRole == "Staff") {
                System.out.println("Redirecting to staff menu");
                StaffMainPage.run();
            }
            else{
                System.out.println("Redirecting to Camp Committee menu");
                CampCommitteeMainPage.run();
            }

            System.out.println(currentUser.getName() + " logged out");
            System.out.println("Wecome to NTU CAMS System!");
        }
    }
}