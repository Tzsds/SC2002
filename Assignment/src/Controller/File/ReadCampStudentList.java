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
    public static void ReadCampStudentListWithoutReset(){
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

                    System.out.println(campName);
                    System.out.println(userID);
                    System.out.println(role);

                    Camp camp = CampRepository.getCampByCampName(campName);
                    Student student;

                    if (role.equals("Committee")) {
                        System.out.println("test");
                        CampCommitteeRepository
                        student = CampCommitteeRepository.getUserByID(userID);
                        System.out.println(student.getName());
                        System.out.println(camp.getCampDetails().getCampName());
                        System.out.println("test1");
                        camp.addCampCommittee(student);
                        System.out.println("test2");
                        CampCommittee temp = (CampCommittee)student;
                        System.out.println("test3");
                        temp.setCommitteeOf(camp);
                    }
                    else {
                        student = StudentRepository.getStudentByID(userID);
                        camp.addParticipants(student);
                    }
                    student.addRegisteredCamp(camp);
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }   
}