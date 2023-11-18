package Controller;
import Controller.Account.LoginManager;
import Controller.File.FileRead;
import Entity.User;
import UI.CampCommitteeMainPage;
import UI.StaffMainPage;
import UI.StudentMainPage;
import UI.Display;

public class Start {
    public static void main(String[] args) {
        Display.WelcomeMessage();
        int reset = Reset.PromptForReset(); // reset or retain data
        FileRead.read(reset);

        while (true) {
            LoginManager.Login();
            User currentUser = LoginManager.getCurrentUser();
            String userRole = LoginManager.getUserRole();
            Display.clearScreen();
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
            Display.WelcomeMessage();
            System.out.println();
        }
    }
}