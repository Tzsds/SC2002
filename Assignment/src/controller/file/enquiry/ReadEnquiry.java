package controller.file.enquiry;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import entity.Enquiry;

/**
 * Utility class for reading and parsing enquiries from a CSV File
 * Provides methods to read enquiries without resetting or with resetting the
 * file content
 * 
 * @author SCSZ Group 4
 * @version 1.0
 * @since 25/11/23
 */
public class ReadEnquiry {
   
    /** The file path to the enquiries CSV file */
    private static String path = "Assignment/database/enquiries.csv";

    /**
     * Reads enquiries from the CSV file without restting its content
     * 
     * @return - ArrayList of Enquiry objects containing the parsed enquiries
     */
    public static ArrayList<Enquiry> readEnquiriesFromCSV() {
        return readWithoutReset();
    }

    /**
     * Reads enquiries from the CSV file without resetting its content
     * 
     * @return - ArrayList of Enquiry objects containing the parsed enquiries
     */
    public static ArrayList<Enquiry> readWithoutReset() {
        ArrayList<Enquiry> listOfEnquiries = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.readLine(); // skip the header
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(",");
                if (words.length >= 3) {
                    String sender = words[0].trim();
                    String content = words[1].trim();
                    String camp = words[2].trim();
                    Enquiry enquiry = new Enquiry(sender, content, camp);

                    // Additional fields (status, replier) can be added based on your CSV structure
                    if (words.length >= 4) {
                        String statusString = words[3].trim();
                        Enquiry.Status status = Enquiry.Status.valueOf(statusString.toUpperCase());
                        enquiry.setStatus(status);
                    }

                    if (words.length >= 4) {
                        String repliedContent = words[4].trim();
                        enquiry.setRepliedContent(repliedContent);
                    }
                    if (words.length >= 4) {
                        String replier = words[5].trim();
                        enquiry.setReplier(replier);
                    }

                    listOfEnquiries.add(enquiry);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return listOfEnquiries;
    }

    /**
     * Reads enquiries from the CSV file with resetting its content
     * Resets the content of the CSV file by overwriting it with a header line
     * 
     * @return - ArrayList of Enquiry objects (empty as the file is reset)
     */
    public static ArrayList<Enquiry> readWithReset() {
        ArrayList<Enquiry> listOfEnquiries = new ArrayList<>();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            String header = "Sender, Content, Camp, Status, RepliedContent, Replier\n";
            writer.write(header);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listOfEnquiries;
    }
}
