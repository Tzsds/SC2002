package controllers.File.user;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import entity.CampCommittee;
import entity.Staff;
import entity.Student;
import repository.userrepository.CampCommitteeRepository;
import repository.userrepository.StaffRepository;
import repository.userrepository.StudentRepository;


public class WriteUser {

    private static String header = "name,faculty,userID,password\n";
    
    public static void FileWriteStaff(){
        String staffCSV = "Assignment/database/staff.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(staffCSV))) {
            // Header of CSV File
            writer.write(header);

            for (Staff s : StaffRepository.getListOfStaff()){
                String data = s.getName() + "," + s.getFaculty() + "," + s.getUserID() + ","
                        + s.getPassword() + "\n";
                writer.write(data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void FileWriteStudent(){
        String studentCSV = "Assignment/database/student.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(studentCSV))) {
            writer.write(header);

            for (Student s : StudentRepository.getListOfStudents()){
                String data = s.getName() + "," + s.getFaculty() + "," + s.getUserID() + ","
                        + s.getPassword() + "\n";
                writer.write(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void FileWriteCampCommittee(){
        String campCommitteeCSV = "Assignment/database/camp_committee.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(campCommitteeCSV))) {
            writer.write("name,faculty,userID,password,points\n");

            for (CampCommittee s : CampCommitteeRepository.getListOfCampCommittee()){
                String data = s.getName() + "," + s.getFaculty() + "," + s.getUserID() + ","
                        + s.getPassword() + "," + s.getPoints() + "\n";
                writer.write(data);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}