package Controller.File;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Entity.Camp;
import Entity.CampCommittee;
import Entity.Student;
import Repository.CampRepository;
import Repository.UserRepository.CampCommitteeRepository;
import Repository.UserRepository.StudentRepository;

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
                    Student student;

                    if (role.equals("Committee")) {
                        student = CampCommitteeRepository.getUserByID(userID);
                        camp.addCampCommittee(student);
                    }
                    else {
                        student = StudentRepository.getStudentByID(userID);
                        camp.addParticipants(student);
                    }

                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }   
}