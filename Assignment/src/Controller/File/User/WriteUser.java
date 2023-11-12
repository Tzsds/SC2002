package Controller.File.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import Entity.CampCommittee;
import Entity.Staff;
import Entity.Student;
import Repository.UserRepository.CampCommitteeRepository;
import Repository.UserRepository.StaffRepository;
import Repository.UserRepository.StudentRepository;


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
        String campCommitteeCSV = "Assignment/database/campcommittee.csv";
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