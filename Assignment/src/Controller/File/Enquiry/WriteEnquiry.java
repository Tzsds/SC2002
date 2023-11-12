package Controller.File.Enquiry;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    /*
     * public static void updateEnquiry(Enquiry originalEnquiry, String newContent)
     * {
     * // Read all existing enquiries
     * List<Enquiry> allEnquiries = ReadEnquiry.readEnquiriesFromCSV();
     * // Find and update the specific enquiry
     * for (int i = 0; i < allEnquiries.size(); i++) {
     * Enquiry currentEnquiry = allEnquiries.get(i);
     * 
     * System.out.println("Checking: " + currentEnquiry.getSender() + ", " +
     * currentEnquiry.getContent() + ", " + currentEnquiry.getCampName());
     * 
     * if (isSameEnquiry(currentEnquiry, originalEnquiry)) {
     * System.out.println("Found matching enquiry: " + currentEnquiry.getSender() +
     * ", " +
     * currentEnquiry.getContent() + ", " + currentEnquiry.getCampName());
     * 
     * // Update the content of the found enquiry
     * currentEnquiry.setContent(newContent);
     * System.out.println("Updated Enquiry content: " + newContent);
     * 
     * break;
     * }
     * }
     * // Write the updated enquiries back to the CSV file
     * try (BufferedWriter writer = new BufferedWriter(new
     * FileWriter("Assignment/database/enquiries.csv"))) {
     * writer.write("sender,content,camp,status,replier\n"); // Write header
     * 
     * for (Enquiry enquiry : allEnquiries) {
     * String line = String.format("%s,%s,%s,%s,%s\n",
     * enquiry.getSender(), enquiry.getContent(), enquiry.getCampName(),
     * enquiry.getStatus().toString(), enquiry.getReplier());
     * writer.write(line);
     * }
     * 
     * } catch (IOException e) {
     * e.printStackTrace();
     * // Handle the exception (e.g., log it, display an error message, etc.)
     * }
     * 
     * System.out.println("File updated successfully!");
     * }
     * 
     * // Helper method to check if two enquiries are the same
     * private static boolean isSameEnquiry(Enquiry originalEnquiry, Enquiry
     * updatedEnquiry) {
     * // Check if the relevant fields match
     * return originalEnquiry.getSender().equals(updatedEnquiry.getSender()) &&
     * originalEnquiry.getContent().equals(updatedEnquiry.getContent()) &&
     * originalEnquiry.getCampName().equals(updatedEnquiry.getCampName());
     * }
     */

    /*
     * public static void updateEnquiry(Enquiry updatedEnquiry) {
     * // Read all existing enquiries
     * List<Enquiry> allEnquiries = ReadEnquiry.readEnquiriesFromCSV();
     * 
     * boolean found = false;
     * 
     * // Find and update the specific enquiry in memory
     * for (int i = 0; i < allEnquiries.size(); i++) {
     * Enquiry currentEnquiry = allEnquiries.get(i);
     * if (currentEnquiry.getSender().equals(updatedEnquiry.getSender()) &&
     * currentEnquiry.getContent().equals(updatedEnquiry.getContent()) &&
     * currentEnquiry.getCampName().equals(updatedEnquiry.getCampName())) {
     * 
     * // Update the matching enquiry with the new content
     * allEnquiries.set(i, updatedEnquiry);
     * found = true;
     * break; // No need to continue searching
     * }
     * }
     * 
     * if (!found) {
     * // If the enquiry was not found, add it to the list
     * allEnquiries.add(updatedEnquiry);
     * }
     * 
     * // Create a StringBuilder to construct the content of the temporary file
     * StringBuilder sb = new StringBuilder();
     * 
     * // Write the header
     * sb.append("sender,content,camp,status,replier\n");
     * 
     * // Append each enquiry to the StringBuilder
     * for (Enquiry enquiry : allEnquiries) {
     * sb.append(String.format("%s,%s,%s,%s,%s\n",
     * enquiry.getSender(), enquiry.getContent(), enquiry.getCampName(),
     * enquiry.getStatus().toString(), enquiry.getReplier()));
     * }
     * 
     * // Write the updated content to the temporary file
     * try (BufferedWriter writer = new BufferedWriter(new
     * FileWriter("Assignment/database/enquiries_temp.csv"))) {
     * writer.write(sb.toString());
     * } catch (IOException e) {
     * e.printStackTrace();
     * // Handle the exception (e.g., log it, display an error message, etc.)
     * }
     * 
     * // Replace the original file with the temporary one
     * File originalFile = new File("Assignment/database/enquiries.csv");
     * File tempFile = new File("Assignment/database/enquiries_temp.csv");
     * tempFile.renameTo(originalFile);
     * 
     * System.out.println("Enquiry updated successfully!");
     * }
     */
    public static void editEnquiries(ArrayList<Enquiry> enquiries, ArrayList<Enquiry> enquiriesFromCSV)
            throws IOException {
        // Iterate over the enquiries that you want to edit and update the content of
        // each enquiry in the list of enquiries from the CSV file.
        for (Enquiry enquiry : enquiries) {
            for (int i = 0; i < enquiriesFromCSV.size(); i++) {
                Enquiry enquiryFromCSV = enquiriesFromCSV.get(i);
                if (enquiryFromCSV.equals(enquiry)) {
                    enquiryFromCSV.setContent(enquiry.getContent());
                    break;
                }
            }
        }

        for (Enquiry enquiry : enquiriesFromCSV) {
            FileWriteEnquiry(enquiry);
        }
    }

    public static void deleteEnquiryFromCSV(Enquiry selectedEnquiry) {
        String enquiryCSV = "Assignment/database/enquiries.csv";
        List<String[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(enquiryCSV))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                if (!isSameEnquiry(selectedEnquiry, columns)) {
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

    public static void updateEnquiryInCSV(Enquiry selectedEnquiry, Enquiry updatedEnquiry, String oldContent) {
        String enquiryCSV = "Assignment/database/enquiries.csv";
        List<String[]> rows = new ArrayList<>();
    
        try (BufferedReader br = new BufferedReader(new FileReader(enquiryCSV))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                if (isSameEnquiry2(selectedEnquiry, columns) && columns[1].equals(selectedEnquiry.getContent())) {
                    System.out.println("Found matching enquiry to update: " + Arrays.toString(columns));
                    System.out.println("Old content: " + selectedEnquiry.getContent());
                    columns[1] = updatedEnquiry.getContent();; // Update the content
                    System.out.println("Enquiry updated to: " + Arrays.toString(columns));
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
                System.out.println("Writing line: " + line.toString());
                bw.write(line.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public static boolean isSameEnquiry(Enquiry enquiry, String[] columns) {
        return enquiry.getSender().equals(columns[0]) &&
                enquiry.getContent().equals(columns[1]) &&
                enquiry.getCampName().equals(columns[2]);
    }

    public static boolean isSameEnquiry2(Enquiry selectedEnquiry, String[] columns) {
        String sender = columns[0];
        String content = columns[1];
        String campName = columns[2];
        System.out.println("Comparing: " + selectedEnquiry.getSender() + ", Content:" +
                selectedEnquiry.getContent() + ", Camp Name: " + selectedEnquiry.getCampName() +
                " with: " + sender + ", " + content + ", " + campName);
        return selectedEnquiry.getSender().equals(sender) &&
                selectedEnquiry.getCampName().equals(campName) &&
                selectedEnquiry.getContent().equals(content);
    }

}
