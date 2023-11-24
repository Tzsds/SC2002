package Repository;

import java.util.ArrayList;
import java.util.List;

import Controller.File.Enquiry.ReadEnquiry;
import entity.Camp;
import entity.Enquiry;

public class EnquiryRepository {
    private static ArrayList<Enquiry> listOfEnquiries = new ArrayList<Enquiry>();

    public static ArrayList<Enquiry> getListOfEnquiries() {
        // Read enquiries from CSV
        listOfEnquiries = ReadEnquiry.readEnquiriesFromCSV();
        return listOfEnquiries;
    }

    public static void addEnquiryToRepo(Enquiry enquiry) {
        listOfEnquiries.add(enquiry);
    }

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

    public static Enquiry getPendingEnquiry(String campName) {
        ArrayList<Enquiry> enquiries = ReadEnquiry.readEnquiriesFromCSV();

        for (Enquiry enquiry : enquiries) {
            if (enquiry.getCampName().equalsIgnoreCase(campName) &&
                    enquiry.getStatus() == Enquiry.Status.PENDING) {
                return enquiry;
            }
        }

        return null;
    }

    public static boolean hasPendingEnquiries(String campName) {
        ArrayList<Enquiry> enquiries = ReadEnquiry.readEnquiriesFromCSV();

        for (Enquiry enquiry : enquiries) {
            if (enquiry.getCampName().equalsIgnoreCase(campName) &&
                    enquiry.getStatus() == Enquiry.Status.PENDING) {
                return true;
            }
        }

        return false;
    }

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
