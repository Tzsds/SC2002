package controllers;

import controllers.utils.InputScanner;

public class Reset {
    public static int PromptForReset(){
        int choice;
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
