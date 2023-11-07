import java.util.Scanner;

public class AccountInput {
    private static Scanner sc = new Scanner(System.in);

    public static String inputID(){
        System.out.print("Enter your User ID: ");
        return sc.nextLine();
    }

    public static String inputPW(){
        System.out.print("Enter your password: ");
        return sc.nextLine();
    }
}
