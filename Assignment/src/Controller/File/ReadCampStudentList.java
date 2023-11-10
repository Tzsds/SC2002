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

                    // can create a getCampByCampName function in CampRepo
                    for (int j = 0; j < CampRepository.getSizeOfCamps(); j++) {
                        Camp tempCamp = CampRepository.get(j);
                        CampDetails tempCampDetails = tempCamp.getCampDetails();
                        String tempCampName = tempCampDetails.getCampName();
                        if (campName.equals(tempCampName)) {
                            break;
                        }
                    }

                    // can create a getuserByUserID function in userRepo
                    for (int i = 0; i < UserRepository.getSizeOfUsers(); i++) {
                        User temp = UserRepository.get(i);
                        String tempID = temp.getUserID();
                        if (userID.equals(tempID)) {
                            break;
                        }
                    }

                    // update student role, add participants, add comm members
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }   
}