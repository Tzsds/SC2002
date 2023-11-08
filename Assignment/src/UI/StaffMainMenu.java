package UI;
public class StaffMainMenu extends MainMenu {
    public void displayMainMenu(){
        super.displayMainMenu();
        System.out.println("(1) View All Camps\n"
                         + "(2) Create New Camp\n"
                         + "(3) View Camps Created\n"
                         + "(4) View Enquiries\n"
                         + "(5) View Suggestions\n"
                         + "(6) Generate Camp Report\n"
                         + "(7) Generate Performance Report\n"
                         + "(8) Change Password\n"
                         + "(9) Log Out\n"
                         + "----------------------------------------");
    }
}
