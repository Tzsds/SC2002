package Controller.File;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Entity.Camp;
import Entity.CampCommittee;
import Entity.CampDetails;
import Entity.Enquiry;
import Entity.Staff;
import Entity.Student;
import Entity.User;
import Repository.CampRepository;
import Repository.UserRepository;

public class ReadCamp {

    //utilities function
    //havent yet to be shifted elsewhr for SOLID

    public static LocalDate formatDate(String dateString) {
        // Define the formatter based on the pattern of the input string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate localDate = LocalDate.parse(dateString, formatter);
            return localDate;
        }
        catch (Exception e) {
            System.out.println("Error parsing the string: " + e.getMessage());
        }
        return null;
    }

    public static int strToInt(String strNumber) {
        int intValue = Integer.parseInt(strNumber);
        return intValue;
    }

    public static boolean strToBool(String bool) {
        if (bool.equals("true"))
            return true;
        return false;
    }

    public static void readUserWithoutReset(){
        String campDetailsCSV = "Assignment/database/camp_details.csv";
        // campName,startDate,endDate,closeDate,openTo,location,slots,campComitteeSlots,description,staffInCharge,visibility

        //Reading in the CSV that has all the updated passwords
        try (BufferedReader br = new BufferedReader(new FileReader(campDetailsCSV))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null){
                String [] words = line.split(",");
                if (words.length >= 11){
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

                    User user = UserRepository.getUserByUserID(staffInCharge);

                    // if staff is Staff variable
                    // CampDetails newCampDetails = new CampDetails(campName, formatDate(startDate), formatDate(endDate), formatDate(closeDate), openTo, location, strToInt(slots), strToInt(campComitteeSlots), description, user, strToBool(visibility));

                    // if staff is String variable (staffid)
                    CampDetails newCampDetails = new CampDetails(campName, formatDate(startDate), formatDate(endDate), formatDate(closeDate), openTo, location, strToInt(slots), strToInt(campComitteeSlots), description, staffInCharge, strToBool(visibility));
                    CampRepository.addCampToRepo(new Camp(newCampDetails));
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}