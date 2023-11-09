package UI;

import java.util.Scanner;

public class InputScanner {
    private static final Scanner scanner = new Scanner(System.in);

    public static int promtForInt(String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }

    public static String promptForString(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public static String waitForUserInput() {
        return scanner.nextLine();
    }
}
