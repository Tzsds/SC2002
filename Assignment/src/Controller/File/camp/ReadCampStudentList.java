package Controller.File.camp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import entity.Camp;
import entity.CampCommittee;
import entity.Student;
import repository.CampRepository;
import repository.userrepository.CampCommitteeRepository;
import repository.userrepository.StudentRepository;

public class ReadCampStudentList {
    private static String campStudentListCSV = "Assignment/database/camp_student_list.csv";

    public static void readCampStudentListWithoutReset(){
        try (BufferedReader br = new BufferedReader(new FileReader(campStudentListCSV))) {
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
                        //System.out.println(student.getUserID() + " add to Camp Comm");
                    }
                    else if (role.equals("Withdrawn")) {
                        camp.addWithdrawnStudent(student);
                        //System.out.println(student.getUserID() + " add to Withdrawn");
                    }
                    else {
                        camp.addParticipants(student);
                        //System.out.println(student.getUserID() + " add to Participants");
                    }
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void readCampStudentListWithReset(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(campStudentListCSV))) {
            String header = "campName,userID,role\n";
            writer.write(header);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}