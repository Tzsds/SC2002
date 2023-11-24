package Controller.File.camps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import entity.Camp;
import entity.CampDetails;
import repository.CampRepository;

import java.util.ArrayList;

public class WriteCampDetails {

    public static void WriteCamp(){
        String path = "Assignment/database/camp_details.csv";
        String header = "campName,startDate,endDate,closeDate,openTo,location,slots,campComitteeSlots,description,staffInCharge,visibility\n";
        ArrayList <Camp> listOfCamp = CampRepository.getListOfCamps();
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path))){
            writer.write(header);
            for (Camp c : listOfCamp){
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
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
