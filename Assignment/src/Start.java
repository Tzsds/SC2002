import java.util.ArrayList;

public class Start {
    public static void main(String[] args){
        System.out.println("Welcome to NTU CAMS System!");

        ArrayList <User> users;
        int reset = Reset.PromptForReset();
        users = FileRead.read(reset); 

        FileWriting.FileWrite(users);
        User current = AccountVerification.Login(users);
        
        //CHANGE PASSWORD FUNCTION 
        ChangeAccountPassword cp = new ChangeAccountPassword();
        cp.updatePassword(current);
        users.set(AccountVerification.getIndex(), current);
        FileWriting.FileWrite(users);
        System.out.println("success");
    }
}
