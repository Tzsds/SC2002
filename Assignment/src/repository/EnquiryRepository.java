package repository;

import java.util.ArrayList;
import java.util.List;

import controller.file.enquiry.ReadEnquiry;
import entity.Camp;
import entity.Enquiry;
/**
 * Represents a repository for storing and managing enquiries
 * This class provides method to retrive, add and query enquiries stored in the repository
 * 
 * @author SCSZ Group 4
 * @version 1.0 
 * @since 25/11/2023
 */
public class EnquiryRepository {
    /** The list of enquiries stored in the repository */
    private static ArrayList<Enquiry> listOfEnquiries = new ArrayList<Enquiry>();

    /**
     * Gets the list of enquiries stored in the repository
     * Enquiries are read from a CSV file and returned as a list
     * 
     * @return The list of enquiries
     */
    public static ArrayList<Enquiry> getListOfEnquiries() {
        // Read enquiries from CSV
        listOfEnquiries = ReadEnquiry.readEnquiriesFromCSV();
        return listOfEnquiries;
    }

    /**
     * Adds a new enquiry to the repository
     * 
     * @param enquiry The enquiry to be added
     */
    public static void addEnquiryToRepo(Enquiry enquiry) {
        listOfEnquiries.add(enquiry);
    }

    /** 
     * Gets a list of enquiries sent by a specific sender
     * 
     * @param senderID The ID of the sender
     * @return The list of enquiries sent by the specified sender
     */
    public static List<Enquiry> getEnquiriesBySender(String senderID) {
        // Read enquiries from CSV
        listOfEnquiries = ReadEnquiry.readEnquiriesFromCSV();
        // Check if the list is empty
        if (listOfEnquiries.isEmpty()) {
            //System.out.println("No Enquiries!");
            return new ArrayList<>(); // Return an empty list
        }
        List<Enquiry> enquiriesBySender = new ArrayList<>();
        for (Enquiry enquiry : listOfEnquiries) {
            if (enquiry.getSender().equals(senderID)) {
                enquiriesBySender.add(enquiry);
            }
        }
        return enquiriesBySender;
    }

    /**
     * Gets the pending enquiry for a specific camp, if any
     * 
     * @param campName The name of the camp
     * @return The pending enquiry for the specified camp, or null if none
     */
    public static Enquiry getPendingEnquiry(String campName) {
        ArrayList<Enquiry> enquiries = ReadEnquiry.readEnquiriesFromCSV();

        for (Enquiry enquiry : enquiries) {
            if (enquiry.getCampName().equalsIgnoreCase(campName) &&
                    enquiry.getStatus() == Enquiry.EnquiryStatus.PENDING) {
                return enquiry;
            }
        }

        return null;
    }

    /**
     * Check if there are pending enquiries for a specific camp
     * 
     * @param campName The name of the camp
     * @return True if there are pending enquiries for the specified camp, false otherwise
     */
    public static boolean hasPendingEnquiries(String campName) {
        ArrayList<Enquiry> enquiries = ReadEnquiry.readEnquiriesFromCSV();

        for (Enquiry enquiry : enquiries) {
            if (enquiry.getCampName().equalsIgnoreCase(campName) &&
                    enquiry.getStatus() == Enquiry.EnquiryStatus.PENDING) {
                return true;
            }
        }

        return false;
    }

    /**
     * Gets a list of enquiries related to a specific camp
     * 
     * @param camp The camp for which enquiries are retrieved
     * @return The list of enquiries related to the specified camp
     */
    public static ArrayList<Enquiry> getEnquiriesByCamp(Camp camp) {
        String campName = camp.getCampDetails().getCampName();
        ArrayList<Enquiry> allEnquiries = getListOfEnquiries();
        ArrayList<Enquiry> enquiriesForSpecificCamp = new ArrayList<>();
        for (Enquiry enquiry : allEnquiries) {
            if (enquiry.getCampName().equals(campName)) {
                enquiriesForSpecificCamp.add(enquiry);
            }
        }
        return enquiriesForSpecificCamp;
    }
}
