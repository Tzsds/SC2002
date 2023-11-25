package controller.camp;

import java.util.ArrayList;

import entity.Camp;
import entity.CampDetails;
import entity.Staff;
import entity.Suggestion;
import repository.CampRepository;
import repository.userrepository.StaffRepository;

/**
 * This class provides functionality to manage camps in the NTU CAMS System
 * It includes methods for adding and removing suggestions, printing camp
 * details, checking if camp is created by a specific staff, and printing camp
 * information for both staff and students
 */
public class CampManager {

    /**
     * Adds a suggestion to the list of suggestions for a given camp
     * 
     * @param tempCamp       - The camp to which the suggestion is added
     * @param tempSuggestion - The suggestion to be added
     */
    public static void addSuggestion(Camp tempCamp, Suggestion tempSuggestion) {
        ArrayList<Suggestion> temp = tempCamp.getListOfSuggestions();
        temp.add(tempSuggestion);
    }

    /**
     * Removes a suggestion from the list of suggestions for a given camp
     * 
     * @param tempCamp       - The camp from which the suggestion is removed
     * @param tempSuggestion - The suggestion to be removed
     */
    public static void removeSuggestion(Camp tempCamp, Suggestion tempSuggestion) {
        ArrayList<Suggestion> temp = tempCamp.getListOfSuggestions();
        temp.remove(tempSuggestion);
    }

    /**
     * Prints detailed information about a camp, including its staff in charge
     * 
     * @param detail - The CampDetails object containing information about the camp
     */
    public static void printCampDetails(CampDetails detail) {
        Staff s = StaffRepository.getStaffByID(detail.getStaffInCharge());
        String name = s.getName();
        System.out.println("Camp Name: " + detail.getCampName());
        System.out.println("Camp Description: " + detail.getDescription());
        System.out.println("Start Date: " + detail.getStartDate());
        System.out.println("End Date: " + detail.getEndDate());
        System.out.println("Registration Close Date: " + detail.getCloseDate());
        System.out.println("Open To: " + detail.getUserGroup());
        System.out.println("Location: " + detail.getLocation());
        System.out.println("Total Slots: " + detail.getTotalSlots());
        System.out.println("Total Camp Committee Slots: " + detail.getCampCommitteeSlots());
        String visibility = "Closed";
        if (detail.getVisibility()) {
            visibility = "Open";
        }
        System.out.println("Visibility: " + visibility);
        System.out.println("Staff in charge: " + name);
    }

    /**
     * Prints camp details for students, excluding information about the staff in
     * charge
     * 
     * @param detail - The CampDetails object containing information about the camp
     */
    public static void printCampDetailsForStudents(CampDetails detail) {
        System.out.println("Camp Name: " + detail.getCampName());
        System.out.println("Camp Description: " + detail.getDescription());
        System.out.println("Start Date: " + detail.getStartDate());
        System.out.println("End Date: " + detail.getEndDate());
        System.out.println("Registration Close Date: " + detail.getCloseDate());
        System.out.println("Location: " + detail.getLocation());
        System.out.println("Remaining Slots: " + detail.getTotalSlots());
        System.out.println("Total Camp Committee Slots: " + detail.getCampCommitteeSlots());
    }

    /**
     * Prints camp details for students, showing only the camp name
     * 
     * @param detail - the CampDetails object containing information about the camp.
     */
    public static void printCampRegistrationForStudents(CampDetails detail) {
        System.out.println("Camp Name: " + detail.getCampName());
    }

    /**
     * Checks if specific staff created a given camp
     * @param campName - The name of the camp to check
     * @param staffID - The ID of the staff to compare
     * @return - True if the staff created the camp, false otherwise
     */
    public static boolean isCampCreatedByStaff(String campName, String staffID) {
        Camp camp = CampRepository.getCampByCampName(campName); // .toLowerCase() or toUpperCase()

        if (camp != null) {
            CampDetails campDetails = camp.getCampDetails();
            String staffInChargeID = campDetails.getStaffInCharge();

            // System.out.println("StaffID:" + staffInChargeID);
            // Compare the staff ID of the camp creator with the given staff ID
            return staffInChargeID.equals(staffID);
        }
        return false;

    }

}
