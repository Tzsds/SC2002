package Controller;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Reset {
    public static int PromptForReset(){
        Scanner sc = new Scanner(System.in);
        int choice;
        while (true){
            try{
                System.out.println("1. Reset data");
                System.out.println("2. Use previous data");
                System.out.print("Input your choice here: ");
                choice = sc.nextInt();
                if (choice != 1 && choice != 2){
                    System.out.println("Please enter the values 1 or 2 only");
                    continue;
                }
                return choice;
            }
            catch(InputMismatchException e){
                System.out.println("Invalid input. Please enter a integer instead:");
                sc.nextLine();
            }
        }
    }
}
