package Controller;
import Controller.Account.LoginManager;
import Controller.File.FileRead;
import entity.Student;
import ui.CampCommitteeMainPage;
import ui.Display;
import ui.MainPage;
import ui.StaffMainPage;
import ui.StudentMainPage;

public class Start {
    public static void main(String[] args) {
        Display.welcomeMessage();
        int reset = Reset.PromptForReset(); // reset or retain data
        FileRead.read(reset);

        while (true) {
            LoginManager.Login();
            String userRole = LoginManager.getUserRole();
            Display.clearScreen();
            MainPage page;
            // entering main page
            if (userRole == "Student") {
                System.out.println("Redirecting to student menu");
                page = new StudentMainPage();
            }
            else if (userRole == "Staff") {
                System.out.println("Redirecting to staff menu");
                page = new StaffMainPage();
            }
            else{
                System.out.println("Redirecting to Camp Committee menu");
                page = new CampCommitteeMainPage();
            }
            page.run();
            Display.welcomeMessage();
        }
    }
}