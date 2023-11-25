package controller.file.camp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import entity.Camp;
import entity.CampDetails;
import repository.CampRepository;

import java.util.ArrayList;

/**
 * Utility class for writing camp details to a csv file
 * Provides a method to write camp details to the file
 * 
 * @author SCSZ Group 4
 * @version 1.0
 * @since 25/11/23
 */
public class WriteCampDetails {

    /**
     * Writes camp details to the CSV file specified in the path
     * The CSV file includes a header line with column names: campName, startDate,
     * endDate, closeDate, openTo, location, slots, campComitteeSlots, description,
     * staffInCharge, visibility
     * The data is obtained from the list of camps in the CampRepository
     */
    public static void WriteCamp() {
        String path = "Assignment/database/camp_details.csv";
        String header = "campName,startDate,endDate,closeDate,openTo,location,slots,campComitteeSlots,description,staffInCharge,visibility\n";
        ArrayList<Camp> listOfCamp = CampRepository.getListOfCamps();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(header);
            for (Camp c : listOfCamp) {
                CampDetails detail = c.getCampDetails();
                String data = detail.getCampName() + "," +
                        detail.getStartDate() + "," +
                        detail.getEndDate() + "," +
                        detail.getCloseDate() + "," +
                        detail.getUserGroup() + "," +
                        detail.getLocation() + "," +
                        detail.getTotalSlots() + "," +
                        detail.getCampCommitteeSlots() + "," +
                        detail.getDescription() + "," +
                        detail.getStaffInCharge() + "," +
                        detail.getVisibility() + "\n";
                writer.write(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
