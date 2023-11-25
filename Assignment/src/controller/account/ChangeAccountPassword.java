package controller.account;

import controller.utils.InputScanner;
import entity.User;

/**
 * The ChangeAccountPassword class provides functionality to change the password
 * for the currently logged-in user
 * 
 * @author SCSZ Group 4
 * @version 1.0 
 * @since 25/11/2023
 */
public class ChangeAccountPassword {
    /**
     * Prompts the user for a new password and updates the current user's password
     */

    public static void changePassword() {
        String password = passwordInput();
        UpdatePassword(password);
    }

    /**
     * Updates the password for the current user
     * 
     * @param password - The new password to set for the current user
     */

    private static void UpdatePassword(String password) {
        User currentUser = LoginManager.getCurrentUser();
        currentUser.setPassword(password);
        System.out.println("Password successfully changed!");
    }

    /**
     * Prompts the user for a new password and validates the input
     * Method ensures that the new password is not the same as the old password
     * and it does not allow the use of the word "password" as a password
     * 
     * @return - The validated new password entered by the user
     */
    private static String passwordInput() {
        String oldpassword = LoginManager.getCurrentUser().getPassword();
        String input1, input2;
        while (true) {
            input1 = InputScanner.promptForString("Enter your new password: ");
            if (input1.equals("password")) {
                System.out.println("Cannot use \"password\" as new password");
                continue;
            }
            if (input1.equals(oldpassword)) {
                System.out.println("Cannot use old password as new password");
                continue;
            }
            input2 = InputScanner.promptForString("Please re-enter you new password: ");
            if (input1.equals(input2)) {
                break;
            }
            System.out.println("Passwords do not match! Please try again");
        }
        return input1;
    }

}
