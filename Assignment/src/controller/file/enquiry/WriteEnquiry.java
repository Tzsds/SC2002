package controller.file.enquiry;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import entity.Enquiry;

/**
 * Utility class for reading and writing enquires to a CSV file
 * Provides methods for writing, deleting, updating, and replying to enquiries
 * in the CSV file
 * 
 * @author SCSZ Group 4
 * @version 1.0
 * @since 25/11/23
 */

public class WriteEnquiry {

    /**
     * Writes an enquiry to the CSV file.
     *
     * @param enquiry - The Enquiry object to be written.
     */
    public static void FileWriteEnquiry(Enquiry enquiry) {
        String enquiryCSV = "Assignment/database/enquiries.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(enquiryCSV, true))) {

            // If the file is empty, write the header
            if (Files.size(Paths.get(enquiryCSV)) == 0) {
                writer.write("sender,content,camp,status,repliedContent,replier\n");
            }

            // Write enquiry to the CSV file
            String data = enquiry.getSender() + "," +
                    enquiry.getContent() + "," +
                    enquiry.getCampName() + "," +
                    enquiry.getStatus() + "," +
                    enquiry.getRepliedContent() + "," +
                    enquiry.getReplier() + "\n";
            writer.write(data);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes an enquiry from the CSV file
     * 
     * @param selectedEnquiry - The Enquiry object to be deleted
     */
    public static void deleteEnquiryFromCSV(Enquiry selectedEnquiry) {
        String enquiryCSV = "Assignment/database/enquiries.csv";
        List<String[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(enquiryCSV))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                if (!areEnquiriesEqual(selectedEnquiry, columns)) {
                    rows.add(columns);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(enquiryCSV))) {
            for (String[] row : rows) {
                StringBuilder line = new StringBuilder();
                for (int i = 0; i < row.length; i++) {
                    line.append(row[i]);
                    if (i < row.length - 1) {
                        line.append(",");
                    }
                }
                bw.write(line.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an enquiry in the CSV file
     * 
     * @param selectedEnquiry - The enquiry object to be updated
     * @param updatedEnquiry  - The updated Enquiry object
     * @param oldContent      - The old content of the enquiry
     */
    public static void updateEnquiryInCSV(Enquiry selectedEnquiry, Enquiry updatedEnquiry, String oldContent) {
        String enquiryCSV = "Assignment/database/enquiries.csv";
        List<String[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(enquiryCSV))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                if (areEnquiriesEqualVerbose(selectedEnquiry, columns)
                        && columns[1].equals(selectedEnquiry.getContent())) {
                    columns[1] = updatedEnquiry.getContent();
                }
                rows.add(columns);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(enquiryCSV))) {
            for (String[] row : rows) {
                StringBuilder line = new StringBuilder();
                for (int i = 0; i < row.length; i++) {
                    line.append(row[i]);
                    if (i < row.length - 1) {
                        line.append(",");
                    }
                }

                bw.write(line.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if an Enquiry object is equal to the columns in a CSV row.
     *
     * @param enquiry - The Enquiry object to compare.
     * @param columns - The CSV row columns.
     * @return true if the Enquiry object is equal to the CSV row, false otherwise.
     */

    public static boolean areEnquiriesEqual(Enquiry enquiry, String[] columns) {
        return enquiry.getSender().equals(columns[0]) &&
                enquiry.getContent().equals(columns[1]) &&
                enquiry.getCampName().equals(columns[2]);
    }

    /**
     * Checks if an Enquiry object is equal to the columns in a CSV row in a verbose
     * manner.
     *
     * @param selectedEnquiry - The Enquiry object to compare.
     * @param columns         - The CSV row columns.
     * @return true if the Enquiry object is equal to the CSV row, false otherwise.
     */
    public static boolean areEnquiriesEqualVerbose(Enquiry selectedEnquiry, String[] columns) {
        String sender = columns[0];
        String content = columns[1];
        String campName = columns[2];

        return selectedEnquiry.getSender().equals(sender) &&
                selectedEnquiry.getCampName().equals(campName) &&
                selectedEnquiry.getContent().equals(content);
    }

    /**
     * Replies to an enquiry in the CSV file.
     *
     * @param selectedEnquiry - The Enquiry object to be replied to.
     */
    public static void replyEnquiryInCSV(Enquiry selectedEnquiry) {
        String enquiryCSV = "Assignment/database/enquiries.csv";
        List<String[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(enquiryCSV))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                if (areEnquiriesEqualVerbose(selectedEnquiry, columns)
                        && columns[1].equals(selectedEnquiry.getContent())
                        && Enquiry.Status.PENDING.toString().equals(columns[3])) {
                    columns[3] = selectedEnquiry.getStatus().toString();
                    columns[4] = selectedEnquiry.getRepliedContent();
                    columns[5] = selectedEnquiry.getReplier();
                }
                rows.add(columns);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(enquiryCSV))) {
            for (String[] row : rows) {
                StringBuilder line = new StringBuilder();
                for (int i = 0; i < row.length; i++) {
                    line.append(row[i]);
                    if (i < row.length - 1) {
                        line.append(",");
                    }
                }

                bw.write(line.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
