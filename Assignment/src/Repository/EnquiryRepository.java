package Repository;

import java.util.ArrayList;

import Entity.Enquiry;

public class EnquiryRepository {
    private static ArrayList<Enquiry> listOfEnquiries = new ArrayList<Enquiry>();

    public static ArrayList<Enquiry> getListOfEnquiries() {
        return listOfEnquiries;
    }

    public static void addEnquiryToRepo(Enquiry enquiry) {
        listOfEnquiries.add(enquiry);
    }
}
