package UI;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputScanner {
    private static final Scanner scanner = new Scanner(System.in);

    public static int promptForInt(String prompt) {
        while (true){
            try{
                System.out.print(prompt);
                int x = scanner.nextInt();
                scanner.nextLine(); //This is to clear the '\n' left in the input stream
                return x;
            }
            catch (InputMismatchException e){
                System.out.println("Wrong input entered. Please try again");
                InputScanner.clear();
            }
        }
    }

    public static String promptForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static String waitForUserInput() {
        return scanner.nextLine();
    }

    public static String waitForUserInputString() {
        return scanner.nextLine();
    }

    public static int waitForUserInputInt() {
        return scanner.nextInt();
    }

    public static void clear(){
        scanner.nextLine();
    }
}
