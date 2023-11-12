package UI;

import java.util.Scanner;

public class InputScanner {
    private static final Scanner scanner = new Scanner(System.in);

    public static int promptForInt(String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }

    public static String promptForString(String prompt) {
        System.out.print(prompt);
        String s = scanner.nextLine();
        return s;
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
}
