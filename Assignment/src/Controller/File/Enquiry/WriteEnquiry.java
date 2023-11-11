package Controller.File.Enquiry;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import Entity.Enquiry;

public class WriteEnquiry {
    public static void FileWriteEnquiry(Enquiry enquiry) {
        String enquiryCSV = "Assignment/database/enquiries.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(enquiryCSV, true))) {

            // If the file is empty, write the header
            if (Files.size(Paths.get(enquiryCSV)) == 0) {
                writer.write("sender,content,camp,status,replier\n");
            }

            // Write enquiry to the CSV file
            String data = enquiry.getSender() + "," +
                    enquiry.getContent() + "," +
                    enquiry.getCampName() + "," +
                    enquiry.getStatus() + "," +
                    enquiry.getReplier() + "\n";
            writer.write(data);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
