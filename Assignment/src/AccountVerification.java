import java.util.ArrayList;
import java.util.Scanner;

public class AccountVerification {

    public void Login(ArrayList<User> users){
        Scanner sc = new Scanner(System.in);
        int enter = 0;
        String correctPW = null;
        while (true){
            System.out.print("Please enter your userID: ");
            String idInput = sc.nextLine();
            for (int i=0; i<users.size(); i++){
                User temp = users.get(i);
                if (temp.getUserID().equals(idInput)){
                    enter = 1;
                    correctPW = temp.getPassword();
                    break;
                }
            }
            if (enter == 1){
                break;
            }
            System.out.println("Invalid userID!!");
            
        }
        enter = 0;
        while (enter == 0){
            System.out.print("Please enter your password: ");
            String pwInput = sc.nextLine();
            if (pwInput.equals(correctPW)){
                break;
            }
            System.out.println("Invalid password! Please try again");
            
        }
    }
}
