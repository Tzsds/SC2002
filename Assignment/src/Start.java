import java.util.ArrayList;

public class Start {
    public static void main(String[] args){
        //CHECK IF RESET OR NOT RESET
        System.out.println("Welcome to NTU CAMS System!");
        //DO THE FILE READ BASED ON RESET/NOT RESET

        ArrayList <User> users;
        FileRead fr = new FileRead();
        FileWriting fw = new FileWriting();
        users = fr.readWithReset();
        fw.FileWrite(users);
        //SHOW THE LOGIN SYSTEM
        //ACCOUNT VERIFICATION
        AccountVerification log = new AccountVerification();
        User current = log.Login(users);
        //LOAD THE PAGE FOR THE SPECIFIC ROLE
        ChangeAccountPassword cp = new ChangeAccountPassword();
        cp.updatePassword(current);
        users.set(log.getIndex(), current);
        fw.FileWrite(users);
        System.out.println("success");
    }
}
