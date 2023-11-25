package controller.file.camp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import entity.Camp;
import entity.CampDetails;
import entity.Staff;
import repository.CampRepository;
import repository.userrepository.StaffRepository;

/**
 * Utility class for reading and parsing camp details from a csv file
 * Provides methods to read camp details
 * 
 * @author SCSZ Group 4
 * @version 1.0
 * @since 25/11/23
 */
public class ReadCampDetails {
    
    /** The file path to the camp details CSV file */
    private static String path = "Assignment/database/camp_details.csv";

    /**
     * Parse a date string into a LocalDate object
     * 
     * @param dateString - The date string in "yyyy-MM-dd" format
     * @return - The parsed LocalDate object or null if parsing fails
     */
    public static LocalDate formatDate(String dateString) {
        // Define the formatter based on the pattern of the input string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate localDate = LocalDate.parse(dateString, formatter);
            return localDate;
        } catch (Exception e) {
            System.out.println("Error parsing the string: " + e.getMessage());
        }
        return null;
    }

    /**
     * Converts a string representing an integer to an int
     *
     * @param strNumber - The string representing an integer
     * @return - The converted integer value
     */
    public static int strToInt(String strNumber) {
        int intValue = Integer.parseInt(strNumber);
        return intValue;
    }

    /**
     * Converts a string representing a boolean to a boolean value
     *
     * @param bool - The string representing a boolean ("true" or "false")
     * @return - The converted boolean value
     */
    public static boolean strToBool(String bool) {
        if (bool.equals("true"))
            return true;
        return false;
    }

    /**
     * Reads camp details from the CSV file without resetting its content
     * Updates the CampRepository and StaffRepository with the retrieved
     * information
     */
    public static void readCampWithoutReset() {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            br.readLine(); // SKIP HEADER;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(",");
                if (words.length >= 11) {
                    String campName = words[0].trim();
                    String startDate = words[1].trim();
                    String endDate = words[2].trim();
                    String closeDate = words[3].trim();
                    String openTo = words[4].trim();
                    String location = words[5].trim();
                    String slots = words[6].trim();
                    String campComitteeSlots = words[7].trim();
                    String description = words[8].trim();
                    String staffInCharge = words[9].trim();
                    String visibility = words[10].trim();

                    // update camp into camp repository
                    CampDetails newCampDetails = new CampDetails(campName, formatDate(startDate), formatDate(endDate),
                            formatDate(closeDate), openTo, location, strToInt(slots), strToInt(campComitteeSlots),
                            description, staffInCharge, strToBool(visibility));
                    Camp camp = new Camp(newCampDetails);
                    CampRepository.addCampToRepo(camp);

                    // update camp into staff created camp list
                    Staff staff = StaffRepository.getStaffByID(staffInCharge);
                    staff.addCampToList(camp);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Resets the content of the camp details CSV file by overwriting it with a
     * header line
     */
    public static void readCampWithReset() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            String header = "campName,startDate,endDate,closeDate,openTo,location,slots,campComitteeSlots,description,staffInCharge,visibility\n";
            writer.write(header);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}