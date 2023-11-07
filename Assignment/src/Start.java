//import java.util.ArrayList;

public class Start {
    public static void main(String[] args){
        System.out.println("Welcome to NTU CAMS System!");
        int reset = Reset.PromptForReset(); //reset or retain data
        FileRead.read(reset);
        AccountVerification.Login();

        User currentUser = AccountVerification.getCurrentUser();

        //change password for current user in the system
        ChangeAccountPassword.changePassword();
        System.out.println(currentUser.getName() + " logged out");

        //another user will be in the system
        AccountVerification.Login();
        currentUser = AccountVerification.getCurrentUser();
        
        //change password for current user in the system
        ChangeAccountPassword.changePassword();
        System.out.println("end of program");
    }
}
