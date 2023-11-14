package Controller.Users;

import java.util.ArrayList;
import java.util.InputMismatchException;

import Controller.Account.LoginManager;
import Controller.Camp.CampManager;
import Controller.Enquiry.StaffEnquiryManager;
import Controller.File.FileWriting;
import Controller.Suggestion.StaffSuggestionManager;
import Entity.Camp;
import Entity.CampDetails;

import Entity.Staff;
import Repository.CampRepository;
import UI.InputScanner;


public class StaffManager {

    public static void viewAllCamps() {
        ArrayList<Camp> listOfCamps = CampRepository.getListOfCamps();
        if (listOfCamps.size() == 0) {
            System.out.println("There is no existing camps.");
        } else {
            for (Camp c : listOfCamps) {
                System.out.println("==========================");
                CampDetails detail = c.getCampDetails();
                CampManager.printCampDetails(detail);
            }
            System.out.println("==========================");
        }
    }

    public static void viewCampsCreated(ArrayList<Camp> campCreated) {
        if (campCreated.size() == 0)
            System.out.println("You have not created any camps");
        for (Camp c : campCreated) {
            System.out.println("==========================");
            CampDetails detail = c.getCampDetails();
            CampManager.printCampDetails(detail);
        }
        System.out.println("==========================");
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

        while (true) {
            for (int i = 0; i < size; i++) {
                String campName = campCreated.get(i).getCampDetails().getCampName();
                System.out.println(i + 1 + ". " + campName);
            }
            try {
                choice = InputScanner.promptForInt("Which camp do you want to edit?: ");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please try again.");
                InputScanner.clear();
                continue;
            }
            if (choice <= 0 || choice > size) {
                System.out.println("Invalid input! Please try again.");
                continue;
            }
            Camp temp = campCreated.get(choice - 1);
            while (true) {
                System.out.println("What do you want to edit?");
                option = InputScanner.promptForInt("1. Camp Name\n"
                                                 + "2. Location\n"
                                                 + "3. User Group\n"
                                                 + "4. Total Slots\n"
                                                 + "5. Camp Comittee Slots\n"
                                                 + "6. Description\n"
                                                 + "7. Visibility\n"
                                                 + "8. Return to Main Menu\n"
                                                 + "Choice: ");
                switch (option) {
                    case 1:
                        String newName = InputScanner.promptForString("Enter Camp New Name: ");
                        temp.editCampName(newName);
                        System.out.println("Camp successfully edited");
                        continue;
                    case 2:
                        String newLocation = InputScanner.promptForString("Enter New Camp Location: ");
                        temp.editLocation(newLocation);
                        System.out.println("Camp successfully edited");
                        continue;
                    case 3:
                        int a = InputScanner.promptForInt("Enter New User Group: (Enter \"1\" for Everyone or \"0\" for "
                                                       + staff.getFaculty() + "): ");
                        String newUserGroup;
                        if (a == 1)
                            newUserGroup = "Everyone";
                        else
                            newUserGroup = staff.getFaculty();
                        temp.editUserGroup(newUserGroup);
                        continue;
                    case 4:
                        int newTotalSlots = InputScanner.promptForInt("Enter New Total Slots: ");
                        temp.editTotalSlots(newTotalSlots);
                        System.out.println("Camp successfully edited");
                        continue;
                    case 5:
                        int newCommitteeSlots = InputScanner.promptForInt("Enter New Camp Committee Slots: ");
                        temp.editCampCommitteeSlots(newCommitteeSlots);
                        System.out.println("Camp successfully edited");
                        continue;
                    case 6:
                        String newDescription = InputScanner.promptForString("Enter New Camp Description: ");
                        temp.editDecsription(newDescription);
                        System.out.println("Camp successfully edited");
                        continue;
                    case 7:
                        int visible = InputScanner.promptForInt("Visibility of camp (Enter \"1\" for On | \"0\" for Off):");
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
