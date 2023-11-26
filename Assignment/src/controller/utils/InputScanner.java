package controller.utils;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Utility class for handling user input.
 * This class provides methods to prompt the user for various types of input.
 * 
 * @author SCSZ Group 4
 * @version 1.0
 * @since 25/11/23
 */

public class InputScanner {
    /**
     * Scanner object to read input from the console
     */
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the user for an integer input.
     * 
     * @param prompt - The prompt message to display to the user.
     * @return integer - The integer entered by the user.
     */

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

    /**
     * Prompts the user for a date input.
     *
     * @param prompt The prompt message to display to the user.
     * @param filter An integer flag to determine the filtering behavior.
     * @return The LocalDate object entered by the user as a date.
     * @throws IllegalArgumentException If the entered date has already passed (when filter is 0).
     * @throws DateTimeException If the entered date does not exist in the calendar.
     * @throws NumberFormatException If non-numeric values are entered for the date.
     * @throws ArrayIndexOutOfBoundsException If the date is not entered in the specified format.
     * @throws InputMismatchException If an invalid input is provided for the date.
     */

    public static LocalDate promptForDate(String prompt, int filter) {
        while (true){
            try{
            String dateString = InputScanner.promptForString(prompt);
            String[] parts = dateString.split("/");

            if(parts.length != 3)
                throw new ArrayIndexOutOfBoundsException("Invalid input. Please enter valid date in specified format");

            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            LocalDate date = LocalDate.of(year, month, day);
            LocalDate currentDate = LocalDate.now();
            if (date.isBefore(currentDate) && filter == 0){
                throw new IllegalArgumentException ("The date entered has already passed. Please enter again");
            }
            return date;
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Invalid input. Please enter valid date in specified format");
            }catch(DateTimeException e){
                System.out.println("Date does not exist in calendar");
            }
            catch(NumberFormatException e){
                System.out.println("Please key in the dates numerically");
            }
            catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }catch (InputMismatchException e){
                System.out.println("Invalid input. Please enter valid date in specified format");
            }
        }
    }

    /**
     * Prompts the user for a start date input.
     * 
     * @param prompt The prompt message to display to the user.
     * @return The LocalDate object entered by the user as a start date.
     * @throws IllegalArgumentException If the entered date has already passed or is less than 2 days after the current date.
     * @throws DateTimeException If the entered date does not exist in the calendar.
     * @throws NumberFormatException If non-numeric values are entered for the date.
     * @throws ArrayIndexOutOfBoundsException If the date is not entered in the specified format.
     * @throws InputMismatchException If an invalid input is provided for the date.
     */

    public static LocalDate promptForStartDate(String prompt) {
        while (true){
            try{
            String dateString = InputScanner.promptForString(prompt);
            String[] parts = dateString.split("/");

            if(parts.length != 3)
                throw new ArrayIndexOutOfBoundsException("Invalid input. Please enter valid date in specified format");

            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            LocalDate date = LocalDate.of(year, month, day);
            LocalDate currentDate = LocalDate.now();
            if (date.isBefore(currentDate)){
                throw new IllegalArgumentException ("The date has already passed. Please enter again");
            }
            if (date.isBefore(currentDate.plusDays(2))){
                throw new IllegalArgumentException ("Start date must be at least 2 days after current date. Please enter again");
            }
            return date;
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Invalid input. Please enter valid date in specified format");
            }catch(DateTimeException e){
                System.out.println("Date does not exist in calendar");
            }
            catch(NumberFormatException e){
                System.out.println("Please key in the dates numerically");
            }
            catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }catch (InputMismatchException e){
                System.out.println("Invalid input. Please enter valid date in specified format");
            }
        }
    }
    
    /**
     * Prompts the user for an end date input relative to a start date.
     *
     * @param prompt The prompt message to display to the user.
     * @param startdate The start date used as a reference for the end date.
     * @return The LocalDate object entered by the user as the end date.
     * @throws IllegalArgumentException If the entered date has already passed, is before the start date, or matches the start date.
     * @throws DateTimeException If the entered date does not exist in the calendar.
     * @throws NumberFormatException If non-numeric values are entered for the date.
     * @throws ArrayIndexOutOfBoundsException If the date is not entered in the specified format.
     * @throws InputMismatchException If an invalid input is provided for the date.
     */
   
    public static LocalDate promptForEndDate(String prompt, LocalDate startdate) {
        while (true){
            try{
            String dateString = InputScanner.promptForString(prompt);
            String[] parts = dateString.split("/");

            if(parts.length != 3)
                throw new ArrayIndexOutOfBoundsException("Invalid input. Please enter valid date in specified format");

            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            LocalDate date = LocalDate.of(year, month, day);
            LocalDate currentDate = LocalDate.now();
            LocalDate startDate = startdate;
            if (date.isBefore(currentDate)){
                throw new IllegalArgumentException ("The date entered has already passed. Please enter again");
            }
            if (date.isBefore(startDate)){
                throw new IllegalArgumentException ("The close date cannot be before start date. Please enter again");
            }
            return date;
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Invalid input. Please enter valid date in specified format");
            }catch(DateTimeException e){
                System.out.println("Date does not exist in calendar");
            }
            catch(NumberFormatException e){
                System.out.println("Please key in the dates numerically");
            }
            catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }catch (InputMismatchException e){
                System.out.println("Invalid input. Please enter valid date in specified format");
            }
        }
    }

    /**
     * Prompts the user for a close date input relative to a start date.
     *
     * @param prompt The prompt message to display to the user.
     * @param startdate The start date used as a reference for the close date.
     * @return The LocalDate object entered by the user as the close date.
     * @throws IllegalArgumentException If the entered date has already passed, is after the start date, or matches the start date.
     * @throws DateTimeException If the entered date does not exist in the calendar.
     * @throws NumberFormatException If non-numeric values are entered for the date.
     * @throws ArrayIndexOutOfBoundsException If the date is not entered in the specified format.
     * @throws InputMismatchException If an invalid input is provided for the date.
     */

    public static LocalDate promptForCloseDate(String prompt, LocalDate startdate) {
        while (true){
            try{
            String dateString = InputScanner.promptForString(prompt);
            String[] parts = dateString.split("/");

            if(parts.length != 3)
                throw new ArrayIndexOutOfBoundsException("Invalid input. Please enter valid date in specified format");

            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            LocalDate date = LocalDate.of(year, month, day);
            LocalDate currentDate = LocalDate.now();
            LocalDate startDate = startdate;
            if (date.isBefore(currentDate)){
                throw new IllegalArgumentException ("The date entered has already passed. Please enter again");
            }
            if (date.isAfter(startDate)){
                throw new IllegalArgumentException ("The registration date cannot be after start date. Please enter again");
            }
            if (date.isEqual(startDate)){
                throw new IllegalArgumentException("The registration date cannot be same as start date. Please enter again");
            }
            return date;
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Invalid input. Please enter valid date in specified format");
            }catch(DateTimeException e){
                System.out.println("Date does not exist in calendar");
            }
            catch(NumberFormatException e){
                System.out.println("Please key in the dates numerically");
            }
            catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }catch (InputMismatchException e){
                System.out.println("Invalid input. Please enter valid date in specified format");
            }
        }
    }

    /**
     * Prompts the user for a string input.
     * 
     * @param prompt - The prompt message to display to the user.
     * @return String - The string entered by the user.
     */

    public static String promptForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Clears the input buffer.
     */

    public static void clear(){
        scanner.nextLine();
    }   
}
