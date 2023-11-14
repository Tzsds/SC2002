package Controller;
import java.util.InputMismatchException;

import UI.InputScanner;

public class Reset {
    public static int PromptForReset(){
        int choice;
        while (true){
            try{
                System.out.println("1. Reset data");
                System.out.println("2. Use previous data");
                choice = InputScanner.promptForInt("Input your choice here: ");
                if (choice != 1 && choice != 2){
                    System.out.println("Please enter the values 1 or 2 only");
                    continue;
                }
                return choice;
            }
            catch(InputMismatchException e){
                System.out.println("Invalid input. Please enter a integer instead:");
                InputScanner.clear();
            }
        }
    }
}
