package Controller.File.Camp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Entity.Camp;
import Entity.CampCommittee;
import Entity.Student;
import Repository.CampRepository;
import Repository.UserRepository.CampCommitteeRepository;
import Repository.UserRepository.StudentRepository;

public class ReadCampStudentList {
    private static String campStudentListSV = "Assignment/database/camp_student_list.csv";

    public static void readCampStudentListWithoutReset(){
        try (BufferedReader br = new BufferedReader(new FileReader(campStudentListSV))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null){
                String [] words = line.split(",");
                if (words.length >= 3){
                    String campName = words[0].trim();
                    String userID = words[1].trim();
                    String role = words[2].trim();
                    
                    Camp camp = CampRepository.getCampByCampName(campName);
                    Student student = StudentRepository.getStudentByID(userID);
                    if (student == null){
                        student = CampCommitteeRepository.getCommitteeByID(userID);
                    }
                    student.addRegisteredCamp(camp);

                    if (role.equals("CampCommittee")) {
                        camp.addCampCommittee(student);
                        CampCommittee committee = CampCommitteeRepository.getCommitteeByID(userID);
                        committee.setCommitteeOf(camp);
                    }
                    else {
                        camp.addParticipants(student);
                    }
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void readCampStudentListWithReset(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(campStudentListSV))) {
            String header = "campName,userID,role\n";
            writer.write(header);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}