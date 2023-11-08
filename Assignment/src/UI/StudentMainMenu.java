package UI;
public class StudentMainMenu extends MainMenu{
    @Override
    public void displayMainMenu(){
        super.displayMainMenu();
        System.out.println("1. View List of Camps Available");
        System.out.println("2. Enquiry Page");
        System.out.println("3. View Registered Camps");
        System.out.println("4. Change Password");
        System.out.println("5. Logout");
        System.out.println("----------------------------------------");
    }
}
