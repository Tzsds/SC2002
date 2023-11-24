package controllers.utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputScanner {
    private static Scanner scanner = new Scanner(System.in);

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

    public static String promptForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static void clear(){
        scanner.nextLine();
    }

    
}
