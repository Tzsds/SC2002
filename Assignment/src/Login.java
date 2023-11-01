import java.util.HashMap;
import java.util.Scanner;

public class Login {

    public void startLogin(){
        LoadDatabase start = new LoadDatabase();
        Scanner sc = new Scanner(System.in);
        HashMap <String, String> passwordManager = new HashMap<String,String>();
        passwordManager = start.Load();
        System.out.print("Please enter your network ID: ");
        String idInput = sc.nextLine();
        
        while (!passwordManager.containsKey(idInput)){
            //System.out.print("\033[H\033[2J");
            //System.out.flush();
            System.out.println("ID does not exist!");
            System.out.print("Please enter your network ID: ");
            idInput = sc.nextLine();
        }
        System.out.println("Username found!");
        System.out.print("Enter your password: ");
        String passwordInput = sc.nextLine();
        
        while (!passwordManager.get(idInput).equals(passwordInput)){
            System.out.println("Wrong password!");
            System.out.print("Enter your password: ");
            passwordInput = sc.nextLine();
        }

        System.out.println("in the system!!");
    }
}
