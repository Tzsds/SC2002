package Controller.File.Camp;

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

public class ReadCampDetails {
    private static String path = "Assignment/database/camp_details.csv";

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

    public static void readCampWithoutReset(){
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            br.readLine(); // SKIP HEADER;
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
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    public static void readCampWithReset(){
         try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            String header = "campName,startDate,endDate,closeDate,openTo,location,slots,campComitteeSlots,description,staffInCharge,visibility\n";
            writer.write(header);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}