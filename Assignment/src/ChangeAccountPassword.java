import java.util.Scanner;

public class ChangeAccountPassword {

    public void updatePassword(User u){
        Scanner sc = new Scanner(System.in);
        String input1, input2;
        while (true){
            System.out.print("Enter your new password: ");
            input1 = sc.nextLine();
            System.out.print("Please re-enter you new password: ");
            input2 = sc.nextLine();
            if (input1.equals(input2)){
                break;
            }
            System.out.println("Passwords do not match! Please try again");
        }
        System.out.println("Password successfully changed");
        u.setPassword(input1);
        sc.close();
    }
}
