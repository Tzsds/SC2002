package Controller.File;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Entity.Enquiry;

public class ReadEnquiry {
    public static ArrayList<Enquiry> readEnquiriesFromCSV() {
        String enquiryCSV = "Assignment/database/enquiries.csv";
        ArrayList<Enquiry> listOfEnquiries = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(enquiryCSV))) {
            String line;
            boolean isFirstLine = true; // Flag to check if it's the first line (header)
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip the first line (header)
                }
                String[] data = line.split(",");

                String sender = data[0].trim();
                String content = data[1].trim();
                String camp = data[2].trim();
                String statusString = data[3].trim();
                String replier = data[4].trim();

                Enquiry.Status status = Enquiry.Status.valueOf(statusString.toUpperCase());
                Enquiry enquiry = new Enquiry(sender, content, camp);
                enquiry.setStatus(status);
                enquiry.setReplier(replier);

                listOfEnquiries.add(enquiry);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }

        return listOfEnquiries;
    }
}
