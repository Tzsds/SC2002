package Controller.Camp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Entity.Camp;
import Entity.CampDetails;
import Entity.Staff;
import Entity.Suggestion;
import Repository.UserRepository.StaffRepository;

public class CampManager {
    public static void addSuggestion(Camp tempCamp, Suggestion tempSuggestion){
        ArrayList<Suggestion> temp = tempCamp.getListOfSuggestions();
        temp.add(tempSuggestion);
    }
    public static void removeSuggestion(Camp tempCamp, Suggestion tempSuggestion){
        ArrayList<Suggestion> temp = tempCamp.getListOfSuggestions();
        temp.remove(tempSuggestion);
    }

    public static void printCampsForStudent(ArrayList<Camp> campList) {
        if (campList.size() != 0) {
            for (Camp camp : campList) {
                CampDetails campDetails = camp.getCampDetails();
                System.out.println("Camp: " + campDetails.getCampName());
                System.out.println("Camp Description: " + campDetails.getDescription());
                System.out.println("Start Date: " + campDetails.getStartDate());
                System.out.println("End Date: " + campDetails.getEndDate());
                System.out.println("Registration Close Date: " + campDetails.getCloseDate());
                System.out.println("Open To: " + campDetails.getUserGroup());
                System.out.println("Location: " + campDetails.getLocation());
                System.out.println("Total Slots: " + campDetails.getTotalSlots());
                System.out.println("Total Camp Committee Slots: " + campDetails.getCampCommitteeSlots());
                System.out.println("=====================================");
            }
        }
    }

    // public static void printCampsForStaff(ArrayList<Camp> campList) {
    //     if (campList.size() != 0) {
    //         for (Camp camp : campList) {
    //             CampDetails campDetails = camp.getCampDetails();
    //             System.out.println("Camp: " + campDetails.getCampName());
    //             System.out.println("Camp Description: " + campDetails.getDescription());
    //             System.out.println("Start Date: " + campDetails.getStartDate());
    //             System.out.println("End Date: " + campDetails.getEndDate());
    //             System.out.println("Registration Close Date: " + campDetails.getCloseDate());
    //             System.out.println("Open To: " + campDetails.getUserGroup());
    //             System.out.println("Location: " + campDetails.getLocation());
    //             System.out.println("Total Slots: " + campDetails.getTotalSlots());
    //             System.out.println("Total Camp Committee Slots: " + campDetails.getCampComitteeSlots());
    //             System.out.println("Visibility: " + campDetails.getVisibility());
    //             System.out.println("=====================================");
    //         }
    //     }
    // }
    
    public static void printCampDetails(CampDetails detail){
        Staff s = StaffRepository.getStaffByID(detail.getStaffInCharge());
        String name = s.getName();
        System.out.println("Staff in charge: " + name);
        System.out.println("Camp: " + detail.getCampName());
        System.out.println("Camp Description: " + detail.getDescription());
        System.out.println("Start Date: " + detail.getStartDate());
        System.out.println("End Date: " + detail.getEndDate());
        System.out.println("Registration Close Date: " + detail.getCloseDate());
        System.out.println("Open To: " + detail.getUserGroup());
        System.out.println("Location: " + detail.getLocation());
        System.out.println("Total Slots: " + detail.getTotalSlots());
        System.out.println("Total Camp Committee Slots: " + detail.getCampCommitteeSlots());
        String visibility = "Closed";
        if (detail.getVisibility()){
            visibility = "Open";
        }
        System.out.println("Visibility: " + visibility);
    }

    public static void printCampDetailsForStudents(CampDetails detail){
        System.out.println("Camp: " + detail.getCampName());
        System.out.println("Camp Description: " + detail.getDescription());
        System.out.println("Start Date: " + detail.getStartDate());
        System.out.println("End Date: " + detail.getEndDate());
        System.out.println("Registration Close Date: " + detail.getCloseDate());
        System.out.println("Location: " + detail.getLocation());
        System.out.println("Total Slots: " + detail.getTotalSlots());
        System.out.println("Total Camp Committee Slots: " + detail.getCampCommitteeSlots());
    }
}
