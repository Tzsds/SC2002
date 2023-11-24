package Controller.user;

import java.util.ArrayList;

import Controller.File.FileWriting;
import Controller.account.LoginManager;
import Controller.camp.CampManager;
import Controller.enquiries.StaffEnquiryManager;
import Controller.suggestion.StaffSuggestionManager;
import Controller.utils.Filter;
import Controller.utils.InputScanner;
import Controller.utils.Sort;
import entity.Camp;
import entity.CampDetails;
import entity.Staff;
import repository.CampRepository;


public class StaffManager {

    public static void viewAllCamps() {
        
        ArrayList<Camp> listOfCamps = CampRepository.getListOfCamps();
        listOfCamps = Sort.insertionSortByName(listOfCamps);
        CampRepository.setListOfCamps(listOfCamps); //So that subsequent sort is faster

        //Choosing filter method
        int filter = Filter.promptForFilter();
        switch(filter){
            case 1: //name
                String filterName = InputScanner.promptForString("What name do you want to filter by?: ");
                listOfCamps = Filter.filterByCampName(listOfCamps, filterName);
                break;
            case 2: //location
                String filterLocation = InputScanner.promptForString("What location do you want to filter by?: ");
                listOfCamps = Filter.filterByLocation(listOfCamps, filterLocation);
                break;
            case 3: //date
                break;
        }
        System.out.println();
        System.out.println("=================================");
        if (listOfCamps.size() == 0) {
            System.out.println("No available camps found.");
        } else {
            for (Camp c : listOfCamps) {
                CampDetails detail = c.getCampDetails();
                CampManager.printCampDetails(detail);
                System.out.println("=================================");
            }
        }
    }

    public static void viewCampsCreated(ArrayList<Camp> campCreated) {
        System.out.println("=================================");
        if (campCreated.size() == 0)
            System.out.println("You have not created any camps");
        for (Camp c : campCreated) {
            CampDetails detail = c.getCampDetails();
            CampManager.printCampDetails(detail);
            System.out.println("=================================");
        }
    }

    public static void editCamp(ArrayList<Camp> campCreated) {
        Staff staff = (Staff) LoginManager.getCurrentUser();
        int choice = 0;
        int option;
        int size = campCreated.size();
        if (size == 0) {
            System.out.println("You have not created any camp yet!");
            return;
        }
        boolean error1 = false;
        while (true) {
            if(!error1){
                for (int i = 0; i < size; i++) {
                    String campName = campCreated.get(i).getCampDetails().getCampName();
                    System.out.println(i + 1 + ". " + campName);
                }
            }
            choice = InputScanner.promptForInt("Which camp do you want to edit?: ");
            if (choice <= 0 || choice > size) {
                System.out.println("Invalid input! Please try again");
                error1 = true;
                continue;
            }
            Camp temp = campCreated.get(choice - 1);
            boolean error2 = false;
            while (true) {
                if(!error2){
                    System.out.println("1. Camp Name\n"
                                        + "2. Location\n"
                                        + "3. User Group\n"
                                        + "4. Total Slots\n"
                                        + "5. Camp Comittee Slots\n"
                                        + "6. Description\n"
                                        + "7. Visibility\n"
                                        + "8. Return to Main Menu");
                }
                error2 = false;
                option = InputScanner.promptForInt("What do you want to edit? ");
                switch (option) {
                    case 1:
                        String newName = InputScanner.promptForString("Enter Camp New Name: ");
                        temp.editCampName(newName);
                        System.out.println("Camp successfully edited");
                        break;
                    case 2:
                        String newLocation = InputScanner.promptForString("Enter New Camp Location: ");
                        temp.editLocation(newLocation);
                        System.out.println("Camp successfully edited");
                        break;
                    case 3:
                        String newUserGroup;
                        while(true){
                            int a = InputScanner.promptForInt("Enter New User Group: (Enter \"1\" for Everyone or \"0\" for "
                                                        + staff.getFaculty() + "): ");
                            if(a < 0 || a > 1){
                                System.out.println("Invalid input. Please try again");
                            }
                            else{
                                if (a == 1){
                                    newUserGroup = "Everyone";
                                    break;
                                }
                                else{
                                    newUserGroup = staff.getFaculty();
                                    break;
                                }
                            }
                        }
                        System.out.println("Camp successfully edited");
                        temp.editUserGroup(newUserGroup);
                        break;
                    case 4:
                        int newTotalSlots = InputScanner.promptForInt("Enter New Total Slots: ");
                        temp.editTotalSlots(newTotalSlots);
                        System.out.println("Camp successfully edited");
                        break;
                    case 5:
                        int newCommitteeSlots = InputScanner.promptForInt("Enter New Camp Committee Slots: ");
                        temp.editCampCommitteeSlots(newCommitteeSlots);
                        System.out.println("Camp successfully edited");
                        break;
                    case 6:
                        String newDescription = InputScanner.promptForString("Enter New Camp Description: ");
                        temp.editDecsription(newDescription);
                        System.out.println("Camp successfully edited");
                        break;
                    case 7:
                        boolean newVisibility;  
                        while(true){
                        int visible = InputScanner.promptForInt("Visibility of camp (Enter \"1\" for On | \"0\" for Off):");
                        if(visible < 0 || visible > 1){
                                System.out.println("Invalid input. Please try again");
                            }
                            else{
                                if (visible == 1){
                                    newVisibility = true;
                                    break;
                                }
                                else{
                                    newVisibility = false;
                                    break;
                                }
                            }
                        }
                        System.out.println("Camp successfully edited");
                        temp.editVisibility(newVisibility);
                        break;
                    case 8:
                        System.out.println("Navigating back to Main Menu");
                        break;
                    default:
                        System.out.println("Invalid option. Please enter a valid option.");
                        error2 = true;
                        continue;
                }
                if(!error2){
                    System.out.println();
                }
                FileWriting.FileWriteCampDetails(); // update to csv
                return;
            }
        }
    }

    public static void viewSuggestions() {
        Staff currentStaff = (Staff) LoginManager.getCurrentUser();
        StaffSuggestionManager.printSuggestions(currentStaff.getListOfCampsCreated());
    }

    public static void viewEnquiries(){
        Staff currentStaff = (Staff)LoginManager.getCurrentUser();
        StaffEnquiryManager.viewAllEnquiriesStaff(currentStaff.getUserID());
    }

    public static void replyEnquiry(){
        Staff currentStaff = (Staff)LoginManager.getCurrentUser();
        StaffEnquiryManager.replyEnquiry(currentStaff.getUserID());
    }
}
