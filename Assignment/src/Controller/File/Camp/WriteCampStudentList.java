package Controller.File.Camp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import entity.Camp;
import entity.CampDetails;
import entity.Student;
import repository.CampRepository;

public class WriteCampStudentList {
    
    public static void WriteStudentList(){
        String path = "Assignment/database/camp_student_list.csv";
        String header = "campName,userID,Role\n";
        ArrayList<Camp> listOfCamp = CampRepository.getListOfCamps();
        ArrayList<Student> listOfStudent;
        ArrayList<Student> listOfCampCommittee;
        ArrayList<Student> listOfWithdrawnStudents;
        String data;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))){
            writer.write(header);
            for (Camp c : listOfCamp){
                CampDetails detail = c.getCampDetails();
                String campName = detail.getCampName();
                listOfStudent = c.getParticipants();
                for (Student s : listOfStudent){
                    data = campName + "," + s.getUserID() + ",Student\n";
                    writer.write(data);
                }
                listOfCampCommittee = c.getCampCommittee();
                for (Student s : listOfCampCommittee){
                    data = campName + "," + s.getUserID() + ",CampCommittee\n";
                    writer.write(data);
                }
                listOfWithdrawnStudents = c.getWithdrawnStudents();
                for (Student s : listOfWithdrawnStudents){
                    data = campName + "," + s.getUserID() + ",Withdrawn\n";
                    writer.write(data);
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
