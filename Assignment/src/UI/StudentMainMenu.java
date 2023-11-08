package UI;
public class StudentMainMenu extends MainMenu{
    @Override
    public void displayMainMenu(){
        super.displayMainMenu();
        System.out.println("1. View Faculty Information");
        System.out.println("2. Status As Committee");
        System.out.println("3. View List of Camps Available");
        System.out.println("4. Enquiry Page");
        System.out.println("5. View Registered Camps");
        System.out.println("6. Change Password");
        System.out.println("7. Logout");
        System.out.println("----------------------------------------");
    }
}
