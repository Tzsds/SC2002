package UI;

import java.time.DateTimeException;
import java.time.LocalDate;
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

    public static LocalDate promptForDate(String prompt) {
        while (true){
            try{
            String dateString = InputScanner.promptForString("Enter camp start date in format (dd/mm/yyyy): ");
            String[] parts = dateString.split("/");

            if (parts.length != 3){
                throw new IllegalArgumentException ("Invalid date format. Please use (dd/mm/yyyy) ");
            }
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            LocalDate date = LocalDate.of(year, month, day);
            LocalDate currenDate = LocalDate.now();
            if (date.isBefore(currenDate)){
                throw new IllegalArgumentException ("The date you input has already past. Please enter again");
            }
            return date;
            }catch (InputMismatchException e){
                System.out.println("Invalid input. Please enter valid date in specified format");
            }catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e){
                System.out.println("Invalid input. Please enter valid date in specified format");
            }catch(DateTimeException e){
                System.out.println((e.getMessage()));
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
