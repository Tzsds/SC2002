package Repository;

import java.util.ArrayList;
import java.util.List;

import Controller.File.Enquiry.ReadEnquiry;
import Entity.Enquiry;

public class EnquiryRepository {
    private static ArrayList<Enquiry> listOfEnquiries = new ArrayList<Enquiry>();

    public static ArrayList<Enquiry> getListOfEnquiries() {
        // Read enquiries from CSV
        listOfEnquiries = ReadEnquiry.readEnquiriesFromCSV();
        // Check if the list is empty
        if (listOfEnquiries.isEmpty()) {
            System.out.println("No Enquiries!");
        }
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
            System.out.println("No Enquiries!");
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
}
