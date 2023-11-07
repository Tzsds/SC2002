import java.util.Scanner;

public class InputScanner {
    private final Scanner scanner;

    public InputScanner() {
        this.scanner = new Scanner(System.in);
    }

    public int promtForInt(String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }
}
