package controller.account;

import controller.utils.InputScanner;
import entity.User;

public class ChangeAccountPassword {

    public static void changePassword(){
        String password = passwordInput();
        UpdatePassword(password);
    }
    
    private static void UpdatePassword(String password){
        User currentUser = LoginManager.getCurrentUser();
        currentUser.setPassword(password);
        System.out.println("Password successfully changed!");
    }

    private static String passwordInput(){
        String input1, input2;
        while (true){
            input1 = InputScanner.promptForString("Enter your new password: ");
            if (input1.equals("password")){
                System.out.println("Cannot use \"password\" as new password");
                continue;
            }
            input2 = InputScanner.promptForString("Please re-enter you new password: ");
            if (input1.equals(input2)){
                break;
            }
            System.out.println("Passwords do not match! Please try again");
        }
        return input1;
    }

}
