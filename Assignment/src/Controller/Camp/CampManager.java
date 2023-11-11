package Controller.Camp;

import java.util.ArrayList;

import Entity.Camp;
import Entity.CampDetails;
import Entity.Suggestion;

public class CampManager {
    public static void addSuggestion(Camp tempCamp, Suggestion tempSuggestion){
        ArrayList<Suggestion> temp = tempCamp.getListOfSuggestions();
        temp.add(tempSuggestion);
    }
    public static void removeSuggestion(Camp tempCamp, Suggestion tempSuggestion){
        ArrayList<Suggestion> temp = tempCamp.getListOfSuggestions();
        temp.remove(tempSuggestion);
    }

    public static void printCampsForStaff(ArrayList<Camp> campList) {
        if (campList.size() != 0) {
            for (Camp camp : campList) {
                CampDetails campDetails = camp.getCampDetails();
                System.out.println("Camp: " + campDetails.getCampName());
                System.out.println("Camp Description: " + campDetails.getDescription());
                System.out.println("Start Date: " + campDetails.getStartDate());
                System.out.println("End Date: " + campDetails.getEndDate());
                System.out.println("Registration Close Date: " + campDetails.getCloseDate());
                System.out.println("Open To: " + campDetails.getOpenTo());
                System.out.println("Location: " + campDetails.getLocation());
                System.out.println("Total Slots: " + campDetails.getTotalSlots());
                System.out.println("Total Camp Committee Slots: " + campDetails.getOpenTo());
                System.out.println("Visibility: " + campDetails.getVisibility());
                System.out.println("=====================================");
            }
        }
    }    
}
