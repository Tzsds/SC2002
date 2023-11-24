package Controller.camp;

import java.util.ArrayList;

import entity.Camp;
import entity.CampDetails;
import entity.Staff;
import entity.Suggestion;
import repository.CampRepository;
import repository.userrepository.StaffRepository;

public class CampManager {
    public static void addSuggestion(Camp tempCamp, Suggestion tempSuggestion) {
        ArrayList<Suggestion> temp = tempCamp.getListOfSuggestions();
        temp.add(tempSuggestion);
    }

    public static void removeSuggestion(Camp tempCamp, Suggestion tempSuggestion) {
        ArrayList<Suggestion> temp = tempCamp.getListOfSuggestions();
        temp.remove(tempSuggestion);
    }

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

    public static void printCampRegistrationForStudents(CampDetails detail) {
        System.out.println("Camp Name: " + detail.getCampName());
    }

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
