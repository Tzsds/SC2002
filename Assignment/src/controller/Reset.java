package controller;

import controller.utils.InputScanner;

/**
 * The class responsible for prompting the user for data reset options
 * It provides a method to display reset option menu and receive user input for choosing whether
 * to reset data or use previous data
 * @author SCSZ Group 4
 * @version 1.0 
 * @since 25/11/2023
 */
public class Reset {

    /**
     * Prompts the user to choose between resetting data and using previous data
     * 
     * @return An integer representing the user's choice:
     *         - 1 for resetting data
     *         - 2 for using previous data
     */
    public static int PromptForReset(){
        int choice;
        // Display reset option menu
        System.out.println("1. Reset data");
        System.out.println("2. Use previous data");
        while (true){
            choice = InputScanner.promptForInt("Input your choice here: ");
            if (choice != 1 && choice != 2){
                System.out.println("Please enter the values 1 or 2 only");
                continue;
            }
            return choice;
        }
    }
}
