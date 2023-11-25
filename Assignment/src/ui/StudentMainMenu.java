package ui;
public class StudentMainMenu implements MainMenuInterface{
    public void displayMenu(){
        System.out.println("----------------------------------------");
        System.out.println("Welcome to the Student Main Page");
        System.out.println("----------------------------------------");
        System.out.println("1. View List of Camps Available");
        System.out.println("2. Register for Camp");
        System.out.println("3. Enquiry Page");
        System.out.println("4. View Registered Camps");
        System.out.println("5. Withdraw From Camps");
        System.out.println("6. Change Password");
        System.out.println("7. Logout");
        System.out.println("8. Quit Program");
        System.out.println("----------------------------------------");
    }
}
