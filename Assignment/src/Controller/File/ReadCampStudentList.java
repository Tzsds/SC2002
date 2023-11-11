package Controller.File;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Entity.Camp;
import Entity.CampDetails;
import Entity.Enquiry;
import Entity.Student;
import Entity.User;
import Repository.CampRepository;
import Repository.UserRepository;

public class ReadCampStudentList {
    public static void readUserWithoutReset(){
        String campDetailsCSV = "Assignment/database/camp_student_list.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(campDetailsCSV))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null){
                String [] words = line.split(",");
                if (words.length >= 3){
                    String campName = words[0].trim();
                    String userID = words[1].trim();
                    String role = words[2].trim();

                    Camp camp = CampRepository.getCampByCampName(campName);
                    Student student = (Student)UserRepository.getUserByUserID(userID);

                    if (role.equals("Committee")) {
                        camp.addCampCommittee(student);
                    }
                    else {
                        camp.addParticipants(student);
                    }

                    // create camp committee, update role to camp committee
                    // update student role, add participants, add comm members
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }   
}