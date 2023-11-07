import java.util.Scanner;

public class ChangeAccountPassword {

    public static void changePassword(){
        String password = passwordInput();
        UpdatePassword(password);
    }
    
    private static void UpdatePassword(String password){
        User currentUser = AccountVerification.getCurrentUser();
        currentUser.setPassword(password);
        UserRepository.replaceUser(currentUser, AccountVerification.getIndex());
        FileWriting.FileWriteUser();
        System.out.println("Password successfully changed!");
    }

    private static String passwordInput(){
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
        return input1;
    }

}
