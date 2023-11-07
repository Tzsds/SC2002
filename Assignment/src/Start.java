//import java.util.ArrayList;

public class Start {
    public static void main(String[] args){
        System.out.println("Welcome to NTU CAMS System!");

        //ArrayList <User> users;
        int reset = Reset.PromptForReset();
        //users = FileRead.read(reset); 
        FileRead.read(reset);
        FileWriting.FileWrite();

        //User current = AccountVerification.Login();
        AccountVerification.Login();

        //CHANGE PASSWORD FUNCTION 
        User currentUser = AccountVerification.getCurrentUser();
        ChangeAccountPassword.updatePassword(currentUser);
        //users.set(AccountVerification.getIndex(), current);
        //UserRepository.replaceUser(current, AccountVerification.getIndex());

        FileWriting.FileWrite();

        System.out.println(currentUser.getName() + " logged out");
        AccountVerification.Login();
        currentUser = AccountVerification.getCurrentUser();
        ChangeAccountPassword.updatePassword(currentUser);
        System.out.println("end of program");
    }
}
