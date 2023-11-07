
import java.util.Scanner;

public class staffUI {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Entering Staff Main Page...\n");
        
        //include staff's name
        System.out.println("(1) Faculty Information\n"
                         + "(2) View All Camps\n"
                         + "(3) Create New Camp\n"
                         + "(4) View Camps Created\n"
                         + "(5) View Enquiries\n"
                         + "(6) View Suggestions\n"
                         + "(7) Generate Camp Report\n"
                         + "(8) Generate Performance Report\n"
                         + "(9) Change Password\n"
                         + "(10) Log Out\n");

        System.out.print("Input your choice of action (1-10): ");
        int input = sc.nextInt();

        do {
            switch(input) {
                case 1:
                    //showFacultyInformation(faculty);
                    //create Faculty class? SCSE, ADM, EEE is-a (inherits) faculty, override showFacultyInformation)?
                    break;
                case 2:
                    //Staff.viewAllCamps();
                    //still need to be changed
                    break;
                case 3:
                    Staff.createNewCamp();
                    break;
                case 4:
                    //viewCampCreatedList();
                    break;
                case 5:
                    //viewEnqueries();
                    break;
                case 6:
                    //viewSuggestions();
                    break;
                case 7:
                    //generateCampReport();
                    break;
                case 8:
                    //generatePerformanceReport();
                    break;
                case 9:
                    //changePassword();
                    break;
                case 10:
                    //logOut();
                    break;
            }
            System.out.print("Input your choice of action (1-10):");
            input = sc.nextInt();

        } while (input != 10);
    }
}
