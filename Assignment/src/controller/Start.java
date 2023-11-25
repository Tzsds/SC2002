package controller;
import controller.account.LoginManager;
import controller.file.FileRead;
import ui.Display;
import ui.NavigationPage;
import ui.campcommittee.CampCommitteeNavigationPage;
import ui.staff.StaffNavigationPage;
import ui.student.StudentNavigationPage;

public class Start {
    public static void main(String[] args) {
        Display.welcomeMessage();
        int reset = Reset.PromptForReset(); // reset or retain data
        FileRead.read(reset);

        while (true) {
            LoginManager.Login();
            String userRole = LoginManager.getUserRole();
            Display.clearScreen();
            NavigationPage page;
            // entering main page
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
            page.run();
            Display.welcomeMessage();
        }
    }
}