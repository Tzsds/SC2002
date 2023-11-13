package Controller.Users;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Controller.Account.LoginManager;
import Controller.Camp.CampManager;
import Controller.File.FileWriting;
import Controller.Suggestion.StaffSuggestionManager;
import Entity.Camp;
import Entity.CampDetails;
import Entity.Staff;
import Repository.CampRepository;

public class StaffManager {

    public static void viewAllCamps(){
        ArrayList<Camp> listOfCamps = CampRepository.getListOfCamps();
        if (listOfCamps.size() == 0){
            System.out.println("There is no existing camps.");
        }
        else{
            for (Camp c : listOfCamps){
                System.out.println("==========================");
                CampDetails detail = c.getCampDetails();
                CampManager.printCampDetails(detail);
            }
            System.out.println("==========================");
        }
    }

    public static void viewCampsCreated(ArrayList<Camp> campCreated){
        if(campCreated.size()==0)
            System.out.println("You have not created any camps");
        for (Camp c: campCreated){
            System.out.println("==========================");
            CampDetails detail = c.getCampDetails();
            CampManager.printCampDetails(detail);
        }
        System.out.println("==========================");
    }

    public static void editCamp(ArrayList<Camp> campCreated){
        Staff staff = (Staff)LoginManager.getCurrentUser();
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        int choice = 0;
        int option;
        int size = campCreated.size();
        if (size == 0){
            System.out.println("You have not created any camp yet!");
            return;
        }
        
        while (true){
            for (int i = 0; i<size; i++){
            String campName = campCreated.get(i).getCampDetails().getCampName();
            System.out.println(i+1 + ". " + campName);
            }
            try{
                System.out.print("Which camp do you want to edit?: ");
                choice = sc1.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Invalid input! Please try again.");
                sc1.nextLine();
                continue;
            }
            if (choice <= 0 || choice > size){
                System.out.println("Invalid input! Please try again.");
                continue;
            }
            Camp temp = campCreated.get(choice-1);
            while(true){
                System.out.println("What do you want to edit?");
                System.out.println("(1) Camp Name\n"
                            + "(2) Location\n"
                            + "(3) User Group\n"
                            + "(4) Total Slots\n"
                            + "(5) Camp Comittee Slots\n"
                            + "(6) Description\n"
                            + "(7) Visibility\n"
                            + "(8) Return to Main Menu");
                
                option = sc2.nextInt();
                Scanner input = new Scanner(System.in);
                switch (option) {
                    case 1:
                        System.out.println("Enter Camp New Name: ");
                        String newName = input.nextLine();
                        temp.editCampName(newName);
                        System.out.println("Camp successfully edited");
                        continue;
                    case 2:
                        System.out.println("Enter New Camp Location: ");
                        String newLocation = input.nextLine();
                        temp.editLocation(newLocation);
                        System.out.println("Camp successfully edited");
                        continue;
                    case 3: 
                        System.out.println("Enter New User Group: (Enter \"1\" for Everyone or \"0\" for " + staff.getFaculty() + "): ");
                        int a = input.nextInt();
                        String newUserGroup;
                        if(a == 1)
                            newUserGroup = "Everyone";
                        else
                            newUserGroup = staff.getFaculty();
                        temp.editUserGroup(newUserGroup);
                        continue;
                    case 4:
                        System.out.println("Enter New Total Slots: ");
                        int newTotalSlots = input.nextInt();
                        temp.editTotalSlots(newTotalSlots);
                        System.out.println("Camp successfully edited");
                        continue;
                    case 5:
                        System.out.println("Enter New Camp Comittee Slots: ");
                        int newComitteeSlots = input.nextInt();
                        temp.editCampComitteeSlots(newComitteeSlots);
                        System.out.println("Camp successfully edited");
                        continue;
                    case 6:
                        System.out.println("Enter New Camp Description: ");
                        String newDescription = input.nextLine();
                        temp.editDecsription(newDescription);
                        System.out.println("Camp successfully edited");
                        continue;
                    case 7:
                        System.out.println("Visibility of camp (Enter \"1\" for On | \"0\" for Off):");
                        int visible = input.nextInt();
                        boolean newVisibility = false;
                        if (visible == 1)
                            newVisibility = true;
                        temp.editVisibility(newVisibility);
                        System.out.println("Camp successfully edited");
                        continue;
                    case 8:
                        System.out.println("Navigating back to Main Menu");
                        break;
                    default:
                        System.out.println("Invalid option. Please enter a valid option.");
                        continue;
                }
                FileWriting.FileWriteCamp(); //update to csv
                return;
            }
        }
    }

    public static void viewSuggestions(){
        Staff currentStaff = (Staff)LoginManager.getCurrentUser();
        StaffSuggestionManager.printSuggestions(currentStaff.getListOfCampsCreated());
    }

}
